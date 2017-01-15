package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Alturasilo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Alturasilo implements java.io.Serializable {

	// Fields

	private Long pkCodigoAlturasilo;
	private Medicion medicion;
	private Double medicionAlturaAlturasilo;
	private Integer numeroAlturaAlturasilo;

	// Constructors

	/** default constructor */
	public Alturasilo() {
	}

	/** full constructor */
	public Alturasilo(Medicion medicion, Double medicionAlturaAlturasilo) {
		this.medicion = medicion;
		this.medicionAlturaAlturasilo = medicionAlturaAlturasilo;
	}

	// Property accessors

	public Long getPkCodigoAlturasilo() {
		return this.pkCodigoAlturasilo;
	}

	public void setPkCodigoAlturasilo(Long pkCodigoAlturasilo) {
		this.pkCodigoAlturasilo = pkCodigoAlturasilo;
	}

	public Medicion getMedicion() {
		return this.medicion;
	}

	public void setMedicion(Medicion medicion) {
		this.medicion = medicion;
	}

	public Double getMedicionAlturaAlturasilo() {
		return this.medicionAlturaAlturasilo;
	}

	public void setMedicionAlturaAlturasilo(Double medicionAlturaAlturasilo) {
		this.medicionAlturaAlturasilo = medicionAlturaAlturasilo;
	}

	public Integer getNumeroAlturaAlturasilo() {
		return numeroAlturaAlturasilo;
	}

	public void setNumeroAlturaAlturasilo(Integer numeroAlturaAlturasilo) {
		this.numeroAlturaAlturasilo = numeroAlturaAlturasilo;
	}

}