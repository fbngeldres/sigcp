package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface MenuBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return the descripcion
	 */
	public abstract String getDescripcion();

	/**
	 * @param descripcion the descripcion to set
	 */
	public abstract void setDescripcion(String descripcion);

	/**
	 * @return the ordenMenu
	 */
	public abstract Short getOrdenMenu();

	/**
	 * @param ordenMenu the ordenMenu to set
	 */
	public abstract void setOrdenMenu(Short ordenMenu);

	/**
	 * @return the privilegioBean
	 */
	public abstract PrivilegioBean getPrivilegioBean();

	/**
	 * @param privilegioBean the privilegioBean to set
	 */
	public abstract void setPrivilegioBean(PrivilegioBean privilegioBean);

	/**
	 * @return the nivelMenuBean
	 */
	public abstract NivelMenuBean getNivelMenuBean();

	/**
	 * @param nivelMenuBean the nivelMenuBean to set
	 */
	public abstract void setNivelMenuBean(NivelMenuBean nivelMenuBean);

	/**
	 * @return the menuPadre
	 */
	public abstract MenuBean getMenuPadre();

	/**
	 * @param menuPadre the menuPadre to set
	 */
	public abstract void setMenuPadre(MenuBean menuPadre);

	/**
	 * @return the menuBeanList
	 */
	public abstract List<MenuBean> getMenuBeanList();

	/**
	 * @param menuBeanList the menuBeanList to set
	 */
	public abstract void setMenuBeanList(List<MenuBean> menuBeanList);

	/**
	 * @return the accionBeanList
	 */
	public abstract List<AccionBean> getAccionBeanList();

	/**
	 * @param accionBeanList the accionBeanList to set
	 */
	public abstract void setAccionBeanList(List<AccionBean> accionBeanList);

	/**
	 * @return the estadoMenu
	 */
	public abstract Boolean getEstadoMenu();

	/**
	 * @param estadoMenu the estadoMenu to set
	 */
	public abstract void setEstadoMenu(Boolean estadoMenu);

}