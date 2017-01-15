package pe.com.pacasmayo.sgcp.util;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesReporteDinamico.java
 * Modificado: Oct 13, 2011 4:13:06 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesReporteDinamico {

	/**
	 * Constantes apliccion (uso interno)
	 */
	public static final String USER_DIR = "user.dir";
	public static final String PATRON_NUMEROS = "#,###.##";
	public static final String TEXT_HTML = "text/html";
	public static final String PATH_IMG_HEADER = "pe/com/pacasmayo/sgcp/presentacion/util/logo.jpg";
	public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
	public static final String APPLICATION_PDF = "application/pdf";

	public static final String HTML_STR = "3";
	public static final String EXCEL_STR = "2";
	public static final String PDF_STR = "1";
	public static final String COMPONENTE = "componente";

	public static final String ESPACIO_TAB = "        ";
	public static final String ESPACIO_BLANCO = " ";

	/**
	 * Constantes del usuario
	 */
	public static final String OCURRIO_UN_ERROR_GENERANDO_EL_REPORTE = ManejadorPropiedades
			.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_GENERANDO_REPORTE);

	public static final String CONSUMO_TM_HUM = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.consumo.tm.humedas");

	public static final String FACT_HUM_CONSUMO = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.factor.humedad.consumo");

	public static final String CONSUMO_TM_SECAS = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.consumo.tm.secas");

	public static final String INGRESO_TM_HUM = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingreso.tm.humedas");

	public static final String FACT_HUM_INGR = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.factor.humedad.ingreso");

	public static final String INGRESO_TM_SECAS = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingreso.tm.secas");

	public static final String SALDO_INICIAL = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.saldo.inicial");

	public static final String DESPACHO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.despacho");

	public static final String INGRESO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingreso");

	public static final String CONSUMO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.consumo");

	public static final String STOCK_FINAL = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.stock.final");

	public static final String FECHA = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.fecha");

	public static final String ALMACEN = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.almacen");

	public static final String UBICACION = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ubicacion");

	public static final String REPORTE_PARTE_DIARIO_HTML = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.nombre.reporte.html");

	public static final String REPORTE_PARTE_DIARIO_XLS = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.nombre.reporte.xls");

	public static final String REPORTE_PARTE_DIARIO_PDF = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.nombre.reporte.pdf");

	public static final String EN_PROCESO_Y_TERMINADOS = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.productos.en.proceso.y.terminados");

	public static final String MATERIA_PRIMA_MP = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.materias.primas");

	public static final String INGRESO_ACUM = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingreso.acumulado");

	public static final String CONSUMO_ACUM = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.consumo.acumulado");

	public static final String PRODUCTO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.producto");

	public static final String ERROR_NO_EXISTEN_DATOS_PARA_REPORTE = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.no.existen.datos");

	public static final String PARTE_DIARIO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario");

	public static final String LINEA_NEGOCIO = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.linea.negocio");

	public static final String PROCESO = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.proceso");

	public static final int REPORTE_PARTE_DIARIO = 1;
	public static final int REPORTE_PRODUCCION_PUESTO_TRABAJO = 2;
	public static final int REPORTE_DETALLE_PUESTO_TRABAJO = 3;
	public static final int REPORTE_CONSUMO_COMPONENTE_PRODUCTO = 4;
	public static final int REPORTE_DETALLE_PRODUCCION = 5;

	public static final String STOCK_FISICO = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.stock.fisico");
	public static final String PORCEN_VARIACION = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.procen.variacion");
	public static final String DIFERENCIA = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.diferencia");
	public static final String DIA = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.dia");
	public static final String INGRESO_HORNOSHV = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingresohhvv");
	public static final String INGREGO_TOTAL = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingresoTotal");

	public static final String INGRESO_HORNOSHH = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.dinamico.parte.diario.ingresohh");

}
