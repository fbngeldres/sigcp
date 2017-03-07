package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoOrdenProduccionFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoOrdenProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoOrdenProduccionQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoOrdenProduccionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		EstadoOrdenProduccionFacade, EstadoOrdenProduccionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public EstadoOrdenProduccionLogic() {

		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.EstadoOrdenProduccionLogicFacade
	 * #obtenerEstadosOrdenProduccion()
	 */
	public List<Estadoordenproduccion> obtenerEstadosOrdenProduccion() throws LogicaException {

		String mensajeError = "";

		try {
			return EstadoOrdenProduccionQuerier.getAll();
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadoordenproduccion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

	public List<EstadoOrdenProduccionBean> obtenerEstadosOrdenProduccionBean() throws LogicaException {

		String mensajeError = "";

		try {
			return beanFactory.transformarListaEstadoOrdenProduccionBean(EstadoOrdenProduccionQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadoordenproduccion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

}
