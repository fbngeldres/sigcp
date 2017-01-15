package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Partediario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Partediario implements java.io.Serializable {

	// Fields

	private Long pkCodigoPartediario;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Estadopartediario estadopartediario;
	private Periodocontable periodocontable;
	private Lineanegocio lineanegocio;
	private Usuario usuarioByFkCodigoUsuarioCierra;
	private Set producciondiarias = new HashSet(0);
	private Set produccionpuestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Partediario() {
	}

	/** minimal constructor */
	public Partediario(Usuario usuarioByFkCodigoUsuarioRegistra, Estadopartediario estadopartediario,
			Periodocontable periodocontable) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.estadopartediario = estadopartediario;
		this.periodocontable = periodocontable;
	}

	/** full constructor */
	public Partediario(Usuario usuarioByFkCodigoUsuarioRegistra, Estadopartediario estadopartediario,
			Periodocontable periodocontable, Lineanegocio lineanegocio, Usuario usuarioByFkCodigoUsuarioCierra,
			Set producciondiarias, Set produccionpuestotrabajos) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.estadopartediario = estadopartediario;
		this.periodocontable = periodocontable;
		this.lineanegocio = lineanegocio;
		this.usuarioByFkCodigoUsuarioCierra = usuarioByFkCodigoUsuarioCierra;
		this.producciondiarias = producciondiarias;
		this.produccionpuestotrabajos = produccionpuestotrabajos;
	}

	// Property accessors

	public Long getPkCodigoPartediario() {
		return this.pkCodigoPartediario;
	}

	public void setPkCodigoPartediario(Long pkCodigoPartediario) {
		this.pkCodigoPartediario = pkCodigoPartediario;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return this.usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Estadopartediario getEstadopartediario() {
		return this.estadopartediario;
	}

	public void setEstadopartediario(Estadopartediario estadopartediario) {
		this.estadopartediario = estadopartediario;
	}

	public Periodocontable getPeriodocontable() {
		return this.periodocontable;
	}

	public void setPeriodocontable(Periodocontable periodocontable) {
		this.periodocontable = periodocontable;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Usuario getUsuarioByFkCodigoUsuarioCierra() {
		return this.usuarioByFkCodigoUsuarioCierra;
	}

	public void setUsuarioByFkCodigoUsuarioCierra(Usuario usuarioByFkCodigoUsuarioCierra) {
		this.usuarioByFkCodigoUsuarioCierra = usuarioByFkCodigoUsuarioCierra;
	}

	public Set getProducciondiarias() {
		return this.producciondiarias;
	}

	public void setProducciondiarias(Set producciondiarias) {
		this.producciondiarias = producciondiarias;
	}

	public Set getProduccionpuestotrabajos() {
		return this.produccionpuestotrabajos;
	}

	public void setProduccionpuestotrabajos(Set produccionpuestotrabajos) {
		this.produccionpuestotrabajos = produccionpuestotrabajos;
	}

}