package py.una.sgf.notificadores;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;
import py.una.cnc.htroot.domain.Usuario;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.dao.SingletonSeguroDao;
import py.una.sgf.dao.SingletonSeguroNotificarUsuarioDao;
import py.una.sgf.domain.Seguro;
import py.una.sgf.domain.SeguroNotificarUsuario;
import py.una.sgf.util.SgfUtil;

@Component
@Scope()
public class SeguroVencimiento {

	@Autowired
	@Lazy(true)
	private SingletonSeguroDao seguroDao;
	@Autowired
	@Lazy(true)
	private SingletonSeguroNotificarUsuarioDao notificarDao;
	@Autowired
	private SgfUtil sgfUtil;
	private volatile List<Seguro> seguros;
	private AppLogger logger;
	private static final long PERIODO = 1000 * 120L;// 120 segundos
	private Long dia = 1000 * 60 * 60 * 24 * 1L;// 1 dia
	@Autowired
	@Lazy(true)
	private SgfConfigBC sgfConfigBC;

	public SeguroVencimiento() {

	}

	@PostConstruct
	public void init() {

		logger = new AppLogger(SeguroVencimiento.class);
		actualizarDatos();
	}

	public synchronized void actualizarDatos() {

		logger.info("actualizando lista");
		seguros = seguroDao.findEntities();
	}

	@Scheduled(fixedRate = PERIODO, initialDelay = 10000)
	private void verificarFecha() {

		actualizarDatos();

		try {
			for (Seguro seguro : seguros) {
				/*
				 * se intenta normalizar las fechas para poder compararlas
				 */
				Date ahora = new Date();
				Long antelacion = dia * seguro.getDiasAntelacion();
				Date notificar = new Date(seguro.getVencimiento().getTime() - antelacion);

				logger.debug("ahora: {} notificar: {}", ahora, notificar);
				if (notificar.compareTo(ahora) == -1) {
					enviarMails(seguro);
				}
			}
		} catch (Exception ex) {
			logger.error("Error en verificarFecha(): {}", ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void enviarMails(Seguro seguro) {

		seguro.setListNotificar(notificarDao.findEntitiesByCondition("WHERE seguro_id = ?", seguro.getId()));
		logger.info("Usuarios a notificar: {}", seguro.getListNotificar().size());
		for (SeguroNotificarUsuario notificar : seguro.getListNotificar()) {
			Usuario usuario = notificar.getUsuario();
			try {
				enviarMail(seguro, usuario.getEmail());
			} catch (Exception e) {
				logger.error("Error al enviar mail: {}", e.getMessage());
				e.printStackTrace();
			}
		}

	}

	private void enviarMail(Seguro seguro, String destinatario) throws AddressException, MessagingException {

		String host = sgfConfigBC.getConfig().getMailHost();
		String remitente = sgfConfigBC.getConfig().getMailRemitente();
		String password = sgfConfigBC.getConfig().getMailPass();
		String asunto = sgfConfigBC.getConfig().getSeguroAsunto();
		String mensaje = sgfConfigBC.getConfig().getSeguroMensaje();
		mensaje = mensaje.replace("#CHAPA#", seguro.getCamion().getChapa());
		mensaje = mensaje.replace("#VENCIMIENTO#", seguro.getVencimiento().toString().subSequence(0, 10));
		mensaje = mensaje.replace("#POLIZA#", seguro.getPoliza());
		getUtil().sendMailPass(host, destinatario, remitente, asunto, mensaje, password);
		logger.info("enviando mail a : {}", destinatario);
		editVencimiento(seguro);

	}

	private void editVencimiento(Seguro seguro) {

		Date actual = seguro.getVencimiento();
		DayOverflow dayOverflow = DayOverflow.LastDay;
		DateTime dateTime = DateTime.forInstant(actual.getTime(), TimeZone.getDefault());
		dateTime = dateTime.plus(0, 1, 0, 0, 0, 0, 0, dayOverflow);

		logger.debug("sumando 1 mes {}", dateTime.toString());

		Date siguiente = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
		seguro.setVencimiento(siguiente);
		seguroDao.edit(seguro);
	}

	private SgfUtil getUtil() {

		return sgfUtil;
	}

}
