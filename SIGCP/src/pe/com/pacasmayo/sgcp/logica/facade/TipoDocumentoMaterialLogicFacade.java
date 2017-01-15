package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoDocumentoMaterialLogicFacade.java
 * Modificado: Apr 28, 2010 9:28:13 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.TipoDocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface TipoDocumentoMaterialLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoDocumentoMaterialBean> obtenerTiposDeDocumentoMaterial() throws LogicaException;

	
	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract TipoDocumentoMaterialBean obtenerTipoDocumentoPorNombre(String nombre) throws LogicaException;
}