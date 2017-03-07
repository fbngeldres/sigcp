package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaKardexLogic;


public class TareaLeerReporteECSLogicJob implements Job {

	private Log logger = LogFactory.getLog(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {

		LectorReporteECSLogic lectorArchivo = null;

		lectorArchivo = new LectorReporteECSLogic();

		try {
			System.out.println("EJECUTANDO DEMONIO >>" + (new Date()));
			//Lee los archivos del directorio
			lectorArchivo.leerArchivosDirectorio();
		} catch (LogicaException e) {
		e.printStackTrace();
			logger.error(e.getMensaje());
		}

		// eliminar registros duplicados obtenidos del ECS
				TablaKardexLogic kardexLogic = new TablaKardexLogic();
				try {
					kardexLogic.eliminarReportesECSDuplicados();
				} catch (LogicaException e) {

					logger.error(e);
				}

	}
}
