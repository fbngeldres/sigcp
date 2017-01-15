package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumocapacidadmanual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocapacidadmanual implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocapmanual;
	private Tipocapacidadoperativa tipocapacidadoperativa;
	private Puestotrabajo puestotrabajo;
	private Ordenproduccionmanual ordenproduccionmanual;
	private Double cantidadConsumocapmanual;
	private Double cantidadejecConsumocapmanual;

	// Constructors

	/** default constructor */
	public Consumocapacidadmanual() {
	}

	/** minimal constructor */
	public Consumocapacidadmanual(Puestotrabajo puestotrabajo, Ordenproduccionmanual ordenproduccionmanual,
			Double cantidadConsumocapmanual, Double cantidadejecConsumocapmanual) {
		this.puestotrabajo = puestotrabajo;
		this.ordenproduccionmanual = ordenproduccionmanual;
		this.cantidadConsumocapmanual = cantidadConsumocapmanual;
		this.cantidadejecConsumocapmanual = cantidadejecConsumocapmanual;
	}

	/** full constructor */
	public Consumocapacidadmanual(Tipocapacidadoperativa tipocapacidadoperativa, Puestotrabajo puestotrabajo,
			Ordenproduccionmanual ordenproduccionmanual, Double cantidadConsumocapmanual, Double cantidadejecConsumocapmanual) {
		this.tipocapacidadoperativa = tipocapacidadoperativa;
		this.puestotrabajo = puestotrabajo;
		this.ordenproduccionmanual = ordenproduccionmanual;
		this.cantidadConsumocapmanual = cantidadConsumocapmanual;
		this.cantidadejecConsumocapmanual = cantidadejecConsumocapmanual;
	}

	// Property accessors

	public Long getPkCodigoConsumocapmanual() {
		return this.pkCodigoConsumocapmanual;
	}

	public void setPkCodigoConsumocapmanual(Long pkCodigoConsumocapmanual) {
		this.pkCodigoConsumocapmanual = pkCodigoConsumocapmanual;
	}

	public Tipocapacidadoperativa getTipocapacidadoperativa() {
		return this.tipocapacidadoperativa;
	}

	public void setTipocapacidadoperativa(Tipocapacidadoperativa tipocapacidadoperativa) {
		this.tipocapacidadoperativa = tipocapacidadoperativa;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Ordenproduccionmanual getOrdenproduccionmanual() {
		return this.ordenproduccionmanual;
	}

	public void setOrdenproduccionmanual(Ordenproduccionmanual ordenproduccionmanual) {
		this.ordenproduccionmanual = ordenproduccionmanual;
	}

	public Double getCantidadConsumocapmanual() {
		return this.cantidadConsumocapmanual;
	}

	public void setCantidadConsumocapmanual(Double cantidadConsumocapmanual) {
		this.cantidadConsumocapmanual = cantidadConsumocapmanual;
	}

	public Double getCantidadejecConsumocapmanual() {
		return this.cantidadejecConsumocapmanual;
	}

	public void setCantidadejecConsumocapmanual(Double cantidadejecConsumocapmanual) {
		this.cantidadejecConsumocapmanual = cantidadejecConsumocapmanual;
	}

}