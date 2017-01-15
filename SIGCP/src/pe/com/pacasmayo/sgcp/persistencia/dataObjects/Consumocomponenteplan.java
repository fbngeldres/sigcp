package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumocomponenteplan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocomponenteplan implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocomponente;
	private Ordenproduccionplan ordenproduccionplan;
	private Dosificacionregistromensual dosificacionregistromensual;
	private Double cantidadDosificacionConsumoco;
	private Double cantidadFactordosificacionCon;

	// Constructors

	/** default constructor */
	public Consumocomponenteplan() {
	}

	/** full constructor */
	public Consumocomponenteplan(Ordenproduccionplan ordenproduccionplan,
			Dosificacionregistromensual dosificacionregistromensual, Double cantidadDosificacionConsumoco,
			Double cantidadFactordosificacionCon) {
		this.ordenproduccionplan = ordenproduccionplan;
		this.dosificacionregistromensual = dosificacionregistromensual;
		this.cantidadDosificacionConsumoco = cantidadDosificacionConsumoco;
		this.cantidadFactordosificacionCon = cantidadFactordosificacionCon;
	}

	// Property accessors

	public Long getPkCodigoConsumocomponente() {
		return this.pkCodigoConsumocomponente;
	}

	public void setPkCodigoConsumocomponente(Long pkCodigoConsumocomponente) {
		this.pkCodigoConsumocomponente = pkCodigoConsumocomponente;
	}

	public Ordenproduccionplan getOrdenproduccionplan() {
		return this.ordenproduccionplan;
	}

	public void setOrdenproduccionplan(Ordenproduccionplan ordenproduccionplan) {
		this.ordenproduccionplan = ordenproduccionplan;
	}

	public Dosificacionregistromensual getDosificacionregistromensual() {
		return this.dosificacionregistromensual;
	}

	public void setDosificacionregistromensual(Dosificacionregistromensual dosificacionregistromensual) {
		this.dosificacionregistromensual = dosificacionregistromensual;
	}

	public Double getCantidadDosificacionConsumoco() {
		return this.cantidadDosificacionConsumoco;
	}

	public void setCantidadDosificacionConsumoco(Double cantidadDosificacionConsumoco) {
		this.cantidadDosificacionConsumoco = cantidadDosificacionConsumoco;
	}

	public Double getCantidadFactordosificacionCon() {
		return this.cantidadFactordosificacionCon;
	}

	public void setCantidadFactordosificacionCon(Double cantidadFactordosificacionCon) {
		this.cantidadFactordosificacionCon = cantidadFactordosificacionCon;
	}

}