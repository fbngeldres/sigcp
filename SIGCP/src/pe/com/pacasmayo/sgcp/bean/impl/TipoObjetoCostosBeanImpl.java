package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TipoObjetoCostosBean;

public class TipoObjetoCostosBeanImpl implements TipoObjetoCostosBean {

	private Long pkTipoObjetoCosto;
	private String nombre;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoObjetoCostoBean#getPkTipoObjetoCosto
	 * ()
	 */
	public Long getPkTipoObjetoCosto() {
		return pkTipoObjetoCosto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoObjetoCostoBean#setPkTipoObjetoCosto
	 * (java.lang.Long)
	 */
	public void setPkTipoObjetoCosto(Long pkTipoObjetoCosto) {
		this.pkTipoObjetoCosto = pkTipoObjetoCosto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoObjetoCostoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoObjetoCostoBean#setNombre(java.lang
	 * .String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
