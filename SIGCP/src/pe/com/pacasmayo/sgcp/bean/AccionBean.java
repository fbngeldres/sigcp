package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface AccionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getPresentacionAccion()
	 */
	public abstract String getPresentacionAccion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setPresentacionAccion(java
	 * .lang.String)
	 */
	public abstract void setPresentacionAccion(String presentacionAccion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getUrlAccion()
	 */
	public abstract String getUrlAccion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setUrlAccion(java.lang.String)
	 */
	public abstract void setUrlAccion(String urlAccion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getOpcionesList()
	 */
	public abstract List<OpcionBean> getOpcionesList();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setOpcionesList(java.util.
	 * List)
	 */
	public abstract void setOpcionesList(List<OpcionBean> opcionesList);

}