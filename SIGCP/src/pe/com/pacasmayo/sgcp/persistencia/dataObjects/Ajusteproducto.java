package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Ajusteproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ajusteproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoAjusteproducto;
	private Ajusteproduccion ajusteproduccion;
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Estadoajusteproducto estadoajusteproducto;
	private Producto producto;
	private Plantillagrupoajuste plantillagrupoajuste;
	private Usuario usuarioByFkCodigoUsuarioAjusta;
	private String observacionAjusteproducto;
	private Double ajusteAjusteproducto;
	private Set puestotrabajoproduccions = new HashSet(0);
	private Set balanceproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ajusteproducto() {
	}

	/** minimal constructor */
	public Ajusteproducto(Usuario usuarioByFkCodigoUsuarioAprueba, Estadoajusteproducto estadoajusteproducto, Producto producto,
			Double ajusteAjusteproducto) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.estadoajusteproducto = estadoajusteproducto;
		this.producto = producto;
		this.ajusteAjusteproducto = ajusteAjusteproducto;
	}

	/** full constructor */
	public Ajusteproducto(Usuario usuarioByFkCodigoUsuarioAprueba, Estadoajusteproducto estadoajusteproducto, Producto producto,
			Usuario usuarioByFkCodigoUsuarioAjusta, String observacionAjusteproducto, Double ajusteAjusteproducto,
			Set puestotrabajoproduccions, Set balanceproductos) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.estadoajusteproducto = estadoajusteproducto;
		this.producto = producto;
		this.usuarioByFkCodigoUsuarioAjusta = usuarioByFkCodigoUsuarioAjusta;
		this.observacionAjusteproducto = observacionAjusteproducto;
		this.ajusteAjusteproducto = ajusteAjusteproducto;
		this.puestotrabajoproduccions = puestotrabajoproduccions;
		this.balanceproductos = balanceproductos;
	}

	// Property accessors

	public Long getPkCodigoAjusteproducto() {
		return this.pkCodigoAjusteproducto;
	}

	public void setPkCodigoAjusteproducto(Long pkCodigoAjusteproducto) {
		this.pkCodigoAjusteproducto = pkCodigoAjusteproducto;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return this.usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public Estadoajusteproducto getEstadoajusteproducto() {
		return this.estadoajusteproducto;
	}

	public void setEstadoajusteproducto(Estadoajusteproducto estadoajusteproducto) {
		this.estadoajusteproducto = estadoajusteproducto;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAjusta() {
		return this.usuarioByFkCodigoUsuarioAjusta;
	}

	public void setUsuarioByFkCodigoUsuarioAjusta(Usuario usuarioByFkCodigoUsuarioAjusta) {
		this.usuarioByFkCodigoUsuarioAjusta = usuarioByFkCodigoUsuarioAjusta;
	}

	public String getObservacionAjusteproducto() {
		return this.observacionAjusteproducto;
	}

	public void setObservacionAjusteproducto(String observacionAjusteproducto) {
		this.observacionAjusteproducto = observacionAjusteproducto;
	}

	public Double getAjusteAjusteproducto() {
		return this.ajusteAjusteproducto;
	}

	public void setAjusteAjusteproducto(Double ajusteAjusteproducto) {
		this.ajusteAjusteproducto = ajusteAjusteproducto;
	}

	public Set getPuestotrabajoproduccions() {
		return this.puestotrabajoproduccions;
	}

	public void setPuestotrabajoproduccions(Set puestotrabajoproduccions) {
		this.puestotrabajoproduccions = puestotrabajoproduccions;
	}

	public Set getBalanceproductos() {
		return this.balanceproductos;
	}

	public void setBalanceproductos(Set balanceproductos) {
		this.balanceproductos = balanceproductos;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the ajusteproduccion
	 */
	public Ajusteproduccion getAjusteproduccion() {
		return ajusteproduccion;
	}

	/**
	 * @param ajusteproduccion the ajusteproduccion to set
	 */
	public void setAjusteproduccion(Ajusteproduccion ajusteproduccion) {
		this.ajusteproduccion = ajusteproduccion;
	}

	/**
	 * @return the plantillagrupoajuste
	 */
	public Plantillagrupoajuste getPlantillagrupoajuste() {
		return plantillagrupoajuste;
	}

	/**
	 * @param plantillagrupoajuste the plantillagrupoajuste to set
	 */
	public void setPlantillagrupoajuste(Plantillagrupoajuste plantillagrupoajuste) {
		this.plantillagrupoajuste = plantillagrupoajuste;
	}

}