package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Plancapacidadregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plancapacidadregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlancapacidadregistro;
	private Plancapacidad plancapacidad;
	private Short mesPlancapregmensual;
	private Integer annoPlancapregmensual;
	private Double cantidadPlancapregmensual;

	// Constructors

	/** default constructor */
	public Plancapacidadregistromensual() {
	}

	/** full constructor */
	public Plancapacidadregistromensual(Plancapacidad plancapacidad, Short mesPlancapregmensual, Integer annoPlancapregmensual,
			Double cantidadPlancapregmensual) {
		this.plancapacidad = plancapacidad;
		this.mesPlancapregmensual = mesPlancapregmensual;
		this.annoPlancapregmensual = annoPlancapregmensual;
		this.cantidadPlancapregmensual = cantidadPlancapregmensual;
	}

	// Property accessors

	public Long getPkCodigoPlancapacidadregistro() {
		return this.pkCodigoPlancapacidadregistro;
	}

	public void setPkCodigoPlancapacidadregistro(Long pkCodigoPlancapacidadregistro) {
		this.pkCodigoPlancapacidadregistro = pkCodigoPlancapacidadregistro;
	}

	public Plancapacidad getPlancapacidad() {
		return this.plancapacidad;
	}

	public void setPlancapacidad(Plancapacidad plancapacidad) {
		this.plancapacidad = plancapacidad;
	}

	public Short getMesPlancapregmensual() {
		return this.mesPlancapregmensual;
	}

	public void setMesPlancapregmensual(Short mesPlancapregmensual) {
		this.mesPlancapregmensual = mesPlancapregmensual;
	}

	public Integer getAnnoPlancapregmensual() {
		return this.annoPlancapregmensual;
	}

	public void setAnnoPlancapregmensual(Integer annoPlancapregmensual) {
		this.annoPlancapregmensual = annoPlancapregmensual;
	}

	public Double getCantidadPlancapregmensual() {
		return this.cantidadPlancapregmensual;
	}

	public void setCantidadPlancapregmensual(Double cantidadPlancapregmensual) {
		this.cantidadPlancapregmensual = cantidadPlancapregmensual;
	}

}