package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumorecursoplan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumorecursoplan implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumorecursoplan;
	private Ordenproduccionplan ordenproduccionplan;
	private Recursoregistromensual recursoregistromensual;
	private Double cantidadConsumorecursoplan;

	// Constructors

	/** default constructor */
	public Consumorecursoplan() {
	}

	/** full constructor */
	public Consumorecursoplan(Ordenproduccionplan ordenproduccionplan, Recursoregistromensual recursoregistromensual,
			Double cantidadConsumorecursoplan) {
		this.ordenproduccionplan = ordenproduccionplan;
		this.recursoregistromensual = recursoregistromensual;
		this.cantidadConsumorecursoplan = cantidadConsumorecursoplan;
	}

	// Property accessors

	public Long getPkCodigoConsumorecursoplan() {
		return this.pkCodigoConsumorecursoplan;
	}

	public void setPkCodigoConsumorecursoplan(Long pkCodigoConsumorecursoplan) {
		this.pkCodigoConsumorecursoplan = pkCodigoConsumorecursoplan;
	}

	public Ordenproduccionplan getOrdenproduccionplan() {
		return this.ordenproduccionplan;
	}

	public void setOrdenproduccionplan(Ordenproduccionplan ordenproduccionplan) {
		this.ordenproduccionplan = ordenproduccionplan;
	}

	public Recursoregistromensual getRecursoregistromensual() {
		return this.recursoregistromensual;
	}

	public void setRecursoregistromensual(Recursoregistromensual recursoregistromensual) {
		this.recursoregistromensual = recursoregistromensual;
	}

	public Double getCantidadConsumorecursoplan() {
		return this.cantidadConsumorecursoplan;
	}

	public void setCantidadConsumorecursoplan(Double cantidadConsumorecursoplan) {
		this.cantidadConsumorecursoplan = cantidadConsumorecursoplan;
	}

}