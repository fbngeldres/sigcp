package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.io.Serializable;

public class RendimientoTermico implements Serializable {

	// Fields

	private Long pkCodigoRendimientoTermico;
	private Puestotrabajo puestoTrabajo;
	private Producto producto;
	private Long valorKiloCalorias1;
	private Long valorKiloCalorias2;

	// Constructors
	/** default constructor */
	public RendimientoTermico() {
	}

	/** full constructor */
	public RendimientoTermico(Puestotrabajo puestotrabajo, Producto producto, Long valorKiloCalorias1, Long valorKiloCalorias2) {
		this.puestoTrabajo = puestotrabajo;
		this.producto = producto;
		this.valorKiloCalorias1 = valorKiloCalorias1;
		this.valorKiloCalorias2 = valorKiloCalorias2;
	}

	// Property accessors

	public Long getPkCodigoRendimientoTermico() {
		return pkCodigoRendimientoTermico;
	}

	public void setPkCodigoRendimientoTermico(Long pkCodigoRendimientoTermico) {
		this.pkCodigoRendimientoTermico = pkCodigoRendimientoTermico;
	}

	public Puestotrabajo getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(Puestotrabajo puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getValorKiloCalorias1() {
		return valorKiloCalorias1;
	}

	public void setValorKiloCalorias1(Long valorKiloCalorias1) {
		this.valorKiloCalorias1 = valorKiloCalorias1;
	}

	public Long getValorKiloCalorias2() {
		return valorKiloCalorias2;
	}

	public void setValorKiloCalorias2(Long valorKiloCalorias2) {
		this.valorKiloCalorias2 = valorKiloCalorias2;
	}

}
