package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.cnc.htroot.domain.Usuario;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.controladores.tablas.PedidoTablaControlador;
import py.una.sgf.domain.Barrio;
import py.una.sgf.domain.Camion;
import py.una.sgf.domain.Ciudad;
import py.una.sgf.domain.Cliente;
import py.una.sgf.domain.Pedido;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/pedido")
public class PedidoFormControlador extends FormControladorAncestro<Pedido> {

	@Autowired
	private PedidoBC pedidoBC;
	@Autowired
	private PedidoTablaControlador pedidoTablaControlador;

	@Override
	protected Pedido getNuevaInstanciaBean() {

		return new Pedido();
	}

	@Override
	public BusinessController<Pedido> getBusinessController() {

		return pedidoBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/pedido/index";
	}

	@Override
	protected PedidoTablaControlador getTablaControlador() {

		return pedidoTablaControlador;
	}

	@Override
	protected void addExtraAttributes(Pedido pedido, ModelMap modelMap) {

		if (pedido.getId() == null) {
			Cliente cliente = new Cliente();
			cliente.setUsuario(new Usuario());
			Barrio barrio = new Barrio();
			barrio.setCiudad(new Ciudad());

			pedido.setCliente(cliente);
			pedido.setBarrio(barrio);
			pedido.setCamion(new Camion());
			modelMap.addAttribute("pedido", pedido);
		} else {
			if (pedido.getBarrio() == null) {
				pedido.setBarrio(new Barrio());
				modelMap.addAttribute("pedido", pedido);
			}
			System.out.println("pedido cliente " + pedido.getCliente().getCedula());
			System.out.println("pedido usuario " + pedido.getCliente().getUsuario().getNombre());
		}
		super.addExtraAttributes(pedido, modelMap);
	}

}
