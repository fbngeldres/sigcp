package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Plantillaproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plantillaconsumo implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlantillaconsumo;
	private Sociedad sociedad;
	private Proceso proceso;
	private Puestotrabajo puestotrabajo;
	private Producto producto;
	private Componente componente;
	private Objetocostos objetocosto;
	private Boolean estado;

	public Plantillaconsumo() {

	}

	/**
	 * @param pkCodigoPlantillaconsumo
	 * @param sociedad
	 * @param proceso
	 * @param puestotrabajo
	 * @param producto
	 * @param componente
	 * @param objetocosto
	 * @param estado
	 */
	public Plantillaconsumo(Long pkCodigoPlantillaconsumo, Sociedad sociedad, Proceso proceso, Puestotrabajo puestotrabajo,
			Producto producto, Componente componente, Objetocostos objetocosto, Boolean estado) {
		super();
		this.pkCodigoPlantillaconsumo = pkCodigoPlantillaconsumo;
		this.sociedad = sociedad;
		this.proceso = proceso;
		this.puestotrabajo = puestotrabajo;
		this.producto = producto;
		this.componente = componente;
		this.objetocosto = objetocosto;
		this.estado = estado;
	}

	/**
	 * @return the pkCodigoPlantillaconsumo
	 */
	public Long getPkCodigoPlantillaconsumo() {
		return pkCodigoPlantillaconsumo;
	}

	/**
	 * @param pkCodigoPlantillaconsumo the pkCodigoPlantillaconsumo to set
	 */
	public void setPkCodigoPlantillaconsumo(Long pkCodigoPlantillaconsumo) {
		this.pkCodigoPlantillaconsumo = pkCodigoPlantillaconsumo;
	}

	/**
	 * @return the sociedad
	 */
	public Sociedad getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(Sociedad sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the proceso
	 */
	public Proceso getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the puestotrabajo
	 */
	public Puestotrabajo getPuestotrabajo() {
		return puestotrabajo;
	}

	/**
	 * @param puestotrabajo the puestotrabajo to set
	 */
	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
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
	 * @return the componente
	 */
	public Componente getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	/**
	 * @return the objetocosto
	 */
	public Objetocostos getObjetocosto() {
		return objetocosto;
	}

	/**
	 * @param objetocosto the objetocosto to set
	 */
	public void setObjetocosto(Objetocostos objetocosto) {
		this.objetocosto = objetocosto;
	}

	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}