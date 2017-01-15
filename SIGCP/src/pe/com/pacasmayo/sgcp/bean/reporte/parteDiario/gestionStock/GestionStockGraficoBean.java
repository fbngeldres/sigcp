package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock;

public class GestionStockGraficoBean {

	// Grafico 1 Toneladas Productos
	private Integer dia;
	private String descripcion;
	private String siglas;
	private Double toneladas;
	// Titulo del Reporte Grafico
	private String titulo;

	// Titulo del Eje Y del primer reporte Grafico
	private String subTituloY;
	// Grafico Toneladas Productos
	private Integer diaTotalSF;
	// Titulo del Eje Y del segundo reporte Grafico
	private String subTituloY2;

	// Stock
	private Double saldoInicial;
	private Double produccion;
	private Double consumo;

	public GestionStockGraficoBean() {
		// TODO Auto-generated constructor stub
	}

	public GestionStockGraficoBean(Integer diaTotalSF, Double toneladas, String titulo, String descripcion, String subTituloY2,
			String subTituloY) {
		this.dia = diaTotalSF;
		this.diaTotalSF = diaTotalSF;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.toneladas = toneladas;
		this.subTituloY2 = subTituloY2;
		this.subTituloY = subTituloY;
	}

	public GestionStockGraficoBean(Integer dia, Double toneladas, String descripcion, Double saldoInicial, Double produccion,
			Double consumo, String titulo, String subTituloY) {
		this.dia = dia;
		this.toneladas = toneladas;
		this.descripcion = descripcion;
		this.saldoInicial = saldoInicial;
		this.produccion = produccion;
		this.consumo = consumo;
		this.titulo = titulo;
		this.subTituloY = subTituloY;
	}

	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * @return the toneladas
	 */
	public Double getToneladas() {
		return toneladas;
	}

	/**
	 * @param toneladas the toneladas to set
	 */
	public void setToneladas(Double toneladas) {
		this.toneladas = toneladas;
	}

	/**
	 * @return the saldoInicial
	 */
	public Double getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * @return the produccion
	 */
	public Double getProduccion() {
		return produccion;
	}

	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(Double produccion) {
		this.produccion = produccion;
	}

	/**
	 * @return the consumo
	 */
	public Double getConsumo() {
		return consumo;
	}

	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the diaTotalSF
	 */
	public Integer getDiaTotalSF() {
		return diaTotalSF;
	}

	/**
	 * @param diaTotalSF the diaTotalSF to set
	 */
	public void setDiaTotalSF(Integer diaTotalSF) {
		this.diaTotalSF = diaTotalSF;
	}

	/**
	 * @return the subTituloY
	 */
	public String getSubTituloY() {
		return subTituloY;
	}

	/**
	 * @param subTituloY the subTituloY to set
	 */
	public void setSubTituloY(String subTituloY) {
		this.subTituloY = subTituloY;
	}

	/**
	 * @return the subTituloY2
	 */
	public String getSubTituloY2() {
		return subTituloY2;
	}

	/**
	 * @param subTituloY2 the subTituloY2 to set
	 */
	public void setSubTituloY2(String subTituloY2) {
		this.subTituloY2 = subTituloY2;
	}

}
