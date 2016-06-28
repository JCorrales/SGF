package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.bc.ViajeBC;
import py.una.sgf.bc.ViajePedidoBC;
import py.una.sgf.controladores.tablas.ViajeTablaControlador;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.registros.PedidoReg2;
import py.una.sgf.view.wrappers.ViajesPedidos;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/viaje")
public class ViajeFormControlador extends FormControladorAncestro<Viaje> {

	@Autowired
	private ViajeBC viajeBC;
	@Autowired
	private PedidoBC pedidoBC;
	@Autowired
	private ViajePedidoBC viajePedidoBC;
	@Autowired
	private ViajeTablaControlador viajeTablaControlador;

	@Override
	public String getAbmUrl() {

		return "/abm/viaje/index";
	}

	@Override
	protected Viaje getNuevaInstanciaBean() {

		return new Viaje();
	}

	@Override
	public BusinessController<Viaje> getBusinessController() {

		return viajeBC;
	}

	@Override
	protected ViajeTablaControlador getTablaControlador() {

		return viajeTablaControlador;
	}

	@RequestMapping(value = "actualizar_pedidos", method = RequestMethod.POST)
	public String actualizarPedidos(ModelMap modelMap, ViajesPedidos viajesPedidos) {

		Viaje viaje = viajeBC.find(viajesPedidos.getViajeId());

		for (PedidoReg2 pedidoReg : viajesPedidos.getPedidosList()) {
			System.out.println("pedidoreg " + pedidoReg.getId());
			Pedido pedido = pedidoBC.find(pedidoReg.getId());
			if (pedidoReg.isSelected()) {
				viajePedidoBC.agregar(viaje, pedido);
			} else {

				viajePedidoBC.quitar(viaje, pedido);
			}
			logger.info("viaje pedido: {}, selected: {}", pedidoReg.getId(), pedidoReg.isSelected());
		}
		return getPedidos(modelMap, viajesPedidos.getViajeId());
	}

	@RequestMapping(value = "get_pedidos", method = RequestMethod.GET)
	public String getPedidos(ModelMap model, Long viajeId) {

		Viaje viaje = viajeBC.find(viajeId);

		if (viaje == null) {
			viaje = new Viaje();
		}

		ViajesPedidos viajesPedidos = new ViajesPedidos();
		viajesPedidos.setViajeId(viajeId);
		viajesPedidos.setPedidosList(viajePedidoBC.getPedidoList(viajeId));
		model.addAttribute("viajesPedidos", viajesPedidos);
		model.addAttribute("pedidosList", viajesPedidos.getPedidosList());
		logger.info("pedidos list: {}", viajesPedidos.getPedidosList().size());
		model.addAttribute("viajeId", viajesPedidos.getViajeId());
		model.addAttribute("pedidosActivo", true);

		return "abm/viaje/pedido_viaje";
	}

}
