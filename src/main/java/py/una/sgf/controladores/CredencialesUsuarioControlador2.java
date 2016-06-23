package py.una.sgf.controladores;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import py.una.cnc.htroot.dao.ResetPassDao;
import py.una.cnc.htroot.dao.UsuarioDao;
import py.una.cnc.htroot.domain.ResetPass;
import py.una.cnc.htroot.domain.Usuario;
import py.una.cnc.htroot.main.Message;
import py.una.cnc.htroot.main.ServiceResponse;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.util.SgfUtil;

/***
 *
 * @author Juan Corrales
 * @Since 1.0
 * @Version 1.0 10 de abr. de 2016
 */
@Controller
@Scope("request")
public class CredencialesUsuarioControlador2 {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private ResetPassDao resetPassDao;
	@Autowired
	private SgfUtil sgfUtil;
	@Autowired
	private Message msg;
	@Autowired
	private SgfConfigBC sgfConfigBC;

	protected AppLogger logger = new AppLogger(this.getClass().getName());

	@RequestMapping(value = "/resetpass2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ServiceResponse<Usuario> cambiarPass(@RequestParam String codigo, ModelMap model) {

		try {
			ServiceResponse<Usuario> response = getUsuario(codigo);
			if (response.getContent() == null) {
				return response;
			}
			ResetPass lastResetPass = resetPassDao.getUltimoReset(codigo);
			if (lastResetPass != null) {
				if ((new Date().getTime() - lastResetPass.getFecha().getTime()) / (1000 * 60) <= sgfConfigBC.getConfig()
						.getResetPassTimeWait()) {
					String respuesta = msg.get("resetpass.limite.de.tiempo");
					respuesta = String.format(respuesta, sgfConfigBC.getConfig().getResetPassTimeWait() / 60);
					return getResponse(respuesta, ServiceResponse.UNSUCCESS);
				}
			}
			sendLink(response.getContent());
			return getResponse(String.format(msg.get("resetpass.msg.exito"), response.getContent().getEmail()),
					ServiceResponse.SUCCESS);
		} catch (Exception exception) {
			logger.error("Error en resetpass: {}", exception, exception.getMessage());
			return getResponse(msg.get("resetpass.error"), ServiceResponse.UNSUCCESS);
		}
	}

	private ServiceResponse<Usuario> getUsuario(String codigo) {

		Usuario usuario = usuarioDao.findEntityByCondition("WHERE Usuario.codigo = ?1", codigo);
		if (usuario == null) {
			return getResponse(msg.get("resetpass.usuario.no.existe"), ServiceResponse.UNSUCCESS);
		}
		if (usuario.getEmail() == null) {
			return getResponse(msg.get("resetpass.usuario.sin.email"), ServiceResponse.UNSUCCESS);
		}
		ServiceResponse<Usuario> response = new ServiceResponse<>();
		response.setContent(usuario);
		return response;
	}

	private void sendLink(Usuario usuario) throws NoSuchAlgorithmException, AddressException, MessagingException {

		String token = sgfUtil.getMD5(Long.toString(System.currentTimeMillis()) + usuario.getCodigo());
		String link = sgfConfigBC.getConfig().getAppHost() + "/getnewpass?token=" + token;
		ResetPass rp = new ResetPass();
		rp.setCodigo(usuario.getCodigo());
		rp.setToken(token);
		rp.setExpirado(false);
		rp.setFecha(new Date());
		resetPassDao.create(rp);
		String host = sgfConfigBC.getConfig().getMailHost();
		String password = sgfConfigBC.getConfig().getMailPass();
		String destinatario = usuario.getEmail();
		String remitente = sgfConfigBC.getConfig().getMailRemitente();
		String asunto = msg.get("resetpass.asunto");
		String mensaje = String.format(msg.get("resetpass.respuesta_solicitud"),
				usuario.getNombre() + " " + usuario.getApellido(), link);
		sgfUtil.sendMailPass(host, destinatario, remitente, asunto, mensaje, password);
	}

	@RequestMapping(value = "/getnewpass2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String getNewPass(@RequestParam String token, ModelMap model) {

		try {
			ResetPass resetPass = resetPassDao.findEntityByCondition("WHERE ResetPass.token = ?1", token);
			if (resetPass == null) {
				return getResponse(msg.get("resetpass.invalid.link"), ServiceResponse.UNSUCCESS, model);
			} else if (resetPass.getExpirado()) {
				return getResponse(msg.get("resetpass.expired.link"), ServiceResponse.UNSUCCESS, model);
			}
			Usuario usuario = usuarioDao.findEntityByCondition("WHERE Usuario.codigo = ?1", resetPass.getCodigo());
			if (usuario == null) {
				return getResponse(msg.get("resetpass.usuario.no.existe"), ServiceResponse.UNSUCCESS, model);
			}
			model.addAttribute("token", token);
			return getResponse(null, ServiceResponse.SUCCESS, model);
		} catch (Exception exception) {
			logger.error("Error en resetpass: {}", exception, exception.getMessage());
			return getResponse(msg.get("resetpass.error"), ServiceResponse.UNSUCCESS, model);
		}
	}

	private String getResponse(String msg, boolean success, ModelMap model) {

		ServiceResponse<Usuario> response = new ServiceResponse<>();
		response.setSuccess(success);
		response.setMessage(msg);
		model.addAttribute("response", response);
		return "newpassword";
	}

	private ServiceResponse<Usuario> getResponse(String msg, boolean success) {

		ServiceResponse<Usuario> response = new ServiceResponse<>();
		response.setSuccess(success);
		response.setMessage(msg);
		return response;
	}

	@RequestMapping(value = "/confirm_pass2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ServiceResponse<Usuario> confirmarPass(@RequestParam String newPass,
			@RequestParam String confirmPass, String token, ModelMap model) {

		ResetPass reset = resetPassDao.findEntityByCondition("WHERE ResetPass.token = ?1", token);
		if (reset == null) {
			return getResponse(msg.get("resetpass.invalid.link"), ServiceResponse.UNSUCCESS);
		} else if (reset.getExpirado() == true) {
			return getResponse(msg.get("resetpass.expired.link"), ServiceResponse.UNSUCCESS);
		}
		Usuario usuario = usuarioDao.findEntityByCondition("WHERE Usuario.codigo =?1", reset.getCodigo());
		if (usuario == null) {
			return getResponse(msg.get("resetpass.usuario.no.existe"), ServiceResponse.UNSUCCESS);
		}
		if (newPass.isEmpty()) {
			return getResponse(msg.get("resetpass.invalid.pass"), ServiceResponse.UNSUCCESS);
		}
		if (newPass.compareTo(confirmPass) != 0) {
			return getResponse(msg.get("resetpass.incorrect.pass"), ServiceResponse.UNSUCCESS);
		}
		try {
			usuario.setPassword(sgfUtil.getEncodedPass(msg.get("password.encoder", "MD5"), newPass));
			usuarioDao.edit(usuario);
			reset.setExpirado(true);
			resetPassDao.edit(reset);
			return getResponse(msg.get("resetpass.success"), ServiceResponse.SUCCESS);
		} catch (Exception ex) {
			return getResponse(msg.get("resetpass.incorrect.pass"), ServiceResponse.UNSUCCESS);
		}
	}

	@RequestMapping(value = "/reset2")
	public String reset() {

		return "resetpass";
	}
}
