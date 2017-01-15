package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Valorpromvariablecalidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Valorpromvariablecalidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoValorpromvariblecalid;
	private Tablakardex tablakardex;
	private Productovariablecalidad productovariablecalidad;
	private Double valorValorpromvariblecalidad;

	// Constructors

	/** default constructor */
	public Valorpromvariablecalidad() {
	}

	/** full constructor */
	public Valorpromvariablecalidad(Tablakardex tablakardex, Productovariablecalidad productovariablecalidad,
			Double valorValorpromvariblecalidad) {
		this.tablakardex = tablakardex;
		this.productovariablecalidad = productovariablecalidad;
		this.valorValorpromvariblecalidad = valorValorpromvariblecalidad;
	}

	// Property accessors

	public Long getPkCodigoValorpromvariblecalid() {
		return this.pkCodigoValorpromvariblecalid;
	}

	public void setPkCodigoValorpromvariblecalid(Long pkCodigoValorpromvariblecalid) {
		this.pkCodigoValorpromvariblecalid = pkCodigoValorpromvariblecalid;
	}

	public Tablakardex getTablakardex() {
		return this.tablakardex;
	}

	public void setTablakardex(Tablakardex tablakardex) {
		this.tablakardex = tablakardex;
	}

	public Productovariablecalidad getProductovariablecalidad() {
		return this.productovariablecalidad;
	}

	public void setProductovariablecalidad(Productovariablecalidad productovariablecalidad) {
		this.productovariablecalidad = productovariablecalidad;
	}

	public Double getValorValorpromvariblecalidad() {
		return this.valorValorpromvariblecalidad;
	}

	public void setValorValorpromvariblecalidad(Double valorValorpromvariblecalidad) {
		this.valorValorpromvariblecalidad = valorValorpromvariblecalidad;
	}

}