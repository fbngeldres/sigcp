package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.Date;

public class ConsumoCarbonesDTO implements Serializable {

	private static final long serialVersionUID = 7200623299578954898L;

	private Date fecha;
	private double consumoMix1;
	private double consumoMix2;
	private Long codigoProducto;
	private Long codigoProceso;

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the consumoMix1
	 */
	public double getConsumoMix1() {
		return consumoMix1;
	}

	/**
	 * @param consumoMix1 the consumoMix1 to set
	 */
	public void setConsumoMix1(double consumoMix1) {
		this.consumoMix1 = consumoMix1;
	}

	/**
	 * @return the consumoMix2
	 */
	public double getConsumoMix2() {
		return consumoMix2;
	}

	/**
	 * @param consumoMix2 the consumoMix2 to set
	 */
	public void setConsumoMix2(double consumoMix2) {
		this.consumoMix2 = consumoMix2;
	}

	public Long getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(Long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

}
