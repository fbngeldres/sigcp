package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.NotDiariaProduccionRepBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ReporteDosificacion;
import pe.com.pacasmayo.sgcp.bean.ResumenNotificacionRepBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnaplantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;

public interface NotificacionProduccionLogicFacade {

	/**
	 * @param codigoRegistroReporteECS
	 * @param codigoNotificacionDiaria
	 * @param codigoHora
	 * @param codigoPuestoTrabajo
	 * @param fechaRegistro
	 * @param codigoOrden
	 * @param codigoMedio
	 * @param obs
	 * @param horaEcs
	 * @param agua
	 * @param energia
	 * @param plantillaproducto
	 * @param datoreporteDTO
	 * @param listaColumnaPlantillaProducto
	 * @throws LogicaException
	 */
	public abstract Notificacionproduccion insertarNotificacionProduccion(Long codigoRegistroReporteECS,
			Long codigoNotificacionDiaria, Long codigoHora, Long codigoPuestoTrabajo, Date fechaRegistro, Integer codigoOrden,
			Integer codigoMedio, String obs, Boolean cambioProduccionNormal, Boolean cambioProduccionLavado,
			String cambioProduccionHora, Double energia, Double agua, Double horaEcs, Plantillaproducto plantillaproducto)
			throws LogicaException;

	/**
	 * Crea la notificaacion de los componentes que fueron consumidos para crear
	 * un producto
	 * 
	 * @param notificacionProduccion
	 * @param datoreporteDTO
	 * @param listaColumnaPlantillaProducto
	 * @throws LogicaException
	 */
	public void crearComponentesNotificacion(Notificacionproduccion notificacionProduccion, DatoReporteDTO datoreporteDTO,
			List<Columnaplantillaproducto> listaColumnaPlantillaProducto) throws LogicaException;

	/**
	 * @param codigoNotificacionDiaria
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Notificacionproduccion> obtenerNotificacionesProduccionByCodigoNotificacionDiaria(
			Long codigoNotificacionDiaria) throws LogicaException;

	/**
	 * @param fechaInicio
	 * @param fechaFin
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoPuestoTrabajo
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotDiariaProduccionRepBean obtenerDatosReporteNotificacionesProduccion(Date fechaInicio, Date fechaFin,
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad, Long codigoPuestoTrabajo, Long codigoProducto)
			throws LogicaException;

	/**
	 * @param datoreporteDTO
	 * @param posicion
	 * @return
	 */
	public Double obtenerValorDeVariableSegunValorColumna(DatoReporteDTO datoreporteDTO, Short posicion);

	public abstract ResumenNotificacionRepBean obtenerDatosReporteResumenNotificacionorFecha(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoPuestoTrabajo, Long codigoProducto, String fechaInicio,
			String fechaFin) throws LogicaException;

	/**
	 * @param datoReporteDTO
	 * @return
	 */
	public abstract boolean validaValoresIngresados(DatoReporteDTO datoReporteDTO);

	/**
	 * Obtiene las notificaciondes de produccion segun parametros enviados
	 * 
	 * @param valorSociedad
	 * @param valorUnidad
	 * @param valorLineaNegocio
	 * @param valorEstadoNotificacion
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<NotificacionProduccionBean> obtenerNotificacionesProduccion(Long valorLineaNegocio,
			Long valorEstadoNotificacion, Long valorPuestoTrabajo, Date fechaInicio, Date fechaFin) throws LogicaException;

	public abstract boolean eliminarPorCodigoNotificacionPuestoTrabajo(Long codigoNotificacion, Long valorPuestoTrabajo,
			UsuarioBean usuarioBean) throws LogicaException;

	public abstract ReporteDosificacion generarReporteDosificacion(Long valorUnidad, Long valorProceso, Long valorProducto,
			Long valorPuestoTrabajo, String fechaInicio, String fechaFin) throws LogicaException;

}
