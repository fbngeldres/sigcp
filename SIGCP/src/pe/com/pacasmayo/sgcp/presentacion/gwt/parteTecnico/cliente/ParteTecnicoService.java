package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoAjusteProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.AjusteProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumosPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("servicioRegistrarAjusteProduccionMes")
public interface ParteTecnicoService extends RemoteService {

	/**
	 * Carga los estados validos para asignar al ajuste de producción
	 * 
	 * @return List<EstadoAjusteProduccionDTO>
	 * @throws ServicioGWTGlobalException
	 */
	public List<EstadoAjusteProduccionDTO> cargarEstadosAjusteProduccion() throws ServicioGWTGlobalException;

	/**
	 * Carga la lista de meses
	 * 
	 * @return List<String>
	 * @throws ServicioGWTGlobalException
	 */
	public List<String> cargarMeses() throws ServicioGWTGlobalException;

	/**
	 * Carga una lista de años
	 * 
	 * @return List<Integer>
	 * @throws ServicioGWTGlobalException
	 */
	public List<Integer> cargarAnios() throws ServicioGWTGlobalException;

	/**
	 * Obtiene una lista con los grupos de ajuste
	 * 
	 * @param codigoLineaNegocio Long
	 * @return List<GrupoAjusteDTO>
	 */
	public List<GrupoAjusteDTO> cargarGrupoAjuste(Long codigoLineaNegocio);

	/**
	 * Obtiene una lista de productos filtrada segun el codigo del grupo de
	 * ajuste al que estos pertenecen
	 * 
	 * @param codigoGrupoAjuste
	 * @return List<ProductoDTO>
	 */
	public List<ProductoDTO> cargarProductosporCodigoGrupoAjuste(Long codigoGrupoAjuste);

	/**
	 * Obtiene el valor propuesto para realizar el ajuste de produccion, este
	 * valor se obtiene restando el stcok fisico menos el de libros
	 * 
	 * @param codigoLinea
	 * @param codigoProducto
	 * @param mesContable
	 * @param anioContable
	 * @return Double
	 * @throws ServicioGWTGlobalException
	 */
	public Double obtenerAjuste(Long codigoLinea, Long codigoProducto, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene una lista con los objetos necesario para mostrar la informacion
	 * en la tabla puesto tranajo produccion, esta contiene tm producidas en el
	 * mes, tm por hora, cantidad de ajuste, valores de dosficacion para validar
	 * la produccion
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return List<RegistroPuestoTrabajoProduccionDTO>
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * Obtiene una lista de objetos RegistroTablaConsumoComponentesDTO, usados
	 * para mostrar informacion en la tabla refrente a los movimeintos de los
	 * compoenetes en el mes
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return List<RegistroTablaConsumoComponentesDTO>
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable, Set<String> otrosProductosAjuste) throws ServicioGWTGlobalException;

	/**
	 * Obtien un map clasificado por puesto de trabajo donde cada clave contien
	 * una lista con los consumos de los componentes de ese puesto de trabajo
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>
	 * @throws ServicioGWTGlobalException
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajo(Long codigoProducto,
			Long codigoLinea, String mesContable, Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * Obtiene los vlaores de conceptos para el producto al cual se le va a
	 * realicar el ajuste
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return RegistroTablaBalanceDTO
	 * @throws ServicioGWTGlobalException
	 */
	public RegistroTablaBalanceDTO obtenerConceptosLibro(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * Obtiene el stcok físico de un producto
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return Double
	 * @throws ServicioGWTGlobalException
	 */
	public Double obtenerStockFisico(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException;

	/**
	 * Almacena en db, los ajustes realizados sobre un producto
	 * 
	 * @param codigoLineaNegocio
	 * @param anio
	 * @param mes
	 * @param codigoPlantillaGrupoAjuste
	 * @param codigoProducto
	 * @param saldoInicialLibroBalance
	 * @param produccionLibroBalance
	 * @param saldoFinalLibroBalance
	 * @param consumoLibroBalance
	 * @param consumoAjusteBalance
	 * @param produccionAjusteBalance
	 * @param detallesProduccionPuestoTrabajo
	 * @param detallesConsumoComponenteAjuste
	 * @param detallesMovimientoAjuste
	 * @return Boolean true si la operacion fue exitosa, falso en caso contrario
	 * @throws ServicioGWTGlobalException
	 */
	public Boolean registrarParteTecnico(Double ajuste, Long codigoLineaNegocio, Integer anio, String mes,
			Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance, double produccionLibroBalance,
			double saldoFinalLibroBalance, double consumoLibroBalance, double consumoAjusteBalance,
			double produccionAjusteBalance, List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String observaciones) throws ServicioGWTGlobalException;

	/**
	 * Verifica si determinado producto tiene un ajuste alamcenado en la base de
	 * datos en un mes particular
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return Long codigo del ajuste del producto o null si no existe
	 * @throws ServicioGWTGlobalException
	 */
	public AjusteProductoDTO verificarSiExisteAjusteBd(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * Obtiene las producciones por puesto de trabajo de los valores de ajuste,
	 * esto quiere decir que los trea de bd cuando ya el ajuste ha sido salvado
	 * 
	 * @param codigoAjusteProducto
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMesBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene los consumos de componentes por puesto de trabajo de los valores
	 * de ajuste, esto quiere decir que los trea de bd cuando ya el ajuste ha
	 * sido salvado
	 * 
	 * @param codigoAjusteProducto
	 * @return Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>
	 * @throws ServicioGWTGlobalException
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajoBD(
			Long codigoAjusteProducto) throws ServicioGWTGlobalException;

	/**
	 * Obtiene los conceptos de componentes de los valores de ajuste, esto
	 * quiere decir que los trea de bd cuando ya el ajuste ha sido salvado
	 * 
	 * @param codigoAjusteProducto
	 * @return List<RegistroTablaAjusteDTO>
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaAjusteDTO> obtenerDatosGridMovimientoAjustesBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException;

	/**
	 * @param codigoAjusteProducto
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return List<RegistroTablaConsumoComponentesDTO>
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerDatosGridConsumoComponentesBD(Long codigoAjusteProducto,
			Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable, Set<String> otrosProductosAjuste)
			throws ServicioGWTGlobalException;

	/**
	 * Valida si existe cubicaciones aprobadas para los datos pasados como
	 * parametro
	 * 
	 * @param anio String
	 * @param mes String
	 * @return Boolean
	 * @throws ServicioGWTGlobalException
	 */
	public Boolean validarSiexistencubicaciones(String codigoLineaNegocio, String anio, String mes)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene todos los productos registrados en el sistema
	 * 
	 * @param callback
	 * @throws ServicioGWTGlobalException
	 */
	public List<ProductoDTO> getProductos() throws ServicioGWTGlobalException;

	/**
	 * Méotod para obtener la desviacion de horas de ajuste
	 * 
	 * @param detallesProduccionPuestoTrabajo
	 * @param codigoLineaNegocio
	 * @param mesContable
	 * @param anioContable
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public Map<String, Double> obtenerDesviacionHoras(Long codigoProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo, Long codigoLineaNegocio,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * Metodo para cargar el
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return
	 */
	public List<RegistroTablaConsumosPuestoTrabajoDTO> obtenerDatosCombustible(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException;

	/**
	 * @param ajuste
	 * @param codigoLineaNegocio
	 * @param anio
	 * @param mes
	 * @param codigoPlantillaGrupoAjuste
	 * @param codigoProducto
	 * @param saldoInicialLibroBalance
	 * @param produccionLibroBalance
	 * @param saldoFinalLibroBalance
	 * @param consumoLibroBalance
	 * @param consumoAjusteBalance
	 * @param produccionAjusteBalance
	 * @param detallesConsumoComponenteAjuste
	 */
	public Boolean registrarParteTecnicoCombustible(Double mermaIngresada, Double ajuste, Long codigoLineaNegocio, Integer anio,
			String mes, Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String fechaFactura, String cantidadFactura,
			String cantidadRestante, String observaciones) throws ServicioGWTGlobalException;

	/**
	 * @param codigoLineaNegocio
	 * @param codigoProducto
	 * @param fecha
	 * @return
	 */
	public String[] obtenerConsumoDesdeFecha(Long codigoLineaNegocio, Long codigoProducto, String fecha)
			throws ServicioGWTGlobalException;

	public String[] obtenerDatosComprobante(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException;

	/**
	 * Carga la tabla de movimiento de Ajuste para el Bunker
	 * 
	 * @param codigoAjusteProducto
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public List<RegistroTablaAjusteDTO> obtenerDatosGridMovimientoAjustesCombustibleBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException;

	/**
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param asyncCallback
	 */
	public Boolean validarParteDiarioCerrado(Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException;

	public Boolean validarSiExisteAjustes(String linea, String anno, String mes);
}