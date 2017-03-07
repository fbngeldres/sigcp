package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.SimpleRow;
import com.smartgwt.client.data.Record;

public class SmartGWTRow extends SimpleRow {
	private Record record;

	public SmartGWTRow(Record record) {
		super();
		this.record = record;
	}

	public Record getRecord() {
		return record;
	}

}
