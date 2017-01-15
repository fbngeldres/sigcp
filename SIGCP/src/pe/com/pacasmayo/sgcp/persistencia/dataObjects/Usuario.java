package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Usuario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Usuario implements java.io.Serializable {

	// Fields

	private Long pkCodigoUsuario;
	private Estadousuario estadousuario;
	private Persona persona;
	private String loginUsuario;
	private String passwordUsuario;
	private Set ordenproduccionsForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set produccionsemanalsForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set ajusteproductosForFkCodigoUsuarioAjusta = new HashSet(0);
	private Set notificaciondiariasForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set plananualsForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set notificaciondiariasForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set documentomaterials = new HashSet(0);
	private Set registromedicionsForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set usuariogrupousuarios = new HashSet(0);
	private Set tablavalors = new HashSet(0);
	private Set ordenproduccionsForFkCodigoUsuarioRegistro = new HashSet(0);
	private Set ajusteproduccionsForFkCodigoUsuario = new HashSet(0);
	private Set ajusteproduccionsForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set registromedicionsForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set cubicacionproductosForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set produccionsemanalsForFkCodigoUsuarioRegistro = new HashSet(0);
	private Set partediariosForFkCodigoUsuarioCierra = new HashSet(0);
	private Set cubicacionproductosForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set cubicacionproductosForFkCodigoUsuario = new HashSet(0);
	private Set partediariosForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set plananualsForFkCodigoUsuarioAprueba = new HashSet(0);
	private Set ajusteproductosForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set bloqueotransaccionFKCodigoUsuario = new HashSet(0);
	private Set tabladistribuiblesForFkCodigoUsuarioRegistra = new HashSet(0);
	private Set tabladistribuiblesForFkCodigoUsuarioAprueba = new HashSet(0);

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(Estadousuario estadousuario, Persona persona, String loginUsuario, String passwordUsuario) {
		this.estadousuario = estadousuario;
		this.persona = persona;
		this.loginUsuario = loginUsuario;
		this.passwordUsuario = passwordUsuario;
	}

	/** full constructor */
	public Usuario(Estadousuario estadousuario, Persona persona, String loginUsuario, String passwordUsuario,
			Set ordenproduccionsForFkCodigoUsuarioAprueba, Set produccionsemanalsForFkCodigoUsuarioAprueba,
			Set ajusteproductosForFkCodigoUsuarioAjusta, Set notificaciondiariasForFkCodigoUsuarioRegistra,
			Set plananualsForFkCodigoUsuarioRegistra, Set notificaciondiariasForFkCodigoUsuarioAprueba, Set documentomaterials,
			Set registromedicionsForFkCodigoUsuarioRegistra, Set usuariogrupousuarios, Set tablavalors,
			Set ordenproduccionsForFkCodigoUsuarioRegistro, Set ajusteproduccionsForFkCodigoUsuario,
			Set ajusteproduccionsForFkCodigoUsuarioRegistra, Set registromedicionsForFkCodigoUsuarioAprueba,
			Set cubicacionproductosForFkCodigoUsuarioRegistra, Set produccionsemanalsForFkCodigoUsuarioRegistro,
			Set partediariosForFkCodigoUsuarioCierra, Set cubicacionproductosForFkCodigoUsuarioAprueba,
			Set cubicacionproductosForFkCodigoUsuario, Set partediariosForFkCodigoUsuarioRegistra,
			Set plananualsForFkCodigoUsuarioAprueba, Set ajusteproductosForFkCodigoUsuarioRegistra,
			Set tabladistribuiblesForFkCodigoUsuarioRegistra, Set tabladistribuiblesForFkCodigoUsuarioAprueba,Set bloqueotransaccionFKCodigoUsuario) {
		super();
		this.estadousuario = estadousuario;
		this.persona = persona;
		this.loginUsuario = loginUsuario;
		this.passwordUsuario = passwordUsuario;
		this.ordenproduccionsForFkCodigoUsuarioAprueba = ordenproduccionsForFkCodigoUsuarioAprueba;
		this.produccionsemanalsForFkCodigoUsuarioAprueba = produccionsemanalsForFkCodigoUsuarioAprueba;
		this.ajusteproductosForFkCodigoUsuarioAjusta = ajusteproductosForFkCodigoUsuarioAjusta;
		this.notificaciondiariasForFkCodigoUsuarioRegistra = notificaciondiariasForFkCodigoUsuarioRegistra;
		this.plananualsForFkCodigoUsuarioRegistra = plananualsForFkCodigoUsuarioRegistra;
		this.notificaciondiariasForFkCodigoUsuarioAprueba = notificaciondiariasForFkCodigoUsuarioAprueba;
		this.documentomaterials = documentomaterials;
		this.registromedicionsForFkCodigoUsuarioRegistra = registromedicionsForFkCodigoUsuarioRegistra;
		this.usuariogrupousuarios = usuariogrupousuarios;
		this.tablavalors = tablavalors;
		this.ordenproduccionsForFkCodigoUsuarioRegistro = ordenproduccionsForFkCodigoUsuarioRegistro;
		this.ajusteproduccionsForFkCodigoUsuario = ajusteproduccionsForFkCodigoUsuario;
		this.ajusteproduccionsForFkCodigoUsuarioRegistra = ajusteproduccionsForFkCodigoUsuarioRegistra;
		this.registromedicionsForFkCodigoUsuarioAprueba = registromedicionsForFkCodigoUsuarioAprueba;
		this.cubicacionproductosForFkCodigoUsuarioRegistra = cubicacionproductosForFkCodigoUsuarioRegistra;
		this.produccionsemanalsForFkCodigoUsuarioRegistro = produccionsemanalsForFkCodigoUsuarioRegistro;
		this.partediariosForFkCodigoUsuarioCierra = partediariosForFkCodigoUsuarioCierra;
		this.cubicacionproductosForFkCodigoUsuarioAprueba = cubicacionproductosForFkCodigoUsuarioAprueba;
		this.cubicacionproductosForFkCodigoUsuario = cubicacionproductosForFkCodigoUsuario;
		this.partediariosForFkCodigoUsuarioRegistra = partediariosForFkCodigoUsuarioRegistra;
		this.plananualsForFkCodigoUsuarioAprueba = plananualsForFkCodigoUsuarioAprueba;
		this.ajusteproductosForFkCodigoUsuarioRegistra = ajusteproductosForFkCodigoUsuarioRegistra;
		this.tabladistribuiblesForFkCodigoUsuarioRegistra = tabladistribuiblesForFkCodigoUsuarioRegistra;
		this.tabladistribuiblesForFkCodigoUsuarioAprueba = tabladistribuiblesForFkCodigoUsuarioAprueba;
                this.bloqueotransaccionFKCodigoUsuario = bloqueotransaccionFKCodigoUsuario;
	}

	// Property accessors

	public Long getPkCodigoUsuario() {
		return this.pkCodigoUsuario;
	}

	public void setPkCodigoUsuario(Long pkCodigoUsuario) {
		this.pkCodigoUsuario = pkCodigoUsuario;
	}

	public Estadousuario getEstadousuario() {
		return this.estadousuario;
	}

	public void setEstadousuario(Estadousuario estadousuario) {
		this.estadousuario = estadousuario;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getPasswordUsuario() {
		return this.passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public Set getOrdenproduccionsForFkCodigoUsuarioAprueba() {
		return this.ordenproduccionsForFkCodigoUsuarioAprueba;
	}

	public void setOrdenproduccionsForFkCodigoUsuarioAprueba(Set ordenproduccionsForFkCodigoUsuarioAprueba) {
		this.ordenproduccionsForFkCodigoUsuarioAprueba = ordenproduccionsForFkCodigoUsuarioAprueba;
	}

	public Set getProduccionsemanalsForFkCodigoUsuarioAprueba() {
		return this.produccionsemanalsForFkCodigoUsuarioAprueba;
	}

	public void setProduccionsemanalsForFkCodigoUsuarioAprueba(Set produccionsemanalsForFkCodigoUsuarioAprueba) {
		this.produccionsemanalsForFkCodigoUsuarioAprueba = produccionsemanalsForFkCodigoUsuarioAprueba;
	}

	public Set getAjusteproductosForFkCodigoUsuarioAjusta() {
		return this.ajusteproductosForFkCodigoUsuarioAjusta;
	}

	public void setAjusteproductosForFkCodigoUsuarioAjusta(Set ajusteproductosForFkCodigoUsuarioAjusta) {
		this.ajusteproductosForFkCodigoUsuarioAjusta = ajusteproductosForFkCodigoUsuarioAjusta;
	}

	public Set getNotificaciondiariasForFkCodigoUsuarioRegistra() {
		return this.notificaciondiariasForFkCodigoUsuarioRegistra;
	}

	public void setNotificaciondiariasForFkCodigoUsuarioRegistra(Set notificaciondiariasForFkCodigoUsuarioRegistra) {
		this.notificaciondiariasForFkCodigoUsuarioRegistra = notificaciondiariasForFkCodigoUsuarioRegistra;
	}

	public Set getPlananualsForFkCodigoUsuarioRegistra() {
		return this.plananualsForFkCodigoUsuarioRegistra;
	}

	public void setPlananualsForFkCodigoUsuarioRegistra(Set plananualsForFkCodigoUsuarioRegistra) {
		this.plananualsForFkCodigoUsuarioRegistra = plananualsForFkCodigoUsuarioRegistra;
	}

	public Set getNotificaciondiariasForFkCodigoUsuarioAprueba() {
		return this.notificaciondiariasForFkCodigoUsuarioAprueba;
	}

	public void setNotificaciondiariasForFkCodigoUsuarioAprueba(Set notificaciondiariasForFkCodigoUsuarioAprueba) {
		this.notificaciondiariasForFkCodigoUsuarioAprueba = notificaciondiariasForFkCodigoUsuarioAprueba;
	}

	public Set getDocumentomaterials() {
		return this.documentomaterials;
	}

	public void setDocumentomaterials(Set documentomaterials) {
		this.documentomaterials = documentomaterials;
	}

	public Set getRegistromedicionsForFkCodigoUsuarioRegistra() {
		return this.registromedicionsForFkCodigoUsuarioRegistra;
	}

	public void setRegistromedicionsForFkCodigoUsuarioRegistra(Set registromedicionsForFkCodigoUsuarioRegistra) {
		this.registromedicionsForFkCodigoUsuarioRegistra = registromedicionsForFkCodigoUsuarioRegistra;
	}

	public Set getUsuariogrupousuarios() {
		return this.usuariogrupousuarios;
	}

	public void setUsuariogrupousuarios(Set usuariogrupousuarios) {
		this.usuariogrupousuarios = usuariogrupousuarios;
	}

	public Set getTablavalors() {
		return this.tablavalors;
	}

	public void setTablavalors(Set tablavalors) {
		this.tablavalors = tablavalors;
	}

	public Set getOrdenproduccionsForFkCodigoUsuarioRegistro() {
		return this.ordenproduccionsForFkCodigoUsuarioRegistro;
	}

	public void setOrdenproduccionsForFkCodigoUsuarioRegistro(Set ordenproduccionsForFkCodigoUsuarioRegistro) {
		this.ordenproduccionsForFkCodigoUsuarioRegistro = ordenproduccionsForFkCodigoUsuarioRegistro;
	}

	public Set getAjusteproduccionsForFkCodigoUsuario() {
		return this.ajusteproduccionsForFkCodigoUsuario;
	}

	public void setAjusteproduccionsForFkCodigoUsuario(Set ajusteproduccionsForFkCodigoUsuario) {
		this.ajusteproduccionsForFkCodigoUsuario = ajusteproduccionsForFkCodigoUsuario;
	}

	public Set getAjusteproduccionsForFkCodigoUsuarioRegistra() {
		return this.ajusteproduccionsForFkCodigoUsuarioRegistra;
	}

	public void setAjusteproduccionsForFkCodigoUsuarioRegistra(Set ajusteproduccionsForFkCodigoUsuarioRegistra) {
		this.ajusteproduccionsForFkCodigoUsuarioRegistra = ajusteproduccionsForFkCodigoUsuarioRegistra;
	}

	public Set getRegistromedicionsForFkCodigoUsuarioAprueba() {
		return this.registromedicionsForFkCodigoUsuarioAprueba;
	}

	public void setRegistromedicionsForFkCodigoUsuarioAprueba(Set registromedicionsForFkCodigoUsuarioAprueba) {
		this.registromedicionsForFkCodigoUsuarioAprueba = registromedicionsForFkCodigoUsuarioAprueba;
	}

	public Set getCubicacionproductosForFkCodigoUsuarioRegistra() {
		return this.cubicacionproductosForFkCodigoUsuarioRegistra;
	}

	public void setCubicacionproductosForFkCodigoUsuarioRegistra(Set cubicacionproductosForFkCodigoUsuarioRegistra) {
		this.cubicacionproductosForFkCodigoUsuarioRegistra = cubicacionproductosForFkCodigoUsuarioRegistra;
	}

	public Set getProduccionsemanalsForFkCodigoUsuarioRegistro() {
		return this.produccionsemanalsForFkCodigoUsuarioRegistro;
	}

	public void setProduccionsemanalsForFkCodigoUsuarioRegistro(Set produccionsemanalsForFkCodigoUsuarioRegistro) {
		this.produccionsemanalsForFkCodigoUsuarioRegistro = produccionsemanalsForFkCodigoUsuarioRegistro;
	}

	public Set getPartediariosForFkCodigoUsuarioCierra() {
		return this.partediariosForFkCodigoUsuarioCierra;
	}

	public void setPartediariosForFkCodigoUsuarioCierra(Set partediariosForFkCodigoUsuarioCierra) {
		this.partediariosForFkCodigoUsuarioCierra = partediariosForFkCodigoUsuarioCierra;
	}

	public Set getCubicacionproductosForFkCodigoUsuarioAprueba() {
		return this.cubicacionproductosForFkCodigoUsuarioAprueba;
	}

	public void setCubicacionproductosForFkCodigoUsuarioAprueba(Set cubicacionproductosForFkCodigoUsuarioAprueba) {
		this.cubicacionproductosForFkCodigoUsuarioAprueba = cubicacionproductosForFkCodigoUsuarioAprueba;
	}

	public Set getCubicacionproductosForFkCodigoUsuario() {
		return this.cubicacionproductosForFkCodigoUsuario;
	}

	public void setCubicacionproductosForFkCodigoUsuario(Set cubicacionproductosForFkCodigoUsuario) {
		this.cubicacionproductosForFkCodigoUsuario = cubicacionproductosForFkCodigoUsuario;
	}

	public Set getPartediariosForFkCodigoUsuarioRegistra() {
		return this.partediariosForFkCodigoUsuarioRegistra;
	}

	public void setPartediariosForFkCodigoUsuarioRegistra(Set partediariosForFkCodigoUsuarioRegistra) {
		this.partediariosForFkCodigoUsuarioRegistra = partediariosForFkCodigoUsuarioRegistra;
	}

	public Set getPlananualsForFkCodigoUsuarioAprueba() {
		return this.plananualsForFkCodigoUsuarioAprueba;
	}

	public void setPlananualsForFkCodigoUsuarioAprueba(Set plananualsForFkCodigoUsuarioAprueba) {
		this.plananualsForFkCodigoUsuarioAprueba = plananualsForFkCodigoUsuarioAprueba;
	}

	public Set getAjusteproductosForFkCodigoUsuarioRegistra() {
		return this.ajusteproductosForFkCodigoUsuarioRegistra;
	}

	public void setAjusteproductosForFkCodigoUsuarioRegistra(Set ajusteproductosForFkCodigoUsuarioRegistra) {
		this.ajusteproductosForFkCodigoUsuarioRegistra = ajusteproductosForFkCodigoUsuarioRegistra;
	}

	public Set getBloqueotransaccionFKCodigoUsuario() {
		return bloqueotransaccionFKCodigoUsuario;
	}

	public void setBloqueotransaccionFKCodigoUsuario(Set bloqueotransaccionFKCodigoUsuario) {
		this.bloqueotransaccionFKCodigoUsuario = bloqueotransaccionFKCodigoUsuario;
	}

	public Set getTabladistribuiblesForFkCodigoUsuarioRegistra() {
		return tabladistribuiblesForFkCodigoUsuarioRegistra;
	}

	public void setTabladistribuiblesForFkCodigoUsuarioRegistra(Set tabladistribuiblesForFkCodigoUsuarioRegistra) {
		this.tabladistribuiblesForFkCodigoUsuarioRegistra = tabladistribuiblesForFkCodigoUsuarioRegistra;
	}

	public Set getTabladistribuiblesForFkCodigoUsuarioAprueba() {
		return tabladistribuiblesForFkCodigoUsuarioAprueba;
	}

	public void setTabladistribuiblesForFkCodigoUsuarioAprueba(Set tabladistribuiblesForFkCodigoUsuarioAprueba) {
		this.tabladistribuiblesForFkCodigoUsuarioAprueba = tabladistribuiblesForFkCodigoUsuarioAprueba;
	}

}