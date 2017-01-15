package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Capacidadoperativa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Capacidadoperativa implements java.io.Serializable {

	// Fields

	private Long pkCodigoCapacidadoperativa;
	private Tipocapacidadoperativa tipocapacidadoperativa;
	private Tasarealproduccion tasarealproduccion;
	private Operacion operacion;
	private Set<Capacidadoperativaregistromensu> capacidadoperativaregistromensus = new HashSet<Capacidadoperativaregistromensu>(
			0);

	// Constructors

	/** default constructor */
	public Capacidadoperativa() {
	}

	/** full constructor */
	public Capacidadoperativa(Tipocapacidadoperativa tipocapacidadoperativa, Tasarealproduccion tasarealproduccion,
			Operacion operacion, Set<Capacidadoperativaregistromensu> capacidadoperativaregistromensus) {
		this.tipocapacidadoperativa = tipocapacidadoperativa;
		this.tasarealproduccion = tasarealproduccion;
		this.operacion = operacion;
		this.capacidadoperativaregistromensus = capacidadoperativaregistromensus;
	}

	// Property accessors

	public Long getPkCodigoCapacidadoperativa() {
		return this.pkCodigoCapacidadoperativa;
	}

	public void setPkCodigoCapacidadoperativa(Long pkCodigoCapacidadoperativa) {
		this.pkCodigoCapacidadoperativa = pkCodigoCapacidadoperativa;
	}

	public Tipocapacidadoperativa getTipocapacidadoperativa() {
		return this.tipocapacidadoperativa;
	}

	public void setTipocapacidadoperativa(Tipocapacidadoperativa tipocapacidadoperativa) {
		this.tipocapacidadoperativa = tipocapacidadoperativa;
	}

	public Tasarealproduccion getTasarealproduccion() {
		return this.tasarealproduccion;
	}

	public void setTasarealproduccion(Tasarealproduccion tasarealproduccion) {
		this.tasarealproduccion = tasarealproduccion;
	}

	public Operacion getOperacion() {
		return this.operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public Set<Capacidadoperativaregistromensu> getCapacidadoperativaregistromensus() {
		return this.capacidadoperativaregistromensus;
	}

	public void setCapacidadoperativaregistromensus(Set<Capacidadoperativaregistromensu> capacidadoperativaregistromensus) {
		this.capacidadoperativaregistromensus = capacidadoperativaregistromensus;
	}

}