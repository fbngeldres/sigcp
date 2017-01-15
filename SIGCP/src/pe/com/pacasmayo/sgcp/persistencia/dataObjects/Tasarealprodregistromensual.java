package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Tasarealprodregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tasarealprodregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoTasarealprodregistrom;
	private Tasarealproduccion tasarealproduccion;
	private Short mesTasarealprodregmensual;
	private Integer annoTasarealprodregmensual;
	private Double cantidadTasarealprodregmensual;

	// Constructors

	/** default constructor */
	public Tasarealprodregistromensual() {
	}

	/** full constructor */
	public Tasarealprodregistromensual(Tasarealproduccion tasarealproduccion, Short mesTasarealprodregmensual,
			Integer annoTasarealprodregmensual, Double cantidadTasarealprodregmensual) {
		this.tasarealproduccion = tasarealproduccion;
		this.mesTasarealprodregmensual = mesTasarealprodregmensual;
		this.annoTasarealprodregmensual = annoTasarealprodregmensual;
		this.cantidadTasarealprodregmensual = cantidadTasarealprodregmensual;
	}

	// Property accessors

	public Long getPkCodigoTasarealprodregistrom() {
		return this.pkCodigoTasarealprodregistrom;
	}

	public void setPkCodigoTasarealprodregistrom(Long pkCodigoTasarealprodregistrom) {
		this.pkCodigoTasarealprodregistrom = pkCodigoTasarealprodregistrom;
	}

	public Tasarealproduccion getTasarealproduccion() {
		return this.tasarealproduccion;
	}

	public void setTasarealproduccion(Tasarealproduccion tasarealproduccion) {
		this.tasarealproduccion = tasarealproduccion;
	}

	public Short getMesTasarealprodregmensual() {
		return this.mesTasarealprodregmensual;
	}

	public void setMesTasarealprodregmensual(Short mesTasarealprodregmensual) {
		this.mesTasarealprodregmensual = mesTasarealprodregmensual;
	}

	public Integer getAnnoTasarealprodregmensual() {
		return this.annoTasarealprodregmensual;
	}

	public void setAnnoTasarealprodregmensual(Integer annoTasarealprodregmensual) {
		this.annoTasarealprodregmensual = annoTasarealprodregmensual;
	}

	public Double getCantidadTasarealprodregmensual() {
		return this.cantidadTasarealprodregmensual;
	}

	public void setCantidadTasarealprodregmensual(Double cantidadTasarealprodregmensual) {
		this.cantidadTasarealprodregmensual = cantidadTasarealprodregmensual;
	}

}