package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Balanceproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Balanceproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoBalanceproducto;
	private Ajusteproducto ajusteproducto;
	private Concepto concepto;
	private Tipobalance tipobalance;
	private Double montoBalanceproducto;
	private String fecha_balanceproducto;

	// Constructors

	/** default constructor */
	public Balanceproducto() {
	}

	/** full constructor */
	public Balanceproducto(Ajusteproducto ajusteproducto, Concepto concepto, Tipobalance tipobalance, Double montoBalanceproducto) {
		this.ajusteproducto = ajusteproducto;
		this.concepto = concepto;
		this.tipobalance = tipobalance;
		this.montoBalanceproducto = montoBalanceproducto;
	}

	// Property accessors

	public Long getPkCodigoBalanceproducto() {
		return this.pkCodigoBalanceproducto;
	}

	public void setPkCodigoBalanceproducto(Long pkCodigoBalanceproducto) {
		this.pkCodigoBalanceproducto = pkCodigoBalanceproducto;
	}

	public Ajusteproducto getAjusteproducto() {
		return this.ajusteproducto;
	}

	public void setAjusteproducto(Ajusteproducto ajusteproducto) {
		this.ajusteproducto = ajusteproducto;
	}

	public Concepto getConcepto() {
		return this.concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public Tipobalance getTipobalance() {
		return this.tipobalance;
	}

	public void setTipobalance(Tipobalance tipobalance) {
		this.tipobalance = tipobalance;
	}

	public Double getMontoBalanceproducto() {
		return this.montoBalanceproducto;
	}

	public void setMontoBalanceproducto(Double montoBalanceproducto) {
		this.montoBalanceproducto = montoBalanceproducto;
	}

	/**
	 * @return the fecha_balanceproducto
	 */
	public String getFecha_balanceproducto() {
		return fecha_balanceproducto;
	}

	/**
	 * @param fecha_balanceproducto the fecha_balanceproducto to set
	 */
	public void setFecha_balanceproducto(String fecha_balanceproducto) {
		this.fecha_balanceproducto = fecha_balanceproducto;
	}

}