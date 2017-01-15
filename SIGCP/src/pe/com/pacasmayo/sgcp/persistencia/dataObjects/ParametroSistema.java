package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Accion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ParametroSistema implements java.io.Serializable {

	// Fields

	private Long pkCodigoParametro;
	private String nombreParametro;
	private String descripcionParametro;
	private String valorParametro;

	public ParametroSistema() {

	}

	/**
	 * @param pkCodigoParametro
	 * @param nombreParametro
	 * @param descripcionParametro
	 * @param valorParametro
	 */
	public ParametroSistema(Long pkCodigoParametro, String nombreParametro, String descripcionParametro, String valorParametro) {
		this.pkCodigoParametro = pkCodigoParametro;
		this.nombreParametro = nombreParametro;
		this.descripcionParametro = descripcionParametro;
		this.valorParametro = valorParametro;
	}

	/**
	 * @return the pkCodigoParametro
	 */
	public Long getPkCodigoParametro() {
		return pkCodigoParametro;
	}

	/**
	 * @param pkCodigoParametro the pkCodigoParametro to set
	 */
	public void setPkCodigoParametro(Long pkCodigoParametro) {
		this.pkCodigoParametro = pkCodigoParametro;
	}

	/**
	 * @return the nombreParametro
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @param nombreParametro the nombreParametro to set
	 */
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	/**
	 * @return the descripcionParametro
	 */
	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	/**
	 * @param descripcionParametro the descripcionParametro to set
	 */
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

}