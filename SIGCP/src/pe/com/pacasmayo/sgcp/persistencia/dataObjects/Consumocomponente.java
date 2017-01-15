package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumocomponente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocomponente implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocomponente;
	private Componente componente;
	private Tablakardex tablakardex;
	private Double consumoConsumocomponente;

	private Double consumoConsumocomponenteHumedo;
	// private Double factorConsumocomponenteHumedo;

	private Factorconsumopuestotrabajo fkCodigoFactorconsumopuestotrabajoFactor1;
	private Factorconsumopuestotrabajo fkCodigoFactorconsumopuestotrabajoFactor2;
	private Double consumoConsumocomponenteSemiseco;
	private Double consumoConsumocomponenteDiferencia;

	// Constructors

	/** default constructor */
	public Consumocomponente() {
	}

	/** full constructor */
	public Consumocomponente(Componente componente, Tablakardex tablakardex, Double consumoConsumocomponente) {
		this.componente = componente;
		this.tablakardex = tablakardex;
		this.consumoConsumocomponente = consumoConsumocomponente;
	}

	// Property accessors

	public Long getPkCodigoConsumocomponente() {
		return this.pkCodigoConsumocomponente;
	}

	public void setPkCodigoConsumocomponente(Long pkCodigoConsumocomponente) {
		this.pkCodigoConsumocomponente = pkCodigoConsumocomponente;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Tablakardex getTablakardex() {
		return this.tablakardex;
	}

	public void setTablakardex(Tablakardex tablakardex) {
		this.tablakardex = tablakardex;
	}

	public Double getConsumoConsumocomponente() {
		return this.consumoConsumocomponente;
	}

	public void setConsumoConsumocomponente(Double consumoConsumocomponente) {
		this.consumoConsumocomponente = consumoConsumocomponente;
	}

	/**
	 * @return the consumoConsumocomponenteHumedo
	 */
	public Double getConsumoConsumocomponenteHumedo() {
		return consumoConsumocomponenteHumedo;
	}

	/**
	 * @param consumoConsumocomponenteHumedo the consumoConsumocomponenteHumedo
	 *            to set
	 */
	public void setConsumoConsumocomponenteHumedo(Double consumoConsumocomponenteHumedo) {
		this.consumoConsumocomponenteHumedo = consumoConsumocomponenteHumedo;
	}

	public Factorconsumopuestotrabajo getFkCodigoFactorconsumopuestotrabajoFactor1() {
		return fkCodigoFactorconsumopuestotrabajoFactor1;
	}

	public void setFkCodigoFactorconsumopuestotrabajoFactor1(Factorconsumopuestotrabajo fkCodigoFactorconsumopuestotrabajoFactor1) {
		this.fkCodigoFactorconsumopuestotrabajoFactor1 = fkCodigoFactorconsumopuestotrabajoFactor1;
	}

	public Factorconsumopuestotrabajo getFkCodigoFactorconsumopuestotrabajoFactor2() {
		return fkCodigoFactorconsumopuestotrabajoFactor2;
	}

	public void setFkCodigoFactorconsumopuestotrabajoFactor2(Factorconsumopuestotrabajo fkCodigoFactorconsumopuestotrabajoFactor2) {
		this.fkCodigoFactorconsumopuestotrabajoFactor2 = fkCodigoFactorconsumopuestotrabajoFactor2;
	}

	public Double getConsumoConsumocomponenteSemiseco() {
		return consumoConsumocomponenteSemiseco;
	}

	public void setConsumoConsumocomponenteSemiseco(Double consumoConsumocomponenteSemiseco) {
		this.consumoConsumocomponenteSemiseco = consumoConsumocomponenteSemiseco;
	}

	public Double getConsumoConsumocomponenteDiferencia() {
		return consumoConsumocomponenteDiferencia;
	}

	public void setConsumoConsumocomponenteDiferencia(Double consumoConsumocomponenteDiferencia) {
		this.consumoConsumocomponenteDiferencia = consumoConsumocomponenteDiferencia;
	}

	// /**
	// * @return the factorConsumocomponenteHumedo
	// */
	// public Double getFactorConsumocomponenteHumedo() {
	// return factorConsumocomponenteHumedo;
	// }
	//
	// /**
	// * @param factorConsumocomponenteHumedo the factorConsumocomponenteHumedo
	// to
	// * set
	// */
	// public void setFactorConsumocomponenteHumedo(Double
	// factorConsumocomponenteHumedo) {
	// this.factorConsumocomponenteHumedo = factorConsumocomponenteHumedo;
	// }

}