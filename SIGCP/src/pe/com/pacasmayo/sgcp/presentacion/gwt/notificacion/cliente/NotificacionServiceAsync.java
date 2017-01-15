package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificacionProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NotificacionServiceAsync {

	/**
	 * Obtiene las columnas del reporte ecs
	 * 
	 * @param codigoPuestoTrabajo codigo del puesto de trabajo
	 * @param codigoProceso codigo del proceso
	 * @param estadoColumna estado de las columnas
	 * @param estadoPlantilla estado de la plantilla
	 * @param existenRegistros
	 * @param nombreArchivo nombre del archivo
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void obtenerColumnasReporte(Long codigoPuestoTrabajo, Long codigoProceso, String estadoColumna,
			String estadoPlantilla, AsyncCallback<List<ColumnareporteDTO>> callback) throws IllegalArgumentException;

	/**
	 * Obtiene los datos del reporte ecs
	 * 
	 * @param fecha fecha del reporte
	 * @param nombre nombre del archivo
	 * @param tipoVariable tipo de variable ( produccion y operacion)
	 * @param codigoProceso codigo del proceso
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void obtenerDatosReporte(Date fecha, String nombre, String tipoVariable, Long codigoProceso,
			AsyncCallback<List<DatoReporteDTO>> callback) throws IllegalArgumentException;

	/**
	 * Crea un registro de notificacion diaria
	 * 
	 * @param codigoLineaNegocio codigo de la linea de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param fechaRegistro fecha de registro
	 * @param codigoPuestoTrabajo
	 * @param observacion observacion
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void registrarNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTrabajo, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	/**
	 * Crea los registro de cada hora de operacion en puesto de trabajo
	 * 
	 * @param datos registros que tiene la tabla del reporte ecs
	 * @param codigoRegistroReporteECS codigo del reporte ecs
	 * @param codigoNotificacionDiaria codigo de la notificacion diaria al que
	 *            sera asociado el registro
	 * @param codigoPuestoTrabajo codigo del puesto del trabajo
	 * @param fechaRegistro fecha
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void registrarNotificacionesOperacion(List<DatoReporteDTO> datos, Long codigoRegistroReporteECS,
			Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;

	/**
	 * Crea los registro de cada hora de produccion en puesto de trabajo
	 * 
	 * @param datos datos registros que tiene la tabla del reporte ecs
	 * @param columnas columnas del reporte
	 * @param codigoRegistroReporteECS codigo del reporte ecs
	 * @param codigoNotificacionDiaria codigo de la notificacion diaria al que
	 *            sera asociado el registro
	 * @param codigoPuestoTrabajo codigo del puesto del trabajo
	 * @param fechaRegistro fecha
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void registrarNotificacionesProduccion(List<DatoReporteDTO> datos, List<ColumnareporteDTO> columnas,
			Long codigoRegistroReporteECS, Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro,
			AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	/**
	 * Obtiene un registro de notificacion diaria dado la linea de negocio, el
	 * tablero de control y la fecha de registro
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaRegistro
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			AsyncCallback<NotificaciondiariaDTO> callback) throws IllegalArgumentException;

	/**
	 * Obtiene las notificaciones segun su id
	 * 
	 * @param codigoNotificacionProduccion codigo de la notificacion de
	 *            produccion
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void obtenerNotificacionesProduccion(Long codigoNotificacionDiaria,
			AsyncCallback<List<NotificacionProduccionDTO>> callback) throws IllegalArgumentException;

	/**
	 * Devuelve una lista de notificaciones diarias segun los campos pasados
	 * como parametro
	 * 
	 * @param codigoLineaNegocio codigo de la lainea de negocio
	 * @param codigoTableroControl codifo del tablero de control
	 * @param fechaRegistro fecha de registro
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void verificarSiExistenRegistrosNotifProd(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTrabajo, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	/**
	 * Devuelve la fecha del dia anterio a la fecha pasada como parametro
	 * 
	 * @param fechaActual fecha
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void obtenerFechaDiaAnterior(Date fechaActual, AsyncCallback<Date> callback) throws IllegalArgumentException;

	/**
	 * Obtiene las ordenes de produccion segun la fecha y procesos pasados como
	 * parametro
	 * 
	 * @param fecha decha
	 * @param codigoProceso codigo del proceso
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void getOrdenesByMesPlantillaLiberadas(Date fecha, Long codigoPlantillaReporte,
			AsyncCallback<List<OrdenProduccionDTO>> callback) throws IllegalArgumentException;

	/**
	 * Obtiene los datos de la bd, para cargar la informacion del reporte ecs,
	 * cuando ya se ha registrado la notificacion diaria
	 * 
	 * @param codigoLineaNegocio codigo de la lines de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param codigoPuestoTrabajo
	 * @param codigoPuestoTrabajo
	 * @param fechaReg fecha
	 * @param callback
	 */
	public void obtenerDatosBD(Long codigoLineaNegocio, Long codigoTableroControl, Long codigoProceso, Long codigoPuestoTrabajo,
			Date fechaReg, AsyncCallback<List<DatoReporteDTO>> callback) throws IllegalArgumentException;

	/**
	 * Obtiene una lista de datos de reporte con valores de variables vacios
	 * pero con las horas asignadas
	 * 
	 * @param callback
	 */
	public void obtenerDatosReporteCargaManual(AsyncCallback<List<DatoReporteDTO>> callback);

	/**
	 * Verifica si existe una notifcacion diaria registrada, para la linea de
	 * negocio, puesto de trabajo y fecha(dia siguiente)
	 * 
	 * @param codigoLineaNegocio Long codigo de la linea de negocio
	 * @param codigoPuestoTrabajo Long codigo puesto de trabajo
	 * @param codigoTableroControl
	 * @param fechaReg Date fecha
	 * @param hora
	 * @param asyncCallback
	 */
	public void validarSiExisteNotficacionDiaSiguinte(Long codigoLineaNegocio, Long codigoPuestoTrabajo,
			Long codigoTableroControl, Date fechaReg, String hora, Long codigoOrdenProduc, AsyncCallback<Integer> asyncCallback);

	/**
	 * Obtiene las colunas de la plantillaReporte directo de la notificacion
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaReg
	 * @param codigoPuestoTrabajo
	 * @param columnas
	 */
	public void obtenerColumnasReportePorNotif(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaReg,
			Long codigoPuestoTrabajo, AsyncCallback<List<ColumnareporteDTO>> columnas);

	public void validarRegistrosNotificacionDiaria(List<DatoReporteDTO> datosProduccion, List<ColumnareporteDTO> columnas,
			AsyncCallback<Boolean> callback);
}
