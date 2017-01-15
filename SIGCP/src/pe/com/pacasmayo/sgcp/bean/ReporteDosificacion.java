package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public class ReporteDosificacion extends ResumenNotificacionRepBean {
	private String periodo;
	private List<SubReporteDosificacion> detallereporte;

	/**
	 * @return the detallereporte
	 */
	public List<SubReporteDosificacion> getDetallereporte() {
		return detallereporte;
	}

	/**
	 * @param detallereporte the detallereporte to set
	 */
	public void setDetallereporte(List<SubReporteDosificacion> detallereporte) {
		this.detallereporte = detallereporte;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
