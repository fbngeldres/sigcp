package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipodocumentomaterial entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipodocumentomaterial implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipodocumentomaterial;
	private String nombreTipodocumentomaterial;
	private Set documentomaterials = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipodocumentomaterial() {
	}

	/** minimal constructor */
	public Tipodocumentomaterial(String nombreTipodocumentomaterial) {
		this.nombreTipodocumentomaterial = nombreTipodocumentomaterial;
	}

	/** full constructor */
	public Tipodocumentomaterial(String nombreTipodocumentomaterial, Set documentomaterials) {
		this.nombreTipodocumentomaterial = nombreTipodocumentomaterial;
		this.documentomaterials = documentomaterials;
	}

	// Property accessors

	public Long getPkCodigoTipodocumentomaterial() {
		return this.pkCodigoTipodocumentomaterial;
	}

	public void setPkCodigoTipodocumentomaterial(Long pkCodigoTipodocumentomaterial) {
		this.pkCodigoTipodocumentomaterial = pkCodigoTipodocumentomaterial;
	}

	public String getNombreTipodocumentomaterial() {
		return this.nombreTipodocumentomaterial;
	}

	public void setNombreTipodocumentomaterial(String nombreTipodocumentomaterial) {
		this.nombreTipodocumentomaterial = nombreTipodocumentomaterial;
	}

	public Set getDocumentomaterials() {
		return this.documentomaterials;
	}

	public void setDocumentomaterials(Set documentomaterials) {
		this.documentomaterials = documentomaterials;
	}

}