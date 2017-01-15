package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class LineaNegocioDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoLineanegocio;
	private String nombreLineanegocio;
	private String descripcionLineanegocio;
	private Long codigoSccLineanegocio;

	public Long getPkCodigoLineanegocio() {
		return pkCodigoLineanegocio;
	}

	public void setPkCodigoLineanegocio(Long pkCodigoLineanegocio) {
		this.pkCodigoLineanegocio = pkCodigoLineanegocio;
	}

	public String getNombreLineanegocio() {
		return this.nombreLineanegocio;
	}

	public void setNombreLineanegocio(String nombreLineanegocio) {
		this.nombreLineanegocio = nombreLineanegocio;
	}

	public String getDescripcionLineanegocio() {
		return descripcionLineanegocio;
	}

	public void setDescripcionLineanegocio(String descripcionLineanegocio) {
		this.descripcionLineanegocio = descripcionLineanegocio;
	}

	public Long getCodigoSccLineanegocio() {
		return codigoSccLineanegocio;
	}

	public void setCodigoSccLineanegocio(Long codigoSccLineanegocio) {
		this.codigoSccLineanegocio = codigoSccLineanegocio;
	}

}
