package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.servidor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.stock.CubicacionProductoLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente.CubicacionProductoService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CubicacionProductoServiceImpl extends RemoteServiceServlet implements CubicacionProductoService,
		ConstantesAplicacionAction {

	private CubicacionProductoLogicFacade cubicacionProductoFacade = new CubicacionProductoLogic();
	private static Logger logger = Logger.getLogger(CubicacionProductoServiceImpl.class);

	/**
	 * Consulta de cubicación dado un Id
	 */
	public CubicacionProductoDTO consultarCubicacion(Long codigoCubicacion) throws ServicioGWTGlobalException {
System.out.println("---> " + codigoCubicacion);
		CubicacionProductoDTO cubicacionProductoDTO = null;

		try {
			System.out.println("--->2 " );
			cubicacionProductoDTO = cubicacionProductoFacade.obtenerCubicacionProducto(codigoCubicacion);
		} catch (LogicaException e) {
			e.printStackTrace();
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesGWT.PROGRAMA_PRODUCCION_SEMANAL) + ": "
					+ ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesGWT.ERROR_REGISTRO_NO_ENCONTRADO);
			throw new ServicioGWTGlobalException(mensajeError, e);
		}

		return cubicacionProductoDTO;
	}

	/**
	 * Modifica la lista de cubicaciones de la Cubicación Producto específicada.
	 */
	public String modificarCubicacion(Long codigoCubicacionProducto, List<TablaCubicacionDTO> cubicaciones)
			throws IllegalArgumentException {
		String MENSJ_EXITO = "";
		try {
			cubicacionProductoFacade.modificarCubicacion(codigoCubicacionProducto, cubicaciones, getUsuarioSession().getCodigo());
			MENSJ_EXITO = ConstantesGWT.OPERACION_EXITOSA;
		} catch (LogicaException e) {
			logger.error(e.getMessage(), e);
		}
		return MENSJ_EXITO;
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
}