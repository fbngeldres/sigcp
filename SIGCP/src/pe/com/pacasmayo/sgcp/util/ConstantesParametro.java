package pe.com.pacasmayo.sgcp.util;

public class ConstantesParametro {

	public static enum FUNCIONALIDADES {
		AGREGAR, ELIMINAR, MODIFICAR, APROBAR, CERRAR, REPROCESAR, ABRIR
	};

	public static final String USUARIO_REPROCESAR = "USUARIO_REPROCESAR";
	public static final String ENVIO_SAP_CONSUMO_MATERIA_PRIMA = "ENVIO_SAP_CONSUMO_MATERIA_PRIMA";
	public static final String PROCESO_REPORTE_PARTETECNICO_RESUMEN = "PROCESO_REPORTE_PARTETECNICO_RESUMEN";
	public static final String TIPOMEDIDA_M3 = "M3";
	public static final String TIPOMEDIDA_TM = "TM";
	public static final String TIPOMEDIDA_HR = "HR";
	public static final String TIPOMEDIDA_GALONES = "Galones";
	public static final String LISTAR_RESUMEN = "LISTAR_RESUMEN";
	public static final String LISTAR_PRODUCTOS_COMPONENTES_RESUMEN = "LISTAR_PRODUCTOS_COMPONENTES_RESUMEN";
	public static final String LISTAR_OPERACIONES_PUESTOTRABAJO_RESUMEN = "LISTAR_OPERACIONES_PUESTOTRABAJO_RESUMEN";
	public static final String LISTAR_HOJACAL = "LISTAR_HOJACAL";
	public static final String REPORTE_PARTEDIARIO_TABLA_DETALLE = "REPORTE_PARTEDIARIO_TABLA_DETALLE";
	public static final String REPORTE_CONSUMOS_VENTAS_PRODUCTOS = "REPORTE_CONSUMOS_VENTAS_PRODUCTOS";
	public static final Long CODIGO_CLASIFICACION_TIPOMOVIMIENTO_INGRESO = Long.valueOf(1);
	public static final Long CODIGO_CLASIFICACION_TIPOMOVIMIENTO_SALIDA = Long.valueOf(2);
	public static final String CANTIDAD_DECIMALES_SGCP = "CANTIDAD_DECIMALES_SGCP";
	public static final String REPORTE_PARTEDIARIO_PRODUCCION_GRAFICO = "REPORTE_PARTEDIARIO_PRODUCCION_GRAFICO";
	public static final String REPORTE_PARTEDIARIO_PRODUCCION_DETALLE = "REPORTE_PARTEDIARIO_PRODUCCION_DETALLE";
	public static final String PUESTO_TRABAJO_KCAL = "PUESTO_TRABAJO_KCAL";
	public static final String PROGRESION_CARBON_KCAL = "PROGRESION_CARBON_KCAL";
	public static final String PROGRESION_BUNKER_KCAL = "PROGRESION_BUNKER_KCAL";
	public static final String PRODUCTOS_ENVIO_SAP = "PRODUCTOS_ENVIO_SAP";
	public static final String DENSIDAD_PETROLEO_KCAL = "DENSIDAD_PETROLEO_KCAL";
	public static final String DENSIDAD_PETROLEO_KCAL_CAL = "DENSIDAD_PETROLEO_KCAL_CAL";
	public static final String RENDIMIENTO_TERMICO_PETROLEO_KCAL = "RENDIMIENTO_TERMICO_PETROLEO_KCAL";
	public static final String RENDIMIENTO_TERMICO_PETROLEO_CAL_KCAL = "RENDIMIENTO_TERMICO_PETROLEO_KCAL_CAL";
	public static final String LISTAR_PRODUCTOS_TERMINADOS = "LISTAR_PRODUCTOS_TERMINADOS";
	public static final String PRODUCTOS_EXCEPCIONES_CUBICAJE = "PRODUCTOS_EXCEPCIONES_CUBICAJE";
	public static final String PRODUCTOS_EXCEPCION_REPORTE_DIFERENCIA = "PRODUCTOS_EXCEPCION_REPORTE_DIFERENCIA";
	public static final String COLUMNAS_REPORTE_NOTIFICACION_AGUA = "COLUMNAS_REPORTE_NOTIFICACION_AGUA";
	public static final String COLUMNAS_REPORTE_NOTIFICACION_HORAS_ECS = "COLUMNAS_REPORTE_NOTIFICACION_HORAS_ECS";
	public static final String ANNO_INICIAL_PLANIFICACION = "ANNO_INICIAL_PLANIFICACION";
	public static final String ANNO_FINAL_PLANIFICACION = "ANNO_FINAL_PLANIFICACION";
	public static final String PRODUCTOS_CLINKER = "PRODUCTOS_CLINKER";
	public static final String PRODUCTOS_BUNKER = "PRODUCTOS_BUNKER";
	public static final String PROCESOS_VALIDACION_REPORTE_DOSIFICACION = "PROCESOS_VALIDACION_REPORTE_DOSIFICACION";
	// Valores por defecto de SGCP
	public static final Integer CANTIDAD_DECIMALES_SGCP_DEFAULT = 2;
	public static final String TIPOMEDIDA_DEFAULT = "TM";
	public static final String PRODUCTOS_CRUDO = "PRODUCTOS_CRUDO";
	public static final String PRODUCTOS_BITUMINOSO = "PRODUCTOS_BITUMINOSO";
	public static final String PRODUCTOS_ANTRACITA = "PRODUCTOS_ANTRACITA";
	public static final String TIPO_CONSUMO_CONSIGNACION = "TIPO_CONSUMO_CONSIGNACION";
	public static final String PRODUCTOS_SIN_DIFERENCIAR_VENTAS_MOVIMIENTO="PRODUCTOS_SIN_DIFERENCIAR_VENTAS_MOVIMIENTO";

	public static final String CAPACIDAD_BOLSA_DEFECTO = "CAPACIDAD_BOLSA_DEFECTO";
	public static final String CODIGO_UNIDAD_MEDIDA_TM = "CODIGO_UNIDAD_MEDIDA_TM";
	public static final String CODIGO_UNIDAD_MEDIDA_BLS = "CODIGO_UNIDAD_MEDIDA_BLS";
	
	
//	String correoParametroSistema = "sac@cpsaa.com.pe";
//	String passwordParametroSistema = "12345";
//	String timeOutParametroSistema = "15000";
//	String transportHostParametroSistema = "10.34.2.60";
//	String mailToParametroSistema = "fbngeldres@gmail.com";

	
	
	public static final String CORREO_ENVIO_EMAIL = "CORREO_ENVIO_EMAIL";
	public static final String PASSWORD_ENVIO_EMAIL = "PASSWORD_ENVIO_EMAIL";
	public static final String TIMEOUT_ENVIO_EMAIL = "TIMEOUT_ENVIO_EMAIL";
	public static final String TRANSPORT_HOST_ENVIO_EMAIL = "TRANSPORT_HOST_ENVIO_EMAIL";
	public static final String MAIL_TO_ENVIO_EMAIL = "MAIL_TO_ENVIO_EMAIL";
	
	
	
}
