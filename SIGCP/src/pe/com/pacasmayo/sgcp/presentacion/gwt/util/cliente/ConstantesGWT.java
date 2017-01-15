package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.Date;
import java.util.LinkedHashMap;

import com.google.gwt.i18n.client.DateTimeFormat;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: Validaciones.java
 * Modificado: Apr 9, 2010 5:01:57 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

/**
 * Clase de constantes usadas en interfaces desarrolladas con SmartClientGWT.
 * Esta clase al igual que todas las ubicadas en los paquetes "cliente", son
 * traducidas a javascript por el compilador de GWT. Las clases de
 * ConstantesLogicaNegocio.java y ConstantesMensajesAplicacion.java, ya que
 * seria una recarga de trabajo importante para la aplicacion tener que cargar
 * cada constante con un llamado del servicio.
 */
public class ConstantesGWT {

	// Banda de iconos
	public static final String NOMBRE_ETIQUETA_LIMPIAR = "etiquetaLimpiar";
	public static final String NOMBRE_ETIQUETA_GRABAR = "etiquetaGrabar";
	public static final String NOMBRE_ETIQUETA_AJUSTAR = "etiquetaAprobar";
	public static final String NOMBRE_ETIQUETA_REFRESCAR = "etiquetaRefrescar";
	public static final String NOMBRE_ETIQUETA_GENERAR = "etiquetaGenerar";
	public static final String NOMBRE_ETIQUETA_CREAR = "etiquetaCrear";
	public static final String NOMBRE_ETIQUETA_CERRAR = "etiquetaCerrar";
	public static final String NOMBRE_IMAGEN_LIMPIAR = "imagenLimpiar";
	public static final String NOMBRE_IMAGEN_GRABAR = "imagenGrabar";
	public static final String NOMBRE_IMAGEN_AJUSTAR = "imagenAprobar";
	public static final String NOMBRE_IMAGEN_REFRESCAR = "imagenRefrescar";
	public static final String NOMBRE_IMAGEN_GENERAR = "imagenGenerar";
	public static final String NOMBRE_IMAGEN_DESCARGAR = "imagenDescargar";
	public static final String NOMBRE_IMAGEN_CREAR = "imagenCrear";
	public static final String NOMBRE_IMAGEN_ELIMINAR = "eliminar-";
	public static final String NOMBRE_ETIQUETA_FILTRAR = "etiquetaFiltrar";
	public static final String NOMBRE_ETIQUETA_APROBAR = "etiquetaAprobar";
	public static final String NOMBRE_ETIQUETA_ANULAR = "etiquetaAnular";
	public static final String NOMBRE_ETIQUETA_NUEVO = "etiquetaNuevo";
	public static final String NOMBRE_ETIQUETA_IMPORTAR = "etiquetaImportar";
	public static final String NOMBRE_ETIQUETA_REVERSION = "etiquetaReversion";
	public static final String NOMBRE_IMAGEN_FILTRAR = "imagenFiltrar";
	public static final String NOMBRE_IMAGEN_APROBAR = "imagenAprobar";
	public static final String NOMBRE_IMAGEN_ANULAR = "imagenAnular";
	public static final String NOMBRE_IMAGEN_NUEVO = "imagenNuevo";
	public static final String NOMBRE_IMAGEN_CERRAR = "imagenCerrar";
	public static final String NOMBRE_IMAGEN_IMPORTAR = "imagenImportar";
	public static final String NOMBRE_IMAGEN_REVERSION = "imagenReversion";

	public static final String NOMBRE_ETIQUETA_PRODUCCIONLAVADO = "etiquetaProduccionLavado";
	public static final String NOMBRE_IMAGEN_PRODUCCIONLAVADO = "imagenProduccionLavado";
	public static final String NOMBRE_ESTILO_PRODUCCIONLAVADO = "cambioproduc";
	public static final String PRODUCCIONLAVADO = "Cambiar Producci&oacute;n";

	public static final String NOMBRE_ETIQUETA_PRODUCCION = "etiquetaProduccion";
	public static final String NOMBRE_IMAGEN_PRODUCCION = "imagenProduccionLavado";
	public static final String NOMBRE_ESTILO_PRODUCCION = "produccion";
	public static final String PRODUCCION = "Cambiar Producci&oacuten";

	public static final String NOMBRE_ESTILO_FILTRAR = "filtrar";
	public static final String FILTRAR = "Filtrar";
	public static final String NOMBRE_ESTILO_APROBAR = "aprobar";
	public static final String APROBAR = "Aprobar";
	public static final String NOMBRE_ESTILO_ANULAR = "anular";
	public static final String ANULAR = "Anular";
	public static final String NOMBRE_ESTILO_NUEVO = "nuevo";
	public static final String NUEVO = "Nuevo";
	public static final String NOMBRE_ESTILO_IMPORTAR = "importar";
	public static final String IMPORTAR = "Importar";
	public static final String NOMBRE_ESTILO_REVERSION = "reversion";
	public static final String REVERSION = "Reversion";

	public static final String NOMBRE_ESTILO_LIMPIAR = "limpiar";
	public static final String LIMPIAR = "Limpiar";
	public static final String NOMBRE_ESTILO_GENERAR = "generar";
	public static final String GENERAR = "Generar";
	public static final String NOMBRE_ESTILO_CREAR = "crear";
	public static final String CREAR = "Crear";
	public static final String NOMBRE_ESTILO_GRABAR = "grabar";
	public static final String NOMBRE_ESTILO_AJUSTAR = "aprobar";
	public static final String GRABAR = "Grabar";
	public static final String AJUSTAR = "Ajustar";
	public static final String NOMBRE_ESTILO_REFRESCAR = "refrescar";
	public static final String REFRESCAR = "Refrescar";
	public static final String NOMBRE_ESTILO_CERRAR = "cerrarMenu";
	public static final String CERRAR = "Cerrar";
	public static final String NOMBRE_ESTILO_DESCARGAR = "download";
	public static final String DESCARGAR = "Descargar";

	// Etiquetas
	public static final String PROGRAMA_DE_PRODUCCION_SEMANAL = "Programa de producci&oacute;n semanal";
	public static final String ETIQUETA_FECHA_INICIO = "Fecha de inicio";
	public static final String ETIQUETA_FECHA_DE_FIN = "Fecha de Fin";
	public static final String SERVER_ERROR = "Ocurri\u00f3 un error contactando al servidor. Por favor, revise su conexi\u00f3n de red, e intente de nuevo.";
	public static final String ERROR_ID_CUBICACION_MES = "Identificador de Cubicaci\u00f3n mes inv\u00e1lido";
	public static final String DIVISION = "Divisi&oacute;n";
	public static final String LINK_CONSULTAR_PRODUCCIONES_SEMANALES = "../produccion/listarProduccionSemanal.action";
	public static final String LINK_MODIFICAR_PRODUCCIONES_SEMANALES = "../produccion/modificarProduccionSemanal.action";
	public static final String LINK_REGISTRAR_PRODUCCIONES_SEMANALES = "../produccion/registrarProduccionSemanal.action";
	public static final String LINK_CONSULTAR_CUBICACIONES_MES = "../stock/consultarCubicacionMes.action";
	public static final String LINK_INICIO = "/inicio.jsp";
	public static final String MENSAJE_REGISTRO_CUBICACION_EXITOSA = "Las cubicaciones fueron registradas exitosamente";
	public static final String MENSAJE_ERROR_REGISTRO_CUBICACION_EXITOSA = "Ocurrio un error al registrar las cubicaciones";
	public static final String MENSAJE_MODIFICACION_CUBICACION_EXITOSA = "Las cubicaciones fueron modificadas exitosamente";
	public static final String MENSAJE_ERROR_MODIFICACION_CUBICACION_EXITOSA = "Ocurrio un error al modificar las cubicaciones";

	// Constantes para apariencia visual de las interfaces
	public static String BORDE_0PX_BLANCO = "0px solid #FFFFFF";
	public static String BORDE_1PX_BLANCO = "1px solid #FFFFFF";
	public static String BORDE_3PX_BLANCO = "3px solid #FFFFFF";
	public static String COLOR_ROJO = "#DF0101";
	public static String COLOR_BLANCO = "#FFFFFF";
	public static String ESTILO_HORA_POTENCIA = "horasPuntaPotencia";
	public static String ESTILO_HORA_ENERGIA = "horasPuntaEnergia";
	public static String COLOR_NARANJA = "#FF8000";

	// Mensajes para el Programa de Produccion Semanal
	public static final String MENSAJE_ERROR_CONSULTAR_HORAS = "Ocurri\u00f3 un error al consultar las horas";
	public static final String MENSAJE_ERROR_CONSULTAR_HORAS_PUNTA = "Ocurri\u00f3 un error al consultar las horas punta";
	public static final String MENSAJE_ERROR_CONSULTAR_MEDIOS_ALMACENAMIENTO = "Ocurri\u00f3 un error al consultar los medios de almacenamiento";
	public static final String MENSAJE_ERROR_CONSULTAR_PUESTOS_TRABAJO = "Ocurri\u00f3 un error al consultar los puestos de trabajo";
	public static final String MENSAJE_ERROR_REGISTRAR_PRODUCCION_SEMANAL = "Ocurri\u00f3 un error al registrar la Producci\u00f3n Semanal";
	public static final String MENSAJE_ERROR_MODIFICAR_PRODUCCION_SEMANAL = "Ocurri\u00f3 un error al modificar la Producci\u00f3n Semanal";
	public static final String MENSAJE_EXITO_REGISTRAR_PRODUCCION_SEMANAL = "Se ha registardo con \u00e9xito la Producci\u00f3n Semanal";
	public static final String MENSAJE_EXITO_MODIFICADO_PRODUCCION_SEMANAL = "Se ha modificado con \u00e9xito la Producci\u00f3n Semanal";
	public static final String MENSAJE_ERROR_CONSULTAR_STOCK_ACTUAL = "Ocurri\u00f3 un error al consultar el Stock Actual";

	// Mapeos a propiedades en el arcivo resources.properties
	public static final String CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR = "ordenProduccion.codigo.estado.preliminar";
	public static final String ESTADO_PRODUCCION_SEMANAL_GENERADA = "produccion.produccionSemanal.estado.generado";
	public static final String ESTADO_PRODUCCION_SEMANAL_APROBADA = "produccion.produccionSemanal.estado.aprobado";
	public static final String NOMBRE_ESTADO_PRODUCCION_SEMANAL_APROBADA = "produccion.produccionSemanal.nombre.estado.aprobado";
	public static final String NOMBRE_ESTADO_PRODUCCION_SEMANAL_GENERADA = "produccion.produccionSemanal.nombre.estado.generado";
	public static final String CODIGO_ESTADO_REGISTRO_MEDICION_GENERADA = "stock.registromedicion.codigo.estado.generado";
	public static final String CODIGO_ESTADO_REGISTRO_MEDICION_APROBADA = "stock.registromedicion.codigo.estado.aprobado";
	public static final String CODIGO_ESTADO_REGISTRO_MEDICION_ANULADO = "stock.registromedicion.codigo.estado.anulado";
	public static final String CODIGO_ESTADO_NOTIFICACION_GENERADA = "notificacion.registronotificacion.codigo.estado.generado";
	public static final String ERROR_REGISTRO_NO_ENCONTRADO = "seguridad.errorOperacion.mensaje.registronoencontrado";
	public static final String PROGRAMA_PRODUCCION_SEMANAL = "produccion.produccionSemanal.titulo";
	public static final String ERROR_REGISTRO_NO_MODIFICADO = "error.produccionSemanal.modificar";
	public static final String ERROR_CAPACIDAD_SILOS_NO_ENCONTRADA = "error.capacidadMedioAlmacenamiento.noEncontrada";
	public static final String ERROR_FECHA_INICIO_PROCESO_PRODUCCION_SEMANAL = "error.produccionSemanal.fechaInicio.proceso";

	public static final String ESTADO_PARTE_DIARIO_ABIERTO = "parteDiario.estado.abierto";
	public static final Long CODIGO_TIPO_MEDIOALMACENAMIENTO_SILO = new Long(1);
	public static final Long CODIGO_TIPO_MEDIOALMACENAMIENTO_TOLVA = new Long(2);
	public static final Long CODIGO_TIPO_MEDIOALMACENAMIENTO_FIGURA = new Long(3);
	public static final Long CODIGO_TIPO_MEDIOALMACENAMIENTO_BOLSON = new Long(4);

	public static final Integer CODIGO_IDENTIFICADOR_NO_VALIDO = 0;

	public static final Long CODIGO_TIPO_HORA_PUNTA_ENERGIA = new Long(1);
	public static final Long CODIGO_TIPO_HORA_PUNTA_POTENCIA = new Long(2);

	public static final String NOMBRE_ESTADO_REGISTRO_MEDICION_APROBADA = "stock.registromedicion.nombre.estado.aprobado";
	public static final String NOMBRE_ESTADO_REGISTRO_MEDICION_GENERADO = "stock.registromedicion.nombre.estado.generado";
	public static final String NOMBRE_ESTADO_REGISTRO_MEDICION_APNULADA = "stock.registromedicion.nombre.estado.anulado";
	public static String PATRON_FECHA_APLICACION = "dd/MM/yyyy";
	public static String AM = " am";
	public static final String PM = " pm";
	public static int MAXIMO_PUESTOS_POR_PRODUCTO_POR_DIA = 5;

	// Mapeos a atributos de entidades hijas de la entidad Registromedicion de
	// hibernate
	public static final String CODIGO_PROCESO = "proceso.pkCodigoProceso";
	public static final String CODIGO_TIPO_MEDIO = "tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien";
	public static final String CODIGO_ESTADO_REGISTRO_MEDICION = "estadoregistromedicion.pkCodigoEstadoregistromedicio";
	public static final String LINK_CONSULTAR_MEDICION = "../stock/consultarMedicionPorProductos.action";

	// Mapeos de atributos de la entidad Registromedicion de hibernate
	public static final String ANNO_REGISTRO_MEDICION = "anoRegistromedicion";
	public static final String MES_REGISTRO_MEDICION = "mesRegistromedicion";
	public static final String FECHA_REGISTRO_MEDICION = "fechaRegistromedicion";

	// Notificaciones
	public static final String LINK_CONSULTAR_NOTIFICACIONES_PLANTA = "../notificacion/listarNotificacionPlanta.action";
	public static final String LINK_CREAR_NOTIFICACIONES_PLANTA = "../notificacion/nuevaNotificacionProduccionPlanta.action";
	public static final String PATRON_FECHA_ARCHIVO_VP = "notificacion.planta.patronFecha.formatoNombreArchivo.variableProduccion";
	public static final String PATRON_NOMBRE_ARCHIVO_VP = "notificacion.planta.formatoNombreArchivo.variableProduccion";
	public static final String TIPO_VARIABLE_PRODUCCION = "notificacion.planta.tipoVariableProduccion";
	public static final String ERROR_PRODUCCION_SEMANAL_FECHA_PROCESO = "Error al obtener la fecha de inicio para el proceso";
	public static final String OPERACION_EXITOSA = "exito";

	// Ajuste y Parte técnico
	public static final String LINK_CONSULTAR_AJUSTE = "../parteTecnico/consultarAjusteProduccionMes.action";
	public static final String LINK_CREAR_AJUSTE = "../parteTecnico/ajusteProduccion.action";
	public static final String RUTA_BARRA_PROGRESO_AJAX = "general.imagen.barraprogreso";
	public static final String MENSAJE_DESVIACION_HORAS_AJUSTADAS = "parteTecnico.ajusteProduccionMes.mensaje.desviacionhorasajustadas";
	public static final String GRUPO_CLINKER = "CLINKER";
	public static final String GRUPO_CARBON = "CARBON";
	public static final String GRUPO_CARBON_MIX = "CARBON.MIX";
	public static final String GRUPO_CRUDO = "CRUDO";
	public static final String GRUPO_CALIZA = "CALIZA";
	public static final String GRUPO_CAL_GRANULADA = "CAL.GRANULADA";

	public static LinkedHashMap<String, String> getHorasDia() {
		LinkedHashMap<String, String> HORAS_DIA = new LinkedHashMap<String, String>();

		HORAS_DIA.put("7", "7");
		HORAS_DIA.put("8", "8");
		HORAS_DIA.put("9", "9");
		HORAS_DIA.put("10", "10");
		HORAS_DIA.put("11", "11");
		HORAS_DIA.put("12", "12");
		HORAS_DIA.put("13", "13");
		HORAS_DIA.put("14", "14");
		HORAS_DIA.put("15", "15");
		HORAS_DIA.put("16", "16");
		HORAS_DIA.put("17", "17");
		HORAS_DIA.put("18", "18");
		HORAS_DIA.put("19", "19");
		HORAS_DIA.put("20", "20");
		HORAS_DIA.put("21", "21");
		HORAS_DIA.put("22", "22");
		HORAS_DIA.put("23", "23");
		HORAS_DIA.put("0", "24");
		HORAS_DIA.put("1", "1");
		HORAS_DIA.put("2", "2");
		HORAS_DIA.put("3", "3");
		HORAS_DIA.put("4", "4");
		HORAS_DIA.put("5", "5");
		HORAS_DIA.put("6", "6");

		return HORAS_DIA;
	}

	public static int ANNOS_ANTES = 8;
	public static int ANNOS_DESPUES = 3;

	public static String[] getAnnos() {

		String[] annos = new String[ANNOS_ANTES + ANNOS_DESPUES];
		int annoInicio = getAnnoActual() - ANNOS_ANTES;

		for (int i = 0; i < annos.length; i++) {

			annos[i] = String.valueOf(annoInicio);

			annoInicio++;
		}

		return annos;
	}

	public static int getMesActual() {

		Date fechaActual = new Date();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM");

		return Integer.parseInt(dateTimeFormat.format(fechaActual));
	}

	public static int getAnnoActual() {

		Date fechaActual = new Date();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy");

		return Integer.parseInt(dateTimeFormat.format(fechaActual));
	}

	// Constantes para la clase Textos
	public static final String CAMPO_FECHA = "fechaItem";
	public static final String ETIQUETA_FECHA = "Fecha";
	public static final String MASCARA_NUMERICA_ANNO_4_DIGITOS = "####";
	public static final String HINT_ANNO = "<nobr>(YYYY)</nobr>";
	public static final String ETIQUETA_ANNO = "A\u00f1o";

	public static final String TEXTO_FECHA_INICIO = "fechaInicio";
	public static final String TEXTO_FECHA_FIN = "fechaFin";
	public static final String TEXTO_FECHA_FILTRADO = "fechaFiltrado";
	public static final String TEXTO_ANNO = "anno";

	// Constantes para manejo de fechas
	public static enum MESES {
		Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre
	}

	public static final String CAMPO_OBLIGATORIO = "<font color=\"red\"> (*)</font>";

	public static final Long CODIGO_ESTADO_MEDICION_APROBADO = new Long(2);
	public static final Long CODIGO_ESTADO_MEDICION_GENERADO = new Long(1);
	public static final Long CODIGO_ESTADO_MEDICION_ANULADO = new Long(3);

	public static final String FORMATO_NUMERO_DOS_DECIMALES = "#,##0.00";
	public static final String FORMATO_NUMERO_TRES_DECIMALES = "#,##0.000";
	public static final String FORMATO_NUMERO_4_DECIMALES = "#,##0.0000";

	// Otras Constantes
	public static final int CERO = 0;
	public static final int UNO = 1;
	public static final int DIAS_SEMANA = 7;

	// Nombres Imagenes (iconos)
	public static final String EDITADO_AUTOMATICO = "calcular-";
	public static final String EDITADO_MANUAL = "brick_edit_16x16";
	public static final String TIPO_COMPONENTE_PRODUCTOS = "Productos";
	public static final String TIPO_COMPONENTE_HOJA_RUTA = "Hoja de Ruta";

	// Estilo de bg en rojo para las tablas de ajuste del parte tecnico
	public static final String CSS_LIGHT_RED_BG = "background_ajuste";
	public static final String CSS_LIGHT_GRAY_BG = "background_reg_alterno";
	public static final String PORCENTAJE_DESV_VALIDO = "parteTecnico.porcentaje.desv.valido";

	public static final String PARTE_DIARIO_CODIGOS_CLINKER = "codigo.productos.clinker";
	public static final String CODIGO_PRODUCTOS_CRUDO = "codigo.productos.crudo";
	public static final String CODIGO_PRODUCTOS_CLINKER_O_CALGRANULADA = "codigo.productos.clinker.calgranulada";
	public static final Long CODIGO_ESTADO_PROD_SEMANAL_GENERADO = 1l;

	public static final String CODIGO_PROCESO_CLINKERIZACION_HH = "codigo.proceso.clinkerizacionHH";
	public static final String CODIGO_PROCESO_CLINKERIZACION_HV = "codigo.proceso.clinkerizacionHV";
	public static final String CODIGO_PROCESO_MOLIENDA_CAL = "codigo.proceso.moliendaCal";
	public static final String CODIGO_PRODUCTO_CLINKER_I = "registro.cubicacion.codigo.producto.clk1";
	public static final String CODIGO_PRODUCTO_CAL_GRANULADA = "registro.cubicacion.codigo.producto.calGranulada";

	public static final String CAR_MIX_S1 = "nombre.carbon.mix.s1";
	public static final String CAR_MIX_S2 = "nombre.carbon.mix.s2";
	public static final String NOMBRE_BUNKER_6 = "producto.petroleoBunker";

	public static final long MILI_SEG_EN_1_ANIO = 31536000000L;

	public static final String CSS_OCULTAR_GRID = "ocultarFormulario";
	public static final String CSS_MOSTRAR_GRID = "mostrarFormulario";

}