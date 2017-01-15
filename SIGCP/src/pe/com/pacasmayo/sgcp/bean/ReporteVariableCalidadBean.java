package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;

public class ReporteVariableCalidadBean {

	private Proceso proceso;
	private List<ReporteVarCalidadPuestoBean> reposteVariableCalidadPuestoTrabajo;

	/**
	 * Para el reporte de ProductoVariableCalidad
	 */
	private List<ReporteVarCalidadProductoComponenteBean> productosVarableCalidad;

	/**
	 * @return
	 */
	private ProcesoBean procesoBean;

	/**
	 * @return the procesoBean
	 */
	public ProcesoBean getProcesoBean() {
		return procesoBean;
	}

	/**
	 * @param procesoBean the procesoBean to set
	 */
	public void setProcesoBean(ProcesoBean procesoBean) {
		this.procesoBean = procesoBean;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public List<ReporteVarCalidadPuestoBean> getReposteVariableCalidadPuestoTrabajo() {
		return reposteVariableCalidadPuestoTrabajo;
	}

	public void setReposteVariableCalidadPuestoTrabajo(List<ReporteVarCalidadPuestoBean> reposteVariableCalidadPuestoTrabajo) {
		this.reposteVariableCalidadPuestoTrabajo = reposteVariableCalidadPuestoTrabajo;
	}

	public int getColspan() {
		if (reposteVariableCalidadPuestoTrabajo != null) {
			int suma = 0;
			for (ReporteVarCalidadPuestoBean pro : reposteVariableCalidadPuestoTrabajo) {
				suma += pro.getColspan();
			}
			return suma;
		}
		return 1;
	}

	/**
	 * @return the productosVarableCalidad
	 */
	public List<ReporteVarCalidadProductoComponenteBean> getProductosVarableCalidad() {
		return productosVarableCalidad;
	}

	/**
	 * @param productosVarableCalidad the productosVarableCalidad to set
	 */
	public void setProductosVarableCalidad(List<ReporteVarCalidadProductoComponenteBean> productosVarableCalidad) {
		this.productosVarableCalidad = productosVarableCalidad;
	}

	public int getColspanRepProdCalidad() {
		if (productosVarableCalidad != null) {
			int suma = 0;
			for (ReporteVarCalidadProductoComponenteBean pro : productosVarableCalidad) {
				suma += pro.getColspan();
			}
			return suma;
		}
		return 1;
	}

}
