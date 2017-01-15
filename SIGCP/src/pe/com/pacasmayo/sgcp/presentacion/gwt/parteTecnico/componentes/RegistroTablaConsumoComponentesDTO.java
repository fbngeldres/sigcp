package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

public class RegistroTablaConsumoComponentesDTO extends OrdenMolCementoDto implements java.io.Serializable {

	private static final long serialVersionUID = 9002416390037392759L;
	private Long codigoComponente;
	private Long codigoProductoComponente;
	private String nombreComponente;
	private String grupoComponente;
	private Double saldoInicial;
	private Double ingreso;
	private Double ajusteProducto;
	private Double fisico;
	private Double consumo;
	private Double consumoAjuste;
	private Double ajusteLogistico;

	public RegistroTablaConsumoComponentesDTO(String nombreComponenteOrden) {
		super(nombreComponenteOrden);
	}

	public RegistroTablaConsumoComponentesDTO() {
		super(null);
	}

	public Long getCodigoComponente() {
		return codigoComponente;
	}

	public void setCodigoComponente(Long codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getIngreso() {
		return ingreso;
	}

	public void setIngreso(Double ingreso) {
		this.ingreso = ingreso;
	}

	public Double getFisico() {
		return fisico;
	}

	public void setFisico(Double fisico) {
		this.fisico = fisico;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	/**
	 * @return the consumoAjuste
	 */
	public Double getConsumoAjuste() {
		return consumoAjuste;
	}

	/**
	 * @param consumoAjuste the consumoAjuste to set
	 */
	public void setConsumoAjuste(Double consumoAjuste) {
		this.consumoAjuste = consumoAjuste;
	}

	public String getGrupoComponente() {
		return grupoComponente;
	}

	public void setGrupoComponente(String grupoComponente) {
		this.grupoComponente = grupoComponente;
	}

	public void setCodigoProductoComponente(Long codigoProductoComponente) {
		this.codigoProductoComponente = codigoProductoComponente;
	}

	public Long getCodigoProductoComponente() {
		return codigoProductoComponente;
	}

	public void setAjusteProducto(Double ajusteProducto) {
		this.ajusteProducto = ajusteProducto;
	}

	public Double getAjusteProducto() {
		return ajusteProducto;
	}

	public Double getLibros() {
		return saldoInicial + ingreso - consumo;
	}

	public Double getFL() {
		return fisico - getLibros();
	}

	public Double getPorcDesviacion() {
		if (fisico == 0) {
			// HACK: matematicamente incorrecto, pero se quiere mostrar un cero
			// en este caso
			return 0d;
		}
		return getFL() * 100 / fisico;
	}

	public Double getAjusteLogistico() {
		return ajusteLogistico;
	}

	public void setAjusteLogistico(Double ajusteLogistico) {
		this.ajusteLogistico = ajusteLogistico;
	}
}