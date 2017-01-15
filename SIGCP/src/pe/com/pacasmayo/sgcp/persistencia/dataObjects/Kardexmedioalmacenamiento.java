package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Kardexmedioalmacenamiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Kardexmedioalmacenamiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoKardexmedioalmacenamiento;
	private Tablakardex fkCodigoTablakardex;
	private Medioalmacenamiento fkCodigoMedioalmacenamiento;
	private Double saldoInicialTablakardexmedioalmac;
	private Double ingresoTablakardexmedioalmac;
	private Double consumoTablakardexmedioalmac;
	private Double stockFinallTablakardexmedioalmac;

	// Constructors

	/** default constructor */
	public Kardexmedioalmacenamiento() {
	}

	/** full constructor */
	public Kardexmedioalmacenamiento(Tablakardex fkCodigoTablakardex, Medioalmacenamiento fkCodigoMedioalmacenamiento,
			Double saldoInicialTablakardexmedioalmac, Double ingresoTablakardexmedioalmac, Double consumoTablakardexmedioalmac,
			Double stockFinallTablakardexmedioalmac) {
		this.fkCodigoTablakardex = fkCodigoTablakardex;
		this.fkCodigoMedioalmacenamiento = fkCodigoMedioalmacenamiento;
		this.saldoInicialTablakardexmedioalmac = saldoInicialTablakardexmedioalmac;
		this.ingresoTablakardexmedioalmac = ingresoTablakardexmedioalmac;
		this.consumoTablakardexmedioalmac = consumoTablakardexmedioalmac;
		this.stockFinallTablakardexmedioalmac = stockFinallTablakardexmedioalmac;
	}

	// Property accessors

	public Long getPkCodigoKardexmedioalmacenamiento() {
		return this.pkCodigoKardexmedioalmacenamiento;
	}

	public void setPkCodigoKardexmedioalmacenamiento(Long pkCodigoKardexmedioalmacenamiento) {
		this.pkCodigoKardexmedioalmacenamiento = pkCodigoKardexmedioalmacenamiento;
	}

	public Tablakardex getFkCodigoTablakardex() {
		return this.fkCodigoTablakardex;
	}

	public void setFkCodigoTablakardex(Tablakardex fkCodigoTablakardex) {
		this.fkCodigoTablakardex = fkCodigoTablakardex;
	}

	public Medioalmacenamiento getFkCodigoMedioalmacenamiento() {
		return this.fkCodigoMedioalmacenamiento;
	}

	public void setFkCodigoMedioalmacenamiento(Medioalmacenamiento fkCodigoMedioalmacenamiento) {
		this.fkCodigoMedioalmacenamiento = fkCodigoMedioalmacenamiento;
	}

	public Double getSaldoInicialTablakardexmedioalmac() {
		return this.saldoInicialTablakardexmedioalmac;
	}

	public void setSaldoInicialTablakardexmedioalmac(Double saldoInicialTablakardexmedioalmac) {
		this.saldoInicialTablakardexmedioalmac = saldoInicialTablakardexmedioalmac;
	}

	public Double getIngresoTablakardexmedioalmac() {
		return this.ingresoTablakardexmedioalmac;
	}

	public void setIngresoTablakardexmedioalmac(Double ingresoTablakardexmedioalmac) {
		this.ingresoTablakardexmedioalmac = ingresoTablakardexmedioalmac;
	}

	public Double getConsumoTablakardexmedioalmac() {
		return this.consumoTablakardexmedioalmac;
	}

	public void setConsumoTablakardexmedioalmac(Double consumoTablakardexmedioalmac) {
		this.consumoTablakardexmedioalmac = consumoTablakardexmedioalmac;
	}

	public Double getStockFinallTablakardexmedioalmac() {
		return this.stockFinallTablakardexmedioalmac;
	}

	public void setStockFinallTablakardexmedioalmac(Double stockFinallTablakardexmedioalmac) {
		this.stockFinallTablakardexmedioalmac = stockFinallTablakardexmedioalmac;
	}

}