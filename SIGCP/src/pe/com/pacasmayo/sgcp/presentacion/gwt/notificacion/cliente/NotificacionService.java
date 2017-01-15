package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificacionProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("servicioNotificacion")
public interface NotificacionService extends RemoteService {

	/**
	 * Obtiene las columnas del reporte ecs
	 * 
	 * @param codigoPuestoTrabajo codigo del puesto de trabajo
	 * @param codigoProceso codigo del proceso
	 * @param estadoColumna estado de las columnas
	 * @param estadoPlantilla estado de la plantilla
	 * @param nombreArchivo nombre del archivo
	 * @return List<ColumnareporteDTO>
	 */
	public List<ColumnareporteDTO> obtenerColumnasReporte(Long codigoPuestoTrabajo, Long codigoProceso, String estadoColumna,
			String estadoPlantilla) throws ServicioGWTGlobalException;

	/**
	 * Obtiene los datos del reporte ecs
	 * 
	 * @param fecha fecha del reporte
	 * @param nombre nombre del archivo
	 * @param tipoVariable tipo de variable ( produccion y operacion)
	 * @param codigoProceso codigo del proceso
	 * @return List<DatoReporteDTO>
	 */
	public List<DatoReporteDTO> obtenerDatosReporte(Date fecha, String nombre, String tipoVariable, Long codigoProceso)
			throws ServicioGWTGlobalException;

	/**
	 * Crea un registro de notificacion diaria
	 * 
	 * @param codigoLineaNegocio codigo de la linea de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param fechaRegistro fecha de registro
	 * @param observacion observacion
	 * @return Boolean true en caso de que el metodo se ejecute correctamente,
	 *         false en caso contrario
	 */
	public Boolean registrarNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTrabajo) throws ServicioGWTGlobalException;

	/**
	 * Crea los registro de cada hora de operacion en puesto de trabajo
	 * 
	 * @param datos registros que tiene la tabla del reporte ecs
	 * @param codigoRegistroReporteECS codigo del reporte ecs
	 * @param codigoNotificacionDiaria codigo de la notificacion diaria al que
	 *            sera asociado el registro
	 * @param codigoPuestoTrabajo codigo del puesto del trabajo
	 * @param fechaRegistro fecha
	 * @return Boolean true en caso de que el metodo se ejecute correctamente,
	 *         false en caso contrario
	 */
	public Boolean registrarNotificacionesOperacion(List<DatoReporteDTO> datos, Long codigoRegistroReporteECS,
			Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro) throws ServicioGWTGlobalException;

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
	 * @return Boolean true en caso de que el metodo se ejecute correctamente,
	 *         false en caso contrario
	 */
	public Boolean registrarNotificacionesProduccion(List<DatoReporteDTO> datos, List<ColumnareporteDTO> columnas,
			Long codigoRegistroReporteECS, Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene un registro de notificacion diaria dado la linea de negocio, el
	 * tablero de control y la fecha de registro
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaRegistro
	 * @return NotificaciondiariaDTO
	 */
	public NotificaciondiariaDTO obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro)
			throws ServicioGWTGlobalException;

	/**
	 * Devuelve una lista de notificaciones diarias segun los campos pasados
	 * como parametro
	 * 
	 * @param codigoLineaNegocio codigo de la lainea de negocio
	 * @param codigoTableroControl codifo del tablero de control
	 * @param fechaRegistro fecha de registro
	 * @return List<NotificaciondiariaDTO>
	 */
	public Boolean verificarSiExistenRegistrosNotifProd(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTranajo) throws ServicioGWTGlobalException;

	/**
	 * Devuelve la fecha del dia anterio a la fecha pasada como parametro
	 * 
	 * @param fechaActual fecha
	 * @return Date
	 */
	public Date obtenerFechaDiaAnterior(Date fechaActual) throws ServicioGWTGlobalException;

	/**
	 * Obtiene las ordenes de produccion segun la fecha y procesos pasados como
	 * parametro
	 * 
	 * @param fecha decha
	 * @param codigoProceso codigo del proceso
	 * @return List<OrdenProduccionDTO>
	 */
	public List<OrdenProduccionDTO> getOrdenesByMesPlantillaLiberadas(Date fecha, Long codigoPlantillaReporte)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene las notificaciones segun su id
	 * 
	 * @param codigoNotificacionProduccion codigo de la notificacion de
	 *            produccion
	 * @return List<NotificacionProduccionDTO>
	 */
	public List<NotificacionProduccionDTO> obtenerNotificacionesProduccion(Long codigoNotificacionProduccion)
			throws ServicioGWTGlobalException;

	/**
	 * Obtiene los datos de la bd, para cargar la informacion del reporte ecs,
	 * cuando ya se ha registrado la notificacion diaria
	 * 
	 * @param codigoLineaNegocio codigo de la lines de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param fechaReg fecha
	 */
	public List<DatoReporteDTO> obtenerDatosBD(Long codigoLineaNegocio, Long codigoTableroControl, Long codigoProceso,
			Long codigoPuestoTrabajo, Date fechaReg) throws ServicioGWTGlobalException;

	/**
	 * Obtiene una lista de datos de reporte con valores de variables vacios
	 * pero con las horas asignadas
	 * 
	 * @return List<DatoReporteDTO>
	 */
	public List<DatoReporteDTO> obtenerDatosReporteCargaManual() throws ServicioGWTGlobalException;

	/**
	 * Verifica si existe una notifcacion diaria registrada, para la linea de
	 * negocio, puesto de trabajo y fecha(dia siguiente)
	 * 
	 * @param codigoLineaNegocio Long codigo de la linea de negocio
	 * @param codigoPuestoTrabajo Long codigo puesto de trabajo
	 * @param fechaReg Date fecha
	 */
	public Integer validarSiExisteNotficacionDiaSiguinte(Long codigoLineaNegocio, Long codigoPuestoTrabajo,
			Long codigoTableroControl, Date fechaReg, String hora, Long codigoOrdenProduc) throws ServicioGWTGlobalException;

	/**
	 * Obtiene las colunas de la plantillaReporte directo de la notificacion
	 * cuanod esta ha sido almacenada en bd
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaReg
	 * @param codigoPuestoTrabajo
	 */
	public List<ColumnareporteDTO> obtenerColumnasReportePorNotif(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaReg, Long codigoPuestoTrabajo) throws ServicioGWTGlobalException;

	/**
	 * Valida que las horas esten ingresadas cuando exista cantidades en los
	 * productos que no son Combustibles
	 * 
	 * @param datosProduccion
	 * @param columnas
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public boolean validarRegistrosNotificacionDiaria(List<DatoReporteDTO> datosProduccion, List<ColumnareporteDTO> columnas)
			throws ServicioGWTGlobalException;
}
