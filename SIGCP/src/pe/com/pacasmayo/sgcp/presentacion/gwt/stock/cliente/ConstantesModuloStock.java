package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import com.google.gwt.i18n.client.Constants;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesModuloStock.java
 * Modificado: May 26, 2011 4:14:00 PM
 * Autor: daniel.franklin
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesModuloStock extends Constants {

	public String etiquetaMedicion();

	public String medicionAprobadaOAnulada();

	public String cubicacionRegistrada();

	public String cubicacionAprobadaOAnulada();

	public String revisarDatosIngresados();

	public String tipoMedioNoSeleccionado();

	public String procesoNoSeleccionado();

	public String cargandoDatos();

	public String noExistenDatosParaMostrar();

	// Variables configurables para el calculo de la cantidad almacenadas en
	// silos

	public String codigoSiloCem1();

	public String codigoSiloCem2();

	public String codigoSiloCem3();

	public String codigoSiloCem4();

	public String codigoSiloCem5();

	public String codigoSiloCem6();

	public String codigoSiloCem7();

	public String codigoSiloCrudo1();

	public String codigoSiloCrudo2();

	public String codigoSiloCrudo3();

	public String codigoSiloCrudo4();

	public String codigoSiloCrudo5();

	public String codigoSiloCrudo6();

	public String codigoSiloHomogen1();

	public String codigoSiloHomogen2();

	public String codigoSiloHomogen3();

	public String codigoSiloHomogen7();

	public String noHaRegistradoNingunaCubicacion();
}
