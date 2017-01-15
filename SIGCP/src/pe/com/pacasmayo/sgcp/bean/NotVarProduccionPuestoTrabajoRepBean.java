package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

public class NotVarProduccionPuestoTrabajoRepBean {

	private String puestoTrabajo;
	private String fecha;
	private String fechaNotDiaria;
	private String producto;
	private String estado;
	private String componente1;
	private String componente2;
	private String componente3;
	private String componente4;
	private String componente5;
	private String componente6;
	private String componente7;
	private String componente8;
	private String componente9;
	private String componente10;
	private String componente11;
	private String componente12;
	private String componente13;
	private String componente14;
	private String componente15;
	private List<NotVariablesProduccionHoraRepBean> variablesProdHoras = new ArrayList<NotVariablesProduccionHoraRepBean>();

	public NotVarProduccionPuestoTrabajoRepBean() {
	}

	/**
	 * @param puestoTrabajo
	 * @param fecha
	 * @param fechaNotDiaria
	 * @param producto
	 * @param estado
	 * @param componente1
	 * @param componente2
	 * @param componente3
	 * @param componente4
	 * @param componente5
	 * @param componente6
	 * @param componente7
	 * @param componente8
	 * @param componente9
	 * @param componente10
	 * @param componente11
	 * @param componente12
	 * @param componente13
	 * @param componente14
	 * @param componente15
	 * @param variablesProdHoras
	 */
	public NotVarProduccionPuestoTrabajoRepBean(String puestoTrabajo, String fecha, String fechaNotDiaria, String producto,
			String estado, String componente1, String componente2, String componente3, String componente4, String componente5,
			String componente6, String componente7, String componente8, String componente9, String componente10,
			String componente11, String componente12, String componente13, String componente14, String componente15,
			List<NotVariablesProduccionHoraRepBean> variablesProdHoras) {
		super();
		this.puestoTrabajo = puestoTrabajo;
		this.fecha = fecha;
		this.fechaNotDiaria = fechaNotDiaria;
		this.producto = producto;
		this.estado = estado;
		this.componente1 = componente1;
		this.componente2 = componente2;
		this.componente3 = componente3;
		this.componente4 = componente4;
		this.componente5 = componente5;
		this.componente6 = componente6;
		this.componente7 = componente7;
		this.componente8 = componente8;
		this.componente9 = componente9;
		this.componente10 = componente10;
		this.componente11 = componente11;
		this.componente12 = componente12;
		this.componente13 = componente13;
		this.componente14 = componente14;
		this.componente15 = componente15;
		this.variablesProdHoras = variablesProdHoras;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaNotDiaria() {
		return fechaNotDiaria;
	}

	public void setFechaNotDiaria(String fechaNotDiaria) {
		this.fechaNotDiaria = fechaNotDiaria;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComponente1() {
		return componente1;
	}

	public void setComponente1(String componente1) {
		this.componente1 = componente1;
	}

	public String getComponente2() {
		return componente2;
	}

	public void setComponente2(String componente2) {
		this.componente2 = componente2;
	}

	public String getComponente3() {
		return componente3;
	}

	public void setComponente3(String componente3) {
		this.componente3 = componente3;
	}

	public String getComponente4() {
		return componente4;
	}

	public void setComponente4(String componente4) {
		this.componente4 = componente4;
	}

	public String getComponente5() {
		return componente5;
	}

	public void setComponente5(String componente5) {
		this.componente5 = componente5;
	}

	public String getComponente6() {
		return componente6;
	}

	public void setComponente6(String componente6) {
		this.componente6 = componente6;
	}

	public String getComponente7() {
		return componente7;
	}

	public void setComponente7(String componente7) {
		this.componente7 = componente7;
	}

	public String getComponente8() {
		return componente8;
	}

	public void setComponente8(String componente8) {
		this.componente8 = componente8;
	}

	public String getComponente9() {
		return componente9;
	}

	public void setComponente9(String componente9) {
		this.componente9 = componente9;
	}

	public String getComponente10() {
		return componente10;
	}

	public void setComponente10(String componente10) {
		this.componente10 = componente10;
	}

	public String getComponente11() {
		return componente11;
	}

	public void setComponente11(String componente11) {
		this.componente11 = componente11;
	}

	public String getComponente12() {
		return componente12;
	}

	public void setComponente12(String componente12) {
		this.componente12 = componente12;
	}

	public String getComponente13() {
		return componente13;
	}

	public void setComponente13(String componente13) {
		this.componente13 = componente13;
	}

	public String getComponente14() {
		return componente14;
	}

	public void setComponente14(String componente14) {
		this.componente14 = componente14;
	}

	public String getComponente15() {
		return componente15;
	}

	public void setComponente15(String componente15) {
		this.componente15 = componente15;
	}

	public List<NotVariablesProduccionHoraRepBean> getVariablesProdHoras() {
		return variablesProdHoras;
	}

	public void setVariablesProdHoras(List<NotVariablesProduccionHoraRepBean> variablesProdHoras) {
		this.variablesProdHoras = variablesProdHoras;
	}

}
