package pe.com.pacasmayo.sgcp.logica.seguridad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.PersonaBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.UsuarioLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cargo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadousuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Persona;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersonaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioGrupoUsuarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioLogic.java
 * Modificado: Jan 19, 2010 3:16:32 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class UsuarioLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, UsuarioLogicFacade,
		ConstantesFiltros {

	private static BeanFactory beanFactory;

	private Log logger = LogFactory.getLog(this.getClass());

	public UsuarioLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.UsuarioLogicFacade#obtenerUsuario
	 * (java.lang.Long)
	 */
	public UsuarioBean obtenerUsuario(Long codigoUsuario) throws LogicaException {

		String mensajeError = "";
		UsuarioBean usuarioBean = null;

		try {
			usuarioBean = beanFactory.transformarUsuario(UsuarioQuerier.getById(codigoUsuario));

		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return usuarioBean;
	}

	

	public UsuarioBean obtenerUsuarioPorLogin(String login) throws LogicaException {

		UsuarioBean usuarioBean = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();

			usuarioBean = beanFactory.transformarUsuario(UsuarioQuerier.getByLogin(login));

			return usuarioBean;
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			throw new LogicaException(e.getMensaje(), e);
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Obtener Usuarios dado un Nombre o un Apellido
	 * 
	 * @param valor
	 * @param opcion
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorNombreoApellido(String valor, String opcion) throws LogicaException {

		String mensajeError = "";
		UsuarioBean usuarioBean = null;
		List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();

		try {
			List<Usuario> listaUsuario = UsuarioQuerier.getByNombreoApellido(valor, opcion);
			if (listaUsuario != null) {
				for (Iterator<Usuario> it = listaUsuario.iterator(); it.hasNext();) {
					Usuario usuario = it.next();
					usuarioBean = beanFactory.transformarUsuario(usuario);
					listaUsuarios.add(usuarioBean);
				}
			}
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(SEGURIDAD_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return listaUsuarios;
	}

	/**
	 * Obtener Usuarios dado un Estado
	 * 
	 * @param valor
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorEstado(String valor) throws LogicaException {

		String mensajeError = "";
		UsuarioBean usuarioBean = null;
		List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();

		try {
			List<Usuario> listaUsuario = UsuarioQuerier.getByEstado(valor);
			if (listaUsuario != null) {
				for (Iterator<Usuario> it = listaUsuario.iterator(); it.hasNext();) {
					Usuario usuario = it.next();
					usuarioBean = beanFactory.transformarUsuario(usuario);
					listaUsuarios.add(usuarioBean);
				}
			}
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(SEGURIDAD_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return listaUsuarios;
	}

	/**
	 * Obtener Usuarios dado un Grupo
	 * 
	 * @param valor
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorGrupo(String valor) throws LogicaException {

		String mensajeError = "";
		UsuarioBean usuarioBean = null;
		List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();

		try {
			List<Usuario> listaUsuario = UsuarioQuerier.getByGrupo(valor);
			if (listaUsuario != null) {
				for (Iterator<Usuario> it = listaUsuario.iterator(); it.hasNext();) {
					Usuario usuario = it.next();
					usuarioBean = beanFactory.transformarUsuario(usuario);
					listaUsuarios.add(usuarioBean);
				}
			}
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(SEGURIDAD_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return listaUsuarios;
	}

	public Usuario obtenerUsuarioDataObject(Long codigoUsuario) throws LogicaException {

		String mensajeError = "";
		Usuario usuario = null;

		try {
			usuario = UsuarioQuerier.getById(codigoUsuario);

		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogicFacade#
	 * obtenerDivisiones()
	 */
	public List<UsuarioBean> obtenerUsuarios() throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			return beanFactory.transformarListaUsuario(UsuarioQuerier.getAll());
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
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogicFacade#
	 * obtenerAtributos()
	 */
	public List<UtilBean> obtenerAtributos() {
		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(UNO);
		filtro.setValor(LOGIN);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(DOS);
		filtro.setValor(NOMBRES);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(TRES);
		filtro.setValor(APELLIDOS);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(CUATRO);
		filtro.setValor(ESTADO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(CINCO);
		filtro.setValor(GRUPO);
		filtros.add(filtro);

		return filtros;
	}

	/**
	 * 
	 */
	public void ingresarUsuario(Usuario usuario) throws LogicaException {

		String mensajeError = "";
		Session session = null;
		Transaction tx = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			UsuarioQuerier.save(usuario);
			tx.commit();
		} catch (SessionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (AplicacionException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * 
	 */
	public void ingresarDatosUsuario(Usuario usuario, Persona persona, Usuariogrupousuario usuarioGrupo,
			UsuarioBean usuarioAuditoria) throws LogicaException {

		String mensajeError = "";
		Session session = null;
		Transaction tx = null;
		String usuarioLogin = null;
		String usuarioNombre = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			PersonaQuerier.save(persona);
			UsuarioQuerier.save(usuario);

			UsuarioGrupoUsuarioQuerier.save(usuarioGrupo);
			tx.commit();

			// datos auditoria
			usuarioLogin = usuario.getLoginUsuario();
			usuarioNombre = persona.getNombrePersona() + " " + persona.getApellidoPersona();
			// --------------------------------
		} catch (SessionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (AplicacionException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * @throws LogicaException
	 * @throws AplicacionException
	 */
	public void eliminarUsuario(UsuarioBean usuarioBean, UsuarioBean usuarioAuditoria) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		String usuarioLogin = null;
		String usuarioNombre = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Usuario usuario = UsuarioQuerier.getById(usuarioBean.getCodigo());

			Persona persona = PersonaQuerier.getById(usuario.getPersona().getPkCodigoPersona());
			// datos auditoria
			usuarioLogin = usuario.getLoginUsuario();
			usuarioNombre = persona.getNombrePersona() + " " + persona.getApellidoPersona();
			// --------------------------------
			Usuariogrupousuario usuarioGrupo = UsuarioGrupoUsuarioQuerier.getByUsuario(usuario);
			UsuarioGrupoUsuarioQuerier.delete(usuarioGrupo);
			UsuarioQuerier.delete(usuario);
			PersonaQuerier.delete(persona.getPkCodigoPersona());
			tx.commit();
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMessage());
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
		} catch (AplicacionException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();
			throw new LogicaException(e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		
	}

	public void actualizar(UsuarioBean usuarioBean, UsuarioBean usuarioAuditoria) throws LogicaException {
		String mensajeError = "";
		Session session = null;
		Transaction tx = null;
		String usuarioLogin = null;
		String usuarioNombre = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Usuario usuario = UsuarioQuerier.getById(usuarioBean.getCodigo());

			// Llenamos el Bean Usuario
			usuario.setLoginUsuario(usuarioBean.getLogin());
			usuario.setPasswordUsuario(usuarioBean.getPassword());
			Estadousuario estadousuario = new Estadousuario();
			estadousuario.setPkCodigoEstadousuario(usuarioBean.getEstadoUsuario().getCodigo());
			usuario.setEstadousuario(estadousuario);

			PersonaBean personaBean = usuarioBean.getPersona();
			Persona persona = usuario.getPersona();

			persona.setNombrePersona(personaBean.getNombre());
			persona.setApellidoPersona(personaBean.getApellido());
			persona.setCorreoPersona(personaBean.getCorreo());
			persona.setExtensionPersona(personaBean.getExtension());
			persona.setTelefonoPersona(personaBean.getTelefono());

			// Datos auditoria
			usuarioLogin = usuarioBean.getLogin();
			usuarioNombre = persona.getNombrePersona() + " " + persona.getApellidoPersona();
			// --------------------------
			Cargo cargo = new Cargo();
			cargo.setPkCodigoCargo(personaBean.getCargo().getCodigo());
			persona.setCargo(cargo);

			persona.setIddocumentoPersona(personaBean.getIdDocumento());

			Usuariogrupousuario usuarioGrupo = UsuarioGrupoUsuarioQuerier.getByUsuario(usuario);
			UsuarioGrupoUsuarioQuerier.delete(usuarioGrupo);

			Grupousuario grupousuario = new Grupousuario();
			grupousuario.setPkCodigoGrupousuario(usuarioBean.getGrupoUsuario().getCodigo());

			usuarioGrupo = new Usuariogrupousuario();
			usuarioGrupo.setUsuario(usuario);
			usuarioGrupo.setGrupousuario(grupousuario);

			UsuarioGrupoUsuarioQuerier.save(usuarioGrupo);

			UsuarioQuerier.update(usuario);
			PersonaQuerier.update(persona);

			tx.commit();
		} catch (SessionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (AplicacionException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		
	}

}