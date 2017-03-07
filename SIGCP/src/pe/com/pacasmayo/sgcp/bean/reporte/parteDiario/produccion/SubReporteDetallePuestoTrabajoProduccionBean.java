package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion;

import java.util.List;

public class SubReporteDetallePuestoTrabajoProduccionBean {
	private String tituloSubReporte;
	private List<PuestoTrabajoProduccionDetalleBean> subReporteDetalle;
	
	private String tituloSubReporteFactores;
	private List<PuestoTrabajoProduccionDetalleBean> subReporteDetalleFactores;

	/**
	 * @return the tituloSubReporte
	 */
	public String getTituloSubReporte() {
		return tituloSubReporte;
	}

	/**
	 * @param tituloSubReporte the tituloSubReporte to set
	 */
	public void setTituloSubReporte(String tituloSubReporte) {
		this.tituloSubReporte = tituloSubReporte;
	}

	/**
	 * @return the subReporteDetalle
	 */
	public List<PuestoTrabajoProduccionDetalleBean> getSubReporteDetalle() {
		return subReporteDetalle;
	}

	/**
	 * @param subReporteDetalle the subReporteDetalle to set
	 */
	public void setSubReporteDetalle(List<PuestoTrabajoProduccionDetalleBean> subReporteDetalle) {
		this.subReporteDetalle = subReporteDetalle;
	}

	public String getTituloSubReporteFactores() {
		return tituloSubReporteFactores;
	}

	public void setTituloSubReporteFactores(String tituloSubReporteFactores) {
		this.tituloSubReporteFactores = tituloSubReporteFactores;
	}

	public List<PuestoTrabajoProduccionDetalleBean> getSubReporteDetalleFactores() {
		return subReporteDetalleFactores;
	}

	public void setSubReporteDetalleFactores(List<PuestoTrabajoProduccionDetalleBean> subReporteDetalleFactores) {
		this.subReporteDetalleFactores = subReporteDetalleFactores;
	}
	
	

}
