
package py.una.sgf.controladores;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.ControladorBase;
import py.una.cnc.htroot.domain.Model;
import py.una.cnc.htroot.main.AppObjects;
import py.una.cnc.htroot.main.ServiceResponse;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.cnc.lib.db.dataprovider.SQLToObject;
import py.una.sgf.dao.ChoferSingletonDao;
import py.una.sgf.dao.ViajePedidoSingletonDao;
import py.una.sgf.domain.Camion;
import py.una.sgf.domain.Chofer;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.ViajePedido;

@Controller
@RequestMapping(value = "/localizador")
@Scope("session")
public class LocalizadorControlador extends ControladorBase<Model> {

	@Autowired
	private ChoferSingletonDao choferDao;
	private AppLogger logger = new AppLogger(LocalizadorControlador.class);
	private Map<String, Chofer> registro = new HashMap<String, Chofer>();
	@Autowired
	private AppObjects appObjects;
	@Autowired
	private ViajePedidoSingletonDao viajePedidoSingletonDao;

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

		Chofer chofer = choferDao.findEntityByCondition("WHERE cedula = ?1", cedula);
		if (chofer.getCamion() == null) {
			Camion camion = new Camion();
			camion.setChapa("NO ASIGNADO");
			camion.setMarca("****");
			camion.setModelo("****");
			chofer.setCamion(camion);
		}
		model.addAttribute("chofer", chofer);
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
		return "abm/mapas/localizador-registro";
	}

	@RequestMapping(value = "get_position_pedidos", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Chofer> getPositionPedidos(@RequestParam(required = true) String cedula) {

		String sql = appObjects.getSqlSource().get("select_position_pedidos");
		SQLToObject<Chofer> sqlToObject = new SQLToObject<>(appObjects.getDataSourcePool());
		sqlToObject.setClassOfT(Chofer.class);
		String dsName = "java:jboss/datasources/sgfDS";

		List<Chofer> choferList;
		try {
			choferList = sqlToObject.createList(dsName, sql, cedula);
		} catch (SQLException exception) {
			throw new RuntimeException(exception.getMessage());
		}

		logger.warning("choferList size: {} cedula {}", choferList.size(), cedula);

		Map<String, Chofer> clientePedidos = new HashMap<String, Chofer>();
		for (Chofer chofer : choferList) {
			clientePedidos.put(chofer.getCedula(), registro.get(chofer.getCedula()));
		}

		return clientePedidos;
	}

	@RequestMapping(value = "get_origen_destino_pedidos", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Pedido> getOrigenDestinoPedidos(@RequestParam(required = true) Long viajeId) {

		List<ViajePedido> viajePedidosList = viajePedidoSingletonDao.findEntitiesByCondition("WHERE viaje_id = ?",
				viajeId);
		List<Pedido> pedidosList = new ArrayList<>();
		for (int i = 0; i < viajePedidosList.size(); i++) {
			pedidosList.add(viajePedidosList.get(i).getPedido());
		}

		return pedidosList;
	}

	@Override
	public BusinessController<Model> getBusinessController() {

		return null;
	}

}
