package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

public class Tipocomponenteajuste implements java.io.Serializable {

	private Long pkCodigoTipoComponenteAjuste;
	private String nombreTipoComponenteAjuste;

	public Tipocomponenteajuste() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the pkCodigoTipoComponenteAjuste
	 */
	public Long getPkCodigoTipoComponenteAjuste() {
		return pkCodigoTipoComponenteAjuste;
	}

	/**
	 * @param pkCodigoTipoComponenteAjuste the pkCodigoTipoComponenteAjuste to
	 *            set
	 */
	public void setPkCodigoTipoComponenteAjuste(Long pkCodigoTipoComponenteAjuste) {
		this.pkCodigoTipoComponenteAjuste = pkCodigoTipoComponenteAjuste;
	}

	/**
	 * @return the nombreTipoComponenteAjuste
	 */
	public String getNombreTipoComponenteAjuste() {
		return nombreTipoComponenteAjuste;
	}

	/**
	 * @param nombreTipoComponenteAjuste the nombreTipoComponenteAjuste to set
	 */
	public void setNombreTipoComponenteAjuste(String nombreTipoComponenteAjuste) {
		this.nombreTipoComponenteAjuste = nombreTipoComponenteAjuste;
	}

}
