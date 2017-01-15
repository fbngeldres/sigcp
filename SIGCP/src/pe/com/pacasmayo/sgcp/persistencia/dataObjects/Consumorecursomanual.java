package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Consumorecursomanual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumorecursomanual implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumorecursomanual;
	private Recurso recurso;
	private Ordenproduccionmanual ordenproduccionmanual;
	private Double cantidadConsumo;
	private Double cantidadejecConsumorecursomanu;

	// Constructors

	/** default constructor */
	public Consumorecursomanual() {
	}

	/** full constructor */
	public Consumorecursomanual(Recurso recurso, Ordenproduccionmanual ordenproduccionmanual, Double cantidadConsumo,
			Double cantidadejecConsumorecursomanu) {
		this.recurso = recurso;
		this.ordenproduccionmanual = ordenproduccionmanual;
		this.cantidadConsumo = cantidadConsumo;
		this.cantidadejecConsumorecursomanu = cantidadejecConsumorecursomanu;
	}

	// Property accessors

	public Long getPkCodigoConsumorecursomanual() {
		return this.pkCodigoConsumorecursomanual;
	}

	public void setPkCodigoConsumorecursomanual(Long pkCodigoConsumorecursomanual) {
		this.pkCodigoConsumorecursomanual = pkCodigoConsumorecursomanual;
	}

	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Ordenproduccionmanual getOrdenproduccionmanual() {
		return this.ordenproduccionmanual;
	}

	public void setOrdenproduccionmanual(Ordenproduccionmanual ordenproduccionmanual) {
		this.ordenproduccionmanual = ordenproduccionmanual;
	}

	public Double getCantidadConsumo() {
		return this.cantidadConsumo;
	}

	public void setCantidadConsumo(Double cantidadConsumo) {
		this.cantidadConsumo = cantidadConsumo;
	}

	public Double getCantidadejecConsumorecursomanu() {
		return this.cantidadejecConsumorecursomanu;
	}

	public void setCantidadejecConsumorecursomanu(Double cantidadejecConsumorecursomanu) {
		this.cantidadejecConsumorecursomanu = cantidadejecConsumorecursomanu;
	}

}