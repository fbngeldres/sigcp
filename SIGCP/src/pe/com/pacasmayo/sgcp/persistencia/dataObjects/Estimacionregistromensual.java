package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Estimacionregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estimacionregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstimacionregistromen;
	private Plancomercializacion plancomercializacion;
	private Short mesEstimacionregistromensual;
	private Integer annoEstimacionregistromensual;
	private Double cantidadEstimacionregistromens;

	// Constructors

	/** default constructor */
	public Estimacionregistromensual() {
	}

	/** minimal constructor */
	public Estimacionregistromensual(Short mesEstimacionregistromensual, Integer annoEstimacionregistromensual,
			Double cantidadEstimacionregistromens) {
		this.mesEstimacionregistromensual = mesEstimacionregistromensual;
		this.annoEstimacionregistromensual = annoEstimacionregistromensual;
		this.cantidadEstimacionregistromens = cantidadEstimacionregistromens;
	}

	/** full constructor */
	public Estimacionregistromensual(Plancomercializacion plancomercializacion, Short mesEstimacionregistromensual,
			Integer annoEstimacionregistromensual, Double cantidadEstimacionregistromens) {
		this.plancomercializacion = plancomercializacion;
		this.mesEstimacionregistromensual = mesEstimacionregistromensual;
		this.annoEstimacionregistromensual = annoEstimacionregistromensual;
		this.cantidadEstimacionregistromens = cantidadEstimacionregistromens;
	}

	// Property accessors

	public Long getPkCodigoEstimacionregistromen() {
		return this.pkCodigoEstimacionregistromen;
	}

	public void setPkCodigoEstimacionregistromen(Long pkCodigoEstimacionregistromen) {
		this.pkCodigoEstimacionregistromen = pkCodigoEstimacionregistromen;
	}

	public Plancomercializacion getPlancomercializacion() {
		return this.plancomercializacion;
	}

	public void setPlancomercializacion(Plancomercializacion plancomercializacion) {
		this.plancomercializacion = plancomercializacion;
	}

	public Short getMesEstimacionregistromensual() {
		return this.mesEstimacionregistromensual;
	}

	public void setMesEstimacionregistromensual(Short mesEstimacionregistromensual) {
		this.mesEstimacionregistromensual = mesEstimacionregistromensual;
	}

	public Integer getAnnoEstimacionregistromensual() {
		return this.annoEstimacionregistromensual;
	}

	public void setAnnoEstimacionregistromensual(Integer annoEstimacionregistromensual) {
		this.annoEstimacionregistromensual = annoEstimacionregistromensual;
	}

	public Double getCantidadEstimacionregistromens() {
		return this.cantidadEstimacionregistromens;
	}

	public void setCantidadEstimacionregistromens(Double cantidadEstimacionregistromens) {
		this.cantidadEstimacionregistromens = cantidadEstimacionregistromens;
	}

}