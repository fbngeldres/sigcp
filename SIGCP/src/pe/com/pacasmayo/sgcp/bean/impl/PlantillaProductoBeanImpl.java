package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ColumnaPlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaReporteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class PlantillaProductoBeanImpl implements PlantillaProductoBean {

	private Long codigo;
	private PlantillaReporteBean plantillaReporte;
	private ProductoBean producto;
	private Date fecha;
	private Integer version;
	private List<ColumnaPlantillaProductoBean> columnas;

	public PlantillaProductoBeanImpl() {

	}

	public List<ColumnaPlantillaProductoBean> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<ColumnaPlantillaProductoBean> columnas) {
		this.columnas = columnas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#setCodigo
	 * (java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#
	 * getPlantillaReporte()
	 */
	public PlantillaReporteBean getPlantillaReporte() {
		return plantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#
	 * setPlantillaReporte(pe.com.pacasmayo.sgcp.bean.PlantillaReporteBean)
	 */
	public void setPlantillaReporte(PlantillaReporteBean plantillaReporte) {
		this.plantillaReporte = plantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#getProducto
	 * ()
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean#setProducto
	 * (pe.com.pacasmayo.sgcp.bean.ProductoBean)
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
