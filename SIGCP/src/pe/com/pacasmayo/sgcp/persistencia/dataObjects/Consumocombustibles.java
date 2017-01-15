package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

public class Consumocombustibles implements java.io.Serializable {

	// Fields
	private Long pkCodigoConsumocombustibles;
	private Puestotrabajoproduccion puestotrabajoproduccion;
	private Componente componente;
	private Double combustibleprodConsumocombustibles;
	private Double combustiblecalentConsumocombustibles;
	private Double combustibleprodRealConsumocombustibles;
	private Double combustiblecalentRealConsumocombustibles;

	public Consumocombustibles() {
	}

	public Consumocombustibles(Long pkCodigoConsumocombustibles, Puestotrabajoproduccion puestotrabajoproduccion,
			Componente componente, Double combustibleprodConsumocombustibles, Double combustiblecalentConsumocombustibles,
			Double combustibleprodRealConsumocombustibles, Double combustiblecalentRealConsumocombustibles) {
		this.pkCodigoConsumocombustibles = pkCodigoConsumocombustibles;
		this.puestotrabajoproduccion = puestotrabajoproduccion;
		this.componente = componente;
		this.combustibleprodConsumocombustibles = combustibleprodConsumocombustibles;
		this.combustiblecalentConsumocombustibles = combustiblecalentConsumocombustibles;
		this.combustibleprodRealConsumocombustibles = combustibleprodRealConsumocombustibles;
		this.combustiblecalentRealConsumocombustibles = combustiblecalentRealConsumocombustibles;
	}

	public Long getPkCodigoConsumocombustibles() {
		return pkCodigoConsumocombustibles;
	}

	/**
	 * @param pkCodigoConsumocombustibles the pkCodigoConsumocombustibles to set
	 */
	public void setPkCodigoConsumocombustibles(Long pkCodigoConsumocombustibles) {
		this.pkCodigoConsumocombustibles = pkCodigoConsumocombustibles;
	}

	/**
	 * @return the puestotrabajoproduccion
	 */
	public Puestotrabajoproduccion getPuestotrabajoproduccion() {
		return puestotrabajoproduccion;
	}

	/**
	 * @param puestotrabajoproduccion the puestotrabajoproduccion to set
	 */
	public void setPuestotrabajoproduccion(Puestotrabajoproduccion puestotrabajoproduccion) {
		this.puestotrabajoproduccion = puestotrabajoproduccion;
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
	 * @return the combustibleprodConsumocombustibles
	 */
	public Double getCombustibleprodConsumocombustibles() {
		return combustibleprodConsumocombustibles;
	}

	/**
	 * @param combustibleprodConsumocombustibles the
	 *            combustibleprodConsumocombustibles to set
	 */
	public void setCombustibleprodConsumocombustibles(Double combustibleprodConsumocombustibles) {
		this.combustibleprodConsumocombustibles = combustibleprodConsumocombustibles;
	}

	/**
	 * @return the combustiblecalentConsumocombustibles
	 */
	public Double getCombustiblecalentConsumocombustibles() {
		return combustiblecalentConsumocombustibles;
	}

	/**
	 * @param combustiblecalentConsumocombustibles the
	 *            combustiblecalentConsumocombustibles to set
	 */
	public void setCombustiblecalentConsumocombustibles(Double combustiblecalentConsumocombustibles) {
		this.combustiblecalentConsumocombustibles = combustiblecalentConsumocombustibles;
	}

	/**
	 * @return the combustibleprodRealConsumocombustibles
	 */
	public Double getCombustibleprodRealConsumocombustibles() {
		return combustibleprodRealConsumocombustibles;
	}

	/**
	 * @param combustibleprodRealConsumocombustibles the
	 *            combustibleprodRealConsumocombustibles to set
	 */
	public void setCombustibleprodRealConsumocombustibles(Double combustibleprodRealConsumocombustibles) {
		this.combustibleprodRealConsumocombustibles = combustibleprodRealConsumocombustibles;
	}

	/**
	 * @return the combustiblecalentRealConsumocombustibles
	 */
	public Double getCombustiblecalentRealConsumocombustibles() {
		return combustiblecalentRealConsumocombustibles;
	}

	/**
	 * @param combustiblecalentRealConsumocombustibles the
	 *            combustiblecalentRealConsumocombustibles to set
	 */
	public void setCombustiblecalentRealConsumocombustibles(Double combustiblecalentRealConsumocombustibles) {
		this.combustiblecalentRealConsumocombustibles = combustiblecalentRealConsumocombustibles;
	}
}
