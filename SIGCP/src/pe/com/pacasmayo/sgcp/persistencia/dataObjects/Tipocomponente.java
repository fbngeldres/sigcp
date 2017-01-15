package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipocomponente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipocomponente implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipocomponente;
	private String nombreTipocomponente;
	private Set hojarutacomponentes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipocomponente() {
	}

	/** minimal constructor */
	public Tipocomponente(String nombreTipocomponente) {
		this.nombreTipocomponente = nombreTipocomponente;
	}

	/** full constructor */
	public Tipocomponente(String nombreTipocomponente, Set hojarutacomponentes) {
		this.nombreTipocomponente = nombreTipocomponente;
		this.hojarutacomponentes = hojarutacomponentes;
	}

	// Property accessors

	public Long getPkCodigoTipocomponente() {
		return this.pkCodigoTipocomponente;
	}

	public void setPkCodigoTipocomponente(Long pkCodigoTipocomponente) {
		this.pkCodigoTipocomponente = pkCodigoTipocomponente;
	}

	public String getNombreTipocomponente() {
		return this.nombreTipocomponente;
	}

	public void setNombreTipocomponente(String nombreTipocomponente) {
		this.nombreTipocomponente = nombreTipocomponente;
	}

	public Set getHojarutacomponentes() {
		return this.hojarutacomponentes;
	}

	public void setHojarutacomponentes(Set hojarutacomponentes) {
		this.hojarutacomponentes = hojarutacomponentes;
	}

}