package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class PuestoTrabajoDTO implements java.io.Serializable {

	private Long pkCodigoPuestotrabajo;
	private String nombrePuestotrabajo;
	private String descripcionPuestotrabajo;
	private String siglasPuestotrabajo;
	private String codigoSapPuestotrabajo;
	private Long codSccPuestotrabajo;

	public Long getPkCodigoPuestotrabajo() {
		return pkCodigoPuestotrabajo;
	}

	public void setPkCodigoPuestotrabajo(Long pkCodigoPuestotrabajo) {
		this.pkCodigoPuestotrabajo = pkCodigoPuestotrabajo;
	}

	public String getNombrePuestotrabajo() {
		return nombrePuestotrabajo;
	}

	public void setNombrePuestotrabajo(String nombrePuestotrabajo) {
		this.nombrePuestotrabajo = nombrePuestotrabajo;
	}

	public String getDescripcionPuestotrabajo() {
		return descripcionPuestotrabajo;
	}

	public void setDescripcionPuestotrabajo(String descripcionPuestotrabajo) {
		this.descripcionPuestotrabajo = descripcionPuestotrabajo;
	}

	public String getSiglasPuestotrabajo() {
		return siglasPuestotrabajo;
	}

	public void setSiglasPuestotrabajo(String siglasPuestotrabajo) {
		this.siglasPuestotrabajo = siglasPuestotrabajo;
	}

	public String getCodigoSapPuestotrabajo() {
		return codigoSapPuestotrabajo;
	}

	public void setCodigoSapPuestotrabajo(String codigoSapPuestotrabajo) {
		this.codigoSapPuestotrabajo = codigoSapPuestotrabajo;
	}

	public Long getCodSccPuestotrabajo() {
		return codSccPuestotrabajo;
	}

	public void setCodSccPuestotrabajo(Long codSccPuestotrabajo) {
		this.codSccPuestotrabajo = codSccPuestotrabajo;
	}
}
