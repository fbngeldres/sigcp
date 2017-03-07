package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.form.fields.DateItem;

public class DateItemv2 extends DateItem{
	
	 static JavaScriptObject startDateJS = JSOHelper.toDateJS((new Date()));
     static JavaScriptObject endDateJS = JSOHelper.toDateJS((new Date()));
     
     static {
    	 setDefaultDateRange(startDateJS, endDateJS);
 	}
     
     
	private native static void setDefaultDateRange (JavaScriptObject startDateJS, JavaScriptObject endDateJS) /*-{
    $wnd.isc.DateItem.addProperties({
        startDate:startDateJS,
        endDate:endDateJS
    });
}-*/;

}
