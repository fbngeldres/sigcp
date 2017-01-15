package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Capacidadoperativaregistromensu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Capacidadoperativaregistromensu implements java.io.Serializable {

	// Fields

	private Long pkCodigoCapacidadoperativareg;
	private Plananual plananual;
	private Capacidadoperativa capacidadoperativa;
	private Short mesCapacidadoperativaregistrom;
	private Integer annoCapacidadoperativaregistro;
	private Double cantidadRegistroCapacidadoper;
	private Set consumocapacidadplans = new HashSet(0);

	// Constructors

	/** default constructor */
	public Capacidadoperativaregistromensu() {
	}

	/** minimal constructor */
	public Capacidadoperativaregistromensu(Capacidadoperativa capacidadoperativa, Short mesCapacidadoperativaregistrom,
			Integer annoCapacidadoperativaregistro, Double cantidadRegistroCapacidadoper) {
		this.capacidadoperativa = capacidadoperativa;
		this.mesCapacidadoperativaregistrom = mesCapacidadoperativaregistrom;
		this.annoCapacidadoperativaregistro = annoCapacidadoperativaregistro;
		this.cantidadRegistroCapacidadoper = cantidadRegistroCapacidadoper;
	}

	/** full constructor */
	public Capacidadoperativaregistromensu(Plananual plananual, Capacidadoperativa capacidadoperativa,
			Short mesCapacidadoperativaregistrom, Integer annoCapacidadoperativaregistro, Double cantidadRegistroCapacidadoper,
			Set consumocapacidadplans) {
		this.plananual = plananual;
		this.capacidadoperativa = capacidadoperativa;
		this.mesCapacidadoperativaregistrom = mesCapacidadoperativaregistrom;
		this.annoCapacidadoperativaregistro = annoCapacidadoperativaregistro;
		this.cantidadRegistroCapacidadoper = cantidadRegistroCapacidadoper;
		this.consumocapacidadplans = consumocapacidadplans;
	}

	// Property accessors

	public Long getPkCodigoCapacidadoperativareg() {
		return this.pkCodigoCapacidadoperativareg;
	}

	public void setPkCodigoCapacidadoperativareg(Long pkCodigoCapacidadoperativareg) {
		this.pkCodigoCapacidadoperativareg = pkCodigoCapacidadoperativareg;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Capacidadoperativa getCapacidadoperativa() {
		return this.capacidadoperativa;
	}

	public void setCapacidadoperativa(Capacidadoperativa capacidadoperativa) {
		this.capacidadoperativa = capacidadoperativa;
	}

	public Short getMesCapacidadoperativaregistrom() {
		return this.mesCapacidadoperativaregistrom;
	}

	public void setMesCapacidadoperativaregistrom(Short mesCapacidadoperativaregistrom) {
		this.mesCapacidadoperativaregistrom = mesCapacidadoperativaregistrom;
	}

	public Integer getAnnoCapacidadoperativaregistro() {
		return this.annoCapacidadoperativaregistro;
	}

	public void setAnnoCapacidadoperativaregistro(Integer annoCapacidadoperativaregistro) {
		this.annoCapacidadoperativaregistro = annoCapacidadoperativaregistro;
	}

	public Double getCantidadRegistroCapacidadoper() {
		return this.cantidadRegistroCapacidadoper;
	}

	public void setCantidadRegistroCapacidadoper(Double cantidadRegistroCapacidadoper) {
		this.cantidadRegistroCapacidadoper = cantidadRegistroCapacidadoper;
	}

	public Set getConsumocapacidadplans() {
		return this.consumocapacidadplans;
	}

	public void setConsumocapacidadplans(Set consumocapacidadplans) {
		this.consumocapacidadplans = consumocapacidadplans;
	}

}