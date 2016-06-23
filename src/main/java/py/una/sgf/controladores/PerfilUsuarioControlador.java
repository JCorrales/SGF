package py.una.sgf.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.ControladorBase;
import py.una.cnc.htroot.controllers.UsuarioFormControlador;
import py.una.cnc.htroot.dao.UsuarioDao;
import py.una.cnc.htroot.domain.Model;
import py.una.cnc.htroot.domain.Usuario;
import py.una.cnc.htroot.main.AppObjects;
import py.una.cnc.htroot.main.CoreConfig;
import py.una.cnc.htroot.main.Message;
import py.una.cnc.htroot.main.ServiceResponse;
import py.una.cnc.htroot.main.SesionUsuario;
import py.una.cnc.htroot.util.Util;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.cnc.lib.db.dataprovider.SQLToObject;
import py.una.sgf.registros.HistorialConexionUsuario;

@Controller
@Scope("request")
public class PerfilUsuarioControlador extends ControladorBase<Model> {

	@Autowired
	private SesionUsuario sesionUsuario;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private Util util;
	@Autowired
	private Message msg;
	@Autowired
	private UsuarioFormControlador usuarioFormControlador;
	@Autowired
	private CoreConfig coreConfig;
	@Autowired
	private AppObjects appObjects;
	private AppLogger logger = new AppLogger(this.getClass());

	@RequestMapping("/change_pass2")
	public @ResponseBody ServiceResponse<Usuario> changePassword(ModelMap model,
			@RequestParam("oldpass") String oldPass, @RequestParam("newpass") String newPass,
			@RequestParam("confirmpass") String confirmPass) {

		ServiceResponse<Usuario> response = new ServiceResponse<>();
		if (sesionUsuario.isActive()) {
			if (newPass.trim().isEmpty()) {
				response.setSuccess(false);
				response.setMessage(msg.get("cambiarpass.ingrese.contrasenha"));
				return response;
			}
			if (newPass.compareTo(confirmPass) != 0) {
				response.setSuccess(false);
				response.setMessage("cambiarpass.contrasenha.no.coinciden");
				return response;
			}
			Usuario usuario = usuarioDao.find(sesionUsuario.getUsuario().getId());
			if (util.getEncodedPass(msg.get("password.encoder"), oldPass).compareTo(usuario.getPassword()) != 0) {
				response.setSuccess(false);
				response.setMessage("cambiarpass.contrasenha.incorrecta");
				return response;
			}
			usuario.setPassword(util.getEncodedPass(msg.get("password.encoder"), newPass));
			usuarioDao.edit(usuario);
			response.setSuccess(true);
			response.setMessage("cambiarpass.contrasenha.cambiada");
			return response;
		} else {
			response.setSuccess(false);
			response.setMessage("cambiarpass.no.loggeado");
			return response;
		}
	}

	@RequestMapping("/perfil")
	public String cambiarContrasenha(ModelMap model) {

		if (sesionUsuario.isActive()) {
			model.addAttribute("sesionUsuario", sesionUsuario);
			model.addAttribute("usuarioActual", sesionUsuario.getUsuario());
			obtenerHistorialConexiones(model);
			return "perfil";
		} else {
			return "login";
		}
	}

	@SuppressWarnings("unchecked")
	private void obtenerHistorialConexiones(ModelMap model) {

		String dataSourceName = coreConfig.getDefaultDataSource();
		String sql = appObjects.getSqlSource().get("select_historial_conexiones");
		SQLToObject<?> sqlToObject = new SQLToObject<>(appObjects.getDataSourcePool());
		sqlToObject.setRecordClass(HistorialConexionUsuario.class);
		try {
			if (sesionUsuario.getUsuario() == null) {
				model.addAttribute("conexiones", new ArrayList<HistorialConexionUsuario>());
				return;
			}
			List<HistorialConexionUsuario> conexiones = (List<HistorialConexionUsuario>) sqlToObject
					.createList(dataSourceName, sql, sesionUsuario.getUsuario().getCodigo());
			model.addAttribute("conexiones", conexiones);
		} catch (SQLException e) {
			logger.info("Error en la base de datos:", e.getMessage());
			model.addAttribute("conexiones", new ArrayList<HistorialConexionUsuario>());
		}
	}

	@RequestMapping(value = "cambiar_foto", method = RequestMethod.POST)
	public String uploadFileHandler(final ModelMap model, @RequestParam MultipartFile file,
			@RequestParam(required = false) Long recordId) {

		Usuario usuario = usuarioDao.find(recordId);
		if (sesionUsuario.getUsuario().equals(usuario)) {
			usuarioFormControlador.uploadFileHandler(model, file, recordId);
			usuario = usuarioDao.find(recordId);
			sesionUsuario.setUsuario(usuario);
			model.addAttribute("sesionUsuario", sesionUsuario);
			model.addAttribute("usuarioActual", sesionUsuario.getUsuario());
			obtenerHistorialConexiones(model);
			return "perfil";
		} else {
			if (sesionUsuario.isActive()) {
				model.addAttribute("sesionUsuario", sesionUsuario);
				model.addAttribute("usuarioActual", sesionUsuario.getUsuario());
				return "perfil";
			} else {
				return "login";
			}
		}
	}

	@ModelAttribute("controlador")
	public PerfilUsuarioControlador getControlador() {

		return this;
	}

	public String[] getTableColumns() {

		return new String[] { "rownum", "fecha", "ip" };
	}

	public String getTableColumnsStr() {

		return "rownum;fecha;ip";
	}

	@Override
	public BusinessController<Model> getBusinessController() {

		return null;
	}

}
