package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

public class ReporteParteDiarioSub_A_A_Bean {
	private String puestoTrabajo;
	private List<ReporteParteDiarioSub_A_A_A_Bean> registroReportePuestoTrabajo = new ArrayList<ReporteParteDiarioSub_A_A_A_Bean>();
	private List<ReporteParteDiarioSub_A_A_B_Bean> registroReporteProducto = new ArrayList<ReporteParteDiarioSub_A_A_B_Bean>();

	/**
	 * 
	 */
	public ReporteParteDiarioSub_A_A_Bean() {
		super();
	}

	/**
	 * @param puestoTrabajo
	 * @param registroReportePuestoTrabajo
	 * @param registroReporteProducto
	 */
	public ReporteParteDiarioSub_A_A_Bean(String puestoTrabajo,
			List<ReporteParteDiarioSub_A_A_A_Bean> registroReportePuestoTrabajo,
			List<ReporteParteDiarioSub_A_A_B_Bean> registroReporteProducto) {
		super();
		this.puestoTrabajo = puestoTrabajo;
		this.registroReportePuestoTrabajo = registroReportePuestoTrabajo;
		this.registroReporteProducto = registroReporteProducto;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public List<ReporteParteDiarioSub_A_A_A_Bean> getRegistroReportePuestoTrabajo() {
		return registroReportePuestoTrabajo;
	}

	public void setRegistroReportePuestoTrabajo(List<ReporteParteDiarioSub_A_A_A_Bean> registroReportePuestoTrabajo) {
		this.registroReportePuestoTrabajo = registroReportePuestoTrabajo;
	}

	public List<ReporteParteDiarioSub_A_A_B_Bean> getRegistroReporteProducto() {
		return registroReporteProducto;
	}

	public void setRegistroReporteProducto(List<ReporteParteDiarioSub_A_A_B_Bean> registroReporteProducto) {
		this.registroReporteProducto = registroReporteProducto;
	}

}
