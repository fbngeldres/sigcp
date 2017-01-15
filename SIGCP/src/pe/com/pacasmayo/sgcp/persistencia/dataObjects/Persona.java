package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Persona entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Persona implements java.io.Serializable {

	// Fields

	private Long pkCodigoPersona;
	private Cargo cargo;
	private String nombrePersona;
	private String apellidoPersona;
	private String iddocumentoPersona;
	private String correoPersona;
	private String telefonoPersona;
	private String extensionPersona;
	private Set usuarios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Persona() {
	}

	/** minimal constructor */
	public Persona(Cargo cargo, String nombrePersona, String apellidoPersona, String iddocumentoPersona) {
		this.cargo = cargo;
		this.nombrePersona = nombrePersona;
		this.apellidoPersona = apellidoPersona;
		this.iddocumentoPersona = iddocumentoPersona;
	}

	/** full constructor */
	public Persona(Cargo cargo, String nombrePersona, String apellidoPersona, String iddocumentoPersona, String correoPersona,
			String telefonoPersona, String extensionPersona, Set usuarios) {
		this.cargo = cargo;
		this.nombrePersona = nombrePersona;
		this.apellidoPersona = apellidoPersona;
		this.iddocumentoPersona = iddocumentoPersona;
		this.correoPersona = correoPersona;
		this.telefonoPersona = telefonoPersona;
		this.extensionPersona = extensionPersona;
		this.usuarios = usuarios;
	}

	// Property accessors

	public Long getPkCodigoPersona() {
		return this.pkCodigoPersona;
	}

	public void setPkCodigoPersona(Long pkCodigoPersona) {
		this.pkCodigoPersona = pkCodigoPersona;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getNombrePersona() {
		return this.nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getApellidoPersona() {
		return this.apellidoPersona;
	}

	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}

	public String getIddocumentoPersona() {
		return this.iddocumentoPersona;
	}

	public void setIddocumentoPersona(String iddocumentoPersona) {
		this.iddocumentoPersona = iddocumentoPersona;
	}

	public String getCorreoPersona() {
		return this.correoPersona;
	}

	public void setCorreoPersona(String correoPersona) {
		this.correoPersona = correoPersona;
	}

	public String getTelefonoPersona() {
		return this.telefonoPersona;
	}

	public void setTelefonoPersona(String telefonoPersona) {
		this.telefonoPersona = telefonoPersona;
	}

	public String getExtensionPersona() {
		return this.extensionPersona;
	}

	public void setExtensionPersona(String extensionPersona) {
		this.extensionPersona = extensionPersona;
	}

	public Set getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

}