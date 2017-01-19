package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Ubicacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ubicacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoUbicacion;
	private Almacen almacen;
	private String nombreUbicacion;
	private String descripcionUbicacion;
	private Set tablakardexesForFkCodigoUbicacionOrigen = new HashSet(0);

	private Set movimientosForFkCodigoUbicacionOrigen = new HashSet(0);
	private Set movimientosForFkCodigoUbicacionDestino = new HashSet(0);
	private Set medioalmacenamientos = new HashSet(0);
	private Set ingresoproductoprocesos = new HashSet(0);
	private Set tablakardexesForFkCodigoUbicacionDestino = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ubicacion() {
	}

	/** minimal constructor */
	public Ubicacion(Almacen almacen, String nombreUbicacion) {
		this.almacen = almacen;
		this.nombreUbicacion = nombreUbicacion;
	}

	/** full constructor */
	public Ubicacion(Almacen almacen, String nombreUbicacion, String descripcionUbicacion,
			Set tablakardexesForFkCodigoUbicacionOrigen,Set movimientosForFkCodigoUbicacionOrigen,
			Set movimientosForFkCodigoUbicacionDestino, Set medioalmacenamientos, Set ingresoproductoprocesos,
			Set tablakardexesForFkCodigoUbicacionDestino) {
		this.almacen = almacen;
		this.nombreUbicacion = nombreUbicacion;
		this.descripcionUbicacion = descripcionUbicacion;
		this.tablakardexesForFkCodigoUbicacionOrigen = tablakardexesForFkCodigoUbicacionOrigen;
	
		this.movimientosForFkCodigoUbicacionOrigen = movimientosForFkCodigoUbicacionOrigen;
		this.movimientosForFkCodigoUbicacionDestino = movimientosForFkCodigoUbicacionDestino;
		this.medioalmacenamientos = medioalmacenamientos;
		this.ingresoproductoprocesos = ingresoproductoprocesos;
		this.tablakardexesForFkCodigoUbicacionDestino = tablakardexesForFkCodigoUbicacionDestino;
	}

	// Property accessors

	public Long getPkCodigoUbicacion() {
		return this.pkCodigoUbicacion;
	}

	public void setPkCodigoUbicacion(Long pkCodigoUbicacion) {
		this.pkCodigoUbicacion = pkCodigoUbicacion;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public String getNombreUbicacion() {
		return this.nombreUbicacion;
	}

	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}

	public String getDescripcionUbicacion() {
		return this.descripcionUbicacion;
	}

	public void setDescripcionUbicacion(String descripcionUbicacion) {
		this.descripcionUbicacion = descripcionUbicacion;
	}

	public Set getTablakardexesForFkCodigoUbicacionOrigen() {
		return this.tablakardexesForFkCodigoUbicacionOrigen;
	}

	public void setTablakardexesForFkCodigoUbicacionOrigen(Set tablakardexesForFkCodigoUbicacionOrigen) {
		this.tablakardexesForFkCodigoUbicacionOrigen = tablakardexesForFkCodigoUbicacionOrigen;
	}



	public Set getMovimientosForFkCodigoUbicacionOrigen() {
		return this.movimientosForFkCodigoUbicacionOrigen;
	}

	public void setMovimientosForFkCodigoUbicacionOrigen(Set movimientosForFkCodigoUbicacionOrigen) {
		this.movimientosForFkCodigoUbicacionOrigen = movimientosForFkCodigoUbicacionOrigen;
	}

	public Set getMovimientosForFkCodigoUbicacionDestino() {
		return this.movimientosForFkCodigoUbicacionDestino;
	}

	public void setMovimientosForFkCodigoUbicacionDestino(Set movimientosForFkCodigoUbicacionDestino) {
		this.movimientosForFkCodigoUbicacionDestino = movimientosForFkCodigoUbicacionDestino;
	}

	public Set getMedioalmacenamientos() {
		return this.medioalmacenamientos;
	}

	public void setMedioalmacenamientos(Set medioalmacenamientos) {
		this.medioalmacenamientos = medioalmacenamientos;
	}

	public Set getIngresoproductoprocesos() {
		return this.ingresoproductoprocesos;
	}

	public void setIngresoproductoprocesos(Set ingresoproductoprocesos) {
		this.ingresoproductoprocesos = ingresoproductoprocesos;
	}

	public Set getTablakardexesForFkCodigoUbicacionDestino() {
		return this.tablakardexesForFkCodigoUbicacionDestino;
	}

	public void setTablakardexesForFkCodigoUbicacionDestino(Set tablakardexesForFkCodigoUbicacionDestino) {
		this.tablakardexesForFkCodigoUbicacionDestino = tablakardexesForFkCodigoUbicacionDestino;
	}

}