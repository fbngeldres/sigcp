package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.bean.TableroPuestoTrabajoBean;

public class TableroPuestoTrabajoBeanImpl implements TableroPuestoTrabajoBean {

	private Long codigo;
	private PuestoTrabajoBean puestoTrabajoBean;
	private TableroControlBean tableroControlBean;

	public Long getCodigo() {

		return codigo;
	}

	public String getDescripcion() {

		return "";
	}

	public String getNombre() {

		return "";
	}

	public void setCodigo(Long codigo) {

		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {

	}

	public void setNombre(String nombre) {

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

	/**
	 * @return the tableroControlBean
	 */
	public TableroControlBean getTableroControlBean() {
		return tableroControlBean;
	}

	/**
	 * @param tableroControlBean the tableroControlBean to set
	 */
	public void setTableroControlBean(TableroControlBean tableroControlBean) {
		this.tableroControlBean = tableroControlBean;
	}

}
