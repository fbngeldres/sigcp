package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class RegistroTablaAjusteDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigoPuesto;
	private String nombrePuesto;
	private Long codigoComponente;
	private Long codigoProductoComponente;
	private String tipoComponente;
	private String nombreComponente;
	private double saldoInicial;
	private double ingreso;
	private double consumo;
	private double saldoFinal;
	private boolean movimientoManual;

	public String getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
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

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getIngreso() {
		return ingreso;
	}

	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public Long getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(Long codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public boolean isMovimientoManual() {
		return movimientoManual;
	}

	public void setMovimientoManual(boolean movimientoManual) {
		this.movimientoManual = movimientoManual;
	}

	public void setCodigoProductoComponente(Long codigoProductoComponente) {
		this.codigoProductoComponente = codigoProductoComponente;
	}

	public Long getCodigoProductoComponente() {
		return codigoProductoComponente;
	}

}