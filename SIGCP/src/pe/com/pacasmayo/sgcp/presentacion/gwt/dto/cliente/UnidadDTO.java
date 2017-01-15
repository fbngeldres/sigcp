package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class UnidadDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pkCodigoUnidad;
	private Long codigoSccUnidad;
	private String nombreUnidad;
	private String descripcionUnidad;
	private String codigoSapUnidad;

	public Long getPkCodigoUnidad() {
		return pkCodigoUnidad;
	}

	public void setPkCodigoUnidad(Long pkCodigoUnidad) {
		this.pkCodigoUnidad = pkCodigoUnidad;
	}

	public Long getCodigoSccUnidad() {
		return codigoSccUnidad;
	}

	public void setCodigoSccUnidad(Long codigoSccUnidad) {
		this.codigoSccUnidad = codigoSccUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}

	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}

	public String getCodigoSapUnidad() {
		return codigoSapUnidad;
	}

	public void setCodigoSapUnidad(String codigoSapUnidad) {
		this.codigoSapUnidad = codigoSapUnidad;
	}
}
