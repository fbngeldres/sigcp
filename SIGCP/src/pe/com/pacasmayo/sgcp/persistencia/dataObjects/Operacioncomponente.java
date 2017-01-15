package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Operacioncomponente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Operacioncomponente implements java.io.Serializable {

	// Fields

	private Long pkCodigoOperacioncomponente;
	private Operacion operacion;
	private Hojarutacomponente hojarutacomponente;
	private Double maxFactorOperacioncomponente;
	private Double minFactorOperacioncomponente;

	// Constructors

	/** default constructor */
	public Operacioncomponente() {
	}

	/** minimal constructor */
	public Operacioncomponente(Operacion operacion, Hojarutacomponente hojarutacomponente) {
		this.operacion = operacion;
		this.hojarutacomponente = hojarutacomponente;
	}

	/** full constructor */
	public Operacioncomponente(Operacion operacion, Hojarutacomponente hojarutacomponente, Double maxFactorOperacioncomponente,
			Double minFactorOperacioncomponente) {
		this.operacion = operacion;
		this.hojarutacomponente = hojarutacomponente;
		this.maxFactorOperacioncomponente = maxFactorOperacioncomponente;
		this.minFactorOperacioncomponente = minFactorOperacioncomponente;
	}

	// Property accessors

	public Long getPkCodigoOperacioncomponente() {
		return this.pkCodigoOperacioncomponente;
	}

	public void setPkCodigoOperacioncomponente(Long pkCodigoOperacioncomponente) {
		this.pkCodigoOperacioncomponente = pkCodigoOperacioncomponente;
	}

	public Operacion getOperacion() {
		return this.operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public Hojarutacomponente getHojarutacomponente() {
		return this.hojarutacomponente;
	}

	public void setHojarutacomponente(Hojarutacomponente hojarutacomponente) {
		this.hojarutacomponente = hojarutacomponente;
	}

	public Double getMaxFactorOperacioncomponente() {
		return this.maxFactorOperacioncomponente;
	}

	public void setMaxFactorOperacioncomponente(Double maxFactorOperacioncomponente) {
		this.maxFactorOperacioncomponente = maxFactorOperacioncomponente;
	}

	public Double getMinFactorOperacioncomponente() {
		return this.minFactorOperacioncomponente;
	}

	public void setMinFactorOperacioncomponente(Double minFactorOperacioncomponente) {
		this.minFactorOperacioncomponente = minFactorOperacioncomponente;
	}

}