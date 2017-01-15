package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Conceptoregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Conceptoregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoConceptoregistromensu;
	private Plannecesidad plannecesidad;
	private Conceptomensual conceptomensual;
	private Short mesConceptoregistromensual;
	private Double cantidadConceptoregistromensua;
	private Integer annoConceptoregistromensual;

	// Constructors

	/** default constructor */
	public Conceptoregistromensual() {
	}

	/** full constructor */
	public Conceptoregistromensual(Plannecesidad plannecesidad, Conceptomensual conceptomensual,
			Short mesConceptoregistromensual, Double cantidadConceptoregistromensua, Integer annoConceptoregistromensual) {
		this.plannecesidad = plannecesidad;
		this.conceptomensual = conceptomensual;
		this.mesConceptoregistromensual = mesConceptoregistromensual;
		this.cantidadConceptoregistromensua = cantidadConceptoregistromensua;
		this.annoConceptoregistromensual = annoConceptoregistromensual;
	}

	// Property accessors

	public Long getPkCodigoConceptoregistromensu() {
		return this.pkCodigoConceptoregistromensu;
	}

	public void setPkCodigoConceptoregistromensu(Long pkCodigoConceptoregistromensu) {
		this.pkCodigoConceptoregistromensu = pkCodigoConceptoregistromensu;
	}

	public Plannecesidad getPlannecesidad() {
		return this.plannecesidad;
	}

	public void setPlannecesidad(Plannecesidad plannecesidad) {
		this.plannecesidad = plannecesidad;
	}

	public Conceptomensual getConceptomensual() {
		return this.conceptomensual;
	}

	public void setConceptomensual(Conceptomensual conceptomensual) {
		this.conceptomensual = conceptomensual;
	}

	public Short getMesConceptoregistromensual() {
		return this.mesConceptoregistromensual;
	}

	public void setMesConceptoregistromensual(Short mesConceptoregistromensual) {
		this.mesConceptoregistromensual = mesConceptoregistromensual;
	}

	public Double getCantidadConceptoregistromensua() {
		return this.cantidadConceptoregistromensua;
	}

	public void setCantidadConceptoregistromensua(Double cantidadConceptoregistromensua) {
		this.cantidadConceptoregistromensua = cantidadConceptoregistromensua;
	}

	public Integer getAnnoConceptoregistromensual() {
		return this.annoConceptoregistromensual;
	}

	public void setAnnoConceptoregistromensual(Integer annoConceptoregistromensual) {
		this.annoConceptoregistromensual = annoConceptoregistromensual;
	}

}