package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.servidor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoRegistroMedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TipoMedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.TipoMedioAlmacenamientoLogic;
import pe.com.pacasmayo.sgcp.logica.stock.EstadoRegistroMedicionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MedicionLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaStockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.StockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente.StockService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class StockServiceImpl extends RemoteServiceServlet implements StockService, ConstantesAplicacionAction,
		ConstantesLogicaNegocio {

	private static final String MENSAJE_EXITO_REGISTRO = "Se ha realizado exitosamente el registro de mediciones.";
	private Log logger = LogFactory.getLog(this.getClass());
	private static MedicionLogicFacade medicionFacade = new MedicionLogic();

	private static ProcesoLogicFacade procesoFacade = new ProcesoLogic();
	private static TipoMedioAlmacenamientoLogicFacade tipoMedioFacade = new TipoMedioAlmacenamientoLogic();
	private static EstadoRegistroMedicionLogicFacade estadoRegistroMedicionFacade = new EstadoRegistroMedicionLogic();
	private static MedioAlmacenamientoLogicFacade mediosFacade = new MedioAlmacenamientoLogic();

	private static String MENSAJE_RESULTADO = "";

	/**
	 * Metodo que carga la lista de RegistroTablaStockDTO por proceso, mes y
	 * a�o. Tambien se pueden utilizar adicionalmente filtros de codigo de
	 * tipo de medio de almacenamiento y codigo de estado de registro de
	 * medicion. Este metodo es usado en el CU de Consulta de medicion(Detalle
	 * de silos).
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaStockDTO> obtenerListaRegistroStock(Long codigoProceso, Long codigoEstadoRegistroMedicion,
			Long codigoTipoMedio, Integer anno, String mes) throws ServicioGWTGlobalException {

		// Mapa de control de los stock por dia. Todos los stock de cada dia
		// tienen el mismo estado.
		Map<Long, List<StockDTO>> mapaStock = new LinkedHashMap<Long, List<StockDTO>>();
		List<RegistroTablaStockDTO> listaRegistroTablaStock = new ArrayList<RegistroTablaStockDTO>();
		Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mes).ordinal()).shortValue();
		numeroMes++;
		try {
			List<StockDTO> listaStock = medicionFacade.obtenerStockMensualProductosPorProceso(codigoProceso, anno, numeroMes,
					codigoEstadoRegistroMedicion, codigoTipoMedio);

			// Recorro la lista resultado del query para ir agregando al mapa
			// segun las fechas
			for (Iterator<StockDTO> iterator = listaStock.iterator(); iterator.hasNext();) {
				StockDTO stockDTO = iterator.next();

				// Agregamos al mapa la fecha y una lista nueva vacia que
				// contendra los stock de ese dia
				if (!mapaStock.containsKey(stockDTO.getCodigoRegistroMedicion())) {
					List<StockDTO> listaStockDia = new ArrayList<StockDTO>();
					mapaStock.put(stockDTO.getCodigoRegistroMedicion(), listaStockDia);
				}

				List<StockDTO> listaStockDia = mapaStock.get(stockDTO.getCodigoRegistroMedicion());
				listaStockDia.add(stockDTO);
			}

			// Recorro el mapa y en cada lista le agrego el stock
			// correspondiente.
			for (Iterator<Long> iterator = mapaStock.keySet().iterator(); iterator.hasNext();) {
				Long codigoRegistro = iterator.next();
				List<StockDTO> listaStockDTO = mapaStock.get(codigoRegistro);

				// Para cada lista busco los stocks de ese dia y los agrego
				for (Iterator<StockDTO> iterator2 = listaStock.iterator(); iterator2.hasNext();) {
					StockDTO stock = iterator2.next();

					// Si pertenece a la misma fecha lo agrego en la lista
					if (stock.getCodigoRegistroMedicion().longValue() == codigoRegistro.longValue()) {
						listaStockDTO.add(stock);
					}
				}
			}

			// Convertimos el mapa de stocks en una lista
			listaRegistroTablaStock = obtenerListaRegistroTablaStockDTO(mapaStock);

		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			throw new ServicioGWTGlobalException(e);
		}
		return listaRegistroTablaStock;
	}

	/**
	 * Metodo que convierte el mapa: Map<Date,List<StockDTO> en una lista de
	 * RegistroTablaStockDTO, para poder cargarlo en el grid. Cada objeto
	 * RegistroTablaStockDTO, contiene la fecha y la lista de StockDTO.
	 * 
	 * @param mapaStockDTO
	 * @return
	 */
	private List<RegistroTablaStockDTO> obtenerListaRegistroTablaStockDTO(Map<Long, List<StockDTO>> mapaStockDTO) {

		List<RegistroTablaStockDTO> listaRegistroTablaStockDTO = new ArrayList<RegistroTablaStockDTO>();

		for (Iterator<Long> iterator = mapaStockDTO.keySet().iterator(); iterator.hasNext();) {
			Long codigoRegistro = iterator.next();

			RegistroTablaStockDTO registro = new RegistroTablaStockDTO();
			registro.setCodigoRegistro(codigoRegistro);

			List<StockDTO> listaStock = mapaStockDTO.get(codigoRegistro);

			// Esto linea podria interepretarse mal por el numero magico. Pero
			// tengo la certeza de que todos los registros del dia tienen el
			// mismo estado.
			registro.setCodigoRegistro(listaStock.get(0).getCodigoRegistroMedicion());
			registro.setNombreEstado(listaStock.get(0).getNombreEstado());
			registro.setCodigoEstado(listaStock.get(0).getCodigoEstado());
			registro.setFecha(listaStock.get(0).getFecha());

			registro.setColumnas(listaStock);
			listaRegistroTablaStockDTO.add(registro);
		}

		return listaRegistroTablaStockDTO;
	}

	/**
	 * Metodo que carga la lista de RegistroTablaMedicionDiaDTO de detalles del
	 * silo por dia. Tambien se pueden utilizar adicionalmente filtros de codigo
	 * de tipo de medio de almacenamiento y codigo de estado de registro de
	 * medicion. Usado en la consulta de mediciones de productos.
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoRegistroMedicion)
			throws ServicioGWTGlobalException {

		List<RegistroTablaMedicionDiaDTO> listaRegistroDetallesStock = new ArrayList<RegistroTablaMedicionDiaDTO>();
		try {
			listaRegistroDetallesStock = medicionFacade.obtenerListaRegistroDetallesStock(codigoRegistroMedicion);
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
		return listaRegistroDetallesStock;
	}

	/**
	 * Metodo para cargar la lista de RegistroTablaStockDTO por proceso y
	 * fecha.Tambien se pueden utilizar adicionalmente filtros de codigo de tipo
	 * de medio de almacenamiento y codigo de estado de registro de medicion.
	 * Este metodo es usado en el CU de Registro de medicion (Carga algunos
	 * datos de detalle de silos).
	 */
	public List<RegistroTablaMedicionDiaDTO> obtenerListaDetallesSilos(Long codigoProceso, Long codigoTipoMedio, String fecha,
			Long codigoEstado, Long lineaNegocio) throws ServicioGWTGlobalException {

		List<RegistroTablaMedicionDiaDTO> listaRegistroDetallesStock = new ArrayList<RegistroTablaMedicionDiaDTO>();

		try {
			Date fechaRegistro = FechaUtil.convertirAFecha(fecha, ConstantesGWT.PATRON_FECHA_APLICACION, null);
			listaRegistroDetallesStock = medicionFacade.obtenerListaRegistroDetallesStock(codigoProceso, codigoTipoMedio,
					fechaRegistro, codigoEstado, lineaNegocio);
		} catch (LogicaException e) {
			logger.error(e);
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
		return listaRegistroDetallesStock;
	}

	/**
	 * Metodo para aprobar un registro de medicion.
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public String cambiarEstadoRegistroMedicion(Long codigoRegistroMedicion, Long codigoEstado) throws ServicioGWTGlobalException {

		String mensaje = null;

	
		return mensaje;
	}

	/**
	 * Metodo para registrar medicion.
	 */
	public String registrarMedicion(Long codigoProceso, Long codigoEstado, Long codigoTipoMedio,
			List<RegistroTablaMedicionDiaDTO> listaRegistros, String fecha) throws ServicioGWTGlobalException {


		return MENSAJE_RESULTADO;
	}

	/**
	 * Metodo que retorna el usuario actual
	 * 
	 * @return
	 */
	protected UsuarioBean getUsuarioSession() {
		HttpSession sesion = getSession();
		return (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
	}

	/**
	 * Metodo que retorna la session
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	public Double obtenerCantidadAlmacenadaFormula(Long codigoSilo, List<Double> listaAlturas) throws ServicioGWTGlobalException {
		double sumaAlturas = 0d;
		for (Double altura : listaAlturas) {
			sumaAlturas += altura;
		}

		if (sumaAlturas == 0 || listaAlturas.size() == 0) {
			return 0d;
		}
		double promedioAlturas = sumaAlturas / listaAlturas.size();
		Double[] alturas = new Double[] { 0d, 0d, 0d };

		// Asigno alturas
		for (int i = 0; i < listaAlturas.size(); i++) {
			if (i < alturas.length) {
				alturas[i] = listaAlturas.get(i);
			}
		}

		Double cantidadAlmacenada = 0d;
		try {
			cantidadAlmacenada = medicionFacade.obtenerCantidadAlmacenada(codigoSilo, promedioAlturas, alturas);
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cantidadAlmacenada;
	}
}