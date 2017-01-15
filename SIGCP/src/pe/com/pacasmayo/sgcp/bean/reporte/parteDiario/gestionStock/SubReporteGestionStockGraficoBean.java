package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock;

import java.util.List;

public class SubReporteGestionStockGraficoBean {
	private String nombre;
	private List<GestionStockGraficoBean> subReporte;

	public SubReporteGestionStockGraficoBean() {
		// subReporte = new ArrayList<ReporteProduccionBean>();
	}

	/**
	 * @return the subReporte
	 */
	public List<GestionStockGraficoBean> getSubReporte() {
		return subReporte;
	}

	/**
	 * @param subReporte the subReporte to set
	 */
	public void setSubReporte(List<GestionStockGraficoBean> subReporte) {
		this.subReporte = subReporte;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
