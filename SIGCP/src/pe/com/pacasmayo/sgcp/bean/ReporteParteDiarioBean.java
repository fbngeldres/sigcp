package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

public class ReporteParteDiarioBean {
	private String division;
	private String sociedad;
	private String unidad;
	private String lineaNegocio;
	private String puestoTrabajo;
	private String proceso;
	private String producto;
	// private String fechaInicio;
	// private String fechaFin;
	private String mes;
	private List<ReporteParteDiarioSub_A_Bean> reporteParteDiarioSub = new ArrayList<ReporteParteDiarioSub_A_Bean>();

	/**
	 * 
	 */
	public ReporteParteDiarioBean() {
		super();
	}

	/**
	 * @param division
	 * @param sociedad
	 * @param unidad
	 * @param lineaNegocio
	 * @param puestoTrabajo
	 * @param proceso
	 * @param producto
	 * @param fechaInicio
	 * @param fechaFin
	 * @param reporteParteDiarioSub
	 */
	public ReporteParteDiarioBean(String division, String sociedad, String unidad, String lineaNegocio, String puestoTrabajo,
			String proceso, String producto, String mes, List<ReporteParteDiarioSub_A_Bean> reporteParteDiarioSub) {
		super();
		this.division = division;
		this.sociedad = sociedad;
		this.unidad = unidad;
		this.lineaNegocio = lineaNegocio;
		this.puestoTrabajo = puestoTrabajo;
		this.proceso = proceso;
		this.producto = producto;
		// this.fechaInicio = fechaInicio;
		// this.fechaFin = fechaFin;
		this.mes = mes;
		this.reporteParteDiarioSub = reporteParteDiarioSub;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSociedad() {
		return sociedad;
	}

	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getLineaNegocio() {
		return lineaNegocio;
	}

	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	// public String getFechaInicio() {
	// return fechaInicio;
	// }
	//
	// public void setFechaInicio(String fechaInicio) {
	// this.fechaInicio = fechaInicio;
	// }
	//
	// public String getFechaFin() {
	// return fechaFin;
	// }
	//
	// public void setFechaFin(String fechaFin) {
	// this.fechaFin = fechaFin;
	// }

	public List<ReporteParteDiarioSub_A_Bean> getReporteParteDiarioSub() {
		return reporteParteDiarioSub;
	}

	public void setReporteParteDiarioSub(List<ReporteParteDiarioSub_A_Bean> reporteParteDiarioSub) {
		this.reporteParteDiarioSub = reporteParteDiarioSub;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}