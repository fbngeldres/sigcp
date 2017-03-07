package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.reporte.ReporteGeneral;

public class ReportePuestoTrabajoProduccionBean extends ReporteGeneral {

	private List<SubReporteGraficoPuestoTrabajoProduccionBean> subReporteGrafico;
	private List<SubReporteDetallePuestoTrabajoProduccionBean> subReporteDetalle;

	public ReportePuestoTrabajoProduccionBean() {
		subReporteGrafico = new ArrayList<SubReporteGraficoPuestoTrabajoProduccionBean>();
		subReporteDetalle = new ArrayList<SubReporteDetallePuestoTrabajoProduccionBean>();
	}

	/**
	 * @return the subReporteGrafico
	 */
	public List<SubReporteGraficoPuestoTrabajoProduccionBean> getSubReporteGrafico() {
		return subReporteGrafico;
	}

	/**
	 * @param subReporteGrafico the subReporteGrafico to set
	 */
	public void setSubReporteGrafico(List<SubReporteGraficoPuestoTrabajoProduccionBean> subReporteGrafico) {
		this.subReporteGrafico = subReporteGrafico;
	}

	/**
	 * @return the subReporteDetalle
	 */
	public List<SubReporteDetallePuestoTrabajoProduccionBean> getSubReporteDetalle() {
		return subReporteDetalle;
	}

	/**
	 * @param subReporteDetalle the subReporteDetalle to set
	 */
	public void setSubReporteDetalle(List<SubReporteDetallePuestoTrabajoProduccionBean> subReporteDetalle) {
		this.subReporteDetalle = subReporteDetalle;
	}

}
