package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

public class PuestoYComponente {
	private String puesto;
	private String componente;

	public PuestoYComponente(String puesto, String componente) {
		super();
		this.puesto = puesto;
		this.componente = componente;
	}

	public String getPuesto() {
		return puesto;
	}

	public String getComponente() {
		return componente;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PuestoYComponente)) {
			return false;
		}

		PuestoYComponente otroObj = (PuestoYComponente) obj;
		return otroObj.getPuesto().equals(puesto) && otroObj.getComponente().equals(componente);
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		return this.puesto + "|" + this.componente;
	}

}
