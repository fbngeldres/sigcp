package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;

public class ReporteVarCalidadProductoBean {

	private Producto producto;
	private List<ReporteVarCalidadProductoComponenteBean> productoComponente;

	/**
	 * Variable Variacion
	 * 
	 * @return
	 */
	private ProductoBean productoBean;

	/**
	 * @return the productoBean
	 */
	public ProductoBean getProductoBean() {
		return productoBean;
	}

	/**
	 * @param productoBean the productoBean to set
	 */
	public void setProductoBean(ProductoBean productoBean) {
		this.productoBean = productoBean;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ReporteVarCalidadProductoComponenteBean> getProductoComponente() {
		return productoComponente;
	}

	public void setProductoComponente(List<ReporteVarCalidadProductoComponenteBean> productoComponente) {
		this.productoComponente = productoComponente;
	}

	public int getColspan() {
		if (productoComponente != null) {
			int suma = 0;
			for (ReporteVarCalidadProductoComponenteBean pro : productoComponente) {
				suma += pro.getColspan();
			}
			return suma;
		}
		return 1;
	}

}
