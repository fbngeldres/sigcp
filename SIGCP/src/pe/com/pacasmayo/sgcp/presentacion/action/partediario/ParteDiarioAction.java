package pe.com.pacasmayo.sgcp.presentacion.action.partediario;

import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;

import com.opensymphony.xwork2.Preparable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GestionStockAction.java
 * Modificado: Apr 14, 2010 4:57:47 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ParteDiarioAction extends AplicacionAction implements Preparable {

	private static final long serialVersionUID = 1L;

	public void prepare() throws Exception {
		asignaPrivilegios();
	}

	public String doListar() {
		return SUCCESS;
	}
}