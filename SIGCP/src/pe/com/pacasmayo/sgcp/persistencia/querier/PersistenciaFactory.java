package pe.com.pacasmayo.sgcp.persistencia.querier;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class PersistenciaFactory implements ConstantesMensajeAplicacion {

	/**
	 * Location of hibernate.cfg.xml file. NOTICE: Location should be on the
	 * classpath as Hibernate uses #resourceAsStream style lookup for its
	 * configuration file. That is place the config file in a Java package - the
	 * default location is the default Java package.<br>
	 * <br>
	 * Examples: <br>
	 * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml".
	 */
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

	/** The single instance of hibernate configuration */
	private static final Configuration cfg = new Configuration();

	/** The single instance of hibernate SessionFactory */
	private static org.hibernate.SessionFactory sessionFactory;

	/** Holds a single instance of Session */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	/** Logger Instance */
	private static Logger logger = Logger.getLogger(PersistenciaFactory.class);

	/**
	 * Default constructor.
	 */
	private PersistenciaFactory() {
	}

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return Session
	 * @throws Exception
	 * @throws HibernateException
	 */
	public static Session currentSession() throws EntornoEjecucionException {
		Session session = threadLocal.get();

		try {
			if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {
					synchronized (cfg) {
						cfg.configure(CONFIG_FILE_LOCATION);
						sessionFactory = cfg.buildSessionFactory();
					}
				}

				session = sessionFactory.openSession();
				threadLocal.set(session);
			}
		} catch (HibernateException e) {
			logger.error(ERROR_COMUNICACION_FALLO, e);
			throw new EntornoEjecucionException(ERROR_COMUNICACION_FALLO, e);
		}

		return session;
	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = threadLocal.get();
		threadLocal.set(null);

		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession(Session session) throws HibernateException {

		threadLocal.set(null);

		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void rollbackTransaction(Transaction tx) throws EntornoEjecucionException {
		try {
			if (tx != null) {
				tx.rollback();
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new EntornoEjecucionException(ERROR_TRANSACCION_FALLO);
		}
	}
}
