package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean;
import pe.com.pacasmayo.sgcp.bean.EstadoColumnaReporteBean;

public class EstadoColumnaReporteBeanImpl implements EstadoColumnaReporteBean {

	private Long codigo;
	private String nombreEstadoColumnaReporte;
	private ColumnaReporteBean columnaReporte;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#
	 * getNombreEstadoPlantillaReporte()
	 */
	public String getNombreEstadoColumnaReporte() {
		return nombreEstadoColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#
	 * setNombreEstadoPlantillaReporte(java.lang.String)
	 */
	public void setNombreEstadoColumnaReporte(String nombreEstadoColumnaReporte) {
		this.nombreEstadoColumnaReporte = nombreEstadoColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#getColumnaReporte
	 * ()
	 */
	public ColumnaReporteBean getColumnaReporte() {
		return columnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoColumnaReporteBean#setColumnaReporte
	 * (pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean)
	 */
	public void setColumnaReporte(ColumnaReporteBean columnaReporte) {
		this.columnaReporte = columnaReporte;
	}
}
