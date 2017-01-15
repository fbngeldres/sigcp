package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.reporte.ReporteGeneral;

public class ReporteGestionStockProduccionBean extends ReporteGeneral {
	private List<SubReporteGestionStockTablaBean> reporteDetalle;
	private List<SubReporteGestionStockGraficoBean> reporteGraficos;

	public ReporteGestionStockProduccionBean() {
		reporteGraficos = new ArrayList<SubReporteGestionStockGraficoBean>();
		reporteDetalle = new ArrayList<SubReporteGestionStockTablaBean>();

	}

	/**
	 * @return the reporteDetalle
	 */
	public List<SubReporteGestionStockTablaBean> getReporteDetalle() {
		return reporteDetalle;
	}

	/**
	 * @param reporteDetalle the reporteDetalle to set
	 */
	public void setReporteDetalle(List<SubReporteGestionStockTablaBean> reporteDetalle) {
		this.reporteDetalle = reporteDetalle;
	}

	/**
	 * @return the reporteGraficos
	 */
	public List<SubReporteGestionStockGraficoBean> getReporteGraficos() {
		return reporteGraficos;
	}

	/**
	 * @param reporteGraficos the reporteGraficos to set
	 */
	public void setReporteGraficos(List<SubReporteGestionStockGraficoBean> reporteGraficos) {
		this.reporteGraficos = reporteGraficos;
	}

}
