package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Columnaplantillaproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Columnaplantillaproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoColumnaplantillaprodu;
	private Plantillaproducto plantillaproducto;
	private Variableoperacion variableoperacion;
	private Componente componente;
	private Columnareporte columnareporte;
	private Double proporcionColumnareporte;

	// Constructors

	/** default constructor */
	public Columnaplantillaproducto() {
	}

	/** minimal constructor */
	public Columnaplantillaproducto(Plantillaproducto plantillaproducto, Columnareporte columnareporte,
			Double proporcionColumnareporte) {
		this.plantillaproducto = plantillaproducto;
		this.columnareporte = columnareporte;
		this.proporcionColumnareporte = proporcionColumnareporte;
	}

	/** full constructor */
	public Columnaplantillaproducto(Plantillaproducto plantillaproducto, Variableoperacion variableoperacion,
			Componente componente, Columnareporte columnareporte, Double proporcionColumnareporte) {
		this.plantillaproducto = plantillaproducto;
		this.variableoperacion = variableoperacion;
		this.componente = componente;
		this.columnareporte = columnareporte;
		this.proporcionColumnareporte = proporcionColumnareporte;
	}

	// Property accessors

	public Long getPkCodigoColumnaplantillaprodu() {
		return this.pkCodigoColumnaplantillaprodu;
	}

	public void setPkCodigoColumnaplantillaprodu(Long pkCodigoColumnaplantillaprodu) {
		this.pkCodigoColumnaplantillaprodu = pkCodigoColumnaplantillaprodu;
	}

	public Plantillaproducto getPlantillaproducto() {
		return this.plantillaproducto;
	}

	public void setPlantillaproducto(Plantillaproducto plantillaproducto) {
		this.plantillaproducto = plantillaproducto;
	}

	public Variableoperacion getVariableoperacion() {
		return this.variableoperacion;
	}

	public void setVariableoperacion(Variableoperacion variableoperacion) {
		this.variableoperacion = variableoperacion;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Columnareporte getColumnareporte() {
		return this.columnareporte;
	}

	public void setColumnareporte(Columnareporte columnareporte) {
		this.columnareporte = columnareporte;
	}

	public Double getProporcionColumnareporte() {
		return this.proporcionColumnareporte;
	}

	public void setProporcionColumnareporte(Double proporcionColumnareporte) {
		this.proporcionColumnareporte = proporcionColumnareporte;
	}

}