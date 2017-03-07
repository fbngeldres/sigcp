package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

public class CellManagerCellSaveHandler implements CellSavedHandler {
	private SmartGWTCellManager cellManager;

	public CellManagerCellSaveHandler(SmartGWTCellManager cellManager) {
		this.cellManager = cellManager;
	}

	public void onCellSaved(CellSavedEvent event) {
		try {
			Cell cell = cellManager.getCell(event.getRecord(), event.getColNum());
			if (cell == null) {
				String msg = "cell not found on onCellSaved!";
				GWT.log(msg);
				throw new IllegalArgumentException(msg);
			}
			cellManager.setCellValue(cell, event.getNewValue());
		} catch (Exception e) {
			GWT.log("Error en cellsaved", e);
			Window.alert("Error interno: " + e.getMessage());
		}
	}

}
