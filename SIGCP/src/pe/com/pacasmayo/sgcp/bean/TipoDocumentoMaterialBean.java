package pe.com.pacasmayo.sgcp.bean;

public interface TipoDocumentoMaterialBean {

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setCodigo(java
	 * .lang.Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getNombre()
	 */
	public abstract String getNombre();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setNombre(java
	 * .lang.String)
	 */
	public abstract void setNombre(String nombre);

}