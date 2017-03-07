package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Accion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Capacidadbolsaproducto implements java.io.Serializable {

	private Long pkCodigoCapacidadbolsaproducto;
	private String codigoSapProducto;
	private Double capacidadNetaBolsa;
	private Double capacidadBrutaBolsa;

	public Capacidadbolsaproducto() {

	}

	public Capacidadbolsaproducto(Long pkCodigoCapacidadbolsaproducto, String codigoSapProducto, Double capacidadNetaBolsa,
			Double capacidadBrutaBolsa) {
		super();
		this.pkCodigoCapacidadbolsaproducto = pkCodigoCapacidadbolsaproducto;
		this.codigoSapProducto = codigoSapProducto;
		this.capacidadNetaBolsa = capacidadNetaBolsa;
		this.capacidadBrutaBolsa = capacidadBrutaBolsa;
	}

	public Long getPkCodigoCapacidadbolsaproducto() {
		return pkCodigoCapacidadbolsaproducto;
	}

	public void setPkCodigoCapacidadbolsaproducto(Long pkCodigoCapacidadbolsaproducto) {
		this.pkCodigoCapacidadbolsaproducto = pkCodigoCapacidadbolsaproducto;
	}

	public String getCodigoSapProducto() {
		return codigoSapProducto;
	}

	public void setCodigoSapProducto(String codigoSapProducto) {
		this.codigoSapProducto = codigoSapProducto;
	}

	public Double getCapacidadNetaBolsa() {
		return capacidadNetaBolsa;
	}

	public void setCapacidadNetaBolsa(Double capacidadNetaBolsa) {
		this.capacidadNetaBolsa = capacidadNetaBolsa;
	}

	public Double getCapacidadBrutaBolsa() {
		return capacidadBrutaBolsa;
	}

	public void setCapacidadBrutaBolsa(Double capacidadBrutaBolsa) {
		this.capacidadBrutaBolsa = capacidadBrutaBolsa;
	}

}