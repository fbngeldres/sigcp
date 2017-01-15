package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Hojarutacomponente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Hojarutacomponente implements java.io.Serializable {

	// Fields

	private Long pkCodigoHojarutacomponente;
	private Componente componente;
	private Hojaruta hojaruta;
	private Tipocomponente tipocomponente;
	private Boolean combustibleHojarutacomponente;
	private Set operacioncomponentes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Hojarutacomponente() {
	}

	/** minimal constructor */
	public Hojarutacomponente(Componente componente, Hojaruta hojaruta, Tipocomponente tipocomponente) {
		this.componente = componente;
		this.hojaruta = hojaruta;
		this.tipocomponente = tipocomponente;
	}

	/** full constructor */
	public Hojarutacomponente(Componente componente, Hojaruta hojaruta, Tipocomponente tipocomponente, Set operacioncomponentes) {
		this.componente = componente;
		this.hojaruta = hojaruta;
		this.tipocomponente = tipocomponente;
		this.operacioncomponentes = operacioncomponentes;
	}

	// Property accessors

	public Long getPkCodigoHojarutacomponente() {
		return this.pkCodigoHojarutacomponente;
	}

	public void setPkCodigoHojarutacomponente(Long pkCodigoHojarutacomponente) {
		this.pkCodigoHojarutacomponente = pkCodigoHojarutacomponente;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Hojaruta getHojaruta() {
		return this.hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	public Tipocomponente getTipocomponente() {
		return this.tipocomponente;
	}

	public void setTipocomponente(Tipocomponente tipocomponente) {
		this.tipocomponente = tipocomponente;
	}

	public Set getOperacioncomponentes() {
		return this.operacioncomponentes;
	}

	public void setOperacioncomponentes(Set operacioncomponentes) {
		this.operacioncomponentes = operacioncomponentes;
	}

	public Boolean getCombustibleHojarutacomponente() {
		return combustibleHojarutacomponente;
	}

	public void setCombustibleHojarutacomponente(Boolean combustibleHojarutacomponente) {
		this.combustibleHojarutacomponente = combustibleHojarutacomponente;
	}

}