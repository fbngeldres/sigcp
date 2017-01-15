package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DocumentoMaterialLogicFacade.java
 * Modificado: Jun 9, 2010 11:26:45 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;

import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface DocumentoMaterialLogicFacade {


	
	/**
	 * @param documentoMaterialBean
	 * @throws LogicaException
	 */
	public abstract void insertarDocumentoMaterial(DocumentoMaterialBean documentoMaterialBean) throws LogicaException;

	/**
	 * @param codigo
	 * @throws LogicaException
	 */
	public abstract void eliminarDocumentoMaterial(Long codigo) throws LogicaException;

	/**
	 * Metodo para eliminar los movimientos que se crearon automaticos
	 * consultando el sap para una fecha determinada
	 * 
	 * @param fechaNotif
	 * @param usuario
	 */
	public abstract void eliminarDocumentosGeneradosSap(Date fechaNotif) throws LogicaException;

}