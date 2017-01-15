package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Ordenproduccionplan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ordenproduccionplan implements java.io.Serializable {

	// Fields

	private Long pkCodigoOrdenproduccionplan;
	private Plananual plananual;
	private Ordenproduccion ordenproduccion;
	private Set consumocomponenteplans = new HashSet(0);
	private Set consumocapacidadplans = new HashSet(0);
	private Set consumorecursoplans = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ordenproduccionplan() {
	}

	/** minimal constructor */
	public Ordenproduccionplan(Plananual plananual, Ordenproduccion ordenproduccion) {
		this.plananual = plananual;
		this.ordenproduccion = ordenproduccion;
	}

	/** full constructor */
	public Ordenproduccionplan(Plananual plananual, Ordenproduccion ordenproduccion, Set consumocomponenteplans,
			Set consumocapacidadplans, Set consumorecursoplans) {
		this.plananual = plananual;
		this.ordenproduccion = ordenproduccion;
		this.consumocomponenteplans = consumocomponenteplans;
		this.consumocapacidadplans = consumocapacidadplans;
		this.consumorecursoplans = consumorecursoplans;
	}

	// Property accessors

	public Long getPkCodigoOrdenproduccionplan() {
		return this.pkCodigoOrdenproduccionplan;
	}

	public void setPkCodigoOrdenproduccionplan(Long pkCodigoOrdenproduccionplan) {
		this.pkCodigoOrdenproduccionplan = pkCodigoOrdenproduccionplan;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Ordenproduccion getOrdenproduccion() {
		return this.ordenproduccion;
	}

	public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Set getConsumocomponenteplans() {
		return this.consumocomponenteplans;
	}

	public void setConsumocomponenteplans(Set consumocomponenteplans) {
		this.consumocomponenteplans = consumocomponenteplans;
	}

	public Set getConsumocapacidadplans() {
		return this.consumocapacidadplans;
	}

	public void setConsumocapacidadplans(Set consumocapacidadplans) {
		this.consumocapacidadplans = consumocapacidadplans;
	}

	public Set getConsumorecursoplans() {
		return this.consumorecursoplans;
	}

	public void setConsumorecursoplans(Set consumorecursoplans) {
		this.consumorecursoplans = consumorecursoplans;
	}

}