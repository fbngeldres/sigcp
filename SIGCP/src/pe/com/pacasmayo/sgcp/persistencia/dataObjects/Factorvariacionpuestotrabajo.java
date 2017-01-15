package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Factorkardex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Factorvariacionpuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoFactorvariacion;
	private Consumopuestotrabajo consumopuestotrabajo;
	private Productovariablevariacion productovariablevariacion;
	private Double valorVariablevariacionFactorv;

	// Constructors

	/** default constructor */
	public Factorvariacionpuestotrabajo() {
	}

	/** full constructor */
	public Factorvariacionpuestotrabajo(Consumopuestotrabajo consumopuestotrabajo,
			Productovariablevariacion productovariablevariacion, Double valorVariablevariacionFactork) {
		this.consumopuestotrabajo = consumopuestotrabajo;
		this.productovariablevariacion = productovariablevariacion;
		this.valorVariablevariacionFactorv = valorVariablevariacionFactork;
	}

	// Property accessors

	public Productovariablevariacion getProductovariablevariacion() {
		return this.productovariablevariacion;
	}

	public void setProductovariablevariacion(Productovariablevariacion productovariablevariacion) {
		this.productovariablevariacion = productovariablevariacion;
	}

	public Double getValorVariablevariacionFactorv() {
		return this.valorVariablevariacionFactorv;
	}

	public void setValorVariablevariacionFactorv(Double valorVariablevariacionFactorv) {
		this.valorVariablevariacionFactorv = valorVariablevariacionFactorv;
	}

	public Long getPkCodigoFactorvariacion() {
		return pkCodigoFactorvariacion;
	}

	public void setPkCodigoFactorvariacion(Long pkCodigoFactorvariacion) {
		this.pkCodigoFactorvariacion = pkCodigoFactorvariacion;
	}

	public Consumopuestotrabajo getConsumopuestotrabajo() {
		return consumopuestotrabajo;
	}

	public void setConsumopuestotrabajo(Consumopuestotrabajo consumopuestotrabajo) {
		this.consumopuestotrabajo = consumopuestotrabajo;
	}

}