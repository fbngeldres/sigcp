package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ColumnaReporteLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocolumnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillareporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.TransactionException;
import pe.com.pacasmayo.sgcp.persistencia.querier.ColumnaReporteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoColumnaReporteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ColumnaReporteLogic implements ConstantesMensajeAplicacion,
		ConstantesMensajePresentacion, ColumnaReporteLogicFacade {

	private static BeanFactory beanFactory;

	private Log logger = LogFactory.getLog(this.getClass());

	private String mensajeError = "";

	public ColumnaReporteLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/**
	 * Método para almacenar un listado de columnas de acuerdo con una plantilla
	 * 
	 * @param columnasBean
	 * @param plantilla
	 * @param tx
	 * @throws AplicacionException
	 */
	public void insertarColumnasReporte(List<ColumnaReporteBean> columnasBean,
			Plantillareporte plantilla, Transaction tx)
			throws AplicacionException {
		String mensajeError = "";

		try {

			Estadocolumnareporte estadoColumna = EstadoColumnaReporteQuerier
					.findByNombre(ConstantesAplicacionAction.ESTADO_ACTIVO);

			Columnareporte columna = null;
			ColumnaReporteBean columnaBean = null;

			for (int i = 0; i < columnasBean.size(); i++) {
				columnaBean = columnasBean.get(i);
				columna = new Columnareporte();
				columna.setEstadocolumnareporte(estadoColumna);
				columna.setPlantillareporte(plantilla);
				columna.setNombreColumnareporte(columnaBean
						.getNombreColumnaReporte());
				columna.setPosicionColumnareporte(columnaBean
						.getPosicionColumnaReporte());

				ColumnaReporteQuerier.save(columna);
			}

			tx.commit();

		} catch (ParametroInvalidoException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			logger.error(mensajeError, e);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Método para actualizar un listado de columnas de una plantilla
	 * 
	 * @param columnasBean
	 * @param plantilla
	 * @param tx
	 * @throws LogicaException
	 */
	public void actualizarColumnasReporte(
			List<ColumnaReporteBean> columnasBean, Plantillareporte plantilla,
			Transaction tx) throws LogicaException {
		String mensajeError = "";

		try {

			List<Estadocolumnareporte> estadosColumna = EstadoColumnaReporteQuerier
					.getAll();

			Columnareporte columna = null;
			ColumnaReporteBean columnaBean = null;

			for (int i = 0; i < columnasBean.size(); i++) {

				columnaBean = columnasBean.get(i);

				if (columnaBean.getCodigo() != null) {
					// La columna no es nueva
					columna = ColumnaReporteQuerier.getById(columnaBean
							.getCodigo());
				} else {
					// La columna es nueva
					columna = new Columnareporte();
					columna.setPkCodigoColumnareporte(new Long(0));
				}

				columna.setEstadocolumnareporte(obtenerEstadoColumna(
						estadosColumna, columnaBean.getEstadoColumnaReporte()
								.getCodigo()));
				columna.setPlantillareporte(plantilla);
				columna.setNombreColumnareporte(columnaBean
						.getNombreColumnaReporte());
				columna.setPosicionColumnareporte(columnaBean
						.getPosicionColumnaReporte());

				if (columna.getPkCodigoColumnareporte() == 0)
					ColumnaReporteQuerier.save(columna);
				else
					ColumnaReporteQuerier.update(columna);

			}

			tx.commit();

		} catch (ParametroInvalidoException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			logger.error(mensajeError, e);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Método para eliminar un listado de columnas de una plantilla
	 * 
	 * @param columnas
	 * @param tx
	 * @throws LogicaException
	 */
	public void eliminarColumnasReporte(Set<Columnareporte> columnas,
			Transaction tx) throws LogicaException {
		String mensajeError = "";

		try {

			Columnareporte columna = null;

			for (int i = 0; i < columnas.size(); i++) {
				columna = (Columnareporte) columnas.toArray()[i];
				ColumnaReporteQuerier.delete(columna);
			}

		} catch (ParametroInvalidoException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			logger.error(mensajeError, e);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			logger.error(mensajeError, e);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Método para obtener las columnas de una plantilla por estado
	 * 
	 * @param codigoPlantilla
	 * @param estado
	 * @throws LogicaException
	 * @return
	 */
	public List<ColumnaReporteBean> obtenerColumnasPorPlantillaYEstado(
			Long codigoPlantilla, String estado) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {

			List<Columnareporte> columnas = ColumnaReporteQuerier
					.obtenerColumnasPorPlantillaYEstado(codigoPlantilla, estado);
			List<ColumnaReporteBean> columnasBean = new ArrayList<ColumnaReporteBean>();

			for (int i = 0; i < columnas.size(); i++) {

				columnasBean.add(beanFactory
						.transformarColumnaPlantillaReporte(columnas.get(i)));
			}
			return columnasBean;
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Método para obtener el estado de una lista de estados de columna
	 * 
	 * @param estadosColumna
	 * @param codigo
	 * @return
	 */
	private Estadocolumnareporte obtenerEstadoColumna(
			List<Estadocolumnareporte> estadosColumna, Long codigo) {
		Estadocolumnareporte columna = null;

		for (int i = 0; i < estadosColumna.size(); i++) {
			columna = estadosColumna.get(i);
			if (codigo.equals(columna.getPkCodigoEstadocolumnareporte())) {
				break;
			}
		}

		return columna;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.facade.ColumnaReporteLogicFacade#
	 * obtenerColumnasDO(java.lang.Long, java.lang.Long, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	private List<Columnareporte> obtenerColumnasDO(Long codigoPuestoTrabajo,
			Long codigoProceso, String estadoColumna, String estadoPlantilla)
			throws LogicaException {
		List<Columnareporte> lista = null;
		try {

			lista = ColumnaReporteQuerier.obtenerColumnas(codigoPuestoTrabajo,
					codigoProceso, estadoColumna, estadoPlantilla);
			return lista;

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Columnareporte.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

	public static void main(String[] args) {
		ColumnaReporteLogic r = new ColumnaReporteLogic();
		try {
			r.obtenerColumnasDTO(96L, 39L, "Activo", "Activo");
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ColumnareporteDTO> obtenerColumnasDTO(Long codigoPuestoTrabajo,
			Long codigoProceso, String estadoColumna, String estadoPlantilla)
			throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {

			List<ColumnareporteDTO> columnasDTO = new ArrayList<ColumnareporteDTO>();
			List<Columnareporte> columnasDO = obtenerColumnasDO(
					codigoPuestoTrabajo, codigoProceso, estadoColumna,
					estadoPlantilla);

			for (Columnareporte columnareporte : columnasDO) {
				ColumnareporteDTO columnaDTO = new ColumnareporteDTO();

				columnaDTO.setPkCodigoColumnareporte(columnareporte
						.getPkCodigoColumnareporte());
				columnaDTO.setPkCodigoEstadocolumnareporte(columnareporte
						.getEstadocolumnareporte()
						.getPkCodigoEstadocolumnareporte());
				columnaDTO.setPkCodigoPlantillareporte(columnareporte
						.getPlantillareporte().getPkCodigoPlantillareporte());
				columnaDTO.setNombreColumnareporte(columnareporte
						.getNombreColumnareporte());
				columnaDTO.setPosicionColumnareporte(columnareporte
						.getPosicionColumnareporte());

				columnasDTO.add(columnaDTO);
			}

			return columnasDTO;
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

}
