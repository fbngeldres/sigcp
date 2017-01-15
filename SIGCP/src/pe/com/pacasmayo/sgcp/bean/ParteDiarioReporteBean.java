package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

public class ParteDiarioReporteBean {
	private String titulo;
	private String unidad;
	private String sociedad;
	private String lineaNegocio;
	private String proceso;
	private String fechaInicio;
	private String fechaFin;
	private String fecha;
	private List<RegistroParteDiarioReporteBean> subReporteParteDiario = new ArrayList<RegistroParteDiarioReporteBean>();

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return the proceso
	 */
	public String getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the subReporteParteDiario
	 */
	public List<RegistroParteDiarioReporteBean> getSubReporteParteDiario() {
		return subReporteParteDiario;
	}

	/**
	 * @param subReporteParteDiario the subReporteParteDiario to set
	 */
	public void setSubReporteParteDiario(List<RegistroParteDiarioReporteBean> subReporteParteDiario) {
		this.subReporteParteDiario = subReporteParteDiario;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
