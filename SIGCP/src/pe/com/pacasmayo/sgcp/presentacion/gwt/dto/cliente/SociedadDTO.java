package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class SociedadDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pkCodigoSociedad;
	private Long codigoSccSociedad;
	private String nombreSociedad;
	private String siglasSociedad;
	private String descripcionSociedad;
	private String codigoSapSociedad;

	public Long getPkCodigoSociedad() {
		return pkCodigoSociedad;
	}

	public void setPkCodigoSociedad(Long pkCodigoSociedad) {
		this.pkCodigoSociedad = pkCodigoSociedad;
	}

	public Long getCodigoSccSociedad() {
		return codigoSccSociedad;
	}

	public void setCodigoSccSociedad(Long codigoSccSociedad) {
		this.codigoSccSociedad = codigoSccSociedad;
	}

	public String getNombreSociedad() {
		return nombreSociedad;
	}

	public void setNombreSociedad(String nombreSociedad) {
		this.nombreSociedad = nombreSociedad;
	}

	public String getSiglasSociedad() {
		return siglasSociedad;
	}

	public void setSiglasSociedad(String siglasSociedad) {
		this.siglasSociedad = siglasSociedad;
	}

	public String getDescripcionSociedad() {
		return descripcionSociedad;
	}

	public void setDescripcionSociedad(String descripcionSociedad) {
		this.descripcionSociedad = descripcionSociedad;
	}

	public String getCodigoSapSociedad() {
		return codigoSapSociedad;
	}

	public void setCodigoSapSociedad(String codigoSapSociedad) {
		this.codigoSapSociedad = codigoSapSociedad;
	}
}
