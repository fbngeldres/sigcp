package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Ordenproduccionmanual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ordenproduccionmanual implements java.io.Serializable {

	// Fields

	private Long pkCodigoOrdenproduccionmanual;
	private Ordenproduccion ordenproduccion;
	private Integer annoOrdenproduccionmanual;
	private Set consumocomponentemanuals = new HashSet(0);
	private Set consumorecursomanuals = new HashSet(0);
	private Set consumocapacidadmanuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ordenproduccionmanual() {
	}

	/** minimal constructor */
	public Ordenproduccionmanual(Ordenproduccion ordenproduccion, Integer annoOrdenproduccionmanual) {
		this.ordenproduccion = ordenproduccion;
		this.annoOrdenproduccionmanual = annoOrdenproduccionmanual;
	}

	/** full constructor */
	public Ordenproduccionmanual(Ordenproduccion ordenproduccion, Integer annoOrdenproduccionmanual,
			Set consumocomponentemanuals, Set consumorecursomanuals, Set consumocapacidadmanuals) {
		this.ordenproduccion = ordenproduccion;
		this.annoOrdenproduccionmanual = annoOrdenproduccionmanual;
		this.consumocomponentemanuals = consumocomponentemanuals;
		this.consumorecursomanuals = consumorecursomanuals;
		this.consumocapacidadmanuals = consumocapacidadmanuals;
	}

	// Property accessors

	public Long getPkCodigoOrdenproduccionmanual() {
		return this.pkCodigoOrdenproduccionmanual;
	}

	public void setPkCodigoOrdenproduccionmanual(Long pkCodigoOrdenproduccionmanual) {
		this.pkCodigoOrdenproduccionmanual = pkCodigoOrdenproduccionmanual;
	}

	public Ordenproduccion getOrdenproduccion() {
		return this.ordenproduccion;
	}

	public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Integer getAnnoOrdenproduccionmanual() {
		return this.annoOrdenproduccionmanual;
	}

	public void setAnnoOrdenproduccionmanual(Integer annoOrdenproduccionmanual) {
		this.annoOrdenproduccionmanual = annoOrdenproduccionmanual;
	}

	public Set getConsumocomponentemanuals() {
		return this.consumocomponentemanuals;
	}

	public void setConsumocomponentemanuals(Set consumocomponentemanuals) {
		this.consumocomponentemanuals = consumocomponentemanuals;
	}

	public Set getConsumorecursomanuals() {
		return this.consumorecursomanuals;
	}

	public void setConsumorecursomanuals(Set consumorecursomanuals) {
		this.consumorecursomanuals = consumorecursomanuals;
	}

	public Set getConsumocapacidadmanuals() {
		return this.consumocapacidadmanuals;
	}

	public void setConsumocapacidadmanuals(Set consumocapacidadmanuals) {
		this.consumocapacidadmanuals = consumocapacidadmanuals;
	}

}