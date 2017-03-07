package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public class SubReporteCombustibleBean {
	private String titulo = "PRUEBA2";
	private List<ReporteParteTecnicoSub_A_Bean> reporteCombustible; // Sub
																	// Reporte
																	// de
																	// Combustible
																	// Solido

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
	 * @return the reporteCombustible
	 */
	public List<ReporteParteTecnicoSub_A_Bean> getReporteCombustible() {
		return reporteCombustible;
	}

	/**
	 * @param reporteCombustible the reporteCombustible to set
	 */
	public void setReporteCombustible(List<ReporteParteTecnicoSub_A_Bean> reporteCombustible) {
		this.reporteCombustible = reporteCombustible;
	}

}
