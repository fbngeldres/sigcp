package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

public class ReporteParteTecnicoOperacionesSub_B_Bean {

	private List<ReporteParteTecnicoOperacionesSub_A_B_Bean> subReportePTPuestoTrabajoComponentes;

	public ReporteParteTecnicoOperacionesSub_B_Bean() {

	}

	public ReporteParteTecnicoOperacionesSub_B_Bean(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {

		subReportePTPuestoTrabajoComponentes = new ArrayList<ReporteParteTecnicoOperacionesSub_A_B_Bean>();
		ReporteParteTecnicoOperacionesSub_A_B_Bean bean = new ReporteParteTecnicoOperacionesSub_A_B_Bean(list);
		subReportePTPuestoTrabajoComponentes.add(bean);
	}

	/**
	 * @return the subReportePTPuestoTrabajoComponentes
	 */
	public List<ReporteParteTecnicoOperacionesSub_A_B_Bean> getSubReportePTPuestoTrabajoComponentes() {
		return subReportePTPuestoTrabajoComponentes;
	}

	/**
	 * @param subReportePTPuestoTrabajoComponentes the
	 *            subReportePTPuestoTrabajoComponentes to set
	 */
	public void setSubReportePTPuestoTrabajoComponentes(
			List<ReporteParteTecnicoOperacionesSub_A_B_Bean> subReportePTPuestoTrabajoComponentes) {
		this.subReportePTPuestoTrabajoComponentes = subReportePTPuestoTrabajoComponentes;
	}

}
