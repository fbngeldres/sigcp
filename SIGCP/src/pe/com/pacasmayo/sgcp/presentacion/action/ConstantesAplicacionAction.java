package pe.com.pacasmayo.sgcp.presentacion.action;

import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ActionConstantes.java
 * Modificado: Nov 25, 2009 9:40:34 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesAplicacionAction {

	/** Reedireccionamiento Globales */
	public static final String REDIRECCION_GLOBAL_LOGIN = "_login";

	/** User session key */
	static final String USERNAME = "login";
	static final String PASSWORD = "password";
	static final String USUARIO_SESION = "usuario";
	static final String HTTP_REQUEST = org.apache.struts2.StrutsStatics.HTTP_REQUEST;

	// Agregado por John Vara 05-04-2011

	static final String HTTP_RESPONSE = org.apache.struts2.StrutsStatics.HTTP_RESPONSE;

	// Fin de agregado por John Vara 05-04-2011

	static final String INTENTO_INICIO_SESION = "login";

	static final String REGISTRO = "input";
	static final String EXITO = "success";
	public static final String EXITO_OPERACION = "exito-operacion";
	public static final String FALLO_OPERACION = "fallo-operacion";
	public static final String CENTRO_COSTOS_REPETIDO = "manejoMaestro.centroCostos.error.existe";

	public static final String CENTRO_COSTOS_VALORESTADISTICO_REPETIDO = "manejoMaestro.centroCostos.error.valorestadistico.existe";
	public static final String CENTRO_COSTOS_CODIGOSAP_REPETIDO = "manejoMaestro.centroCostos.error.codigosap.existe";

	public static final String GRUPO_USUARIO_ADMIN = "Administrador";
	public static final String WEB_SERVICE = "seguridad.ruta.webservice";
	public static final String DOMINIO_PACASMAYO = "seguridad.ruta.nombredominio";

	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	public static final String ESTADO_PRODUCCION_SEMANAL_GENERADA = "produccion.produccionSemanal.estado.generado";
	public static final String ESTADO_PRODUCCION_SEMANAL_APROBADA = "produccion.produccionSemanal.estado.aprobado";
	public static final String ERROR_PRODUCCION_SEMANAL_ESTADO_INCORRECTO = "produccion.produccionSemanal.errorOperacion.mensaje.estadoIncorrecto";
	public static final String CONCEPTOS_REQUERIDOS_PRODUCCION_SEMANAL = "produccion.produccionSemanal.concepto.requerido";

	public int ANIOS_A_FUTURO_PLAN_ANUAL = 10;
	public int ANIOS_A_FUTURO_PRODUCCION_SEMANAL = 5;

	public static int ANNOS_ANTES = 1;
	public static int ANNOS_DESPUES = 3;

	static final String CODIGO_POR_DEFECTO_DIVISION = "division.codigo.pordefecto";
	static final String CODIGO_POR_DEFECTO_SOCIEDAD = "sociedad.codigo.pordefecto";
	static final String CODIGO_POR_DEFECTO_UNIDAD = "unidad.codigo.pordefecto";

	static final String CODIGO_POR_DEFECTO_ESTADO_GENERADO = "3";
	static final String CODIGO_POR_DEFECTO_ESTADO_ACTIVO = "1";
	static final String CODIGO_POR_DEFECTO_ESTADO_INACTIVO = "2";

	static final String CODIGO_POR_DEFECTO_TIPO_COMPONENTE = "1";
	public static final long CODIGO_POR_DEFECTO_ESTADO_PRODUCTO = 1;

	static final String CODIGO_POR_DEFECTO_UNIDAD_MEDIDA = "1";

	static final Integer NUMERO_COLUMNAS_INICIAL_REPORTE_ECS = 16;

	public static final String EXITO_OPERACION_REALIZADA = "manejoMaestros.operacion.exito";
	public static final String EXITO_OPERACION_NO_REALIZADA = "manejoMaestros.operacion.noexito";

	public static final String PUESTO_TRABAJO_NOMBRE_EXISTE = "manejoMaestro.puestoTrabajo.nombre.existe";
	public static final String PUESTO_TRABAJO_SIGLAS_EXISTE = "manejoMaestro.puestoTrabajo.siglas.existe";

	public static final String DIVISION_CODIGO_SAP_EXISTE = "manejoMaestro.division.codigoSAP.existe";

	public static final String SOCIEDAD_CODIGO_SAP_EXISTE = "manejoMaestro.sociedad.codigoSAP.existe";
	public static final String SOCIEDAD_NOMBRE_EXISTE = "manejoMaestro.sociedad.nombre.existe";

	public static final String PROCESO_ASOCIACION_LINEA_NEGOCIO_TIPO_PRODUCTO_EXISTE = "manejoMaestro.proceso.asocioacionLineaNegocioTipoProducto.existe";

	public static final String PRODUCTO_NOMBRE_EXISTE = "manejoMaestro.producto.nombre.existe";
	public static final String PRODUCTO_CODIGO_SAC_EXISTE = "manejoMaestro.producto.codigoSAC.existe";
	public static final String PRODUCTO_SIGLAS_EXISTE = "manejoMaestro.producto.siglas.existe";
	public static final String PRODUCTO_ACTIVO_HOJA_RUTA = "manejoMaestro.producto.activo.hojaRuta";

	public static final String VARIABLEVARIACION_NOMBREVACIO = "manejoMaestro.variableVariacion.nombreVacio";

	public static final String PRODUCTOVARIABLEVARIACION_VALORVACIO = "manejoMaestro.productoVariableVariacion.valorVacio";
	public static final String PRODUCTOVARIABLEVARIACION_FECHAVACIA = "manejoMaestro.productoVariableVariacion.fechaVacia";
	public static final String PRODUCTOVARIABLEVARIACION_EXISTENTE = "manejoMaestro.productoVariableVariacion.fechaExistente";

	public static final String LINEA_NEGOCIO_NOMBRE_EXISTE = "manejoMaestro.lineaNegocio.nombre.existe";

	public static final String RENDIMIENTOTERMICO_PUESTOTRABAJOVACIO = "manejoMaestro.rendimientoTermico.puestoTrabajoVacio";
	public static final String RENDIMIENTOTERMICO_PRODUCTOVACIO = "manejoMaestro.rendimientoTermico.productoVacio";
	public static final String RENDIMIENTOTERMICO_KCAL1VACIO = "manejoMaestro.rendimientoTermico.kcal1Vacio";
	public static final String RENDIMIENTOTERMICO_KCAL2VACIO = "manejoMaestro.rendimientoTermico.kcal2Vacio";
	public static final String RENDIMIENTOTERMICO_KCAL1MAYOR = "manejoMaestro.rendimientoTermico.kcal1mayor";

	public static final String ESTADO_ACTIVO = "Activo";
	public static final String ESTADO_INACTIVO = "Inactivo";

	public static final String AREA_NOMBRE_EXISTE = "manejoMaestro.area.nombre.existe";

	public static final String URL_BASE_REPORTE_ECS_GENERADO = "reporteECS.urlbase.reporteECSGenerado";

	public static final String URL_BASE_REPORTE_ECS_PROCESADO = "reporteECS.urlbase.reporteECSProcesado";

	public static final String VERSION_INICIAL_PLAN = "1.0";
	
	//agregados
	public static final String ERROR_DIVISION = "formatoreporte.error.division.requerido";
	public static final String ERROR_SOCIEDAD = "formatoreporte.error.sociedad.requerido";
	public static final String ERROR_UNIDAD = "formatoreporte.error.unidad.requerido";
	public static final String ERROR_LINEANEGOCIO = "formatoreporte.error.lineanegocio.requerido";

	static final long CODIGO_POR_DEFECTO_ESTADO_ORDEN_PRODUCCION = 1;
	static final long CODIGO_HOJA_RUTA_ACTIVA = 1;
	static final long CODIGO_POR_DEFECTO_CONCEPTO_CONSUMO = 3;
	static final long CODIGO_POR_DEFECTO_CONCEPTO_PRODUCCION = 2;
	static final long CODIGO_ESTADO_PLAN_ANUAL_GENERADO = 1;
	static final long CODIGO_ESTADO_PLAN_ANUAL_APROBADO = 2;
	static final long CODIGO_ESTADO_PLAN_ANUAL_HISTORICO = 3;
	static final long CODIGO_UNIDAD_MEDIDA_TONELADA = 2;
	static final long CODIGO_CERO = 0;
	static final long CODIGO_PLANTILLA_ESTADO_ACTIVO = 1;

	public final long CODIGO_TIPO_CLASIFICACION_MOVIMIENTO_INGRESO = 1;
	public final long CODIGO_TIPO_CLASIFICACION_MOVIMIENTO_SALIDA = 2;
	public final long CODIGO_TIPO_CLASIFICACION_MOVIMIENTO_TRASLADO = 3;

	public final long CODIGO_TIPO_CAPACIDAD_OPERATIVA_TONELADAS = 1;
	public final long CODIGO_TIPO_CAPACIDAD_OPERATIVA_DIAS = 2;

	public final long CODIGO_ESTADO_PRODUCTO_INACTIVO = 2;
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_KARDEXDIARIO_BI = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteKardexDiarioBi.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_PARTEDIARIO_BI = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteParteDiarioBi.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_VARIABLES_PRODUCCION = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteVariables.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_RESUMEN_NOTIFICACION = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteResumenNotificacion.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_PARTE_DIARIO = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteParteDiario.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_REPORTE_PARTE_TECNICO = "var currentTime = new Date()\n"
			+ "var URL = '../../../scripts/reporteParteTecnicoBi.js?time=' + currentTime.getSeconds()\n"
			+ "$.getScript(URL , function () {insertarBotones(Botones);});\n" + "}\n" + "}\n" + "load();\n" + "" + "</script>";
	public static final String SCRIPT_SETEAR_PLUGIN_PARA_AJAX = "load = function() {\n"
			+ "load.getScript(\"../../../scripts/jquery-1.4.min.js\");\n" + "load.tryReady(0);\n" + "}\n"
			+ "load.getScript = function(filename) {\n" + "var fileref = document.createElement('script');\n"
			+ "fileref.setAttribute(\"type\",\"text/javascript\");\n" + "fileref.setAttribute(\"src\", filename);\n"
			+ "if (typeof fileref!=\"undefined\")\n" + "document.getElementsByTagName(\"head\")[0].appendChild(fileref);\n"
			+ "}\n" + "load.tryReady = function(time_elapsed) {\n" + "if (typeof $ == \"undefined\") {\n"
			+ "if (time_elapsed <= 5000){\n" + "setTimeout(\"load.tryReady(\" + (time_elapsed + 200) + \")\",20);\n"
			+ "} else {\n" + "alert(\"No se Logro Cargar Botones\")}}\n " + "else {\n";

	public static final String SCRIPT_HTML_REPORTE_PLAN_PRODUCCION = "<link rel=\"stylesheet\" type=\"text/css\" href=\"../../../css/SGCP-toolTip.css\"> \n"
			+ "<script type='text/javascript'src='../../../scripts/jquery-1.4.min.js'></script>"
			+ "<script type='text/javascript'src='../../../scripts/tooltip/jquery.tools.min.js'></script>"
			+ "<script type='text/javascript'src='../../../scripts/reportePlanProduccion.js'></script></head>";
	public static final String SCRIPT_HTML_REPORTE_PARTE_DIARIO = "<link src='../../../css/SGCP-estilo.css' rel='stylesheet' type='text/css' />\n"
			+ "<script type='text/javascript'src='../../../scripts/jquery-1.4.min.js'></script>"
			+ "<script type='text/javascript'src='../../../scripts/reportePlanProduccion.js'></script></head>";
	public static final String BORRAR_TEMPORAL = "BORRAR_TEMPORAL";
	public final long CODIGO_TIPO_OBJETO_COSTOS_CENTRO_COSTOS = 1;
	public final long CODIGO_TIPO_OBJETO_COSTOS_ORDENES = 2;
	public final long NUMERO_DE_MESES = 12;
	public final int CANTIDAD_DE_MESES = 12;

	public static final Short MES_CORTE_DEFECTO = 1; // (Enero por defecto)
	public static final int FICHA_ACTIVA_DEFECTO = 1;
	public static final int FICHA_ACTIVA_CONCEPTO = 2;
	public static final String ESTADO_PLAN_GENERADO = "planificacion.estado.generado";

	public static final String PLAN_ANUAL_FILTRO_TIPO_ANO = "1";
	public static final String PLAN_ANUAL_FILTRO_TIPO_LINEANEGOCIO = "2";
	public static final String PLAN_ANUAL_FILTRO_TIPO_ESTADO = "3";

	public static final int PLAN_ANUAL_PRIMER_TAB = 1;
	public static final int PLAN_ANUAL_SEGUNDO_TAB = 2;
	public static final int PLAN_ANUAL_TERCER_TAB = 3;
	public static final String TAB_ACTIVO = "tab_current";
	public static final String TAB_OCULTO = "tab";

	public static final String FILTRO_UTILBEAN_CODIGO = "Código";

	public static final String CANTIDAD_CERO = "0";

	// Begin FABIAN GELDRES
	public static final String STRSESIONLST = "lstLista";
	public static final String CODIGO_PRODUCTO_TERMINADO = "004";
	public static final String CODIGO_VENTA = "601";
	// END FABIAN GELDRES

	public static final int CONCEPTOS_MOSTRAR = 4;

	// Begin Constantes de la clase NotificacionServiceImpl
	public String NOMBRE_ESTADO_NOTIFICACION = "Generada";
	public String CODIGO_PUESTOTRABAJO = "puestotrabajo.pkCodigoPuestotrabajo";
	public String CODIGO_COMPONENTE = "componente.pkCodigoComponente";
	// End Constantes de la clase NotificacionServiceImpl

	// Inicio de Stephany Lopez
	public Long TABLAVALOR_CODIGO_VENTA = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.generar.tipoconsumocomponente.venta.codigo"));
	public Long TABLAVALOR_CODIGO_COMPONENTE = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.generar.tipoconsumocomponente.componente.codigo"));
	public Long TABLAVALOR_CODIGO_CONSUMOSINTERNO = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.generar.tipoconsumocomponente.consumosinterno.codigo"));
	public String TABLAVALOR_DESCRIPCION_COMPONENTE = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.tipoconsumocomponente.componente.descripcion");
	public String TABLAVALOR_DESCRIPCION_CONSUMO = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.tipoconsumocomponente.consumo.descripcion");
	// public String LISTA_CONFIGURACION_TRASLADOS =
	// ManejadorPropiedades.obtenerPropiedadPorClave("lista.configuracion.productos.trasladados");
	// public String LISTA_CONFIGURACION_GRUPOS =
	// ManejadorPropiedades.obtenerPropiedadPorClave("lista.configuracion.obtener.grupos");
	public String GRUPO_TABLAVALOR_CODIGO_CLINKER = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.grupo.clinker.codigo");
	public String GRUPO_TABLAVALOR_CODIGO_PT = ManejadorPropiedades.obtenerPropiedadPorClave("tablavalor.grupo.pt.codigo");
	public String GRUPO_TABLAVALOR_CODIGO_CAL = ManejadorPropiedades.obtenerPropiedadPorClave("tablavalor.grupo.cal.codigo");
	// Fin de Stephany Lopez

	// Nuevas variables para Generar Parte Diario - Stephany
	public String CABECERA_SALDO_INICIAL = "Saldo Inicial";
	public String CABECERA_PRODUCCION = "Produccion";
	public String CABECERA_CONSUMO = "Consumo";
	public String CABECERA_SALDO_FINAL = "Saldo Final";

	public String CABECERA_TMPH = "TMPH";
	public String CABECERA_HR = "HT";
	// Fin variables para Generar Parte Diario

	// Nuevas variables para Reporte Parte Diario - Stephany
	public static final String CABECERA_NOMBRE_FACTOR = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.partediario.produccion.nombre.factor");

	public static final String ERROR_CLASIFICACION_NULA = "error.clasificacion.null";

	public static final String ERROR_ENVIO_SAP = "errorOperacion.mensaje.errorRFC";

	// plantilla consumo

	public static final String PLANTILLA_CONSUMO_ERROR_INSERTAR = "mensaje.plantillaconsumo.operacion.agregar.fallida";

	// Tabla Valor
	public static final String CODIGO_CENTRO_DEFAULT = "2004";
}
