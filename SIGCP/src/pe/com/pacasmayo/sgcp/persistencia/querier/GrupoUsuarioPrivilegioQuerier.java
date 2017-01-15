package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioPrivilegioQuerier.java
 * Modificado: Apr 19, 2010 5:25:20 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuarioprivilegio;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class GrupoUsuarioPrivilegioQuerier implements ConstantesMensajeAplicacion {

	private static String GRUPOUSUARIOCODIGO = "grupousuario.pkCodigoGrupousuario";

	@SuppressWarnings("unchecked")
	public static List<Grupousuarioprivilegio> getByCodigoGrupoUsuario(Long codigoGrupoUsuario) throws AplicacionException {
		try {
			List grupoUsuarioPrivilegioList = Querier.findByProperty(Grupousuarioprivilegio.class, GRUPOUSUARIOCODIGO,
					codigoGrupoUsuario);
			return grupoUsuarioPrivilegioList;
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
