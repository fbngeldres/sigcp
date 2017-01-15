package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Datoreporte entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Datoreporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoDatoreporte;
	private Hora hora;
	private Registroreporteecs registroreporteecs;
	private Double variable1Datoreporte;
	private Double variable2Datoreporte;
	private Double variable3Datoreporte;
	private Double variable4Datoreporte;
	private Double variable5Datoreporte;
	private Double variable6Datoreporte;
	private Double variable7Datoreporte;
	private Double variable8Datoreporte;
	private Double variable9Datoreporte;
	private Double variable10Datoreporte;
	private Double variable11Datoreporte;
	private Double variable12Datoreporte;
	private Double variable13Datoreporte;
	private Double variable14Datoreporte;
	private Double variable15Datoreporte;

	// Constructors

	/** default constructor */
	public Datoreporte() {
	}

	/** minimal constructor */
	public Datoreporte(Hora hora, Registroreporteecs registroreporteecs) {
		this.hora = hora;
		this.registroreporteecs = registroreporteecs;
	}

	/** full constructor */
	public Datoreporte(Hora hora, Registroreporteecs registroreporteecs, Double variable1Datoreporte,
			Double variable2Datoreporte, Double variable3Datoreporte, Double variable4Datoreporte, Double variable5Datoreporte,
			Double variable6Datoreporte, Double variable7Datoreporte, Double variable8Datoreporte, Double variable9Datoreporte,
			Double variable10Datoreporte, Double variable11Datoreporte, Double variable12Datoreporte,
			Double variable13Datoreporte, Double variable14Datoreporte, Double variable15Datoreporte) {
		this.hora = hora;
		this.registroreporteecs = registroreporteecs;
		this.variable1Datoreporte = variable1Datoreporte;
		this.variable2Datoreporte = variable2Datoreporte;
		this.variable3Datoreporte = variable3Datoreporte;
		this.variable4Datoreporte = variable4Datoreporte;
		this.variable5Datoreporte = variable5Datoreporte;
		this.variable6Datoreporte = variable6Datoreporte;
		this.variable7Datoreporte = variable7Datoreporte;
		this.variable8Datoreporte = variable8Datoreporte;
		this.variable9Datoreporte = variable9Datoreporte;
		this.variable10Datoreporte = variable10Datoreporte;
		this.variable11Datoreporte = variable11Datoreporte;
		this.variable12Datoreporte = variable12Datoreporte;
		this.variable13Datoreporte = variable13Datoreporte;
		this.variable14Datoreporte = variable14Datoreporte;
		this.variable15Datoreporte = variable15Datoreporte;
	}

	// Property accessors

	public Long getPkCodigoDatoreporte() {
		return this.pkCodigoDatoreporte;
	}

	public void setPkCodigoDatoreporte(Long pkCodigoDatoreporte) {
		this.pkCodigoDatoreporte = pkCodigoDatoreporte;
	}

	public Hora getHora() {
		return this.hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Registroreporteecs getRegistroreporteecs() {
		return this.registroreporteecs;
	}

	public void setRegistroreporteecs(Registroreporteecs registroreporteecs) {
		this.registroreporteecs = registroreporteecs;
	}

	public Double getVariable1Datoreporte() {
		return this.variable1Datoreporte;
	}

	public void setVariable1Datoreporte(Double variable1Datoreporte) {
		this.variable1Datoreporte = variable1Datoreporte;
	}

	public Double getVariable2Datoreporte() {
		return this.variable2Datoreporte;
	}

	public void setVariable2Datoreporte(Double variable2Datoreporte) {
		this.variable2Datoreporte = variable2Datoreporte;
	}

	public Double getVariable3Datoreporte() {
		return this.variable3Datoreporte;
	}

	public void setVariable3Datoreporte(Double variable3Datoreporte) {
		this.variable3Datoreporte = variable3Datoreporte;
	}

	public Double getVariable4Datoreporte() {
		return this.variable4Datoreporte;
	}

	public void setVariable4Datoreporte(Double variable4Datoreporte) {
		this.variable4Datoreporte = variable4Datoreporte;
	}

	public Double getVariable5Datoreporte() {
		return this.variable5Datoreporte;
	}

	public void setVariable5Datoreporte(Double variable5Datoreporte) {
		this.variable5Datoreporte = variable5Datoreporte;
	}

	public Double getVariable6Datoreporte() {
		return this.variable6Datoreporte;
	}

	public void setVariable6Datoreporte(Double variable6Datoreporte) {
		this.variable6Datoreporte = variable6Datoreporte;
	}

	public Double getVariable7Datoreporte() {
		return this.variable7Datoreporte;
	}

	public void setVariable7Datoreporte(Double variable7Datoreporte) {
		this.variable7Datoreporte = variable7Datoreporte;
	}

	public Double getVariable8Datoreporte() {
		return this.variable8Datoreporte;
	}

	public void setVariable8Datoreporte(Double variable8Datoreporte) {
		this.variable8Datoreporte = variable8Datoreporte;
	}

	public Double getVariable9Datoreporte() {
		return this.variable9Datoreporte;
	}

	public void setVariable9Datoreporte(Double variable9Datoreporte) {
		this.variable9Datoreporte = variable9Datoreporte;
	}

	public Double getVariable10Datoreporte() {
		return this.variable10Datoreporte;
	}

	public void setVariable10Datoreporte(Double variable10Datoreporte) {
		this.variable10Datoreporte = variable10Datoreporte;
	}

	public Double getVariable11Datoreporte() {
		return this.variable11Datoreporte;
	}

	public void setVariable11Datoreporte(Double variable11Datoreporte) {
		this.variable11Datoreporte = variable11Datoreporte;
	}

	public Double getVariable12Datoreporte() {
		return this.variable12Datoreporte;
	}

	public void setVariable12Datoreporte(Double variable12Datoreporte) {
		this.variable12Datoreporte = variable12Datoreporte;
	}

	public Double getVariable13Datoreporte() {
		return this.variable13Datoreporte;
	}

	public void setVariable13Datoreporte(Double variable13Datoreporte) {
		this.variable13Datoreporte = variable13Datoreporte;
	}

	public Double getVariable14Datoreporte() {
		return this.variable14Datoreporte;
	}

	public void setVariable14Datoreporte(Double variable14Datoreporte) {
		this.variable14Datoreporte = variable14Datoreporte;
	}

	public Double getVariable15Datoreporte() {
		return this.variable15Datoreporte;
	}

	public void setVariable15Datoreporte(Double variable15Datoreporte) {
		this.variable15Datoreporte = variable15Datoreporte;
	}

}