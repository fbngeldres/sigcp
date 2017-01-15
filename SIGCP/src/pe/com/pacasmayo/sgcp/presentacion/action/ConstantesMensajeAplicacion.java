package pe.com.pacasmayo.sgcp.presentacion.action;

import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesMensajeAplicacion.java
 * Modificado: Nov 30, 2009 9:59:20 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesMensajeAplicacion {

	public static final String OPERACION_REALIZADA_EXITOSAMENTE = "manejoMaestros.operacion.exito";

	static final String ERROR_PARAMETRO_INVALIDO = "errorOperacion.mensaje.parametroinvalido";
	static final String ERROR_FATAL_FALLO = "errorOperacion.mensaje.fatalfallo";
	static final String ERROR_QUERY_FALLO = "errorOperacion.mensaje.queryfallo";
	static final String ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT = "errorOperacion.mensaje.resultadoNoUnnico";
	static final String ERROR_OBJETO_NO_VALIDO = "errorOperacion.mensaje.objetonulo";
	static final String ERROR_VIOLACION_INTEGRIDAD_BD = "errorOperacion.mensaje.violacionintegridadbd";
	static final String ERROR_OBJETO_NO_ELIMINADO = "errorOperacion.mensaje.objetonoeliminado";
	static final String ERROR_OBJETO_NO_ENCONTRADO = "errorOperacion.mensaje.objetonoencontrado";
	static final String ERROR_USO_SESION_INAPROPIADA = "errorOperacion.mensaje.usosesioninapropiada";
	static final String ERROR_TRANSACCION_FALLO = "errorOperacion.mensaje.transaccionfallo";
	static final String ERROR_COMUNICACION_FALLO = "errorOperacion.mensaje.comunicacionfallo";
	static final String ERROR_HIBERNATE = "errorOperacion.mensaje.hibernate";
	static final String ERROR_NUMERO_DE_SERIES = "errorOperacion.mensaje.numeroseriegraficos";
	static final String ERROR_ESTADO_PLAN_INVALIDO = "planificacion.error.estadoplan.invalido";
	static final String ERROR_CONSULTA_LISTA = "errorOperacion.mensaje.listanoencontrada";
	static final String ERROR_FALLA_CONSULTA_LISTA = "errorOperacion.mensaje.fallaconsultalista";
	static final String ESTADO_PLAN_ANUAL_APROBADO = "planificacion.estado.aprobado";
	static final String PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO = "planificacion.codigo.estado.plan.aprobado";
	static final String PLANIFICACION_CODIGO_ESTADO_PLAN_HISTORICO = "planificacion.codigo.estado.plan.historico";
	static final String ESTADO_ORDEN_PRODUCCION_PRELIMINAR = "estado.ordenproduccion.preliminar";
	static final String ERROR_ORDEN_PRODUCCION_CONSULTA = "ordenproduccion.error.consulta";
	static final String ERROR_ORDEN_PRODUCCION_USUARIO_REG = "ordenproduccion.error.usuarioRegistro";
	static final String ERROR_ORDEN_PRODUCCION_USUARIO_APR = "ordenproduccion.error.usuarioAprobacion";
	static final String ERROR_ORDEN_PRODUCCION_REGISTRAR = "ordenproduccion.error.guardar";
	static final String ERROR_ORDEN_PRODUCCION_REQUERIDA = "error.ordenProduccion.requerida";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_DIVISION = "ordenproduccion.error.cargar.division";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_SOCIEDAD = "ordenproduccion.error.cargar.sociedad";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_UNIDAD = "ordenproduccion.error.cargar.unidad";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_LINEA = "ordenproduccion.error.cargar.linea";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_PROCESO = "ordenproduccion.error.cargar.proceso";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_PRODUCTO = "ordenproduccion.error.cargar.producto";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_OPERACIONES = "ordenproduccion.error.cargar.operaciones";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_COMPONENTES = "ordenproduccion.error.cargar.componentes";
	static final String ERROR_ORDEN_PRODUCCION_CARGA_DATOS_ORDEN_PRODUCCION = "ordenproduccion.error.cargar.datosordenproduccion";
	static final String ERROR_ORDEN_PRODUCCION_VALIDACION = "ordenproduccion.error.validar.ordenproduccion";
	static final String ORDEN_PRODUCCION_ES_PARA_UN_PRODUCTO = "ordenproduccion.error.ordenproduccionproducto";
	static final String ORDEN_PRODUCCION_FALTA_FACTORES_DOSIFICACION_TASA_REAL = "ordenproduccion.error.fatores.dosificacion.tasa.real";

	static final String ERROR_REQUIERE_PRODUCTO_ANIO_MES = "ordenproduccion.error.requiere.productoaniomes";
	static final String ERROR_ORDEN_PENDIENTE_POR_GENERAR = "ordenproduccion.error.ordenpendientegenerar";
	static final String ERROR_ORDEN_DE_PRODUCCION_ACTIVA_PRODUCTO_MES_ANIO = "ordenproduccion.error.ordenactiva";
	static final String ERROR_PRODUCTO_SIN_HOJA_DE_RUTA_ACTIVA = "ordenproduccion.error.productosinhojaactiva";
	static final String ERROR_MES_CORTE_INVALIDO = "planificacion.error.mesCorte.invalido";
	static final String ERROR_PLAN_ANUAL_NULO = "planificacion.error.plananual.nulo";
	static final String ERROR_LINEA_NEGOCIO_NULO = "planificacion.error.lineaNegocio.nula";
	static final String ERROR_VERSION_NULA = "planificacion.error.version.nula";
	static final String ERROR_PLAN_ANUAL_NO_ENCONTRADO = "planificacion.error.plananual.noencontrado";
	public static final String ERROR_OBJETO_COSTOS_NO_ENCONTRADO = "manejoMaestro.centroCostos.RegistroDistribuible.error.objetoCostosNoEncontrado";
	public static final String ERROR_PLAN_ANUAL_EXISTENTE = "Ya existe una Planificación para la Línea de Negocio y año seleccionados.";
	static final String ERROR_USUARIO_INVALIDO = "seguridad.inicio.mensaje.login.validacion";
	static final String ERROR_LOGIN_PASSWORD_REQUERIDO = "seguridad.inicio.error.login.password.requerido";
	static final String ERROR_COMUNICACION_WEB_SERVICE_FALLIDA = "seguridad.errorOperacion.mensaje.registronoencontrado";
	static final String ERROR_OBTENER_ORDEN_PRODUCCION = "ordenproduccion.error.consulta.orden";
	static final String ERROR_APROBAR_AJUSTEPRODUCCION = "errorOperacion.mensaje.aprobarAjusteProduccion";
	static final String ERROR_APROBAR_AJUSTEPRODUCCION_INCIAL = "errorOperacion.mensaje.aprobarAjusteProduccion.inicial";
	static final String MENSAJE_APROBACION_REGISTROMEDICION_EXITOSA = "Se aprob\u00f3 con \u00e9xito el registro de medici\u00f3n.";

	static final String MENSAJE_OPERACION_EXISTE = "Ya existe una Operación con el mismo Nombre, Actividad, Puesto de Trabajo.";
	static final String MENSAJE_NOMBRE_OPERACION_VACIO = "Debe ingresar el nombre de la Operación.";
	static final String MENSAJE_ACTIVIDAD_VACIO = "Debe seleccionar una Actividad.";
	static final String MENSAJE_PUESTO_TRABAJO_VACIO = "Debe seleccionar un Puesto de Trabajo.";
	static final String MENSAJE_COMPONENTE_VACIO = "Debe seleccionar componentes.";

	static final String MENSAJE_UNIDAD_EXISTE = "Ya existe una Unidad con el mismo Nombre.";
	static final String ERROR_UNIDAD_REQUERIDA = "manejoMaestro.error.unidad.requerido";
	static final String ERROR_PROCESO_REQUERIDO = "manejoMaestro.error.proceso.requerido";
	static final String ERROR_SOCIEDAD_REQUERIDO = "manejoMaestro.error.sociedad.requerido";
	static final String ERROR_LINEANEGOCIO_REQUERIDO = "manejoMaestro.error.lineaNegocio.requerido";
	static final String ERROR_PRODUCTO_REQUERIDO = "manejoMaestro.error.producto.requerido";
	static final String ERROR_TIPODOCUMENTO_REQUERIDO = "stock.movimientoProducto.error.tipoDocumento.requerido";
	static final String ERROR_ALMACEN_REQUERIDO = "manejoMaestro.error.tipoDocumento.requerido";
	static final String ERROR_UBICACION_REQUERIDO = "manejoMaestro.error.ubicacion.requerido";
	static final String ERROR_TIPOMOVIMIENTO_REQUERIDO = "manejoMaestro.error.tipoMovimiento.requerido";
	static final String ERROR_CANTIDADTM_REQUERIDO = "stock.movimientoProducto.error.cantidadTM.requerido";
	static final String ERROR_NROVIAJE_REQUERIDO = "stock.movimientoProducto.error.nroViaje.requerido";
	static final String ERROR_FACTORVOLQUETE_REQUERIDO = "stock.movimientoProducto.error.factorCapacidadVolquete.requerido";
	static final String ERROR_ACTIVIDAD_REQUERIDA = "error.actividad.requerida";
	static final String ERROR_ORIGEN_REQUERIDO = "error.origen.requerido";
	static final String ERROR_DESTINO_REQUERIDO = "error.destino.requerido";
	static final String ERROR_TURNO_REQUERIDO = "error.turno.requerido";
	static final String ERROR_HORA_REQUERIDA = "error.Hora.noValida";
	static final String ERROR_HOROMETRO_INICIAL_REQUERIDO = "error.horometroInicial.requerido";
	static final String ERROR_HOROMETRO_FINAL_REQUERIDO = "error.HorometroFinal.requerido";
	static final String ERROR_HOROMETRO_FINAL_INVALIDO = "error.HorometroFinal.mayor";
	static final String ERROR_OBSERVACION_INVALIDO = "error.observacion.invalida";

	static final String MENSAJE_HOJA_RUTA_PRODCUTO_EXISTE_1 = "Un Producto solo se puede asignar a una Hoja de Ruta. Ya existe una Hoja de Ruta para el Producto ";
	static final String MENSAJE_HOJA_RUTA_PRODCUTO_EXISTE_2 = ".";
	static final String MENSAJE_HOJA_RUTA_NOMBRE_EXISTE = "Ya existe una Hoja de Ruta con el mismo Nombre. Por favor, ingrese otro Nombre.";
	static final String MENSAJE_VALIDACION_MINIMO_MAXIMO = "El Valor Mínimo no debe ser mayor al Valor Máximo.";
	static final String ERROR_HOJA_RUTA_NOMBRE_REQUERIDO = "Debe ingresar el Nombre de la Hoja de Ruta.";

	static final String MENSAJE_ALMACEN_NOMBRE_EXISTE = "Ya se encuentra registrado un Almacén con este Nombre para la Unidad Seleccionada.";
	static final String MENSAJE_ALMACEN_CODIGO_SAP_EXISTE = "Ya se encuentra registrado un Almacén con este Código SAP.";

	static final String ERROR_MEDIO_ALMACENAMIENTO_PRODUCTO_REQUERIDO = "error.medioAlmacenamiento.procesoProducto.requerido";

	// Errores rFc

	public static final String REPORTEECS_ARCHIVOECS_NO_ENCONTRADO_EN_RUTA = "reporteECS.errorOperacion.mensaje.archivoecsnoencontradoenruta";
	public static final String REPORTEECS_ARCHIVOECS_NO_PUEDE_SER_LEIDO = "reporteECS.errorOperacion.mensaje.archivoecsnopuedeserleido";
	public static final String REPORTEECS_ARCHIVOECS_NO_PUEDE_SER_DECODIFICADO = "reporteECS.errorOperacion.mensaje.archivoecsnopuedeserdecodificado";
	public static final String REPORTEECS_ARCHIVOECS_NO_CONTIENE_DATOS_REQUERIDOS = "reporteECS.errorOperacion.mensaje.archivoecsnocontienedatosrequeridos";
	public static final String REPORTEECS_ARCHIVOECS_NO_ENCUENTRA_HORAS_EN_BD = "reporteECS.errorOperacion.mensaje.archivoecsnolocalizahorasBD";

	public static final String ERROR_FORMATO_NUMERO = "Error en el formato de los números.";

	public final String ERROR_CLASE_MOVIMIENTO_CODIGO_SAP_EXISTE = "Ya se encuentra registrada una Clase de Movimiento con el Código SAP ";
	public final String ERROR_CLASE_MOVIMIENTO_NOMBRE_EXISTE = "Ya se encuentra registrada una Clase de Movimiento con el Nombre ";

	public static final String ERROR_OBTENER_PROPIEDAD = "No se encontró una propiedad. Consulte con el Administrador del Sistema.";

	public static final String ERROR_PERIODO_CONTABLE_NO_ENCONTRADO = "periodoContable.error.noEncontrado";

	public static final String ERROR_TIPO_DOCUMENTO_MATERIAL_NO_ENCONTRADO = "tipoDocumentoMaterial.error.noEncontrado";

	public static final String ERROR_UBICACION_NO_ENCONTRADA = "ubicacion.error.noEncontrada";
	public static final String ERROR_UNIDAD_MEDIDA_NO_ENCONTRADA = "unidadMedida.error.noEncontrada";
	public static final String ERROR_TIPO_MOVIMIENTO_NO_ENCONTRADO = "tipoMovimiento.error.noEncontrado";
	public static final String ERROR_ESTADO_MOVIMIENTO_NO_ENCONTRADO = "estadoMovimiento.error.noEncontrado";

	public static final String ERROR_MOVIMIENTOS_DESPACHO_CREADOS = "despachoCantera.error.movimientosCreados";
	public static final String ERROR_DESPACHO_CREADOS = "despachoCantera.error.despachosRegistrados";

	public static final String ERROR_NOTIFICACION_CANTERA_NULL = "notificacionCantera.error.requerida";
	public static final String ERROR_NOTIFICACION_CANTERA_CODIGO_INVALIDO = "notificacionCantera.error.codigoRequerido";

	public static final String ERROR_STOCK_CUBICACION_REPETIDA = "stock.aprobarCubicacionMes.error.cubicacio.existente";
	public static final String ERROR_STOCK_LISTAR_CUBICACION = "stock.listarCubicacion.error";
	public static final String ERROR_STOCK_CONSULTAR_CUBICACION = "stock.consultarCubicacion.error";
	public static final String ERROR_STOCK_REGISTRAR_CUBICACION = "stock.registrarCubicacion.error";
	public static final String ERROR_STOCK_MODIFICAR_CUBICACION = "stock.modificarCubicacion.error";
	public static final String ERROR_STOCK_ANULAR_CUBICACION = "stock.anularCubicacion.error";
	public static final String ERROR_STOCK_APROBAR_CUBICACION = "stock.aprobarCubicacion.error";

	public static final String ERROR_STOCK_LISTAR_INGRESO_PRODUCTO = "stock.listarIngresoProducto.error";
	public static final String ERROR_STOCK_CONSULTAR_INGRESO_PRODUCTO = "stock.consultarIngresoProducto.error";
	public static final String ERROR_STOCK_REGISTRAR_INGRESO_PRODUCTO = "stock.registrarIngresoProducto.error";
	public static final String ERROR_CANTIDAD_TURNOS_STOCK_REGISTRAR_INGRESO_PRODUCTO = "stock.registrarIngresoProducto.cantidad.turnos.error";
	public static final String ERROR_STOCK_MODIFICAR_INGRESO_PRODUCTO = "stock.modificarIngresoProducto.error";
	public static final String ERROR_STOCK_ANULAR_INGRESO_PRODUCTO = "stock.anularIngresoProducto.error";
	public static final String ERROR_STOCK_FORMATO_FECHA = "stock.registrarIngresoProducto.formatoFecha.error";
	public static final String FORMATO_FECHA_ARCHIVO_INGRESO_PRODUCTO = "stock.ingresoProducto.archivo.formato.fecha";
	public static final String FORMATO_FECHA_ARCHIVO_INGRESO_PRODUCTO_ALTERNO = "stock.ingresoProducto.archivo.formato.fechaAlterno";
	public static final String NOMBRE_TURNO_STOCK_INGRESO_PRODUCTO = "stock.registrarIngresoProducto.nombre.turno";
	public static final String ERROR_MEDICION_DIARIA_YA_REGISTRADA = "error.medicionDiaria.registrada";

	public static final String ERROR_NOTIFICACION_CONSULTA_DATOS_REPORTE = "notificacion.consultar.datos.reporte.error";

	public static final String ERROR_STOCK_ANULAR_INGRESO_PRODUCTO_EQUIVALENCIA_PRODUCTO = "stock.importarIngresoProducto.equivalecia.producto.error";
	public static final String ERROR_STOCK_ANULAR_INGRESO_PRODUCTO_EQUIVALENCIA_TURNO = "stock.importarIngresoProducto.equivalecia.turno.error";

	public static final String ERROR_DESPACHOS_CANTERA_REQUERIDOS = "despachosCantera.error.requerida";

	public static final String ETIQUETA_ESTADO_AJUSTADO = "estado.ajustado";
	public static final String ETIQUETA_ESTADO_APROBADO = "estado.aprobado";
	public static final String ETIQUETA_ESTADO_GENERADO = "estado.generado";
	public static final String ETIQUETA_ESTADO_INICIAL = "estado.inicial";
	public static final String ETIQUETA_ESTADO_ACTIVA = "estado.activo";

	public static final String ETIQUETA_CONCEPTO_SALDO_INICIAL = "concepto.si";
	public static final String ETIQUETA_CONCEPTO_SALDO_FINAL = "concepto.sf";
	public static final String ETIQUETA_CONCEPTO_CONSUMO = "concepto.consumo";
	public static final String ETIQUETA_CONCEPTO_MERMA = "concepto.merma";
	public static final String ETIQUETA_CONCEPTO_PRODUCCION = "concepto.produccion";

	public static final String ETIQUETA_CONCEPTO_CONSUMO_COMPROBANTE = "concepto.cc";
	public static final String ETIQUETA_CONCEPTO_CONSUMO_RESTANTE = "concepto.cr";

	public static final String ETIQUETA_BALANCE_BALANCE = "tipoBalance.balance";
	public static final String ETIQUETA_BALANCE_AJUSTE = "tipoBalance.ajuste";

	public static final String ETIQUETA_UNIDAD_MEDIDA_TONS = "unidad.medida.toneldas";

	public static final String ETIQUETA_TIPO_PRODUCTO_MAT_PRIMA = "tipo.producto.matPrim";

	public static final String ETIQUETA_MOVIMIENTO_DESCRIPCION_AJUSTE = "movimiento.descripcion.ajuste";
	public static final String ETIQUETA_TIPO_DOCUMENTO_MATERIAL_INGRESO = "tipo.documento.material.ingreso";
	public static final String ETIQUETA_TIPO_DOCUMENTO_MATERIAL_CONSUMO = "tipo.documento.material.consumo";
	public static final String ETIQUETA_CODIGO_SAP_INGRESO = "sap.ingreso";
	public static final String ETIQUETA_CODIGO_SAP_CONSUMO = "sap.consumo";

	public static final String ETIQUETA_UBICACION_PLANTA_PACASMAYO = "ubicacion.planta.pacasmayo";

	static final String HOJA_RUTA_SIN_FACTORES_DOS_PARTE_1 = "planificacion.error.sinFactorDos.parte1";
	static final String HOJA_RUTA_SIN_FACTORES_DOS_PARTE_2 = "planificacion.error.sinFactorDos.parte2";
	public static final String ETIQUETA_ESTADO_AJUSTE_PRODUCCION = "reportes.reportePlanProduccion.estado.estadoAjusteProduccion";
	public static final String ETIQUETA_ESTADO_AJUSTE_PRODUCTO = "reportes.reportePlanProduccion.estado.estadoAjusteProducto";

	public static final String ERROR_GUARDAR_PRODUCCOION_SEMANAL = "error.produccionSemanal.registrar";

	public static final String GUARDAR_PRODUCCOION_SEMANAL_CORERCTO = "produccionSemanal.modificar.correcto";

	public static final String MEDIO_ALMACENAMIENTO_NO_ENCONTRADO = "error.medioAlmacenamiento.noEncontrado";

	public static final String ERROR_PRODUCCION_SEMANAL_NO_ENCONTRADA = "produccionSemanal.error.noEncontrada";
	public static final String ERROR_REVERSAR_PRODUCCION_SEMANAL_FECHA = "error.produccionSemanal.reversar.fechaInvalida";
	public static final String ERROR_CONCEPTO_NO_ENCONTRADO = "error.concepto.noEncontrado";
	public static final String MENSAJE_VALIDACION_PRODUCCION_SIMULTANEA_PRODUCTOS = "Para una misma hora no se pueden producir simultaneamente los Productos: Cemento I y Cemento V; o Cemento HS y Cemento MS. Revise la Programacion.";
	public static final String MENSAJE_VALIDACION_CAPACIDAD_MAXIMA_MEDIO_DE_ALMACENAMIENTO = "El Saldo Final excede la Capacidad Máxima del Medio de Almacenamiento.";
	public static final String ERROR_OBTENER_VALOR_ARCHIVO = "error.archivo.configuracion";

	public static final String ERROR_ORDEN_PRODUCCION_ESTADO_ARCHIVO_CONFIGURACION = "error.ordenProduccion.estadoArchivoConfiguracion.invalido";

	public static final String ERROR_PERSISTENCIA_PRODUCCION_SEMANAL = "error.persistencia.produccionSemanal";

	public static final String ERROR_DIVISION_REQUERIDA = "manejoMaestro.error.division.requerido";

	public static final String ERROR_ANNIO_REQUERIDO = "parteTecnico.error.anio.requerido";
	public static final String ERROR_MES_INICIO_REQUERIDO = "planificacion.error.mesinicio.requerido";
	public static final String ERROR_MES_FIN_REQUERIDO = "planificacion.error.mesfin.requerido";

	public static final String PARTE_TECNICO_PRODUCCION_CEMENTO = "parteTecnico.produccionCemento.etiqueta";
	public static final String PARTE_TECNICO_PRODUCCION_CRUDO = "parteTecnico.produccionCrudo.etiqueta";
	public static final String PARTE_TECNICO_PRODUCCION_CLINKER = "parteTecnico.produccionClinker.etiqueta";
	public static final String ERROR_COMPARACION_LISTADETALLESCOMPONENTES = "error.comparacion.listadetallescomponentes";

	public static final String ERROR_CONSULTA_CONSUMO_RECURSO = "consumo.recurso.error.consulta";
	public static final String ERROR_PLANIFICACION_VALIDAR_SINHR = "planificacion.error.planAnual.validar.sinhr";

	public static final String IMPORTACION_ARCHIVOXLS_NO_PUEDE_SER_LEIDO = "importacion.errorOperacion.mensaje.archivoxlsnopuedeserleido";

	public static final String IMPORTACION_ARCHIVOXLS_NO_ENCONTRADO = "importacion.errorOperacion.mensaje.archivoxlsno.encontrado";

	public static final String ERROR_CODIGO_SAP_NO_UNICO = "errorOperacion.mensaje.codigosap.no.unico";
	public static final String ERROR_CODIGO_SAP_NO_ENCONTRADO = "errorOperacion.mensaje.codigosap.no.encontrado";

	public static final String ERROR_VARIABLE_OBJETO_NO_ENCONTRADO = "error.tablaoperacion.variableobjeto.noencontrada";

	public static final String ERROR_COMPONENTE_REPETIDO = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarcomponentes.error.mensaje.componenterepetido");
	public static final String ERROR_COMPONENTE_YAINGRESADO_ENCONSUMO = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarcomponentes.error.mensaje.componenteyaingresado");
	public static final String ERROR_CONSUMO_REPETIDO = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarconsumos.error.mensaje.consumorepetido");
	public static final String ERROR_CONSUMO_YAINGRESADO_ENCOMPONENTE = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarconsumos.error.mensaje.consumoyaingresado");
	public static final String ERROR_NUMERO_ORDEN_INVALIDA = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarconsumos.error.mensaje.numeroorden.invalida");
	public static final String ERROR_PRODUCTO_REPETIDO = ManejadorPropiedades
			.obtenerPropiedadPorClave("tablavalor.listarproductos.error.mensaje.productorepetido");

	public static final String PRODUCTO_SIN_HOJARUTA_SIN_COMPONENTES = ManejadorPropiedades
			.obtenerPropiedadPorClave("reporte.partetecnico.mensajeerror.producto.sin.hojaruta");
}
