package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Divisioncargo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Divisioncargo implements java.io.Serializable {

	// Fields

	private Long pkCodigoDivisioncargo;
	private Division division;
	private Cargo cargo;

	// Constructors

	/** default constructor */
	public Divisioncargo() {
	}

	/** full constructor */
	public Divisioncargo(Division division, Cargo cargo) {
		this.division = division;
		this.cargo = cargo;
	}

	// Property accessors

	public Long getPkCodigoDivisioncargo() {
		return this.pkCodigoDivisioncargo;
	}

	public void setPkCodigoDivisioncargo(Long pkCodigoDivisioncargo) {
		this.pkCodigoDivisioncargo = pkCodigoDivisioncargo;
	}

	public Division getDivision() {
		return this.division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}