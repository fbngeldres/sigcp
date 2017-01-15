package pe.com.pacasmayo.sgcp.bean.impl;

public class ResultadoBeanImpl {

	private Boolean exitoOperacion;
	private String mensajeOperacion;

	public ResultadoBeanImpl() {
		exitoOperacion = Boolean.FALSE;
		mensajeOperacion = "";
	}

	/**
	 * @return the exitoOperacion
	 */
	public Boolean getExitoOperacion() {
		return exitoOperacion;
	}

	/**
	 * @param exitoOperacion the exitoOperacion to set
	 */
	public void setExitoOperacion(Boolean exitoOperacion) {
		this.exitoOperacion = exitoOperacion;
	}

	/**
	 * @return the mensajeOperacion
	 */
	public String getMensajeOperacion() {
		return mensajeOperacion;
	}

	/**
	 * @param mensajeOperacion the mensajeOperacion to set
	 */
	public void setMensajeOperacion(String mensajeOperacion) {
		this.mensajeOperacion = mensajeOperacion;
	}

}
