package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumocapacidadplan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocapacidadplan implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocapacidadplan;
	private Ordenproduccionplan ordenproduccionplan;
	private Capacidadoperativaregistromensu capacidadoperativaregistromensu;
	private Double cantidadConsumocapacidadplan;

	// Constructors

	/** default constructor */
	public Consumocapacidadplan() {
	}

	/** full constructor */
	public Consumocapacidadplan(Ordenproduccionplan ordenproduccionplan,
			Capacidadoperativaregistromensu capacidadoperativaregistromensu, Double cantidadConsumocapacidadplan) {
		this.ordenproduccionplan = ordenproduccionplan;
		this.capacidadoperativaregistromensu = capacidadoperativaregistromensu;
		this.cantidadConsumocapacidadplan = cantidadConsumocapacidadplan;
	}

	// Property accessors

	public Long getPkCodigoConsumocapacidadplan() {
		return this.pkCodigoConsumocapacidadplan;
	}

	public void setPkCodigoConsumocapacidadplan(Long pkCodigoConsumocapacidadplan) {
		this.pkCodigoConsumocapacidadplan = pkCodigoConsumocapacidadplan;
	}

	public Ordenproduccionplan getOrdenproduccionplan() {
		return this.ordenproduccionplan;
	}

	public void setOrdenproduccionplan(Ordenproduccionplan ordenproduccionplan) {
		this.ordenproduccionplan = ordenproduccionplan;
	}

	public Capacidadoperativaregistromensu getCapacidadoperativaregistromensu() {
		return this.capacidadoperativaregistromensu;
	}

	public void setCapacidadoperativaregistromensu(Capacidadoperativaregistromensu capacidadoperativaregistromensu) {
		this.capacidadoperativaregistromensu = capacidadoperativaregistromensu;
	}

	public Double getCantidadConsumocapacidadplan() {
		return this.cantidadConsumocapacidadplan;
	}

	public void setCantidadConsumocapacidadplan(Double cantidadConsumocapacidadplan) {
		this.cantidadConsumocapacidadplan = cantidadConsumocapacidadplan;
	}

}