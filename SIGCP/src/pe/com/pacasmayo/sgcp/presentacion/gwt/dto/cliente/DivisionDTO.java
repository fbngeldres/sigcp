package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/**
 * Division entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DivisionDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pkCodigoDivision;
	private String nombreDivision;
	private String descripcionDivision;

	// Constructors

	/** default constructor */
	public DivisionDTO() {
	}

	/** minimal constructor */
	public DivisionDTO(String nombreDivision) {
		this.nombreDivision = nombreDivision;
	}

	/** full constructor */
	public DivisionDTO(String nombreDivision, String descripcionDivision) {
		this.nombreDivision = nombreDivision;
		this.descripcionDivision = descripcionDivision;

	}

	// Property accessors

	public Long getPkCodigoDivision() {
		return this.pkCodigoDivision;
	}

	public void setPkCodigoDivision(Long pkCodigoDivision) {
		this.pkCodigoDivision = pkCodigoDivision;
	}

	public String getNombreDivision() {
		return this.nombreDivision;
	}

	public void setNombreDivision(String nombreDivision) {
		this.nombreDivision = nombreDivision;
	}

	public String getDescripcionDivision() {
		return this.descripcionDivision;
	}

	public void setDescripcionDivision(String descripcionDivision) {
		this.descripcionDivision = descripcionDivision;
	}
}