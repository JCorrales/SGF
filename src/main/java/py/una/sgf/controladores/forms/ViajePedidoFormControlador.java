package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.ViajePedidoBC;
import py.una.sgf.domain.ViajePedido;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/viaje_pedido")
public class ViajePedidoFormControlador extends FormControladorAncestro<ViajePedido> {

	@Autowired
	private ViajePedidoBC viajePedidoBC;

	@Override
	public String getAbmUrl() {

		return "/abm/viaje_pedido/index";
	}

	@Override
	protected ViajePedido getNuevaInstanciaBean() {

		return new ViajePedido();
	}

	@Override
	public BusinessController<ViajePedido> getBusinessController() {

		return viajePedidoBC;
	}

}
