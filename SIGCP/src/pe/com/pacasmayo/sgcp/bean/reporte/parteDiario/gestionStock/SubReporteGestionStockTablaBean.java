package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock;

import java.util.List;

public class SubReporteGestionStockTablaBean {

	private String nombreDetalle;
	private List<GestionStockDetalleBean> subReporteDetalle;

	/**
	 * @return the nombreDetalle
	 */
	public String getNombreDetalle() {
		return nombreDetalle;
	}

	/**
	 * @param nombreDetalle the nombreDetalle to set
	 */
	public void setNombreDetalle(String nombreDetalle) {
		this.nombreDetalle = nombreDetalle;
	}

	/**
	 * @return the subReporteDetalle
	 */
	public List<GestionStockDetalleBean> getSubReporteDetalle() {
		return subReporteDetalle;
	}

	/**
	 * @param subReporteDetalle the subReporteDetalle to set
	 */
	public void setSubReporteDetalle(List<GestionStockDetalleBean> subReporteDetalle) {
		this.subReporteDetalle = subReporteDetalle;
	}

}
