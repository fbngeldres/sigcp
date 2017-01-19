package pe.com.pacasmayo.sgcp.logica.notificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesSap.java
 * Modificado: Feb 28, 2011 1:54:47 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesSap {

	// rFc
	public static final String RFC_INGRESO = PropiedadesSap.getPropiedad("rfc.ingreso");
	public static final String RFC_CONSUMO = PropiedadesSap.getPropiedad("rfc.consumo");
	public static final String RFC_DESPACHOS = PropiedadesSap.getPropiedad("rfc.despachos");
	public static final String RFC_DESPACHOS2 = PropiedadesSap.getPropiedad("rfc.despachos2");


	public static final String MANDANTE = PropiedadesSap.getPropiedad("mandante");
	public static final String USUARIO = PropiedadesSap.getPropiedad("usuario");
	public static final String CONTRASENA = PropiedadesSap.getPropiedad("contrasena");
	public static final String IDIOMA = PropiedadesSap.getPropiedad("idioma");
	public static final String IPSAP = PropiedadesSap.getPropiedad("ip.sap");
	public static final String NUMSISTEMA = PropiedadesSap.getPropiedad("num.sistema");

	public static final String SOCIEDAD_CAMPO = PropiedadesSap.getPropiedad("sociedad.campo");
	public static final String CENTRO_CAMPO = PropiedadesSap.getPropiedad("centro.campo");
	public static final String ALMACEN_CAMPO = PropiedadesSap.getPropiedad("almacen.campo");
	public static final String FECHA_CONTABILIZACION_CAMPO = PropiedadesSap.getPropiedad("fecha.contabilizacion.campo");

	public static final String CODIGO_SAP_SOCIEDAD = PropiedadesSap.getPropiedad("codigo.sap.sociedad");
	public static final String CODIGO_SAP_CENTRO = PropiedadesSap.getPropiedad("codigo.sap.centro");
	public static final String CODIGO_SAP_ALMACEN = PropiedadesSap.getPropiedad("codigo.sap.almacen");

	public static final String FECHA_CONTABILIZACION_CAMPO_CONSUMO = PropiedadesSap
			.getPropiedad("fecha.contabilizacion.campo.consumo");
	public static final String FECHA_CAMPO = PropiedadesSap.getPropiedad("fecha.campo");
	public static final String FECHA_FIN_CAMPO = PropiedadesSap.getPropiedad("fechafin.campo");


	public static final String TABLA_SALIDA_TI_MATPRIMA = PropiedadesSap.getPropiedad("tabla.salida.ti.matprima");
	public static final String TABLA_SALIDA_GT_MSEG = PropiedadesSap.getPropiedad("tabla.salida.gt.mseg");
	public static final String TABLA_SALIDA_IT_RESULT = PropiedadesSap.getPropiedad("tabla.salida.it.result");

	public static final String TEXCAB = PropiedadesSap.getPropiedad("texcab");
	public static final String TIPO_MOVIMIENTO_CONSUMO = PropiedadesSap.getPropiedad("tipo.movimiento.consumo");
	public static final String TIPO_MOVIMIENTO_INGRESO = PropiedadesSap.getPropiedad("tipo.movimiento.ingreso");

	public static final String DESTINO_MATPROC_PCTLP = PropiedadesSap.getPropiedad("destino.matproc.pctlp");
	public static final String DESTINO_CPSAA_MATPRO = PropiedadesSap.getPropiedad("destino.cpsaa.matpro");

	public static final String RFC_TABLAVALOR_SOCIEDAD = PropiedadesSap.getPropiedad("rfc.tablavalor.sociedad");
	public static final String RFC_TABLAVALOR_MES = PropiedadesSap.getPropiedad("rfc.tablavalor.mes");
	public static final String RFC_TABLAVALOR_GRUPO = PropiedadesSap.getPropiedad("rfc.tablavalor.grupo");
	public static final String RFC_TABLAVALOR_ANIO = PropiedadesSap.getPropiedad("rfc.tablavalor.anio");
	public static final String RFC_TABLAVALOR_CENTRO = PropiedadesSap.getPropiedad("rfc.tablavalor.centro");
	public static final String RFC_TABLAVALOR_TABLA_SALIDA = PropiedadesSap.getPropiedad("rfc.tablavalor.tablasalida");

	public static String procesoRFCCorrecto = "@5B@";
	public static String procesoRFCIncorrecto = "@5C@";
}
