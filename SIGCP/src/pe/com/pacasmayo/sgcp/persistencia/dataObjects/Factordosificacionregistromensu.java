package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Factordosificacionregistromensu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Factordosificacionregistromensu implements java.io.Serializable {

	// Fields

	private Long pkCodigoFactordosificacionreg;
	private Factordosificacion factordosificacion;
	private Short mesFactordosificacionregistrom;
	private Integer annoFactordosificacionregistro;
	private Double cantidadFactordosificacionregi;

	// Constructors

	/** default constructor */
	public Factordosificacionregistromensu() {
	}

	/** full constructor */
	public Factordosificacionregistromensu(Factordosificacion factordosificacion, Short mesFactordosificacionregistrom,
			Integer annoFactordosificacionregistro, Double cantidadFactordosificacionregi) {
		this.factordosificacion = factordosificacion;
		this.mesFactordosificacionregistrom = mesFactordosificacionregistrom;
		this.annoFactordosificacionregistro = annoFactordosificacionregistro;
		this.cantidadFactordosificacionregi = cantidadFactordosificacionregi;
	}

	// Property accessors

	public Long getPkCodigoFactordosificacionreg() {
		return this.pkCodigoFactordosificacionreg;
	}

	public void setPkCodigoFactordosificacionreg(Long pkCodigoFactordosificacionreg) {
		this.pkCodigoFactordosificacionreg = pkCodigoFactordosificacionreg;
	}

	public Factordosificacion getFactordosificacion() {
		return this.factordosificacion;
	}

	public void setFactordosificacion(Factordosificacion factordosificacion) {
		this.factordosificacion = factordosificacion;
	}

	public Short getMesFactordosificacionregistrom() {
		return this.mesFactordosificacionregistrom;
	}

	public void setMesFactordosificacionregistrom(Short mesFactordosificacionregistrom) {
		this.mesFactordosificacionregistrom = mesFactordosificacionregistrom;
	}

	public Integer getAnnoFactordosificacionregistro() {
		return this.annoFactordosificacionregistro;
	}

	public void setAnnoFactordosificacionregistro(Integer annoFactordosificacionregistro) {
		this.annoFactordosificacionregistro = annoFactordosificacionregistro;
	}

	public Double getCantidadFactordosificacionregi() {
		return this.cantidadFactordosificacionregi;
	}

	public void setCantidadFactordosificacionregi(Double cantidadFactordosificacionregi) {
		this.cantidadFactordosificacionregi = cantidadFactordosificacionregi;
	}

}