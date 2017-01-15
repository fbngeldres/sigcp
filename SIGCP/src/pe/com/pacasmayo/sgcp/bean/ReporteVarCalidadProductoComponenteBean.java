package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;

public class ReporteVarCalidadProductoComponenteBean {

	private Componente componente;
	private List<ReporteEquivalenciasccvarcalidadBean> componenteDescripcion;

	/**
	 * Producto Para el Reporte Variable Calidad
	 */
	private Producto producto;

	/**
	 * Reporte Variacion
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

	/**
	 * @return the componente
	 */
	public Componente getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	/**
	 * @return the componenteDescripcion
	 */
	public List<ReporteEquivalenciasccvarcalidadBean> getComponenteDescripcion() {
		return componenteDescripcion;
	}

	/**
	 * @param componenteDescripcion the componenteDescripcion to set
	 */
	public void setComponenteDescripcion(List<ReporteEquivalenciasccvarcalidadBean> componenteDescripcion) {
		this.componenteDescripcion = componenteDescripcion;
	}

	public int getColspan() {
		if (componenteDescripcion != null) {
			return componenteDescripcion.size();
		}
		return 1;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
