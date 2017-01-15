package pe.com.pacasmayo.sgcp.logica.seguridad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioPrivilegioLogic.java
 * Modificado: Apr 19, 2010 4:47:16 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioPrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.GrupoUsuarioPrivilegioLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.TransactionException;
import pe.com.pacasmayo.sgcp.persistencia.querier.GrupoUsuarioPrivilegioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class GrupoUsuarioPrivilegioLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		GrupoUsuarioPrivilegioLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;

	public GrupoUsuarioPrivilegioLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.seguridad.GrupoUsuarioPrivilegioLogicFacade
	 * #getByGrupoUsuario(java.lang.Long)
	 */
	public List<GrupoUsuarioPrivilegioBean> getByGrupoUsuario(Long codigoGrupoUsuario) throws LogicaException {

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
		List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegioBeanList = null;
		// String mensajeError = "";

		try {
			grupoUsuarioPrivilegioBeanList = beanFactory.transformarListaGrupoUsuarioPrivilegio(GrupoUsuarioPrivilegioQuerier
					.getByCodigoGrupoUsuario(codigoGrupoUsuario));
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(SEGURIDAD_GRUPOUSUARIOPRIVILEGIO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return grupoUsuarioPrivilegioBeanList;
	}
}
