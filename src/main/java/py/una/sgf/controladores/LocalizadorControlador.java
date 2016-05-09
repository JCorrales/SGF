
package py.una.sgf.controladores;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import py.una.cnc.htroot.main.ServiceResponse;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.sgf.dao.ChoferSingletonDao;
import py.una.sgf.domain.Chofer;

@Controller
@RequestMapping(value = "/localizador")
public class LocalizadorControlador {

	@Autowired
	private ChoferSingletonDao choferDao;
	private AppLogger logger = new AppLogger(LocalizadorControlador.class);
	private Map<String, Chofer> registro = new HashMap<String, Chofer>();

	@RequestMapping(value = "registrar", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ServiceResponse<String> registrar(@RequestParam(required = true) String cedula) {

		ServiceResponse<String> response = new ServiceResponse<String>();
		Chofer chofer = choferDao.findEntityByCondition("WHERE cedula = ?", cedula);
		if (chofer == null) {
			logger.error("Chofer con cedula {} no existe", cedula);
			response.setMessage("No existe chofer con cedula: " + cedula);
			response.setSuccess(false);
			return response;
		}

		if (registro.containsKey(chofer.getCedula())) {
			response.setMessage("Chofer ya registrado");
			response.setSuccess(false);
			return response;
		}

		registro.put(chofer.getCedula(), chofer);
		response.setMessage("Chofer " + chofer.getCedula() + " registrado con exito");
		response.setSuccess(true);
		return response;

	}

	@RequestMapping(value = "send_position", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ServiceResponse<String> sendPosition(@RequestParam(required = true) String cedula,
			@RequestParam(required = true) BigDecimal longitud, @RequestParam(required = true) BigDecimal latitud) {

		logger.info("recibiendo posicion para cedula: {}, latitud: {}, longitud: {} ", cedula, latitud, longitud);
		ServiceResponse<String> response = new ServiceResponse<String>();
		Chofer chofer = choferDao.findEntityByCondition("WHERE cedula = ?", cedula);
		if (chofer == null) {
			response.setSuccess(false);
			return response;
		}
		chofer.setLatitud(latitud);
		chofer.setLongitud(longitud);
		registro.put(cedula, chofer);

		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value = "get_position_list", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Chofer> getPositions() {

		return registro;
	}

	@RequestMapping(value = "get_info_window", method = RequestMethod.GET)
	public String getInfoWindow(ModelMap model, @RequestParam(required = true) String cedula) {

		model.addAttribute("chofer", choferDao.findEntityByCondition("WHERE cedula = ?1", cedula));
		return "abm/chofer/info-window :: content";
	}

	@RequestMapping(value = "borrar_registro", method = RequestMethod.POST)
	public String borrarRegistro(ModelMap model, @RequestParam(required = true) String cedula) {

		registro.remove(cedula);
		return "redirect:registro";
	}

	@RequestMapping(value = "registro", method = RequestMethod.GET)
	public String registro(ModelMap model) {

		model.addAttribute("choferList", registro.values());
		return "abm/localizador/index";
	}

}
