package pe.com.pacasmayo.sgcp.presentacion.action;


/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesMensajePresentacion.java
 * Modificado: Jan 20, 2010 2:30:28 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesMensajePresentacion {

	// Generales
	public static final String SESION_VENCIDA = "errorOperacion.mensaje.sesionvencida";
	public static final String COMUNICACION_BD_FALLO = "errorOperacion.mensaje.comunicacionbdfallo";
	public static final String LUNES = "produccion.lunes";
	public static final String MARTES = "produccion.martes";
	public static final String MIERCOLES = "produccion.miercoles";
	public static final String JUEVES = "produccion.jueves";
	public static final String VIERNES = "produccion.viernes";
	public static final String SABADO = "produccion.sabado";
	public static final String DOMINGO = "produccion.domingo";
	public static final String NO_DISPONE_REGISTROS_ASOCIADOS = "resultadoOperacion.mensaje.nodisponeregistrosasociados";
	public static final String FORMATO_INVALIDO = "errorOperacion.mensaje.formatoInvalido";
	public static final String FALLA_CONVERSION_NUMERICA = "errorOperacion.mensaje.fallaconversionnumero";
	public static final String FALLA_CONVERSION_FECHA = "errorOperacion.mensaje.fallaConversionFecha";
	public static final String FALLA_PRODUCTO_NO_ENCONTRADO = "error.producto.noEncontrado";
	public static final String FALLA_PRODUCTO_FALLO_OPERACION = "errorOperacion.mensaje.fallaoperacion";

	// ----- Iniciar Sesion ----- //
	public static final String SEGURIDAD_AUTENTICACION_USUARIO_FALLO = "seguridad.inicio.mensaje.login.validacion";
	public static final String SEGURIDAD_REGISTRO_NO_ENCONTRADO = "seguridad.errorOperacion.mensaje.registronoencontrado";

	// ----- Grupos de Usuario y Privilegio
	public static final String SEGURIDAD_GRUPOUSUARIOPRIVILEGIO_NO_ENCONTRADO = "seguridad.errorOperacion.mensaje.ningunregistroencontrado";
	public static final String USUARIO_LOGIN_EXISTE = "mensaje.validacion.usuario.login.existe";
	// --- Manejo Maestros -----//
	public static final String MAESTROS_CAMPO_OBJETO_INVALIDO = "manejoMaestro.errorOperacion.mensaje.objetoinvalido";
	public static final String MAESTROS_REGISTRO_DUPLICADO = "manejoMaestro.errorOperacion.mensaje.registroduplicado";
	public static final String MAESTROS_REGISTRO_NO_ELIMINADO = "manejoMaestro.errorOperacion.mensaje.registroeliminado";
	public static final String MAESTROS_REGISTRO_NO_ENCONTRADO = "manejoMaestro.errorOperacion.mensaje.registronoencontrado";
	public static final String MAESTROS_REGISTRO_RELACIONADO = "manejoMaestro.errorOperacion.mensaje.registrorelacionado";
	public static final String MAESTROS_REGISTRO_IGUAL = "errorOperacion.mensaje.registroIgual";
	public static final String MAESTROS_REGISTRO_OCUPADO = "errorOperacion.mensaje.registroOcupado";
	public static final String MENSAJE_ORDEN_DE_PROCESO_REGISTRADA = "manejoMaestro.proceso.orden.existe";
	public static final String MENSAJE_PROCESO_NO_MODIFICABLE = "manejoMaestro.proceso.nomodificar";
	public static final String MENSAJE_HOJA_RUTA_RELACIONADA_MODIFICAR = "manejoMaestro.hojaruta.activo.modificar";
	public static final String MENSAJE_ERROR_FUNCION_HOJA_RUTA = "manejoMaestro.error.hojaruta.funcion";
	public static final String MENSAJE_HOJA_RUTA_RELACIONADA_ELIMINAR = "manejoMaestro.hojaruta.activo.eliminar";
	public static final String MAESTROS_REGISTRO_YA_APROBADO = "errorOperacion.mensaje.ya.aprobado";
	// --- Planificacion ----///
	public static final String PLAN_UNIDAD_INVALIDA = "planificacion.error.unidad.invalida";
	public static final String PLAN_ESTADO_PLAN_INVALIDA = "planificacion.error.estadoPlan.invalido";
	public static final String PLAN_NO_APROBADO = "planificacion.error.estadoPlan.noaprobado";
	public static final String PLAN_LINEA_NEGOCIO_NOTSELECTED = "planificacion.error.lineaNegocio.notSelected";
	public static final String PLAN_PRODUCCION_NOTFOUND = "planificacion.error.produccion.noEncontrado";
	public static final String PLAN_TABLA_COMERCIALIZACION_INCOMPLETA = "planificacion.error.planComercializacion.incompleto";
	public static final String PLAN_LINEA_NEGOCIO_CODIGO = "planAnual.lineaNegocio.codigo";
	public static final String PLAN_COMERCIALIZACION = "planAnual.planComercializacion";
	public static final String PLAN_ANUAL_EXISTENTE = "planificacion.error.planAnual.existente";
	public static final String PLAN_ANUAL_OBTENER_ERROR = "planificacion.error.planAnual.obtener";
	public static final String PLAN_ANUAL_OBTENER_NECESIDADES = "planificacion.error.plannecesidad.noexiste";

	// --- Programación Semanal ----///
	public static final String PROGRAMA_SEMANAL_TIPOCOMPARATIVO_GENERAL = "produccion.produccionSemanal.estadisticasaldoinicial.tipoComparativoPlanVsEje";
	public static final String PROGRAMA_SEMANAL_TIPOCOMPARATIVO_PRODUCTO = "produccion.produccionSemanal.estadisticasaldoinicial.tipoComparativoProducto";
	public static final String PROGRAMA_SEMANAL_PLANIFICADO = "produccion.produccionSemanal.estadisticasaldoinicial.planificado";
	public static final String PROGRAMA_SEMANAL_EJECUTADO = "produccion.produccionSemanal.estadisticasaldoinicial.ejecutado";
	public static final String PROGRAMA_SEMANAL_ESTADISTICA_GENERAL = "produccion.produccionSemanal.estadisticasaldoinicial.estadisticageneral";
	public static final String PROGRAMA_SEMANAL_ESTADISTICA_PRODUCTO = "produccion.produccionSemanal.estadisticasaldoinicial.estadisticaproducto";

	// --- Reporte ECS ----///
	public static final String REPORTEECS_LINEAPROCESO_NO_ENCONTRADO = "reporteECS.errorOperacion.mensaje.lineaprocesonoencontrado";
	public static final String REPORTEECS_COLUMNASPLANTILLAS_NO_DEFINIDA = "reporteECS.errorOperacion.mensaje.columnasplantillasnodefinidas";
	public static final String REPORTEECS_ESTADOPLANTILLAREPORTE_NO_DEFINIDO = "reporteECS.errorOperacion.mensaje.estadoplantillareportenodefinido";
	public static final String REPORTEECS_PUESTOTRABAJO_NO_DEFINIDO = "reporteECS.errorOperacion.mensaje.puestotrabajonodefinido";
	public static final String REPORTEECS_FECHAREGISTRO_RANGONOVALIDO = "reporteECS.error.fechaRegistro.rangovalido";
	public static final String REPORTEECS_COLUMNASPLANTILLAS_ESTADO_NO_DEFINIDO = "reporteECS.errorOperacion.mensaje.columnasplantillasestadonodefinido";
	public static final String REPORTEECS_ERROR_MODIFICAR_TIENE_NOTIF_ASOCIADAS = "reporteecs.error.modificar.tiene.notif.asociadas";
	public static final String REPORTEECS_COLUMNA_NO_ELIMINADO_POR_REFERENCIA = "reporteECS.errorOperacion.mensaje.columnaeliminadareferencia";
	public static final String REPORTEECS_COLUMNASPLANTILLAPRODUCTOS_NO_DEFINIDA = "reporteECS.errorOperacion.mensaje.columnasplantillaproductosnodefinidas";
	public static final String REPORTEECS_COLUMNASPLANTILLAPRODUCTOS_REPETIDA = "reporteECS.errorOperacion.mensaje.columnasplantillaproductosrepetida";
	public static final String REPORTEECS_COLUMNASPLANTILLAPRODUCTOS_PROPORCION_INCORRECTA = "reporteECS.errorOperacion.mensaje.columnasplantillaproductosproporcionincorrecta";
	public static final String REPORTEECS_PLANTILLA_PADRE_NO_DISPONIBLE = "reporteECS.errorOperacion.mensaje.plantillapadrenodisponible";
	public static final String REPORTEECS_COMPONENTESUHOJARUTA_NO_DISPONIBLE_NO_ACTIVA = "reporteECS.errorOperacion.mensaje.componenteuhojarutanodisponiblenoactiva";
	public static final String REPORTEECS_COMPONENTES_NO_DISPONIBLE = "reporteECS.errorOperacion.mensaje.componentenodisponible";
	public static final String REPORTEECS_FECHA_REGISTRO_REQUERIDA = "reporteECS.error.fechaRegistro.requerido";
	public static final String REPORTEECS_PLANTILLA_DUPLICADA = "reporteECS.errorOperacion.mensaje.plantilladuplicada";
	public static final String REPORTEECS_PLANTILLA_PRODUCTO_DUPLICADA = "reporteECS.errorOperacion.mensaje.plantillaproductoduplicada";
	public static final String REPORTEECS_SIGLAS_NO_EXISTENTE = "reporteECS.errorOperacion.mensaje.siglasnoexistentes";
	public static final String REPORTEECS_LINEA_PROCESO_REQUERIDO = "reporteECS.errorOperacion.mensaje.lineayproceso.requerido";

	// --- Parte Tecnico ---///
	public static final String PARTETECNICO_PRODUCTO_REQUERIDO = "reporteECS.error.producto.requerido";
	public static final String PARTETECNICO_ANIO_REQUERIDO = "parteTecnico.error.anio.requerido";
	public static final String PARTETECNICO_MES_REQUERIDO = "parteTecnico.error.mes.requerido";
	public static final String PARTETECNICO_CODIGO_NO_VALIDO = "parteTecnico.mensaje.eliminar.codigonovalido";
	public static final String PARTETECNICO_REGRISTRO_NO_ENCONTRADO = "parteTecnico.mensaje.eliminar.registronoencontrado";
	public static final String PARTETECNICO_REGRISTRO_APROBADO = "parteTecnico.mensaje.eliminar.registroaprobado";
	public static final String PARTETECNICO_AJUSTE_REGRISTRO_APROBADO = "parteTecnico.mensaje.eliminar.ajustepartetecnico.registroaprobado";
	public static final String PARTETECNICO_CONSTANTE_APROBADO = "parteTecnico.ajusteProduccion.nombreEstado.ajustado";
	public static final String CODIGO_TIPO_CLASIFICACION_PRODUCTO_COMBUSTIBLE = "codigo.tipoClaseProducto.combustible";

	// --- Notificacion ---///
	public static final String NOTIFICACIONPLANTA_NOIMPORTADO = "notificacion.notificacionPlanta.error.rfc";
	public static final String NOTIFICACIONPLANTA_ESTADO_NO_CAMBIADO = "notificacion.notificacionPlanta.error.estadoNoCambiado";
	public static final String NOTIFICACIONCANTERA_ERROR_CONSULTAR_CABECERA = "notificacion.error.consultar.notificacionDiaria";
	public static final String NOTIFICACIONCANTERA_ERROR_REGISTRAR = "notificacion.error.registrar.notificacionCantera";
	public static final String NOTIFICACIONCANTERA_ERROR_EDITAR = "notificacion.error.editar.notificacionCantera";
	public static final String NOTIFICACIONCANTERA_CABECERA_NO_ENCONTRADA = "notificacion.error.notificacionDiaria.noEncontrada";
	public static final String NOTIFICACION_CABECERA_REQUERIDA = "notificacion.error.notificacionDiaria.requerida";
	public static final String NOTIFICACIONCANTERA_PRODUCTO_EQUIVALENCIA_NOENCONTRADO = "notificacion.despachoCantera.error.productoEquiNoEncontrado";
	public static final String NOTIFICACIONCANTERA_ARCHIVOS_DESPACHO_NOENCONTRADO = "notificacion.despachoCantera.error.archivoNoEncontrado";
	public static final String NOTIFICACIONCANTERA_ARCHIVO_DESPACHO_NOVALIDO = "notificacion.despachoCantera.error.formatoArchivo";
	public static final String NOTIFICACIONCANTERA_ARCHIVOS_DESPACHO_ERROR = "notificacion.despachoCantera.error.lecturaArchivo";
	public static final String NOTIFICACIONCANTERA_ARCHIVOS_DESPACHO_ERROR_VALIDAR = "notificacion.despachoCantera.error.validarLecturaArchivo";
	public static final String ERROR_UBICACION_CANTERA_NO_ENCONTRADA = "error.UbicacionCantera.NoEncontrada";
	public static final String NOTIFICACION_DIARIA_CODIGO = "notificacionDiaria.codigo";
	public static final String NOTIFICACION_CANTERA_CODIGO = "notificacionCantera.codigo";
	public static final String NOTIFICACION_CANTERA_CONSULTA = "notificacionCantera.consulta";
	public static final String NOTIFICACION_DIARIA_CANTERA_CONSULTA = "notificacionDiaria.cantera.consulta";
	public static final String TOTAL_TM_ETIQUETA = "totalTm.etiqueta";
	public static final String ERROR_PERIODO_CONTABLE_CERRADO = "periodoContable.error.cerrado";
	public static final String ERROR_ORDEN_PRODUCCION_NO_ENCONTRADA = "ordenProduccion.error.NoEncontrada";
	public static final String ERROR_PUESTO_TRABAJO_NO_ENCONTRADO = "puestoTrabajo.error.NoEncontrado";
	public static final String ERROR_CAPACIDAD_VOLQUETE_NO_ENCONTRADA = "capacidadVolquete.error.NoEncontrada";
	public static final String LINEA_NEGOCIO_NOMBRE_ETIQUETA = "lineaNegocio.nombre";
	public static final String LINEA_NEGOCIO_NO_ENCONTRADA = "lineaNegocio.noEncontrada";
	public static final String ERROR_NOTIFICACIONCANTERA_EDITAR_NO_ENCONTRADA = "error.notificacionCantera.editar.registroNoEncontrado";
	public static final String ERROR_CONSULTA_AJUSTEPRODUCTO = "reportes.reportePlanProduccion.mensaje.error.obtenerAjusteProductoPorMesAnio";
	public static final String MENSAJE_NO_DATA_ANIO_REPORTE_PLANPRODUCCION = "reportes.reportePlanProduccion.mensaje.info.noExisteDataEnAnio";
	public static final String MENSAJE_NO_DATA_ANIO_REPORTE_VARIABLESPRODUCCION = "reportes.reporteVariablesProduccion.mensaje.info.noExisteDataEnAnio";
	public static final String ERROR_PLAN_ANUAL = "planificacion.error.planAnual";
	public static final String ERROR_PLAN_ANUAL_DOUNIDAD_NOEXISTE = "planificacion.error.planAnual.noExiste";
	public static final String ERROR_PLAN_ANUAL_CONSULTAROPERACION_NOENCONTRADO = "planificacion.error.planAnual.consultarOperacion.noEncontrado";
	public static final String ERROR_EXCEPTION_ELEMENTO_NO_ENCONTRADO = "errorOperacion.mensaje.elementoNoEncontrado.exception";
	public static final String ERROR_EXCEPTION_NO_CARGO_COMPONENTES = "errorOperacion.mensaje.noCargoComponentes.exception";
	public static final String ERROR_SELECCIONAR_PROCESO = "manejoMaestro.error.centroCostos.proceso";
	public static final String ERROR_SELECCIONAR_PRODUCTO = "manejoMaestro.error.centroCostos.producto";
	public static final String CAMPOS_OBLIG_FILTRAR = "notificacion.notificacionPlanta.error.camposListar.requerido";
	public static final String CODIGOS_MOVIMIENTOS_TRANSLADO = "codigos.movimientos.traslados.traslado";
	public static final String CODIGOS_MOVIMIENTOS_ANULACION_TRANSLADO = "codigos.movimientos.traslados.anulacion.traslado";
	public static final String ERROR_RFC_DESPACHO_NO_EXISTE_ORDEN_PARA_PRODUCTO = "error.rfc.despacho.no.existe.orden.para.producto";

	// Tabla Valor
	public static final String TABLAVALOR_LISTA_CODIGOS_VENTA = "lista.configuracion.obtener.ventas";
	public static final String TABLAVALOR_LISTA_CODIGOS_OTROS_CONSUMOS = "lista.configuracion.obtener.otrosconsumos";
	public static final String CODIGO_PRODCUTO_CLK_I_ = "registro.cubicacion.codigo.producto.clk1";
	public static final String NOMBREREPORTE_TABLAVALOR = "tablavalor.reporte";

	// Stock
	public static final String ERROR_FORMATO_ARCHIVO_CSV = "stock.error.formatoArchivo.csv";
	public static final String ERROR_COMPLETAR_CAMPOS = "stock.error.completarCampos";
	public static final String PARTE_DIARIO_CERRADO = "stock.error.parteDiario.cerrado";
	public static final String CODIGO_HOJA_RUTA_GENERADA = "manejoMaestro.hojaRuta.estado.generada";

	// Reporte Sistema Informacion
	public static final String MENSAJE_OPERACION_EXITOSA = "mensaje.sistema.informacion.exito";


}
