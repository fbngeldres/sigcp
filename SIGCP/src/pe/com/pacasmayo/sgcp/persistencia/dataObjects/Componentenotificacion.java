package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Componentenotificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Componentenotificacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoComponentenotificacio;
	private Componente componente;
	private Notificacionproduccion notificacionproduccion;
	private Double valorComponentenotificacion;

	// Constructors

	/** default constructor */
	public Componentenotificacion() {
	}

	/** full constructor */
	public Componentenotificacion(Componente componente, Notificacionproduccion notificacionproduccion,
			Double valorComponentenotificacion) {
		this.componente = componente;
		this.notificacionproduccion = notificacionproduccion;
		this.valorComponentenotificacion = valorComponentenotificacion;
	}

	// Property accessors

	public Long getPkCodigoComponentenotificacio() {
		return this.pkCodigoComponentenotificacio;
	}

	public void setPkCodigoComponentenotificacio(Long pkCodigoComponentenotificacio) {
		this.pkCodigoComponentenotificacio = pkCodigoComponentenotificacio;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Notificacionproduccion getNotificacionproduccion() {
		return this.notificacionproduccion;
	}

	public void setNotificacionproduccion(Notificacionproduccion notificacionproduccion) {
		this.notificacionproduccion = notificacionproduccion;
	}

	public Double getValorComponentenotificacion() {
		return this.valorComponentenotificacion;
	}

	public void setValorComponentenotificacion(Double valorComponentenotificacion) {
		this.valorComponentenotificacion = valorComponentenotificacion;
	}

}