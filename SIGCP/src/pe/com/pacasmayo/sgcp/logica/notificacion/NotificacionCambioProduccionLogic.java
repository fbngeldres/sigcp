package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TipoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.logica.facade.HojaRutaComponenteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionCambioProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaComponenteLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnaplantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.querier.ColumnaPlantillaProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.Querier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoComponenteQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class NotificacionCambioProduccionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		NotificacionCambioProduccionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;
	private HojaRutaComponenteLogicFacade hojaRutaComponenteFacade = new HojaRutaComponenteLogic();
	private NotificacionProduccionLogicFacade notificacionProduccionFacade = new NotificacionProduccionLogic();

	public NotificacionCambioProduccionLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.facade.NotificacionCambioProduccionLogicFacade
	 * #modificarNotificacionProduccion(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void modificarNotificacionProduccion(Notificacionproduccion notificacionProduccion, DatoReporteDTO datoreporteDTO)
			throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			notificacionProduccion = Querier.getById(Notificacionproduccion.class,
					notificacionProduccion.getPkCodigoNotificacionproduccio());

			String horaCambioProduccion = notificacionProduccion.getHoraCambioproduccionNotificac();
			String[] horaCambioDividida = horaCambioProduccion.split(":");
			Long minutoCambio = new Long(horaCambioDividida[1]);
			Double porcentajeCambio = (minutoCambio.doubleValue()) / 60;

			List<Componentenotificacion> componentesDespuesCambio = new ArrayList<Componentenotificacion>(
					notificacionProduccion.getComponentenotificacions());

			Notificacionproduccion notificacionHoraAnterior = NotificacionProduccionQuerier
					.obtenerNotificacionHoraAnterior(notificacionProduccion);

			List<Componentenotificacion> componentesHoraAntesCambio = new ArrayList<Componentenotificacion>(
					notificacionHoraAnterior.getComponentenotificacions());

			List<Columnaplantillaproducto> listaColumnaPlantillaProducto = ColumnaPlantillaProductoQuerier
					.obtenerColumnasPorCodigoPlantillaProducto(notificacionHoraAnterior.getPlantillaproducto()
							.getPkCodigoPlantillaproducto());

			List<Componentenotificacion> componentesAntesCambio = obtenerComponentesSegunPlantillaYDatoReporte(
					listaColumnaPlantillaProducto, datoreporteDTO);

			actualizarComponentes(componentesHoraAntesCambio, porcentajeCambio, componentesDespuesCambio, componentesAntesCambio);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	private List<Componentenotificacion> obtenerComponentesSegunPlantillaYDatoReporte(
			List<Columnaplantillaproducto> listaColumnaPlantillaProducto, DatoReporteDTO datoreporteDTO) {
		List<Componentenotificacion> componentesNotif = new ArrayList<Componentenotificacion>();
		for (Iterator<Columnaplantillaproducto> iterator = listaColumnaPlantillaProducto.iterator(); iterator.hasNext();) {
			Columnaplantillaproducto columnapp = iterator.next();
			Componente componente = columnapp.getComponente();
			Double proporcion = columnapp.getProporcionColumnareporte();

			Short posicionColumnareporte = columnapp.getColumnareporte().getPosicionColumnareporte();

			Double valorVariable = notificacionProduccionFacade.obtenerValorDeVariableSegunValorColumna(datoreporteDTO,
					posicionColumnareporte);

			if (valorVariable != null) {
				Componentenotificacion componentenotificacion = new Componentenotificacion();
				Double valorComponentenotificacion = 0d;
				if (proporcion == 100) {
					valorComponentenotificacion = valorVariable;
				} else {
					valorComponentenotificacion = (proporcion * valorVariable) / 100;
				}
				componentenotificacion.setValorComponentenotificacion(valorComponentenotificacion);
				componentenotificacion.setComponente(componente);

				componentesNotif.add(componentenotificacion);
			}
		}
		return componentesNotif;
	}

	private void actualizarComponentes(List<Componentenotificacion> componentesHoraAntesCambio, Double porcentajeCambio,
			List<Componentenotificacion> componentesDespuesCambio, List<Componentenotificacion> componentesAntesCambio)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		for (Componentenotificacion componentenotifHoraAntesCambio : componentesHoraAntesCambio) {
			long codigoComponenteHoraAntesCamb = componentenotifHoraAntesCambio.getComponente()
					.getProductoByFkCodigoProductoComponente().getPkCodigoProducto().longValue();
			boolean coincide = false;
			for (Componentenotificacion componentenotifDespuesCambio : componentesDespuesCambio) {
				long codigoHoraCambio = componentenotifDespuesCambio.getComponente().getProductoByFkCodigoProductoComponente()
						.getPkCodigoProducto().longValue();

				Double valorComponentenotifdespuesCambio = componentenotifDespuesCambio.getValorComponentenotificacion();

				if (codigoComponenteHoraAntesCamb == codigoHoraCambio) {
					Double valorAntesCambio = valorComponentenotifdespuesCambio * porcentajeCambio;
					Double valorHoraAntesCambio = componentenotifHoraAntesCambio.getValorComponentenotificacion();
					componentenotifHoraAntesCambio.setValorComponentenotificacion(valorHoraAntesCambio + valorAntesCambio);

					ComponenteNotificacionQuerier.update(componentenotifHoraAntesCambio);

					Double valorHoraCambio = valorComponentenotifdespuesCambio * (1 - porcentajeCambio);
					componentenotifDespuesCambio.setValorComponentenotificacion(valorHoraCambio);
					ComponenteNotificacionQuerier.update(componentenotifDespuesCambio);
					coincide = true;
					break;
				}
			}

			if (!coincide) {
				for (Componentenotificacion componentenotifAntesCambio : componentesAntesCambio) {
					long codigoComponenteAntesCambio = componentenotifAntesCambio.getComponente()
							.getProductoByFkCodigoProductoComponente().getPkCodigoProducto().longValue();

					if (codigoComponenteHoraAntesCamb == codigoComponenteAntesCambio) {
						Double valorComponentenotifAntesCambio = componentenotifAntesCambio.getValorComponentenotificacion();

						Double valorComponentenotifHoraAntesCambio = componentenotifHoraAntesCambio
								.getValorComponentenotificacion();

						componentenotifHoraAntesCambio.setValorComponentenotificacion(valorComponentenotifAntesCambio
								+ valorComponentenotifHoraAntesCambio);
						ComponenteNotificacionQuerier.update(componentenotifHoraAntesCambio);
					}
				}
			}
		}
	}

	public NotificacionProduccionBean obtenerNotificacionProduccionById(Long codigo) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();

			NotificacionProduccionBean notificacionProduccion = beanFactory
					.transformarNotificacionProduccion(NotificacionProduccionQuerier.getById(codigo));

			return notificacionProduccion;

		} catch (AplicacionException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			throw new LogicaException(mensajeError, e);

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

		}

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.facade.NotificacionCambioProduccionLogicFacade
	 * #crearNotificacionProduccionLavado(pe.com.pacasmayo.sgcp.persistencia.
	 * dataObjects.Notificacionproduccion)
	 */
	public void crearNotificacionProduccionLavado(Notificacionproduccion notificacionproduccion) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		List<ComponenteBean> agregarOrdenProduccion;

		HojaRutaComponenteBean hojaRutaComponente = new HojaRutaComponenteBeanImpl();
		Iterator<ComponenteBean> itComp;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			notificacionproduccion = NotificacionProduccionQuerier.getById(notificacionproduccion
					.getPkCodigoNotificacionproduccio());

			List<ComponenteNotificacionBean> listaComponentesNotificacion = beanFactory
					.tranformarListaComponenteNotificacion(ComponenteNotificacionQuerier
							.getByNotificacionProduccion(notificacionproduccion.getPkCodigoNotificacionproduccio()));
			Iterator<ComponenteNotificacionBean> itNP = listaComponentesNotificacion.iterator();
			List<ComponenteBean> componentesNP = new ArrayList<ComponenteBean>();

			while (itNP.hasNext()) {
				componentesNP.add(itNP.next().getComponente());
			}
			Ordenproduccion ordenProduccion = notificacionproduccion.getOrdenproduccion();

			List<HojaRutaComponenteBean> listaHojaRutaComponente = beanFactory
					.transformarListaHojaRutaComponente((HojaRutaComponenteQuerier.findByCodigoHojaRuta(ordenProduccion
							.getHojaruta().getPkCodigoHojaruta())));
			Iterator<HojaRutaComponenteBean> itOP = listaHojaRutaComponente.iterator();
			TipoComponenteBean tipoComponente = beanFactory.transformarTipoComponente(TipoComponenteQuerier.getById(new Long(2)));

			List<ComponenteBean> componentesOP = new ArrayList<ComponenteBean>();

			while (itOP.hasNext()) {
				componentesOP.add(itOP.next().getComponente());
			}

			agregarOrdenProduccion = compararComponentes(componentesNP, componentesOP);
			itComp = agregarOrdenProduccion.iterator();

			while (itComp.hasNext()) {
				hojaRutaComponente.setComponente(itComp.next());
				hojaRutaComponente.setHojaRuta(beanFactory.transformarHojaRuta(ordenProduccion.getHojaruta()));
				hojaRutaComponente.setTipoComponente(tipoComponente);

				hojaRutaComponenteFacade.insertarHojaRutaComponente(hojaRutaComponente);
			}

		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			if (tx != null)
				tx.rollback();
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		}

		catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			if (tx != null)
				tx.rollback();

			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			if (tx != null)
				tx.rollback();

			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);

		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

		}

	}

	public List<ComponenteBean> compararComponentes(List<ComponenteBean> listaComponentesA, List<ComponenteBean> listaComponentesB) {

		Iterator<ComponenteBean> itA = listaComponentesA.iterator();
		ComponenteBean componenteA;

		List<ComponenteBean> resultado = new ArrayList<ComponenteBean>();

		while (itA.hasNext()) {

			componenteA = itA.next();

			if (!this.existeComponente(componenteA, listaComponentesB)) {

				resultado.add(componenteA);
			}

		}

		return resultado;

	}

	public Boolean existeComponente(ComponenteBean componente, List<ComponenteBean> listaComponentes) {

		Iterator<ComponenteBean> it = listaComponentes.iterator();

		while (it.hasNext()) {

			if (componente.getCodigo().equals(it.next().getCodigo())) {

				return true;
			}
		}

		return false;
	}

}
