package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.DatoReporteBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.DatoReporteLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Datoreporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Turno;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.TransactionException;
import pe.com.pacasmayo.sgcp.persistencia.querier.DatoReporteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HoraQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.TurnoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class DatoReporteLogic implements ConstantesMensajeAplicacion, ConstantesMensajePresentacion, DatoReporteLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private Map<Long, Hora> horasCargadas = new HashMap<Long, Hora>();
	private Map<Long, Datoreporte> datoscargados = new HashMap<Long, Datoreporte>();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.reporteECS.DatoReporteLogicFacade#
	 * insertarDatosReporte(java.util.List,
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs,
	 * org.hibernate.Transaction)
	 */
	public void insertarDatosReporte(List<DatoReporteBean> datosBean, Registroreporteecs registroReporteECS, Transaction tx)
			throws LogicaException {
		String mensajeError = "";

		try {

			cargarHoras();

			Datoreporte dato = null;
			DatoReporteBean datoReporteBean = null;

			for (int i = 0; i < datosBean.size(); i++) {

				datoReporteBean = datosBean.get(i);

				Hora hora = horasCargadas.get(datoReporteBean.getHora().getCodigo());

				dato = new Datoreporte();

				dato.setRegistroreporteecs(registroReporteECS);
				dato.setHora(hora);
				dato.setVariable1Datoreporte(datoReporteBean.getVariable1Datoreporte());
				dato.setVariable2Datoreporte(datoReporteBean.getVariable2Datoreporte());
				dato.setVariable3Datoreporte(datoReporteBean.getVariable3Datoreporte());
				dato.setVariable4Datoreporte(datoReporteBean.getVariable4Datoreporte());
				dato.setVariable5Datoreporte(datoReporteBean.getVariable5Datoreporte());
				dato.setVariable6Datoreporte(datoReporteBean.getVariable6Datoreporte());
				dato.setVariable7Datoreporte(datoReporteBean.getVariable7Datoreporte());
				dato.setVariable8Datoreporte(datoReporteBean.getVariable8Datoreporte());
				dato.setVariable9Datoreporte(datoReporteBean.getVariable9Datoreporte());
				dato.setVariable10Datoreporte(datoReporteBean.getVariable10Datoreporte());
				dato.setVariable11Datoreporte(datoReporteBean.getVariable11Datoreporte());
				dato.setVariable12Datoreporte(datoReporteBean.getVariable12Datoreporte());
				dato.setVariable13Datoreporte(datoReporteBean.getVariable13Datoreporte());
				dato.setVariable14Datoreporte(datoReporteBean.getVariable14Datoreporte());
				dato.setVariable15Datoreporte(datoReporteBean.getVariable15Datoreporte());

				DatoReporteQuerier.save(dato);
			}

			tx.commit();
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.reporteECS.DatoReporteLogicFacade#
	 * actualizarDatosReporte(java.util.List,
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs,
	 * org.hibernate.Transaction)
	 */
	public void actualizarDatosReporte(List<DatoReporteBean> datosBean, Registroreporteecs registroReporteECS, Transaction tx)
			throws LogicaException {
		String mensajeError = "";

		try {

			datoscargados = new HashMap<Long, Datoreporte>();
			cargarDatos(registroReporteECS);
			cargarHoras();

			Datoreporte dato = null;
			DatoReporteBean datoReporteBean = null;

			if (horasCargadas != null && horasCargadas.size() > 0) {
				for (int i = 0; i < datosBean.size(); i++) {

					datoReporteBean = datosBean.get(i);
					Hora hora = horasCargadas.get(datoReporteBean.getHora().getCodigo());

					dato = datoscargados.get(datoReporteBean.getHora().getCodigo());

					dato.setRegistroreporteecs(registroReporteECS);
					dato.setHora(hora);
					dato.setVariable1Datoreporte(datoReporteBean.getVariable1Datoreporte());
					dato.setVariable2Datoreporte(datoReporteBean.getVariable2Datoreporte());
					dato.setVariable3Datoreporte(datoReporteBean.getVariable3Datoreporte());
					dato.setVariable4Datoreporte(datoReporteBean.getVariable4Datoreporte());
					dato.setVariable5Datoreporte(datoReporteBean.getVariable5Datoreporte());
					dato.setVariable6Datoreporte(datoReporteBean.getVariable6Datoreporte());
					dato.setVariable7Datoreporte(datoReporteBean.getVariable7Datoreporte());
					dato.setVariable8Datoreporte(datoReporteBean.getVariable8Datoreporte());
					dato.setVariable9Datoreporte(datoReporteBean.getVariable9Datoreporte());
					dato.setVariable10Datoreporte(datoReporteBean.getVariable10Datoreporte());
					dato.setVariable11Datoreporte(datoReporteBean.getVariable11Datoreporte());
					dato.setVariable12Datoreporte(datoReporteBean.getVariable12Datoreporte());
					dato.setVariable13Datoreporte(datoReporteBean.getVariable13Datoreporte());
					dato.setVariable14Datoreporte(datoReporteBean.getVariable14Datoreporte());
					dato.setVariable15Datoreporte(datoReporteBean.getVariable15Datoreporte());

					DatoReporteQuerier.update(dato);
				}

				tx.commit();
			}

		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * M�todo para cargar en memoria las horas
	 */
	private void cargarHoras() {
		List<Hora> horas = HoraQuerier.getAll();

		for (int i = 0; i < horas.size(); i++) {
			Hora hora = horas.get(i);
			horasCargadas.put(hora.getPkCodigoHora(), hora);
		}
	}

	/**
	 * M�todo para obtener los datos del reporte por hora
	 * 
	 * @param registroReporte
	 */
	@SuppressWarnings("unchecked")
	private void cargarDatos(Registroreporteecs registroReporte) {
		Datoreporte dato = null;
		Set<Datoreporte> datos = registroReporte.getDatoreportes();
		Iterator<Datoreporte> datosIter = datos.iterator();

		while (datosIter.hasNext()) {
			dato = datosIter.next();
			datoscargados.put(dato.getHora().getPkCodigoHora(), dato);
		}

	}

	/**
	 * Metodo para obtener la lista de vairables por hora dada una fecha y un
	 * nombre de archivo ECS
	 * 
	 * @param fecha
	 * @param nombre
	 * @return List<Datoreporte>
	 * @throws LogicaException
	 */
	private List<Datoreporte> cargarDatosVariablesPorHoraDO(Date fecha, String nombre) throws LogicaException {
		String mensajeError = "";

		try {
			return DatoReporteQuerier.obtenerFilasVariables(fecha, nombre);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Metodo para obtener la lista de vairables por un nombre de archivo ECS
	 * 
	 * @param nombre
	 * @return List<Datoreporte>
	 * @throws LogicaException
	 */
	private List<Datoreporte> cargarDatosVariablesPorHoraDO(String nombre) throws LogicaException {
		String mensajeError = "";

		try {
			return DatoReporteQuerier.obtenerFilasVariables(nombre);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Metodo para obtener la lista de vairables por hora dada una fecha y un
	 * nombre de archivo ECS
	 * 
	 * @param fecha
	 * @param nombre
	 * @return List<DatoReporteDTO>
	 * @throws LogicaException
	 */
	public List<DatoReporteDTO> cargarDatosVariablesPorHoraDTO(Date fecha, String nombre, String tipoVariable, Long codigoProceso)
			throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		List<DatoReporteDTO> variablesHora = new ArrayList<DatoReporteDTO>();
		List<Datoreporte> variablesHoraDO = null;
		try {

			if (tipoVariable.equals(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesGWT.TIPO_VARIABLE_PRODUCCION))) {

				variablesHoraDO = cargarDatosVariablesPorHoraDO(nombre);

			} else {
				variablesHoraDO = cargarDatosVariablesPorHoraDO(fecha, nombre);
			}

			if (variablesHoraDO == null || variablesHoraDO.size() == 0) {
				return null;
			}

			List<Turno> turnos = TurnoQuerier.getAllOrderBy(TurnoQuerier.NOMBRE_TURNO);

			if (turnos != null && turnos.size() > 0) {

				List<Datoreporte> datosUtimoTurno = new ArrayList<Datoreporte>();
				// como los registros vienen ordenados por hora tomo los
				// registros
				// de las horas que no corresponden al primer turno para
				// colocarlos
				// al
				// final
				// ya q corresponden al ultimo turno
				for (int i = 1; i <= turnos.get(0).getHoraInicioTurno() - 1; i++) {
					if (variablesHoraDO.get(i - 1).getHora().getHoraHora() == i) {
						datosUtimoTurno.add(variablesHoraDO.get(i - 1));

					}
				}

				if (!datosUtimoTurno.isEmpty()) {
					// se eliminan los datos del comienzo de la lista
					variablesHoraDO.removeAll(datosUtimoTurno);
					// se vuelven a colocar pero al final de la lista
					variablesHoraDO.addAll(datosUtimoTurno);
				}

			}

			for (Iterator<Datoreporte> itt = variablesHoraDO.iterator(); itt.hasNext();) {
				Datoreporte datoreporte = itt.next();
				DatoReporteDTO datoReporteDTO = new DatoReporteDTO();

				datoReporteDTO.setPkCodigoDatoreporte(datoreporte.getPkCodigoDatoreporte());
				datoReporteDTO.setPkCodigoHora(datoreporte.getHora().getPkCodigoHora());
				datoReporteDTO.setHora(datoreporte.getHora().getHoraHora());
				datoReporteDTO.setCodigoTurno(datoreporte.getHora().getTurno().getPkCodigoTurno());
				datoReporteDTO.setPkRegistroReporte(datoreporte.getRegistroreporteecs().getPkCodigoRegistroreporteecs());
				datoReporteDTO.setVariable1Datoreporte(datoreporte.getVariable1Datoreporte());
				datoReporteDTO.setVariable2Datoreporte(datoreporte.getVariable2Datoreporte());
				datoReporteDTO.setVariable3Datoreporte(datoreporte.getVariable3Datoreporte());
				datoReporteDTO.setVariable4Datoreporte(datoreporte.getVariable4Datoreporte());
				datoReporteDTO.setVariable5Datoreporte(datoreporte.getVariable5Datoreporte());
				datoReporteDTO.setVariable6Datoreporte(datoreporte.getVariable6Datoreporte());
				datoReporteDTO.setVariable7Datoreporte(datoreporte.getVariable7Datoreporte());
				datoReporteDTO.setVariable8Datoreporte(datoreporte.getVariable8Datoreporte());
				datoReporteDTO.setVariable9Datoreporte(datoreporte.getVariable9Datoreporte());
				datoReporteDTO.setVariable10Datoreporte(datoreporte.getVariable10Datoreporte());
				datoReporteDTO.setVariable11Datoreporte(datoreporte.getVariable11Datoreporte());
				datoReporteDTO.setVariable12Datoreporte(datoreporte.getVariable12Datoreporte());
				datoReporteDTO.setVariable13Datoreporte(datoreporte.getVariable13Datoreporte());
				datoReporteDTO.setVariable14Datoreporte(datoreporte.getVariable14Datoreporte());
				datoReporteDTO.setVariable15Datoreporte(datoreporte.getVariable15Datoreporte());

				variablesHora.add(datoReporteDTO);
			}

			// Adicionalmente si es VP se deben agregar la lista de ordenes de
			// produccion a
			// cada objeto DatoReporteDTO y las cuales contienem en si al
			// producto y
			// la lista de silos ....
			// if (tipoVariable.equals("VP")) {
			//
			// Integer annio = FechaUtil.getAnnoActual();
			//
			// Short mes = new Short(FechaUtil.getMesActual().shortValue());
			// mes++;
			//
			// List<OrdenProduccionDTO> ordenes =
			// ordenFacade.getOrdenesDTOByMesYProceso(mes, codigoProceso,
			// annio);
			//
			// for (DatoReporteDTO datoReporteDTO : variablesHora) {
			// datoReporteDTO.setOrdenes(ordenes);
			// }
			// }
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return variablesHora;
	}

	public void actualizarDatosReporteDataObject(List<Datoreporte> datos, Registroreporteecs registroReporteECS, Transaction tx)
			throws LogicaException {
		String mensajeError = "";

		try {

			datoscargados = new HashMap<Long, Datoreporte>();
			cargarDatos(registroReporteECS);
			cargarHoras();

			Datoreporte dato = null;

			if (horasCargadas != null && horasCargadas.size() > 0) {
				for (int i = 0; i < datos.size(); i++) {

					dato = datos.get(i);
					// Hora hora =
					// horasCargadas.get(dato.getHora().getPkCodigoHora());

					dato = datoscargados.get(dato.getHora().getPkCodigoHora());

					DatoReporteQuerier.update(dato);
				}

				tx.commit();
			}

		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

	}

}
