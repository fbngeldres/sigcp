package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Plantillaajusteproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plantillaajusteproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlantillaajusteproduc;
	private Plantillagrupoajuste plantillagrupoajuste;
	private Produccion produccion;
	private Short ordenPlantillaajusteproducto;

	// Constructors

	/** default constructor */
	public Plantillaajusteproducto() {
	}

	/** full constructor */
	public Plantillaajusteproducto(Plantillagrupoajuste plantillagrupoajuste, Produccion produccion,
			Short ordenPlantillaajusteproducto) {
		this.plantillagrupoajuste = plantillagrupoajuste;
		this.produccion = produccion;
		this.ordenPlantillaajusteproducto = ordenPlantillaajusteproducto;
	}

	// Property accessors

	public Long getPkCodigoPlantillaajusteproduc() {
		return this.pkCodigoPlantillaajusteproduc;
	}

	public void setPkCodigoPlantillaajusteproduc(Long pkCodigoPlantillaajusteproduc) {
		this.pkCodigoPlantillaajusteproduc = pkCodigoPlantillaajusteproduc;
	}

	public Plantillagrupoajuste getPlantillagrupoajuste() {
		return this.plantillagrupoajuste;
	}

	public void setPlantillagrupoajuste(Plantillagrupoajuste plantillagrupoajuste) {
		this.plantillagrupoajuste = plantillagrupoajuste;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Short getOrdenPlantillaajusteproducto() {
		return this.ordenPlantillaajusteproducto;
	}

	public void setOrdenPlantillaajusteproducto(Short ordenPlantillaajusteproducto) {
		this.ordenPlantillaajusteproducto = ordenPlantillaajusteproducto;
	}

}