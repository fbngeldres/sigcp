package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/**
 * @author Fabian
 */
public class ReporteParteTecnicoBean {
	private String division;
	private String sociedad;
	private String unidad;
	private String lineaNegocio;
	private String producto;
	private String mes;
	private String anio;
	private String tituloReporte;
	private String estado;

	// Resumen
	private List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes;

	// RESUMEN OPERACIONES
	private List<ReporteParteTecnicoOperacionesSub_B_Bean> subReportePTOperacionesComponentes;

	// Consumo por Puesto de Trabajo
	private List<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean> reporteConsumoPorPuestoTrabajo;

	// Lista Producto Terminado
	private List<ReporteProductoTerminado_Bean> reporteProductoTerminado;

	// Lista Hoja Cal
	private List<ReporteHojaCal_Bean> reporteHojaCal;

	// Combustibles
	private List<ReporteCombustibles_Bean> reporteCombustibles;

	// Anexo
	private List<ReporteAnexo_Bean> reporteAnexo;

	// Reporte Cantera

	private List<ReporteParteTecnicoSub_A_Bean> subReporteCanteras;

	// Reporte envasado
	private List<ReporteParteTecnicoSub_A_Bean> subReporteEmbolsadura;

	// TM envasado
	private List<ReporteParteTecnicoOperacionesSub_A_B_Bean> subReporteProduccionEnvasado;

	public ReporteParteTecnicoBean() {

	}

	/**
	 * @param division
	 * @param sociedad
	 * @param unidad
	 * @param lineaNegocio
	 * @param puestoTrabajo
	 * @param proceso
	 * @param producto
	 * @param mes
	 * @param anio
	 * @param subReportePtComponentes
	 */
	public ReporteParteTecnicoBean(String titulo, String division, String sociedad, String unidad, String lineaNegocio,
			String producto, String mes, String anio, String estado) {

		this.tituloReporte = titulo;
		this.division = division;
		this.sociedad = sociedad;
		this.unidad = unidad;
		this.lineaNegocio = lineaNegocio;
		this.estado = estado;
		this.producto = producto;
		this.mes = mes;
		this.anio = anio;

	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the unidad
	 */
	public String getUnidad() {
		return unidad;
	}

	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/**
	 * @return the lineaNegocio
	 */
	public String getLineaNegocio() {
		return lineaNegocio;
	}

	/**
	 * @param lineaNegocio the lineaNegocio to set
	 */
	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/**
	 * @return the puestoTrabajo
	 */
	// public String getPuestoTrabajo() {
	// return puestoTrabajo;
	// }
	//
	// /**
	// * @param puestoTrabajo the puestoTrabajo to set
	// */
	// public void setPuestoTrabajo(String puestoTrabajo) {
	// this.puestoTrabajo = puestoTrabajo;
	// }
	/**
	 * @return the proceso
	 */
	// public String getProceso() {
	// return proceso;
	// }
	//
	// /**
	// * @param proceso the proceso to set
	// */
	// public void setProceso(String proceso) {
	// this.proceso = proceso;
	// }
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the subReportePtComponentes
	 */
	public List<ReporteParteTecnicoSub_A_Bean> getSubReportePtComponentes() {
		return subReportePtComponentes;
	}

	/**
	 * @param subReportePtComponentes the subReportePtComponentes to set
	 */
	public void setSubReportePtComponentes(List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes) {
		this.subReportePtComponentes = subReportePtComponentes;
	}

	/**
	 * @return the tituloReporte
	 */
	public String getTituloReporte() {
		return tituloReporte;
	}

	/**
	 * @param tituloReporte the tituloReporte to set
	 */
	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	// /**
	// * @return the subReportePTPuestoTrabajoComponentes
	// */
	// public List<ReporteParteTecnicoSub_B_Bean>
	// getSubReportePTPuestoTrabajoComponentes() {
	// return subReportePTPuestoTrabajoComponentes;
	// }
	//
	// /**
	// * @param subReportePTPuestoTrabajoComponentes the
	// * subReportePTPuestoTrabajoComponentes to set
	// */
	// public void
	// setSubReportePTPuestoTrabajoComponentes(List<ReporteParteTecnicoSub_B_Bean>
	// subReportePTPuestoTrabajoComponentes) {
	// this.subReportePTPuestoTrabajoComponentes =
	// subReportePTPuestoTrabajoComponentes;
	// }

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the subReportePTOperacionesComponentes
	 */
	public List<ReporteParteTecnicoOperacionesSub_B_Bean> getSubReportePTOperacionesComponentes() {
		return subReportePTOperacionesComponentes;
	}

	/**
	 * @param subReportePTOperacionesComponentes the
	 *            subReportePTOperacionesComponentes to set
	 */
	public void setSubReportePTOperacionesComponentes(
			List<ReporteParteTecnicoOperacionesSub_B_Bean> subReportePTOperacionesComponentes) {
		this.subReportePTOperacionesComponentes = subReportePTOperacionesComponentes;
	}

	public List<ReporteProductoTerminado_Bean> getReporteProductoTerminado() {
		return reporteProductoTerminado;
	}

	public void setReporteProductoTerminado(List<ReporteProductoTerminado_Bean> reporteProductoTerminado) {
		this.reporteProductoTerminado = reporteProductoTerminado;
	}

	public List<ReporteCombustibles_Bean> getReporteCombustibles() {
		return reporteCombustibles;
	}

	public void setReporteCombustibles(List<ReporteCombustibles_Bean> reporteCombustibles) {
		this.reporteCombustibles = reporteCombustibles;
	}

	public List<ReporteAnexo_Bean> getReporteAnexo() {
		return reporteAnexo;
	}

	public void setReporteAnexo(List<ReporteAnexo_Bean> reporteAnexo) {
		this.reporteAnexo = reporteAnexo;
	}

	/**
	 * @return the reporteConsumoPorPuestoTrabajo
	 */
	public List<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean> getReporteConsumoPorPuestoTrabajo() {
		return reporteConsumoPorPuestoTrabajo;
	}

	/**
	 * @param reporteConsumoPorPuestoTrabajo the reporteConsumoPorPuestoTrabajo
	 *            to set
	 */
	public void setReporteConsumoPorPuestoTrabajo(
			List<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean> reporteConsumoPorPuestoTrabajo) {
		this.reporteConsumoPorPuestoTrabajo = reporteConsumoPorPuestoTrabajo;
	}

	/**
	 * @return the reporteHojaCal
	 */
	public List<ReporteHojaCal_Bean> getReporteHojaCal() {
		return reporteHojaCal;
	}

	/**
	 * @param reporteHojaCal the reporteHojaCal to set
	 */
	public void setReporteHojaCal(List<ReporteHojaCal_Bean> reporteHojaCal) {
		this.reporteHojaCal = reporteHojaCal;
	}

	/**
	 * @return the subReporteCanteras
	 */
	public List<ReporteParteTecnicoSub_A_Bean> getSubReporteCanteras() {
		return subReporteCanteras;
	}

	/**
	 * @param subReporteCanteras the subReporteCanteras to set
	 */
	public void setSubReporteCanteras(List<ReporteParteTecnicoSub_A_Bean> subReporteCanteras) {
		this.subReporteCanteras = subReporteCanteras;
	}

	public List<ReporteParteTecnicoSub_A_Bean> getSubReporteEmbolsadura() {
		return subReporteEmbolsadura;
	}

	public void setSubReporteEmbolsadura(List<ReporteParteTecnicoSub_A_Bean> subReporteEmbolsadura) {
		this.subReporteEmbolsadura = subReporteEmbolsadura;
	}

	public List<ReporteParteTecnicoOperacionesSub_A_B_Bean> getSubReporteProduccionEnvasado() {
		return subReporteProduccionEnvasado;
	}

	public void setSubReporteProduccionEnvasado(List<ReporteParteTecnicoOperacionesSub_A_B_Bean> subReporteProduccionEnvasado) {
		this.subReporteProduccionEnvasado = subReporteProduccionEnvasado;
	}

}