package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumocomponentemanual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocomponentemanual implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocomponentemanu;
	private Componente componente;
	private Ordenproduccionmanual ordenproduccionmanual;
	private Double cantidadConsumocomponentemanua;
	private Double cantidadejecConsumocomponentem;

	// Constructors

	/** default constructor */
	public Consumocomponentemanual() {
	}

	/** full constructor */
	public Consumocomponentemanual(Componente componente, Ordenproduccionmanual ordenproduccionmanual,
			Double cantidadConsumocomponentemanua, Double cantidadejecConsumocomponentem) {
		this.componente = componente;
		this.ordenproduccionmanual = ordenproduccionmanual;
		this.cantidadConsumocomponentemanua = cantidadConsumocomponentemanua;
		this.cantidadejecConsumocomponentem = cantidadejecConsumocomponentem;
	}

	// Property accessors

	public Long getPkCodigoConsumocomponentemanu() {
		return this.pkCodigoConsumocomponentemanu;
	}

	public void setPkCodigoConsumocomponentemanu(Long pkCodigoConsumocomponentemanu) {
		this.pkCodigoConsumocomponentemanu = pkCodigoConsumocomponentemanu;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Ordenproduccionmanual getOrdenproduccionmanual() {
		return this.ordenproduccionmanual;
	}

	public void setOrdenproduccionmanual(Ordenproduccionmanual ordenproduccionmanual) {
		this.ordenproduccionmanual = ordenproduccionmanual;
	}

	public Double getCantidadConsumocomponentemanua() {
		return this.cantidadConsumocomponentemanua;
	}

	public void setCantidadConsumocomponentemanua(Double cantidadConsumocomponentemanua) {
		this.cantidadConsumocomponentemanua = cantidadConsumocomponentemanua;
	}

	public Double getCantidadejecConsumocomponentem() {
		return this.cantidadejecConsumocomponentem;
	}

	public void setCantidadejecConsumocomponentem(Double cantidadejecConsumocomponentem) {
		this.cantidadejecConsumocomponentem = cantidadejecConsumocomponentem;
	}

}