package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Tableropuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tableropuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoTableropuestotrabajo;
	private Puestotrabajo puestotrabajo;
	private Tablerocontrol tablerocontrol;

	// Constructors

	/** default constructor */
	public Tableropuestotrabajo() {
	}

	/** full constructor */
	public Tableropuestotrabajo(Puestotrabajo puestotrabajo, Tablerocontrol tablerocontrol) {
		this.puestotrabajo = puestotrabajo;
		this.tablerocontrol = tablerocontrol;
	}

	// Property accessors

	public Long getPkCodigoTableropuestotrabajo() {
		return this.pkCodigoTableropuestotrabajo;
	}

	public void setPkCodigoTableropuestotrabajo(Long pkCodigoTableropuestotrabajo) {
		this.pkCodigoTableropuestotrabajo = pkCodigoTableropuestotrabajo;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Tablerocontrol getTablerocontrol() {
		return this.tablerocontrol;
	}

	public void setTablerocontrol(Tablerocontrol tablerocontrol) {
		this.tablerocontrol = tablerocontrol;
	}

}