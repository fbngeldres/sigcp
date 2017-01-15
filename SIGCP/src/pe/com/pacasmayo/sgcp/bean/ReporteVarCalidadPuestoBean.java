package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;

public class ReporteVarCalidadPuestoBean {

	private Puestotrabajo puestotrabajo;
	private List<ReporteVarCalidadProductoBean> productos;

	/**
	 * Reprote Variacion
	 */
	private PuestoTrabajoBean puestoTrabajoBean;
	private List<ReporteVarCalidadProductoComponenteBean> listaProductos;

	/**
	 * @return the listaProductos
	 */
	public List<ReporteVarCalidadProductoComponenteBean> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos the listaProductos to set
	 */
	public void setListaProductos(List<ReporteVarCalidadProductoComponenteBean> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * @return the puestoTrabajoBean
	 */
	public PuestoTrabajoBean getPuestoTrabajoBean() {
		return puestoTrabajoBean;
	}

	/**
	 * @param puestoTrabajoBean the puestoTrabajoBean to set
	 */
	public void setPuestoTrabajoBean(PuestoTrabajoBean puestoTrabajoBean) {
		this.puestoTrabajoBean = puestoTrabajoBean;
	}

	public Puestotrabajo getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public List<ReporteVarCalidadProductoBean> getProductos() {
		return productos;
	}

	public void setProductos(List<ReporteVarCalidadProductoBean> productos) {
		this.productos = productos;
	}

	public int getColspan() {
		if (productos != null) {
			int suma = 0;
			for (ReporteVarCalidadProductoBean pro : productos) {
				suma += pro.getColspan();
			}
			return suma;
		}
		return 1;
	}

}
