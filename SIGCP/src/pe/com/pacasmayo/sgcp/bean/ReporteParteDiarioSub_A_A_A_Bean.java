package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ReporteParteDiarioSub_A_A_A_Bean.java
 * Modificado: May 9, 2011 4:14:22 PM 
 * Autor: Saul Hernandez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ReporteParteDiarioSub_A_A_A_Bean {
	private String concepto;
	private Double valor_1;
	private Double valor_2;
	private Double valor_3;
	private Double valor_4;
	private Double valor_5;
	private Double valor_6;
	private Double valor_7;
	private Double valor_8;
	private Double valor_9;
	private Double valor_10;
	private Double valor_11;
	private Double valor_12;
	private Double valor_13;
	private Double valor_14;
	private Double valor_15;
	private Double valor_16;
	private Double valor_17;
	private Double valor_18;
	private Double valor_19;
	private Double valor_20;
	private Double valor_21;
	private Double valor_22;
	private Double valor_23;
	private Double valor_24;
	private Double valor_25;
	private Double valor_26;
	private Double valor_27;
	private Double valor_28;
	private Double valor_29;
	private Double valor_30;
	private Double valor_31;
	private Double suma;
	private Double valorTMPH;

	public Double getSuma() {
		suma = 0.0;

		if (valor_1 != null) {
			suma += valor_1;
		}
		if (valor_2 != null) {
			suma += valor_2;
		}
		if (valor_3 != null) {
			suma += valor_3;
		}
		if (valor_4 != null) {
			suma += valor_4;
		}
		if (valor_5 != null) {
			suma += valor_5;
		}
		if (valor_6 != null) {
			suma += valor_6;
		}
		if (valor_7 != null) {
			suma += valor_7;
		}
		if (valor_8 != null) {
			suma += valor_8;
		}
		if (valor_9 != null) {
			suma += valor_9;
		}
		if (valor_10 != null) {
			suma += valor_10;
		}
		if (valor_11 != null) {
			suma += valor_11;
		}
		if (valor_12 != null) {
			suma += valor_12;
		}
		if (valor_13 != null) {
			suma += valor_13;
		}
		if (valor_14 != null) {
			suma += valor_14;
		}
		if (valor_15 != null) {
			suma += valor_15;
		}
		if (valor_16 != null) {
			suma += valor_16;
		}
		if (valor_17 != null) {
			suma += valor_17;
		}
		if (valor_18 != null) {
			suma += valor_18;
		}
		if (valor_19 != null) {
			suma += valor_19;
		}
		if (valor_20 != null) {
			suma += valor_20;
		}
		if (valor_21 != null) {
			suma += valor_21;
		}
		if (valor_22 != null) {
			suma += valor_22;
		}
		if (valor_23 != null) {
			suma += valor_23;
		}

		if (valor_24 != null) {
			suma += valor_24;
		}
		if (valor_25 != null) {
			suma += valor_25;
		}
		if (valor_26 != null) {
			suma += valor_26;
		}
		if (valor_27 != null) {
			suma += valor_27;
		}
		if (valor_28 != null) {
			suma += valor_28;
		}
		if (valor_29 != null) {
			suma += valor_29;
		}
		if (valor_30 != null) {
			suma += valor_30;
		}
		if (valor_31 != null) {
			suma += valor_31;
		}

		if (concepto != null && concepto.trim().equals("TMPH")) {
			/*
			 * if (cont > 0) { suma = suma / cont; }
			 */
			suma = valorTMPH;
		}

		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	/**
	 * 
	 */
	public ReporteParteDiarioSub_A_A_A_Bean() {
		super();
	}

	/**
	 * @param concepto
	 * @param valor_1
	 * @param valor_2
	 * @param valor_3
	 * @param valor_4
	 * @param valor_5
	 * @param valor_6
	 * @param valor_7
	 * @param valor_8
	 * @param valor_9
	 * @param valor_10
	 * @param valor_11
	 * @param valor_12
	 * @param valor_13
	 * @param valor_14
	 * @param valor_15
	 * @param valor_16
	 * @param valor_17
	 * @param valor_18
	 * @param valor_19
	 * @param valor_20
	 * @param valor_21
	 * @param valor_22
	 * @param valor_23
	 * @param valor_24
	 * @param valor_25
	 * @param valor_26
	 * @param valor_27
	 * @param valor_28
	 * @param valor_29
	 * @param valor_30
	 * @param valor_31
	 */
	public ReporteParteDiarioSub_A_A_A_Bean(String concepto, Double valor_1, Double valor_2, Double valor_3, Double valor_4,
			Double valor_5, Double valor_6, Double valor_7, Double valor_8, Double valor_9, Double valor_10, Double valor_11,
			Double valor_12, Double valor_13, Double valor_14, Double valor_15, Double valor_16, Double valor_17,
			Double valor_18, Double valor_19, Double valor_20, Double valor_21, Double valor_22, Double valor_23,
			Double valor_24, Double valor_25, Double valor_26, Double valor_27, Double valor_28, Double valor_29,
			Double valor_30, Double valor_31, Double suma) {
		super();
		this.concepto = concepto;
		this.valor_1 = valor_1;
		this.valor_2 = valor_2;
		this.valor_3 = valor_3;
		this.valor_4 = valor_4;
		this.valor_5 = valor_5;
		this.valor_6 = valor_6;
		this.valor_7 = valor_7;
		this.valor_8 = valor_8;
		this.valor_9 = valor_9;
		this.valor_10 = valor_10;
		this.valor_11 = valor_11;
		this.valor_12 = valor_12;
		this.valor_13 = valor_13;
		this.valor_14 = valor_14;
		this.valor_15 = valor_15;
		this.valor_16 = valor_16;
		this.valor_17 = valor_17;
		this.valor_18 = valor_18;
		this.valor_19 = valor_19;
		this.valor_20 = valor_20;
		this.valor_21 = valor_21;
		this.valor_22 = valor_22;
		this.valor_23 = valor_23;
		this.valor_24 = valor_24;
		this.valor_25 = valor_25;
		this.valor_26 = valor_26;
		this.valor_27 = valor_27;
		this.valor_28 = valor_28;
		this.valor_29 = valor_29;
		this.valor_30 = valor_30;
		this.valor_31 = valor_31;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getValor_1() {
		return valor_1;
	}

	public void setValor_1(Double valor_1) {
		this.valor_1 = valor_1;
	}

	public Double getValor_2() {
		return valor_2;
	}

	public void setValor_2(Double valor_2) {
		this.valor_2 = valor_2;
	}

	public Double getValor_3() {
		return valor_3;
	}

	public void setValor_3(Double valor_3) {
		this.valor_3 = valor_3;
	}

	public Double getValor_4() {
		return valor_4;
	}

	public void setValor_4(Double valor_4) {
		this.valor_4 = valor_4;
	}

	public Double getValor_5() {
		return valor_5;
	}

	public void setValor_5(Double valor_5) {
		this.valor_5 = valor_5;
	}

	public Double getValor_6() {
		return valor_6;
	}

	public void setValor_6(Double valor_6) {
		this.valor_6 = valor_6;
	}

	public Double getValor_7() {
		return valor_7;
	}

	public void setValor_7(Double valor_7) {
		this.valor_7 = valor_7;
	}

	public Double getValor_8() {
		return valor_8;
	}

	public void setValor_8(Double valor_8) {
		this.valor_8 = valor_8;
	}

	public Double getValor_9() {
		return valor_9;
	}

	public void setValor_9(Double valor_9) {
		this.valor_9 = valor_9;
	}

	public Double getValor_10() {
		return valor_10;
	}

	public void setValor_10(Double valor_10) {
		this.valor_10 = valor_10;
	}

	public Double getValor_11() {
		return valor_11;
	}

	public void setValor_11(Double valor_11) {
		this.valor_11 = valor_11;
	}

	public Double getValor_12() {
		return valor_12;
	}

	public void setValor_12(Double valor_12) {
		this.valor_12 = valor_12;
	}

	public Double getValor_13() {
		return valor_13;
	}

	public void setValor_13(Double valor_13) {
		this.valor_13 = valor_13;
	}

	public Double getValor_14() {
		return valor_14;
	}

	public void setValor_14(Double valor_14) {
		this.valor_14 = valor_14;
	}

	public Double getValor_15() {
		return valor_15;
	}

	public void setValor_15(Double valor_15) {
		this.valor_15 = valor_15;
	}

	public Double getValor_16() {
		return valor_16;
	}

	public void setValor_16(Double valor_16) {
		this.valor_16 = valor_16;
	}

	public Double getValor_17() {
		return valor_17;
	}

	public void setValor_17(Double valor_17) {
		this.valor_17 = valor_17;
	}

	public Double getValor_18() {
		return valor_18;
	}

	public void setValor_18(Double valor_18) {
		this.valor_18 = valor_18;
	}

	public Double getValor_19() {
		return valor_19;
	}

	public void setValor_19(Double valor_19) {
		this.valor_19 = valor_19;
	}

	public Double getValor_20() {
		return valor_20;
	}

	public void setValor_20(Double valor_20) {
		this.valor_20 = valor_20;
	}

	public Double getValor_21() {
		return valor_21;
	}

	public void setValor_21(Double valor_21) {
		this.valor_21 = valor_21;
	}

	public Double getValor_22() {
		return valor_22;
	}

	public void setValor_22(Double valor_22) {
		this.valor_22 = valor_22;
	}

	public Double getValor_23() {
		return valor_23;
	}

	public void setValor_23(Double valor_23) {
		this.valor_23 = valor_23;
	}

	public Double getValor_24() {
		return valor_24;
	}

	public void setValor_24(Double valor_24) {
		this.valor_24 = valor_24;
	}

	public Double getValor_25() {
		return valor_25;
	}

	public void setValor_25(Double valor_25) {
		this.valor_25 = valor_25;
	}

	public Double getValor_26() {
		return valor_26;
	}

	public void setValor_26(Double valor_26) {
		this.valor_26 = valor_26;
	}

	public Double getValor_27() {
		return valor_27;
	}

	public void setValor_27(Double valor_27) {
		this.valor_27 = valor_27;
	}

	public Double getValor_28() {
		return valor_28;
	}

	public void setValor_28(Double valor_28) {
		this.valor_28 = valor_28;
	}

	public Double getValor_29() {
		return valor_29;
	}

	public void setValor_29(Double valor_29) {
		this.valor_29 = valor_29;
	}

	public Double getValor_30() {
		return valor_30;
	}

	public void setValor_30(Double valor_30) {
		this.valor_30 = valor_30;
	}

	public Double getValor_31() {
		return valor_31;
	}

	public void setValor_31(Double valor_31) {
		this.valor_31 = valor_31;
	}

	public Double getValorTMPH() {
		return valorTMPH;
	}

	public void setValorTMPH(Double valorTMPH) {
		this.valorTMPH = valorTMPH;
	}

}
