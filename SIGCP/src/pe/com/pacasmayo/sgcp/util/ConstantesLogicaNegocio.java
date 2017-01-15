package pe.com.pacasmayo.sgcp.util;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesLogicaNegocio.java
 * Modificado: Feb 4, 2010 10:52:40 AM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesLogicaNegocio {

	public final String VERSION_INICIAL = "planificacion.version.inicial";

	/**
	 * Hoja de Ruta - Componente
	 */
	public static final String ESTADO_HOJA_RUTA_ACTIVA = "manejoMaestro.hojaRuta.estado.activa";

	/**
	 * Concepto
	 */
	public static final String CONCEPTO_CONSUMO = "manejoMaestro.concepto.consumo";
	public static final Long ID_CONCEPTO_CONSUMO = new Long(3);

	/**
	 * Producción Semanal
	 */
	public static final String CODIGO_ESTADO_PRODUCCION_SEMANAL_GENERADA = "produccion.produccionSemanal.estado.generado";
	public static final String CODIGO_ESTADO_PRODUCCION_SEMANAL_APROBADA = "produccion.produccionSemanal.estado.aprobado";
	public static final String ERROR_PRODUCCION_SEMANAL_ESTADO_INCORRECTO = "produccion.produccionSemanal.errorOperacion.mensaje.estadoIncorrecto";
	public static final String CONCEPTOS_REQUERIDOS_PRODUCCION_SEMANAL = "produccion.produccionSemanal.concepto.requerido";
	public static final String CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR = "ordenProduccion.codigo.estado.preliminar";
	public static final String CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA = "ordenProduccion.codigo.estado.liberada";

	public static final String CODIGO_ESTADO_ORDEN_PRODUCCION_CERRADA = "ordenProduccion.codigo.estado.cerrada";
	public static final String NOMBRE_ESTADO_ORDEN_PRODUCCION_LIBERADA = "estado.ordenProduccion.liberada";
	public static final String CODIGO_CONCEPTO_SALDO_INICIAL = "concepto.codigo.saldoInicial";
	public static final String CODIGO_CONCEPTO_PRODUCCION = "concepto.codigo.produccion";
	public static final String CODIGO_CONCEPTO_CONSUMO = "concepto.codigo.consumo";
	public static final String CODIGO_CONCEPTO_SALDO_FINAL = "concepto.codigo.saldoFinal";
	public static final int NUMERO_DIAS_SEMANA = 7;
	public static final String ESTADO_PRODUCCION_GENERADA = "produccion.produccionSemanal.estado.generado";
	public static final String ESTADO_PRODUCCION_APROBADA = "produccion.produccionSemanal.estado.aprobado";
	public static final String ERROR_ESTADO_INCORRECTO = "produccion.produccionSemanal.errorOperacion.mensaje.estadoIncorrecto";
	public static final String CONCEPTO_REQUERIDO_SEPARADO_COMAS = "produccion.produccionSemanal.concepto.requerido";
	public static final String NOMBRE_ESTADO_PRODUCCION_SEMANAL_GENERADA = "produccion.produccionSemanal.nombre.estado.generado";
	public static final int PRIMER_DIA_SEMANA = 0;
	public static final int PROMEDIO_DIAS = 28;

	// Constantes para mapear con el resources.properties
	// el catalogo de constantes de hora del dia

	public static final String HORA_UNO = "horaUno";
	public static final String HORA_DOS = "horaDos";
	public static final String HORA_TRES = "horaTres";
	public static final String HORA_CUATRO = "horaCuatro";
	public static final String HORA_CINCO = "horaCinco";
	public static final String HORA_SEIS = "horaSeis";
	public static final String HORA_SIETE = "horaSiete";
	public static final String HORA_OCHO = "horaOcho";
	public static final String HORA_NUEVE = "horaNueve";
	public static final String HORA_DIEZ = "horaDiez";
	public static final String HORA_ONCE = "horaOnce";
	public static final String HORA_DOCE = "horaDoce";
	public static final String HORA_TRECE = "horaTrece";
	public static final String HORA_CATORCE = "horaCatorce";
	public static final String HORA_QUINCE = "horaQuince";
	public static final String HORA_DIECISEIS = "horaDieciseis";
	public static final String HORA_DIECISIETE = "horaDiecisiete";
	public static final String HORA_DIECIOCHO = "horaDieciocho";
	public static final String HORA_DIECINUEVE = "horaDiecinueve";
	public static final String HORA_VEINTE = "horaVeinte";
	public static final String HORA_VEINTIUNO = "horaVeintiuno";
	public static final String HORA_VEINTIDOS = "horaVeintidos";
	public static final String HORA_VEINTITRES = "horaVeintitres";
	public static final String HORA_CERO = "horaCero";

	// Codigos de productos...
	public static final String CODIGO_PRODUCTO_CALIZA1_FBR = "producto.codigo.caliza1";
	public static final String CODIGO_PRODUCTO_CALIZA_CAL_FBR = "producto.codigo.caliza.cal";
	public static final String CODIGO_PRODUCTO_CAL_GRANULADA = "producto.codigo.cal.granulada";
	public static final String CODIGO_PRODUCTO_YESO = "producto.codigo.yeso";
	public static final String CODIGO_PRODUCTO_CLINKER1 = "producto.codigo.clinker1";
	public static final String CODIGO_PRODUCTO_CLINKER5 = "producto.codigo.clinker5";
	public static final String CODIGO_PRODUCTO_CEMENTO1 = "producto.codigo.cemento1";
	public static final String CODIGO_PRODUCTO_CEMENTO_1CO = "producto.codigo.cemento1Co";
	public static final String CODIGO_PRODUCTO_DIATOMITA = "producto.codigo.diatomita";
	public static final String CODIGO_PRODUCTO_CEMENTO5 = "producto.codigo.cemento5";
	public static final String CODIGO_PRODUCTO_CEMENTOHS = "producto.codigo.cementohs";
	public static final String CODIGO_PRODUCTO_CEMENTOMS = "producto.codigo.cementoms";

	public static final String CODIGO_PRODUCTO_CLZ_CAL_CANTERAS = "producto.codigo.clz.cal.canteras";
	public static final String CODIGO_PRODUCTO_CLZ_P_CAL_TOLVAS = "producto.codigo.clz.p.cal.tolvas";
	public static final String CODIGO_PRODUCTO_CLZ_TRITURAC_I = "producto.codigo.clz.triturac.i";
	public static final String CODIGO_PRODUCTO_CLZ_ADIC_MOL_HH_TRIT_SECUND_FABR = "producto.codigo.clz.adic.mol.hh.trit.secund.fabr";
	public static final String CODIGO_PRODUCTO_CLZ_I_EXP = "producto.codigo.clz.i.exp";
	public static final String CODIGO_PRODUCTO_CLZ_I_PREP = "producto.codigo.clz.i.prep";

	public static final String CODIGO_PRODUCTO_ARC_CHANC_SECUND = "producto.codigo.arc.chanc.secund";
	public static final String CODIGO_PRODUCTO_ARC_CHUNGAL_FABR = "producto.codigo.arc.chungal.fabr";
	public static final String CODIGO_PRODUCTO_ARC_PITURAS_FABR = "producto.codigo.arc.pituras.fabr";
	public static final String CODIGO_PRODUCTO_ARCILLA_RIOJA = "producto.codigo.arcilla.rioja";

	public static final String CODIGO_PRODUCTO_FE_BAJA_LEY_80 = "producto.codigo.fe.baja.ley.80";
	public static final String CODIGO_PRODUCTO_FE_ALTA_LEY_85 = "producto.codigo.fe.alta.ley.85";

	public static final String CODIGO_PRODUCTO_ARENA = "producto.codigo.arena";

	public static final String CODIGO_PRODUCTO_FLUORITA = "producto.codigo.fluorita";

	public static final String CODIGO_PRODUCTO_C_ANTR_905_00041 = "producto.codigo.c.antr.905.00041";
	public static final String CODIGO_PRODUCTO_C_ANTR_905_00101 = "producto.codigo.c.antr.905.00101";
	public static final String CODIGO_PRODUCTO_C_ANTR_905_00102 = "producto.codigo.c.antr.905.00102";

	public static final String CODIGO_PRODUCTO_PECOKE = "producto.codigo.pecoke";

	public static final String CODIGO_PROCESO_MOLIENDA_CEMENTO = "codigo.proceso.moliendaCemento";
	public static final String CODIGO_PROCESO_MOLIENDA_CRUDO = "codigo.proceso.moliendaCrudo";
	public static final String CODIGO_PROCESO_CLINKERIZACION_HH = "codigo.proceso.clinkerizacionHH";
	public static final String CODIGO_PROCESO_CLINKERIZACION_HV = "codigo.proceso.clinkerizacionHV";
	public static final String CODIGO_PROCESO_MOLIENDA_CARBON = "codigo.proceso.molienda.carbon";

	// Gestion de Stock
	public static final String TIPODOCUMENTO_INGRESO = "stock.tipoDocumento.ingreso";
	public static final String TIPODOCUMENTO_SALIDA = "stock.tipoDocumento.salida";
	public static final String TIPODOCUMENTO_TRANSFERENCIA = "stock.tipoDocumento.transferencia";

	public static final String CLASIFICACIONTIPODOCUMENTO_INGRESO = "stock.clasificacionTipoMovimiento.ingreso";
	public static final String CLASIFICACIONTIPODOCUMENTO_SALIDA = "stock.clasificacionTipoMovimiento.salida";

	public static final String MOVIMIENTO_CANTIDADTM = "stock.movimientoProducto.etiqueta.movimiento.cantidadTM";
	public static final String MOVIMIENTO_NROVIAJE = "stock.movimientoProducto.etiqueta.movimiento.nroViaje";
	public static final String  MOVIMIENTO_LOGISTICO= "stock.movimientoProducto.etiqueta.movimiento.logistico";
	public static final String FLAGINDICADOR_MANUAL = "stock.movimientoProducto.etiqueta.indicador.manual";
	public static final String FLAGINDICADOR_AUTOGENERADO = "stock.movimientoProducto.etiqueta.indicador.autogenerado";

	public static final String OPCION_1 = "1";
	public static final String OPCION_2 = "2";
	public static final String OPCION_3 = "3";
	public static final int MES = 1;
	public static final int ANIO = 2;
	public static final int DIA = 0;
	public static final int DIA_INICIO_MES = 1;
	public static final int DIA_INICIAL_CERO = 0;
	public static final String NO_SPACE = "";
	public static final int UNO = 1;
	public static final String UNIDADMEDIDA = "stock.movimientoProducto.etiqueta.movimiento.unidadMedida";
	public static final String MOVIMIENTO_NO_SELECCIONADO = "stock.movimientoProducto.error.movimiento.requerido";
	public static final String CAMPOS_LISTAR_OBLIGATORIOS_MOVIMIENTOS = "stock.movimientoProducto.error.camposListar.requerido";
	public static final String ERROR_ELIMINAR_MOVIMIENTO_MES_CERRADO = "stock.movimientoProducto.error.mesCerrado.eliminar";
	public static final String ERROR_INGRESAR_MOVIMIENTO_MES_CERRADO = "stock.movimientoProducto.error.mesCerrado.ingresar";
	public static final String PERIODO_CONTABLE_NO_EXISTE = "stock.movimientoProducto.error.periodocontable.inexistente";

	// Notificaciones de Planta
	public static final String CAMPOS_LISTAR_OBLIGATORIOS_NOTIFICACIONES = "notificacion.notificacionPlanta.error.camposListar.requerido";
	public static final String MES_ERRADO = "notificacion.notificacionPlanta.error.mes";
	public static final String ANIO_ERRADO = "notificacion.notificacionPlanta.error.anio";
	public static final String ANIO_MES_VACIO = "notificacion.notificacionPlanta.error.aniomesvacio";
	public static final String NOTIFICACION_NO_SELECCIONADA = "notificacion.notificacionPlanta.error.notificacion.requerido";
	public static final String NOTIFICACION_NO_SELECCIONADA_CERRAR = "notificacion.notificacionPlanta.error.notificacion.requerido.cerrar";
	public static final String NOTIFICACION_NO_SELECCIONADA_ELIMINAR = "notificacion.notificacionPlanta.error.notificacion.requerido.eliminar";
	public static final String ESTADONOTIFICACION_GENERADO = "notificacion.notificacionPlanta.estadoGenerado";
	public static final String ERROR_ESTADONOTIFICACION_GENERADO = "notificacion.notificacionPlanta.error.estadoGenerado";
	public static final String PRODUCTO_MATERIA_PRIMA = "notificacion.notificacionPlanta.materiaPrima";
	public static final String ESTADONOTIFICACION_APROBADO = "notificacion.notificacionPlanta.estadoAprobado";
	public static final String ESTADONOTIFICACION_CERRADO = "notificacion.notificacionPlanta.estadoCerrado";
	public static final String CODIGO_ESTADOPARTEDIARIO_CERRADO = "partediario.codigo.estadoParteDiario.cerrado";
	public static final String CODIGO_ESTADONOTIFICACION_APROBADO = "notificacion.notificacionPlanta.codigo.estadoAprobado";
	public static final String CODIGO_ESTADONOTIFICACION_CERRADO = "notificacion.notificacionPlanta.codigo.estadoCerrado";
	public static final String ERROR_COMUNICACION_SAP = "notificacion.notificacionPlanta.aprobar.error";

	// Notificacion de Cantera
	public static final String CAMPO_OBLIGATORIO_VER_NOTIFICACION = "notificacion.consultaDetalleCantera.error.CampoObligatorio";
	public static final String CAMPOS_OBLIGATORIOS_NOTIFICACIONES = "notificacion.consultaCantera.error.CamposObligatorios";
	public static final String CAMPOS_OBLIGATORIOS_NOTIFICACIONES_REGISTRO = "notificacion.registroCantera.error.CamposObligatorios";
	public static final String ESTADO_NOTIFICACION_GENERADA = "notificacion.estado.generada.nombre";
	public static final String FECHA_MAYOR_ACTUAL_INVALIDA = "fecha.mayor.fechaActual.invalida";
	public static final String DIRECTORIO_DESPACHOS_CANTERA = "notificacion.despachoCantera.directorioArchivos";
	public static final String DESPACHOS_CANTERA_CANTIDAD_ARCHIVO_BALANZA = "notificacion.despachoCantera.cantidad.balanzaArchivo";
	public static final String DESPACHOS_CANTERA_FORMATO_ARCHIVO_BALANZA = "notificacion.despachoCantera.formatoNombreArchivos";
	public static final String DESPACHOS_CANTERA_FORMATO_FECHA_ARCHIVO_BALANZA = "notificacion.despachoCantera.formatoFechaEnArchivos";
	public static final String[] NOMBRE_CELL_ARCHIVO_DESPACHO = new String[] { "Balanza", "Pesaje", "Placa", "Placa Remolque",
			"Proveedor", "Conductor", "Fecha", "Tara", "Peso Bruto", "Peso Neto", "Documento", "Producto", "Nombre Producto" };
	public static final Integer[] CELLS_ARCHIVO_DESPACHO = new Integer[] { 0, 2, 4, 5, 6, 7, 8, 9, 11 };
	public static final int CELL_CONDUCTOR_OPCIONAL_ARCHIVO_DESPACHO = 4;

	/**
	 * Programacion Produccion Semanal
	 */
	public static final String VALIDAR_PRODUCCION_SIMULTANEA_PRODUCTOS = "produccionSemenal.validarProduccionSimultaneaProductosCementos";

	public enum FORMATO_CELL_ARCHIVO {
		BASCULA, PESAJE, PLACA, PLACA_REMOLQUE, PROVEEDOR, CONDUCTOR, FECHA, TARA, PESO_BRUTO, PESO_NETO, DOCUMENTO, CODIGO_PRODUCTO, NOMBRE_PRODUCTO
	}

	public static final String PATRON_FECHA_ARCHIVO_BALANZA = "dd/MM/yyyy HH:mm:ss";

	public static final String VARIABLE_OPERACION = "notificacion.ProduccionPlanta.VariablesOperacion";
	public static final String VARIABLE_PRODUCCION = "notificacion.ProduccionPlanta.VariablesProduccion";

	/**
	 * Tipo Producto
	 */
	public static final String CODIGO_PRODUCTO_TERMINADO = "tipoProducto.codigo.productoTerminado";
	public static final String CODIGO_PRODUCTO_EN_PROCESO = "tipoProducto.codigo.productoEnProceso";
	public static final String CODIGO_PRODUCTO_MATERIA_PRIMA = "tipoProducto.codigo.materiaPrima";

	/**
	 * Variable Calidad
	 */
	public static final String CODIGO_FACTOR_HUMEDAD_INGRESO = "variableCalidad.codigo.factorHumedad.ingreso";
	public static final String CODIGO_FACTOR_HUMEDAD_CONSUMO = "variableCalidad.codigo.factorHumedad.consumo";

	/**
	 * Variables Tipo Documento Material
	 */
	public static final String TIPO_DOCUMENTO_MATERIAL_INGRESO_NOMBRE = "tipoDocumentoMaterial.nombre.Ingreso";

	/**
	 * movimiento
	 */
	public static final String TIPO_MOVIMIENTO_INGRESO_CANTERA_NOMBRE = "tipoMovimiento.ingreso.despachoCantera.nombre";
	public static final String NOMBRE_ESTADO_MOVIMIENTO_ACTIVO = "estadoMovimiento.activo.nombre";
	public static final String MOVIMIENTO_NOMBRE_ORIGEN_AUTOMATICO = "movimiento.origen.automatico.nombre";

	/**
	 * Actividad
	 */
	// Noimbre usado por la Notificacion de cantera
	public static final String ACTIVIDAD_PERFORACION = "nombre.actividad.perforacion";
	public static final String ACTIVIDAD_CARGUIO = "nombre.actividad.cargio";

	public static final String ERROR_CONSULTA_TURNO = "turno.error.consulta";

	public static final String NOMBRE_DESTINO_DESPACHO_CANTERA = "despachoCantera.nombreDestino.Fabrica";
	public static final String ERROR_UBICACION_DESTINO_DESPACHO_CANTERA_NO_ENCONTRADO = "despachoCantera.error.destinoNoEncontrado";
	public static final int LONGITUD_MAXIMA_OBSERVACION_NOTIFICACION_DIARIA = 100;

	/**
	 * PARTE TECNICO
	 */
	public static final String NOMBRE_GRUPO_CEMENTO = "parteTecnico.grupoAjuste.nombre.cemento";
	public static final String NOMBRE_GRUPO_CRUDO = "parteTecnico.grupoAjuste.nombre.crudo";
	public static final String NOMBRE_GRUPO_CLINKER = "parteTecnico.grupoAjuste.nombre.clinker";
	public static final String NOMBRE_ESTADO_AJUSTE_PRODUCTO_AJUSTADO = "parteTecnico.ajusteProducto.nombreEstado.ajustado";
	public static final String NOMBRE_ESTADO_AJUSTE_PRODUCTO_GENERADO = "parteTecnico.ajusteProducto.nombreEstado.generado";
	public static final String NOMBRE_ESTADO_AJUSTE_PRODUCTO_INICIAL = "parteTecnico.ajusteProducto.nombreEstado.inicial";

	public static final String NOMBRE_PROCESO_MOLIENDA_CEMENTO = "parteTecnico.anexo.proceso.moliendaCemento";
	public static final String NOMBRE_PROCESO_MOLIENDA_CRUDO = "parteTecnico.anexo.proceso.moliendaCrudo";
	public static final String NOMBRE_PROCESO_MOLIENDA_CLINKER = "parteTecnico.anexo.proceso.moliendaClinker";

	public static final String TIPO_COMPONENTE_HOJA_RUTA = "parteTecnico.ajusteProduccionMes.tipoComponente.hojaRuta";
	public static final String TIPO_COMPONENTE_MERMA = "parteTecnico.ajusteProduccionMes.tipoComponente.merma";
	public static final String TIPO_COMPONENTE_PRODUCTOS = "parteTecnico.ajusteProduccionMes.tipoComponente.productos";

	public static final String ESTADO_APROBADO_AJUSTEPRODUCCION = "parteTecnico.ajusteProduccionMes.estadoAprobado";

	/**
	 * Parte diario
	 */
	public static final String ESTADO_PARTE_DIARIO_ABIERTO = "abierto";
	public static final String PRODUCTO_EN_PROCESO = "producto en proceso";
	public static final String PRODUCTO_TERMINADO = "producto terminado";

	public static final String TIPO_MOV_INGRESO = "ingreso de material";
	public static final String TIPO_MOV_SALIDA = "salida de material";

	public static final String ALMACEN_MP_STR = "Alm Cen.Pmyo";
	public static final String ALAMCEN_PP_Y_PT_STR = "Alm PP PT Pmyo";

	public static final String NOMBRE_ALMACEN = "nombreAlmacen";
	public static final String NOMBRE_UBICACION = "nombreUbicacion";

	public static final String UBICACION_PLANTA_STR = "planta pacasmayo";
	public static final String PROCESO_CEMENTO = "molienda de cemento";
	public static final String PROCESO_CRUDO = "molienda de crudo";
	public static final String NOMBRE_UNIDAD_ALMACEN = "unidad.nombreUnidad";
	public static final String NOMBRE_UNIDAD_ALMACEN_PLANTA_CPSAA_STR = "PLANTA PACASMAYO - CPSAA";
	public static final String FACTOR_CONVERSION_BOLSA = "factor.conversion.bolsa";
	public static final String CODIGO_MOVIMIENTOS_ANULACION_SALIDA = "codigos.movimientos.anulacion.salida";
	public static final String CODIGO_MOVIMIENTOS_ANULACION_SALIDA1 = "codigos.movimientos.anulacion.salida1";
	public static final String CODIGO_MOVIMIENTOS_ENTREGA_RETORNO_LIBRE = "codigos.movimientos.entrega.retorno.libre";
	public static final String CODIGO_VARIABLE_CALIDAD_HUMEDAD = "codigo.variable.calidad.humedad";
	public static final String CODIGO_PRODUCTO_SAP_NO_EXISTE_EN_SGCP = "error.codigo.producto.sap.no.existe.sgcp";
	public static final String CODIGO_SAP_TIPO_MOV_NO_EXISTE = "error.codigo.tipo.movimiento.sap.no.existe.sgcp";
	public static final String CODIGO_MOVIMIENTOS_MOVIMIENTO_LOGISTICO = "codigos.movimientos.movimiento.logistico";
	// AGREGADO POR FABIAN GELDRES
	// Resuelve el caso 1495
	public static final String CODIGO_SAP_INGRESO_MERCADERIA = "codigo.sap.ingreso.mercaderia";
	public static final String PUESTO_TRABAJO_CALCULA_KCAL = "puesto.trabajo.calcula.kcal";

	public static final String CODIGO_TIPO_MEDIO_SILO = "manejoMaestro.tipoMedioAlmacenamiento.codigo.silo";

	/**
	 * Costos
	 */
	public static final String ERROR_CONSULTA_SAP_TABLAVALOR = "tablavalor.listasap.error.rfc";
	public static final String ERROR_GENERAR_CODIGO_SAP_TABLAVALOR = "tablavalor.generar.codigo.error.rfc";

	/**
	 * Usuarios
	 */
	public static final String MANEJOMAESTRO_USUARIO_ERROR_BASE = "manejoMaestro.usuario.error.base";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_NOMBRES = "manejoMaestro.usuario.error.nombres";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_APELLIDOS = "manejoMaestro.usuario.error.apellidos";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_CORREO = "manejoMaestro.usuario.error.correo";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_FORMATO_CORREO = "manejoMaestro.usuario.error.formato.correo";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_CARGO = "manejoMaestro.usuario.error.cargo";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_IDENTIFICACION = "manejoMaestro.usuario.error.identificacion";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_LOGIN = "manejoMaestro.usuario.error.login";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_PASSWORD = "manejoMaestro.usuario.error.password";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_ESTADO_USUARIO = "manejoMaestro.usuario.error.estado.usuario";
	public static final String MANEJOMAESTRO_USUARIO_ERROR_GRUPO_USUARIO = "manejoMaestro.usuario.error.grupo.usuario";

	/**
	 * Reporte Notificacion variable de produccion
	 */
	public static final int LLENAR_ENCABEZADO = 1;
	public static final int NO_LLENAR_ENCABEZADO = 0;
	public static final int LISTA_VACIA = 0;
	public static final int LISTA_LLENA = 1;

	/**
	 * Reporte ParteDiario
	 */
	public static final int PRIMERA_VUELTA = 1;
	public static final int YA_NO_ES_LA_PRIMERA_VUELTA = 0;
	public static final String ESTADO_ORDEN_PRODUCCION_LIBERADA = "estado.orden.produccion.liberada";

	/**
	 * Parte Tecnico
	 */
	public static final String NO_ES_POSIBLE_ENCONTRAR_ORDEN_PRODUC_PARTE_TEC = "parteTecnico.no.es.posible.encontrar.orden.produc";

	/**
	 * este debe debe ser usado concatenandole el codigo del almacen
	 */
	public static final String ALMACEN_PARA_UBICACION_POR_DEFECTO = "almacen.para.ubicacion.por.defecto";

	public static final String UBICACION_POR_DEFECTO_PT = "parte.diario.codigo.ubicacion.pt";
	public static final String UBICACION_POR_DEFECTO_PP = "parte.diario.codigo.ubicacion.pp";
	public static final String UBICACION_POR_DEFECTO_MP = "parte.diario.codigo.ubicacion.mp";

	public static final String ALMACEN_POR_DEFECTO_PT = "parte.diario.codigo.almacen.pt";
	public static final String ALMACEN_POR_DEFECTO_PP = "parte.diario.codigo.almacen.pp";
	public static final String ALMACEN_POR_DEFECTO_MP = "parte.diario.codigo.almacen.mp";

	/**
	 * Reporte KardexDiario BI
	 */

	public static final String CARGA_COMPLETADA_DEL_JSP = "CARGA_COMPLETADA";
	public static final String BI_TODO = "TODO";
	public static final String TABLA_KARDEX = "TABLA_KARDEX";
	public static final String TABLA_ALMACEN = "TABLA_ALMACEN";
	public static final String TABLA_MEDIO_ALMACENAMIENTO = "TABLA_MEDIO_ALMACENAMIENTO";
	public static final String TABLA_UBICACION_ORIGEN = "TABLA_UBICACION_ORIGEN";
	public static final String TABLA_UBICACION_DESTINO = "TABLA_UBICACION_DESTINO";
	public static final String REPORTE_KARDEXDIARIOBI_GUARDAR = "GUARDAR";
	public static final String REPORTE_PARTEDIARIOBI_GUARDAR = "GUARDAR";
	public static final String REPORTE_PARTETECNICOBI_GUARDAR = "GUARDAR";
	/**
	 * Procesos y producto por defecto parte diario MP
	 */
	public static final String PROCESO_RECEPCION_MINERAL_TERCEROS = "proceso.codigo.recepcion.mineral.terceros";
	public static final String PROCESO_CALIZA_FABRICA = "proceso.codigo.recepcion.mineral.propios";
	public static final String PROCESO_CALIZA_CAL_FABRICA = "proceso.codigo.recepcion.caliza.cal";

	public static final String PRODUCTO_CALIZA_FABRICA = "producto.codigo.caliza.fabrica";
	public static final String PRODUCTO_CALIZA_CAL_FABRICA = "producto.codigo.caliza.cal.fabrica";

	public static final String BOLSAS = ManejadorPropiedades.obtenerPropiedadPorClave("producto.presentacion.bolsas");
	public static final String TONELADAS = ManejadorPropiedades.obtenerPropiedadPorClave("producto.presentacion.toneladas");

	public static final String ERROR_NOTIFICACION_APROBADA = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.consultaNotificacionPlanta.error.notificacion.aprobada");
	public static final String ERROR_NOTIFICACION_CERRADO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.consultaNotificacionPlanta.error.notificacion.cerrado");
	public static final String ERROR_NOTIFICACION_CERRADO_REPROCESAR = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.notificacionPlanta.error.notificacion.cerrado");
	public static final String ERROR_NOTIFICACION_CIERRE = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.consultaNotificacionPlanta.error.notificacion.cierre");
	public static final String ERROR_NOTIFICACION_REPROCESO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.consultaNotificacionPlanta.error.notificacion.reproceso");
	public static final String ERROR_REPROCESAR_PTGENERADO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.consultaNotificacionPlanta.error.notificacion.reprocesar.ptgenerado");

	public static final String ERROR_PRODUCTO_HOJA_RUTA = ManejadorPropiedades
			.obtenerPropiedadPorClave("error.producto.no.posee.hoja.ruta.o.no.esta.activa");

	public static final String PRODUCTO_CRUDO_SIGLAS = "producto.crudo.siglas";
	public static final String PRODUCTO_CRUDO_NEGRO = "producto.crudo.negro";
	public static final String CODIGO_PRODUCTO_CRUDO_NEGRO = "codigo.producto.crudo.negro";

	public static final String PRODUCTO_PETROLEOBUNKER = "producto.petroleoBunker";

	public static final String PROCESO_CLINKERIZACION_HV = "proceso.clinkerizacionHV";
	public static final String PROCESO_CLINKERIZACION_HH = "proceso.clinkerizacionHH";
	public static final String PROCESO_MOLIENDA_CAL = "proceso.moliendaCal";
	public static final String PROCESO_CALCINACION_CAL = "proceso.calcinacion.cal";

	public static final String PROCESO_MOLIENDA_CARBON = "proceso.moliendaCarbon";
	public static final String PRODUCTO_CALIZACAL_FABRICA = "producto.calizaCalFabrica";

	public static final String PUESTO_TRABAJO_HORNOI = "puesto.trabajo.cal.hornoI";
	public static final String PUESTO_TRABAJO_HORNOWAELS = "puesto.trabajo.cal.hornoWaels";
	public static final String PUESTO_TRABAJO_HCAL = "puesto.trabajo.cal.hcal";
	public static final String NOMBRE_CLASIFICACION_MOVIMIENTO_INGRESO = "nombre.clasificacion.movimiento.ingreso";

	public static final String CODIGO_PRODUCTOS_CLINKER = "codigo.productos.clinker";
	public static final String CODIGO_PRODUCTOS_CLINKER_CUBICACION = "codigo.productos.clinker.cubicacion";

	public static final String CODIGO_TIPO_MOVIMIENTO_SAP_INGRESO = "parteTecnico.tipoMovimientoSAP.ingreso";
	public static final String CODIGO_TIPO_MOVIMIENTO_SAP_SALIDA = "parteTecnico.tipoMovimientoSAP.salida";

	public static final String TIPO_CATEG_PRODUCTO_COMBUSTIBLE = "tipo.categoria.producto.combustible";

	/**
	 * Codigos de productos los cuales debe mostrar el consumo dependiendo de lo
	 * que se consumio en el producto en proceso al que pertenecen
	 */
	public static String CODIGOS_PRODUCTOS_CONSUMO_VIENE_DE_PP = "codigo.productos.consumo.pp";

	/**
	 * Variables de calidad
	 */

	public static final String CODIGO_CARBON_MIX_S1 = "codigo.carbon.mix.s1";
	public static final String CODIGO_CARBON_MIX_S2 = "codigo.carbon.mix.s2";

	public static final String PODER_CALORIFICO_PETROLEO = "poder.calorifico.petroleo.bunker";
	public static final String DESIDAD_PETROLEO = "densidad.petroleo.bunker";
	public static final String ERROR_BALANZA_CALIZA_CAL = "error.balanza.caliza.cal";

	public static final String VAR_CALIDAD_PODER_CALORIFICO_CARBON_PETROLEO = "variable.calidad.poder.califico";
	public static final String VAR_CALIDAD_PERDIDA_FUEGO = "variable.calidad.perdidas.fuego";
	public static final String VAR_CALIDAD_PERDIDA_FUEGO_CRUDO_NEGRO = "variable.calidad.perdidas.fuego.crudo.negro";
	public static final String VAR_CALIDAD_HUMEDAD_CRUDO = "variable.calidad.humedad.crudo";
	public static final String VAR_CALIDAD_CENIZAS_CAL = "variable.calidad.cenizas.cal";
	public static final String VAR_CALIDAD_CENIZAS_CARBONES = "variable.calidad.cenizas.carbones";
	public static final String VAR_CALIDAD_PODER_CALORIFICO_CAL = "variable.calidad.poder.calofirico.cal";
	public static final String VAR_CALIDAD_PODER_CALORIFICO_PETROLEO = "variable.calidad.poder.calofirico.petroleo";

	public static final String ERROR_CREANDO_PLAN_CAPACIDAD = "manejoMaestro.plan.capacidad.error.crear";

	public static final String STOCK_CUBICACIONPRODUCTO_ESTADO_GENERADO = "stock.cubicacionProducto.estado.generado";
	public static final String STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO = "stock.cubicacionProducto.estado.aprobado";
	public static final String STOCK_CUBICACIONPRODUCTO_ESTADO_ANULADO = "stock.cubicacionProducto.estado.anulado";

	public static final String STOCK_MEDICION_ESTADO_GENERADO = "stock.medicion.codigo.estado.generado";
	public static final String STOCK_MEDICION_ESTADO_APROBADO = "stock.medicion.codigo.estado.aprobado";
	public static final String STOCK_MEDICION_ESTADO_ANULADO = "stock.medicion.codigo.estado.anulado";

	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO1 = "parteDiario.produccion.clinker.horno1";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO2 = "parteDiario.produccion.clinker.horno2";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO3 = "parteDiario.produccion.clinker.horno3";

	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL1 = "parteDiario.produccion.clinker.horno.vertical1";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL2 = "parteDiario.produccion.clinker.horno.vertical2";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL3 = "parteDiario.produccion.clinker.horno.vertical3";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL4 = "parteDiario.produccion.clinker.horno.vertical4";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL5 = "parteDiario.produccion.clinker.horno.vertical5";
	public static final String PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL6 = "parteDiario.produccion.clinker.horno.vertical6";

	/* BEGIN Mensajes para las validaciones de formularios */
	public static final String CONST_MENSAJE_ERROR = "mensaje.general.error";
	public static final String CONST_MENSAJE_OTROS_ERRORES = "mensaje.general.otros.errores";
	public static final String CONST_DIVISION = "mensaje.validacion.division";
	public static final String CONST_SOCIEDAD = "mensaje.validacion.sociedad";
	public static final String CONST_UNIDAD = "mensaje.validacion.unidad";
	public static final String CONST_LINEANEGOCIO = "mensaje.validacion.lineaNegocio";
	public static final String CONST_MES = "mensaje.validacion.mes";
	public static final String CONST_PROCESO = "mensaje.validacion.proceso";

	public static final String CONST_ACTIVIDAD = "mensaje.validacion.actividad";
	public static final String CONST_CECO_EMISOR = "mensaje.validacion.cecoemisor";
	public static final String CONST_CECO_RECPTOR = "mensaje.validacion.cecoreceptor";
	public static final String CONST_HOROINI = "mensaje.validacion.horometroinicial";
	public static final String CONST_HOROFIN = "mensaje.validacion.horometrofinal";
	public static final String CONST_HORAINI = "mensaje.validacion.horahombreinicial";
	public static final String CONST_HORAFIN = "mensaje.validacion.horahombrefinal";
	public static final String CONST_CANT = "mensaje.validacion.cantidad";

	public static final String CONST_PRODUCTO = "mensaje.validacion.producto";
	public static final String CONST_ANIO = "mensaje.validacion.anio";
	public static final String CONST_INFORME = "mensaje.validacion.informe";
	public static final String CONST_FECHAINICIO = "mensaje.validacion.fechaInicio";
	public static final String CONST_FECHAFIN = "mensaje.validacion.fechaFin";
	public static final String CONST_PRODPLANTM = "mensaje.validacion.produccion.planificadas.tm";
	public static final String CONST_PRODPLANDIAS = "mensaje.validacion.produccion.planificadas.dias";
	public static final String CONST_DOSIFICPLAN = "mensaje.validacion.dosificacion.planificada";
	public static final String CONST_PUESTOTRABAJO = "mensaje.validacion.puestoTrabajo";
	public static final String CONST_AREA = "mensaje.validacion.area";
	public static final String CONST_FECHAREGISTRO = "mensaje.validacion.fecharegistro";
	public static final String CONST_LISTAMATERIAL = "mensaje.validacion.listamaterial";
	public static final String CONST_NOMBRES = "mensaje.validacion.usuario.nombres";
	public static final String CONST_APELLIDOS = "mensaje.validacion.usuario.apellidos";
	public static final String CONST_IDENTIFICACION = "mensaje.validacion.usuario.identificacion";
	public static final String CONST_CORREO = "mensaje.validacion.usuario.correo";
	public static final String CONST_CARGO = "mensaje.validacion.usuario.cargo";
	public static final String CONST_LOGIN = "mensaje.validacion.usuario.login";
	public static final String CONST_PASSWORD = "mensaje.validacion.usuario.password";
	public static final String CONST_ESTADO_USUARIO = "mensaje.validacion.usuario.estado";
	public static final String CONST_GRUPO_USUARIO = "mensaje.validacion.usuario.grupo";
	public static final String CONST_ERROR_VAL_CORREO = "mensaje.validacion.formato.correo";
	/* END Mensajes para las validaciones de formularios */

	// Begin Constantes de la clase OrdenProduccionLogic
	public String ORDEN_LIBERADA = "ordenProduccion.liberada";
	public String ORDEN_NO_MANUAL_O_PRELIMINAR = "ordenProduccion.noManual.noPreliminar";
	public String MAS_UNA_HOJA_ACTIVA = "ordenProduccion.masUnaHoja.activa";
	public String NO_HAY_HOJA_ACTIVA = "ordenProduccion.noHayHoja.activa";
	public String NO_PLAN_ANUAL_LINNEG_ANIO = "ordenProduccion.noPlanAnual.lineaNegocio.anio";
	// End Constantes de la clase OrdenProduccionLogic

	public static final String ERROR_GENERANDO_REPORTE = "reporte.dinamico.parte.diario.error.reporte";

	public static final String PLATILLA_REPORTE_CODIGO_ESTADO_ACTIVO = "platilla.reporte.codigo.estado.activo";
	public static final String PLATILLA_REPORTE_CODIGO_ESTADO_INACTIVO = "platilla.reporte.codigo.estado.inactivo";

	public static final Long CODIGO_TURNO_DEFECTO = 1L;

	public static final String CODIGO_PRODCUTO_CLK_I = "registro.cubicacion.codigo.producto.clk1";

	public static final String CUBICAJE = "stock.nombre.reporte.cubicaje";
	public static final String LINEA_NEGOCIO_CEMENTO = "codigo.linea.negocio.cemento";

	// Reporte stock
	public static final String NOMBRE_EJEY2_REPORTE_PARTEDIARIO_GESTIONSTOCK = "reporte.partediario.gestionstock.nombre.ejey2";

	public static final String NOMBRE_SALDO_TOTAL = "reporte.partediario.gestionstock.nombre.saldoTotal";
	public static final String NOMBRE_TOTAL_PRODUCCION = "reporte.partediario.gestionstock.nombre.totalProduccion";
	public static final String NOMBRE_TOTAL_CONSUMO = "reporte.partediario.gestionstock.nombre.totalConsumo";

	public static final String TITULO_REPORTE_CONSUMO_COMBUSTIBLE = "reporte.partetecnico.titulo.consumo.combustibles";

	// Plan Capacidad
	public static final String CODIGO_ESTADO_PLAN_CAPACIDAD_GENERADO = "plan.capacidad.estado.generado";
	public static final String CODIGO_ESTADO_PLAN_CAPACIDAD_HISTORICO = "plan.capacidad.estado.historico";
	public static final String CODIGO_ESTADO_PLAN_CAPACIDAD_APROBADO = "plan.capacidad.estado.aprobado";
}