package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cubicacionproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Cubicacionproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoCubicacionproducto;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Usuario usuarioByFkCodigoUsuario;
	private Estadocubicacion estadocubicacion;
	private Lineanegocio lineanegocio;
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Produccion produccion;
	private Date fechaCubicacionproducto;
	private Double stockFisicoCubicacionproducto;
	private Short mesCubicacionproducto;
	private Integer anoCubicacionproducto;
	private Double toneladasCubicacionproducto;
	private Set cubicacions = new HashSet(0);
	private Double humedadPonderadaCubicacionproducto;

	private Double stockLibros;

	// Constructors

	/** default constructor */
	public Cubicacionproducto() {
	}

	/** minimal constructor */
	public Cubicacionproducto(Usuario usuarioByFkCodigoUsuarioRegistra, Usuario usuarioByFkCodigoUsuario,
			Estadocubicacion estadocubicacion, Lineanegocio lineanegocio, Produccion produccion, Date fechaCubicacionproducto,
			Double stockFisicoCubicacionproducto, Short mesCubicacionproducto, Integer anoCubicacionproducto,
			Double toneladasCubicacionproducto) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
		this.estadocubicacion = estadocubicacion;
		this.lineanegocio = lineanegocio;
		this.produccion = produccion;
		this.fechaCubicacionproducto = fechaCubicacionproducto;
		this.stockFisicoCubicacionproducto = stockFisicoCubicacionproducto;
		this.mesCubicacionproducto = mesCubicacionproducto;
		this.anoCubicacionproducto = anoCubicacionproducto;
		this.toneladasCubicacionproducto = toneladasCubicacionproducto;
	}

	/** full constructor */
	public Cubicacionproducto(Usuario usuarioByFkCodigoUsuarioRegistra, Usuario usuarioByFkCodigoUsuario,
			Estadocubicacion estadocubicacion, Lineanegocio lineanegocio, Usuario usuarioByFkCodigoUsuarioAprueba,
			Produccion produccion, Date fechaCubicacionproducto, Double stockFisicoCubicacionproducto,
			Short mesCubicacionproducto, Integer anoCubicacionproducto, Double toneladasCubicacionproducto,
			Double humedadPonderadaCubicacionproducto, Set cubicacions) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
		this.estadocubicacion = estadocubicacion;
		this.lineanegocio = lineanegocio;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.produccion = produccion;
		this.fechaCubicacionproducto = fechaCubicacionproducto;
		this.stockFisicoCubicacionproducto = stockFisicoCubicacionproducto;
		this.mesCubicacionproducto = mesCubicacionproducto;
		this.anoCubicacionproducto = anoCubicacionproducto;
		this.toneladasCubicacionproducto = toneladasCubicacionproducto;
		this.cubicacions = cubicacions;
		this.humedadPonderadaCubicacionproducto = humedadPonderadaCubicacionproducto;
	}

	// Property accessors

	public Long getPkCodigoCubicacionproducto() {
		return this.pkCodigoCubicacionproducto;
	}

	public void setPkCodigoCubicacionproducto(Long pkCodigoCubicacionproducto) {
		this.pkCodigoCubicacionproducto = pkCodigoCubicacionproducto;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return this.usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Usuario getUsuarioByFkCodigoUsuario() {
		return this.usuarioByFkCodigoUsuario;
	}

	public void setUsuarioByFkCodigoUsuario(Usuario usuarioByFkCodigoUsuario) {
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
	}

	public Estadocubicacion getEstadocubicacion() {
		return this.estadocubicacion;
	}

	public void setEstadocubicacion(Estadocubicacion estadocubicacion) {
		this.estadocubicacion = estadocubicacion;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return this.usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Date getFechaCubicacionproducto() {
		return this.fechaCubicacionproducto;
	}

	public void setFechaCubicacionproducto(Date fechaCubicacionproducto) {
		this.fechaCubicacionproducto = fechaCubicacionproducto;
	}

	public Double getStockFisicoCubicacionproducto() {
		return this.stockFisicoCubicacionproducto;
	}

	public void setStockFisicoCubicacionproducto(Double stockFisicoCubicacionproducto) {
		this.stockFisicoCubicacionproducto = stockFisicoCubicacionproducto;
	}

	public Short getMesCubicacionproducto() {
		return this.mesCubicacionproducto;
	}

	public void setMesCubicacionproducto(Short mesCubicacionproducto) {
		this.mesCubicacionproducto = mesCubicacionproducto;
	}

	public Integer getAnoCubicacionproducto() {
		return this.anoCubicacionproducto;
	}

	public void setAnoCubicacionproducto(Integer anoCubicacionproducto) {
		this.anoCubicacionproducto = anoCubicacionproducto;
	}

	public Double getToneladasCubicacionproducto() {
		return this.toneladasCubicacionproducto;
	}

	public void setToneladasCubicacionproducto(Double toneladasCubicacionproducto) {
		this.toneladasCubicacionproducto = toneladasCubicacionproducto;
	}

	public Set getCubicacions() {
		return this.cubicacions;
	}

	public void setCubicacions(Set cubicacions) {
		this.cubicacions = cubicacions;
	}

	/**
	 * @return the stockLibros
	 */
	public Double getStockLibros() {
		return stockLibros;
	}

	/**
	 * @param stockLibros the stockLibros to set
	 */
	public void setStockLibros(Double stockLibros) {
		this.stockLibros = stockLibros;
	}

	/**
	 * @return the humedadPonderadaCubicacionproducto
	 */
	public Double getHumedadPonderadaCubicacionproducto() {
		return humedadPonderadaCubicacionproducto;
	}

	/**
	 * @param humedadPonderadaCubicacionproducto the
	 *            humedadPonderadaCubicacionproducto to set
	 */
	public void setHumedadPonderadaCubicacionproducto(Double humedadPonderadaCubicacionproducto) {
		this.humedadPonderadaCubicacionproducto = humedadPonderadaCubicacionproducto;
	}

}