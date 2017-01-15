package pe.com.pacasmayo.sgcp.bean;

public interface NivelMenuBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the numeroNivelMenu
	 */
	public abstract Short getNumeroNivelMenu();

	/**
	 * @param numeroNivelMenu the numeroNivelMenu to set
	 */
	public abstract void setNumeroNivelMenu(Short numeroNivelMenu);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

}