package pe.com.pacasmayo.sgcp.logica.seguridad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MenuLogic.java
 * Modificado: Apr 13, 2010 9:24:22 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.AccionBean;
import pe.com.pacasmayo.sgcp.bean.EstadoPrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioPrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.MenuBean;
import pe.com.pacasmayo.sgcp.bean.OpcionBean;
import pe.com.pacasmayo.sgcp.bean.PrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.MenuLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Accion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Menu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Nivelmenu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Opcion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Opcionaccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Privilegio;
import pe.com.pacasmayo.sgcp.persistencia.querier.AccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MenuQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NivelMenuQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OpcionAccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OpcionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PrivilegioQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.CadenaUtil;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class MenuLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesFiltros, MenuLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;
	public static String TOPMENUS = "menus";
	public static String MAPA_OPCIONES = "opciones";
	public static String ACTION_MENU = "VER";

	// public enum OPCIONES {
	// AGREGAR, APROBAR, VERSIONAR, MODIFICAR, ELIMINAR, CONSULTAR, VER, COPIAR,
	// GRABAR, RECALCULAR
	// }

	public enum OPCIONES {
		NOOPCION, CONSULTAR, APROBAR, REVERTIR, NUEVO, ELIMINAR, GESTIONAR, CERRAR, MODIFICAR, VERSIONAR, LIBERAR, EXPORTAR, ACTIVAR, INACTIVAR, COPIAR, VER, GENERAR, CARGAAUTOMATICA, IMPORTAR, PRECONSOLIDAR,GRABAR
	};

	public String ESTADOPRIVILEGIO = "Activo";
	public String actionEnConstruccion = "../seguridad/enConstruccion.action";
	public String anchoDeLosItemsPrincipales = "150px";
	public String imagenDeItemsSecundarios = "../images/submenu-icon.gif";

	public static GrupoUsuarioPrivilegioLogic grupoUsuarioPrivilegioLogic = new GrupoUsuarioPrivilegioLogic();

	private MenuRepository obtenerRepositorio(HttpSession sesion) {
		return (MenuRepository) sesion.getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
	}

	private void agregarTopMenuASesion(HttpSession sesion, String login) {
		MenuRepository repository = (MenuRepository) sesion.getServletContext().getAttribute("repository" + login);
		sesion.setAttribute(TOPMENUS + login, repository.getTopMenus());

	}

	private void agregarMapaOpcionesASesion(HttpSession sesion, String login, Map<String, List<OPCIONES>> mapaDeOpciones) {
		sesion.setAttribute(MAPA_OPCIONES + login, mapaDeOpciones);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private List<MenuComponent> obtenerTopMenuDeSesion(HttpSession sesion, String login) {
		return (List<MenuComponent>) sesion.getAttribute(TOPMENUS + login);
	}

	private OPCIONES transformarOpcionBeanAENUM(OpcionBean opcionBean) {

		if (opcionBean.getNombre().equals(OPCIONES.VER.toString())) {
			return OPCIONES.VER;
		}
		if (opcionBean.getNombre().equals(OPCIONES.NUEVO.toString())) {
			return OPCIONES.NUEVO;
		}
		if (opcionBean.getNombre().equals(OPCIONES.APROBAR.toString())) {
			return OPCIONES.APROBAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.CONSULTAR.toString())) {
			return OPCIONES.CONSULTAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.ELIMINAR.toString())) {
			return OPCIONES.ELIMINAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.MODIFICAR.toString())) {
			return OPCIONES.MODIFICAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.COPIAR.toString())) {
			return OPCIONES.COPIAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.REVERTIR.toString())) {
			return OPCIONES.REVERTIR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.GESTIONAR.toString())) {
			return OPCIONES.GESTIONAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.CERRAR.toString())) {
			return OPCIONES.CERRAR;
		}

		if (opcionBean.getNombre().equals(OPCIONES.VERSIONAR.toString())) {
			return OPCIONES.VERSIONAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.LIBERAR.toString())) {
			return OPCIONES.LIBERAR;
		}

		if (opcionBean.getNombre().equals(OPCIONES.EXPORTAR.toString())) {
			return OPCIONES.EXPORTAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.ACTIVAR.toString())) {
			return OPCIONES.ACTIVAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.INACTIVAR.toString())) {
			return OPCIONES.INACTIVAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.GENERAR.toString())) {
			return OPCIONES.GENERAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.CARGAAUTOMATICA.toString())) {
			return OPCIONES.CARGAAUTOMATICA;
		}

		if (opcionBean.getNombre().equals(OPCIONES.CARGAAUTOMATICA.toString())) {
			return OPCIONES.CARGAAUTOMATICA;
		}
		if (opcionBean.getNombre().equals(OPCIONES.IMPORTAR.toString())) {
			return OPCIONES.IMPORTAR;
		}
		if (opcionBean.getNombre().equals(OPCIONES.PRECONSOLIDAR.toString())) {
			return OPCIONES.PRECONSOLIDAR;
		}
		
		if (opcionBean.getNombre().equals(OPCIONES.GRABAR.toString())) {
			return OPCIONES.GRABAR;
		}

		return OPCIONES.NOOPCION;
	}

	private void generaMenu(HttpSession sesion, List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegioBeanList,
			Map<String, List<OPCIONES>> mapaDeOpciones, String login) {

		try {
			MenuRepository defaultRepository = obtenerRepositorio(sesion);

			MenuRepository repository = new MenuRepository();

			if (sesion.getServletContext().getAttribute("repository" + login) == null) {
				repository = new MenuRepository();
			} else {
				repository = (MenuRepository) sesion.getServletContext().getAttribute("repository" + login);
			}
			repository.setDisplayers(defaultRepository.getDisplayers());
			repository.removeAllMenus();

			for (Iterator<GrupoUsuarioPrivilegioBean> iterator = grupoUsuarioPrivilegioBeanList.iterator(); iterator.hasNext();) {
				GrupoUsuarioPrivilegioBean grupoUsuarioPrivilegioBean = iterator.next();
				PrivilegioBean privilegioBean = grupoUsuarioPrivilegioBean.getPrivilegioBean();

				if (privilegioBean != null) {
					EstadoPrivilegioBean estado = privilegioBean.getEstadoPrivilegioBean();

					if (estado.getNombre().equals(ESTADOPRIVILEGIO)) {

						anadirMenu(repository, new MenuComponent(), privilegioBean.getMenuList(), mapaDeOpciones);
					}
				}
			}
			sesion.getServletContext().setAttribute("repository" + login, repository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void anadirMenu(MenuRepository repository, MenuComponent menuPadre, List<MenuBean> menuBeanList,
			Map<String, List<OPCIONES>> mapaDeOpciones) {
	

		for (Iterator<MenuBean> iteratorMenuBean = menuBeanList.iterator(); iteratorMenuBean.hasNext();) {
			MenuBean menu = iteratorMenuBean.next();
			if (menu.getEstadoMenu() != null && menu.getEstadoMenu()) {
				MenuComponent itemPrincipal = new MenuComponent();

				itemPrincipal.setName(menu.getNombre());
				itemPrincipal.setTitle(menu.getNombre());
				itemPrincipal.setDescription(menu.getDescripcion());

				if (menu.getMenuPadre() == null) {

					itemPrincipal.setName(CadenaUtil.removerCaracteresEspeciales(itemPrincipal.getName()) );
					itemPrincipal.setTitle(CadenaUtil.removerCaracteresEspeciales(itemPrincipal.getTitle()));
					itemPrincipal.setDescription(CadenaUtil.removerCaracteresEspeciales(itemPrincipal.getDescription()));
					
					repository.addMenu(itemPrincipal);
				} else {

					menuPadre.addMenuComponent(itemPrincipal);
				}

				if (menu.getMenuBeanList().isEmpty()) {
					itemPrincipal.setLocation(actionEnConstruccion);
					itemPrincipal.setImage(imagenDeItemsSecundarios);

					activarAction(menu.getAccionBeanList(), mapaDeOpciones, itemPrincipal);
				} else
					anadirMenu(repository, itemPrincipal, menu.getMenuBeanList(), mapaDeOpciones);

			}
		}

		return;
	}

	private void activarAction(List<AccionBean> accionBeanList, Map<String, List<OPCIONES>> mapaDeOpciones, MenuComponent menu) {
		for (Iterator<AccionBean> itorAccion = accionBeanList.iterator(); itorAccion.hasNext();) {
			AccionBean accionBean = itorAccion.next();

			List<OPCIONES> listaOpciones = new ArrayList<OPCIONES>();

			if (accionBean.getOpcionesList() != null && accionBean.getOpcionesList().size() > 0) {
				menu.setLocation(accionBean.getUrlAccion());
				menu.setImage(imagenDeItemsSecundarios);
			}

			for (Iterator<OpcionBean> itorOpcion = accionBean.getOpcionesList().iterator(); itorOpcion.hasNext();) {

				OpcionBean opcionBean = itorOpcion.next();
				OPCIONES opcion = transformarOpcionBeanAENUM(opcionBean);

				if (!listaOpciones.contains(opcion))
					listaOpciones.add(opcion);

			}

			mapaDeOpciones.put(accionBean.getNombre(), listaOpciones);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#configurarMenu
	 * (javax.servlet.http.HttpSession, pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#configurarMenu
	 * (javax.servlet.http.HttpSession, pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void configurarMenu(HttpSession sesion, UsuarioBean usuario) throws LogicaException {
		Set<GrupoUsuarioBean> gruposUsuarioList = usuario.getGrupoUsuarios();

		Map<String, List<OPCIONES>> mapaDeOpciones = new HashMap<String, List<OPCIONES>>();

		for (Iterator<GrupoUsuarioBean> iterator = gruposUsuarioList.iterator(); iterator.hasNext();) {
			GrupoUsuarioBean grupoUsuarioBean = iterator.next();

			List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegioBeanList = grupoUsuarioPrivilegioLogic
					.getByGrupoUsuario(grupoUsuarioBean.getCodigo());

			generaMenu(sesion, grupoUsuarioPrivilegioBeanList, mapaDeOpciones, usuario.getLogin());
		}

		agregarTopMenuASesion(sesion, usuario.getLogin());
		agregarMapaOpcionesASesion(sesion, usuario.getLogin(), mapaDeOpciones);
	}

	public MenuLogic() {
		MenuLogic.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#obtenerMenu(java
	 * .lang.Long)
	 */
	public MenuBean obtenerMenu(Long codigoMenu) throws LogicaException {

		MenuBean menuBean = null;
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

		try {
			menuBean = beanFactory.transformarMenuMantenimiento(MenuQuerier.getById(codigoMenu));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return menuBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#obtenerMenus()
	 */
	public List<MenuBean> obtenerMenus() throws LogicaException {

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

		try {
			return beanFactory.transformarListaMenu(MenuQuerier.getAll(), Short.parseShort("0"));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA) + Menu.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#obtenerMenuPorNombre
	 * (java.lang.String)
	 */
	public List<MenuBean> obtenerMenuPorNombre(String nombre) throws LogicaException {

		List<MenuBean> listaMenuBean = null;
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

		try {
			listaMenuBean = beanFactory.transformarListaMenu(MenuQuerier.findByNombre(nombre), Short.parseShort("0"));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA) + Menu.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMenuBean;

	}

	public List<MenuBean> obtenerMenuPadres() throws LogicaException {
		List<MenuBean> listaMenuBean = null;
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

		try {
			listaMenuBean = beanFactory.transformarListaMenu(MenuQuerier.obtenerMenusPadres(), Short.parseShort("0"));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA) + Menu.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMenuBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#obtenerAtributos()
	 */
	public List<UtilBean> obtenerAtributos() {

		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(1);
		filtro.setValor(CODIGO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(2);
		filtro.setValor(NOMBRE);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#insertarMenu(pe
	 * .com.pacasmayo.sgcp.bean.MenuBean)
	 */
	public void insertarMenu(MenuBean menuBean) throws AplicacionException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Menu menu = new Menu();

			Menu menuPadre = MenuQuerier.getById(menuBean.getMenuPadre().getCodigo());

			menu.setMenu(menuPadre);

			Nivelmenu nivelMenu = NivelMenuQuerier.getById(menuBean.getNivelMenuBean().getCodigo());

			menu.setNivelmenu(nivelMenu);

			Privilegio privilegio = PrivilegioQuerier.getById(menuBean.getPrivilegioBean().getCodigo());

			menu.setPrivilegio(privilegio);

			menu.setDescripcionMenu(menuBean.getDescripcion());
			menu.setNombreMenu(menuBean.getNombre());
			menu.setOrdenMenu(menuBean.getOrdenMenu());

			MenuQuerier.save(menu);

			// se registra la accion, opcion y opcionaccion
			if (menuBean.getAccionBeanList() != null) {
				for (int i = 0; i < menuBean.getAccionBeanList().size(); i++) {
					Accion accion = new Accion();
					accion.setPresentacionAccion("");
					accion.setMenu(menu);
					accion.setNombreAccion(menuBean.getAccionBeanList().get(i).getNombre());
					accion.setUrlAccion(menuBean.getAccionBeanList().get(i).getUrlAccion());

					AccionQuerier.save(accion);
					if (menuBean.getAccionBeanList().get(i).getOpcionesList() != null
							&& menuBean.getAccionBeanList().get(i).getOpcionesList().size() > 0) {
						for (int j = 0; j < menuBean.getAccionBeanList().get(i).getOpcionesList().size(); j++) {
							Opcion opcion = OpcionQuerier.getById(menuBean.getAccionBeanList().get(i).getOpcionesList().get(j)
									.getCodigo());

							Opcionaccion opcionAccion = new Opcionaccion();
							opcionAccion.setAccion(accion);
							opcionAccion.setOpcion(opcion);
							OpcionAccionQuerier.save(opcionAccion);

							// accion.getOpcionaccions().add(opcionAccion);
						}
					}
					// se almacena la accion

				}
			}
			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#actualizarMenu
	 * (pe.com.pacasmayo.sgcp.bean.MenuBean)
	 */
	public void actualizarMenu(MenuBean menuBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Menu menu = MenuQuerier.getById(menuBean.getCodigo());

			Menu menuPadre = MenuQuerier.getById(menuBean.getMenuPadre().getCodigo());

			menu.setMenu(menuPadre);

			Nivelmenu nivelMenu = NivelMenuQuerier.getById(menuBean.getNivelMenuBean().getCodigo());

			menu.setNivelmenu(nivelMenu);

			Privilegio privilegio = PrivilegioQuerier.getById(menuBean.getPrivilegioBean().getCodigo());

			menu.setPrivilegio(privilegio);

			menu.setDescripcionMenu(menuBean.getDescripcion());
			menu.setNombreMenu(menuBean.getNombre());
			menu.setOrdenMenu(menuBean.getOrdenMenu());

			MenuQuerier.update(menu);

			// se registra la accion, opcion y opcionaccion
			if (menuBean.getAccionBeanList() != null) {

				// eliminamos todas las acciones que estaban asociadas al menu,
				// para ingresar la nueva accion

				List<Opcionaccion> listaOpcionAccion = OpcionAccionQuerier.obtenerOpcionAccionPorMenu(menu.getPkCodigoMenu());
				if (listaOpcionAccion != null && listaOpcionAccion.size() > 0) {
					for (Opcionaccion opcionaccion : listaOpcionAccion) {
						OpcionAccionQuerier.delete(opcionaccion);
					}
				}

				List<Accion> listaAccion = AccionQuerier.obtenerAccionPorMenu(menu.getPkCodigoMenu());
				if (listaAccion != null && listaAccion.size() > 0) {
					for (Accion accion : listaAccion) {
						AccionQuerier.delete(accion);
					}
				}

				for (int i = 0; i < menuBean.getAccionBeanList().size(); i++) {
					Accion accion = new Accion();
					accion.setPresentacionAccion("");
					accion.setMenu(menu);
					accion.setNombreAccion(menuBean.getAccionBeanList().get(i).getNombre());
					accion.setUrlAccion(menuBean.getAccionBeanList().get(i).getUrlAccion());

					AccionQuerier.save(accion);

					if (menuBean.getAccionBeanList().get(i).getOpcionesList() != null
							&& menuBean.getAccionBeanList().get(i).getOpcionesList().size() > 0) {
						for (int j = 0; j < menuBean.getAccionBeanList().get(i).getOpcionesList().size(); j++) {
							Opcion opcion = OpcionQuerier.getById(menuBean.getAccionBeanList().get(i).getOpcionesList().get(j)
									.getCodigo());

							Opcionaccion opcionAccion = new Opcionaccion();
							opcionAccion.setAccion(accion);
							opcionAccion.setOpcion(opcion);
							OpcionAccionQuerier.save(opcionAccion);

							// accion.getOpcionaccions().add(opcionAccion);
						}
					}
				}
			}
			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogicFacade#eliminarMenu(pe
	 * .com.pacasmayo.sgcp.bean.MenuBean)
	 */
	public void eliminarMenu(MenuBean menuBean) throws AplicacionException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Menu menu = MenuQuerier.getById(menuBean.getCodigo());

			List<Opcionaccion> listaOpcionAccion = OpcionAccionQuerier.obtenerOpcionAccionPorMenu(menu.getPkCodigoMenu());
			if (listaOpcionAccion != null && listaOpcionAccion.size() > 0) {
				for (Opcionaccion opcionaccion : listaOpcionAccion) {
					OpcionAccionQuerier.deleteAllById(opcionaccion.getPkCodigoOpcionaccion());
				}
			}

			List<Accion> listaAccion = AccionQuerier.obtenerAccionPorMenu(menu.getPkCodigoMenu());
			if (listaAccion != null && listaAccion.size() > 0) {
				for (Accion accion : listaAccion) {
					AccionQuerier.deleteAllById(accion.getPkCodigoAccion());
				}
			}

			MenuQuerier.deleteById(menu.getPkCodigoMenu());

			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}
}
