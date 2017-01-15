package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class ColumnareporteDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570494922767808485L;
	private Long pkCodigoColumnareporte;
	private Long pkCodigoEstadocolumnareporte;
	private Long pkCodigoPlantillareporte;
	private String nombreColumnareporte;
	private Short posicionColumnareporte;

	/**
	 * @return the pkCodigoColumnareporte
	 */
	public Long getPkCodigoColumnareporte() {
		return pkCodigoColumnareporte;
	}

	/**
	 * @param pkCodigoColumnareporte the pkCodigoColumnareporte to set
	 */
	public void setPkCodigoColumnareporte(Long pkCodigoColumnareporte) {
		this.pkCodigoColumnareporte = pkCodigoColumnareporte;
	}

	/**
	 * @return the pkCodigoEstadocolumnareporte
	 */
	public Long getPkCodigoEstadocolumnareporte() {
		return pkCodigoEstadocolumnareporte;
	}

	/**
	 * @param pkCodigoEstadocolumnareporte the pkCodigoEstadocolumnareporte to
	 *            set
	 */
	public void setPkCodigoEstadocolumnareporte(Long pkCodigoEstadocolumnareporte) {
		this.pkCodigoEstadocolumnareporte = pkCodigoEstadocolumnareporte;
	}

	/**
	 * @return the pkCodigoPlantillareporte
	 */
	public Long getPkCodigoPlantillareporte() {
		return pkCodigoPlantillareporte;
	}

	/**
	 * @param pkCodigoPlantillareporte the pkCodigoPlantillareporte to set
	 */
	public void setPkCodigoPlantillareporte(Long pkCodigoPlantillareporte) {
		this.pkCodigoPlantillareporte = pkCodigoPlantillareporte;
	}

	/**
	 * @return the nombreColumnareporte
	 */
	public String getNombreColumnareporte() {
		return nombreColumnareporte;
	}

	/**
	 * @param nombreColumnareporte the nombreColumnareporte to set
	 */
	public void setNombreColumnareporte(String nombreColumnareporte) {
		this.nombreColumnareporte = nombreColumnareporte;
	}

	/**
	 * @return the posicionColumnareporte
	 */
	public Short getPosicionColumnareporte() {
		return posicionColumnareporte;
	}

	/**
	 * @param posicionColumnareporte the posicionColumnareporte to set
	 */
	public void setPosicionColumnareporte(Short posicionColumnareporte) {
		this.posicionColumnareporte = posicionColumnareporte;
	}
}