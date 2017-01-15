package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Plantillagrupoajuste entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plantillagrupoajuste implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlantillagrupoajuste;
	private Lineanegocio lineanegocio;
	private Short ordenPlantillagrupoajuste;
	private String nombrePlantillagrupoajuste;
	private Set plantillaajusteproductos = new HashSet(0);
	private Set ajusteproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plantillagrupoajuste() {
	}

	/** minimal constructor */
	public Plantillagrupoajuste(Lineanegocio lineanegocio, Short ordenPlantillagrupoajuste, String nombrePlantillagrupoajuste) {
		this.lineanegocio = lineanegocio;
		this.ordenPlantillagrupoajuste = ordenPlantillagrupoajuste;
		this.nombrePlantillagrupoajuste = nombrePlantillagrupoajuste;
	}

	/** full constructor */
	public Plantillagrupoajuste(Lineanegocio lineanegocio, Short ordenPlantillagrupoajuste, String nombrePlantillagrupoajuste,
			Set plantillaajusteproductos) {
		this.lineanegocio = lineanegocio;
		this.ordenPlantillagrupoajuste = ordenPlantillagrupoajuste;
		this.nombrePlantillagrupoajuste = nombrePlantillagrupoajuste;
		this.plantillaajusteproductos = plantillaajusteproductos;
	}

	// Property accessors

	public Long getPkCodigoPlantillagrupoajuste() {
		return this.pkCodigoPlantillagrupoajuste;
	}

	public void setPkCodigoPlantillagrupoajuste(Long pkCodigoPlantillagrupoajuste) {
		this.pkCodigoPlantillagrupoajuste = pkCodigoPlantillagrupoajuste;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Short getOrdenPlantillagrupoajuste() {
		return this.ordenPlantillagrupoajuste;
	}

	public void setOrdenPlantillagrupoajuste(Short ordenPlantillagrupoajuste) {
		this.ordenPlantillagrupoajuste = ordenPlantillagrupoajuste;
	}

	public String getNombrePlantillagrupoajuste() {
		return this.nombrePlantillagrupoajuste;
	}

	public void setNombrePlantillagrupoajuste(String nombrePlantillagrupoajuste) {
		this.nombrePlantillagrupoajuste = nombrePlantillagrupoajuste;
	}

	public Set getPlantillaajusteproductos() {
		return this.plantillaajusteproductos;
	}

	public void setPlantillaajusteproductos(Set plantillaajusteproductos) {
		this.plantillaajusteproductos = plantillaajusteproductos;
	}

	/**
	 * @return the ajusteproductos
	 */
	public Set getAjusteproductos() {
		return ajusteproductos;
	}

	/**
	 * @param ajusteproductos the ajusteproductos to set
	 */
	public void setAjusteproductos(Set ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

}