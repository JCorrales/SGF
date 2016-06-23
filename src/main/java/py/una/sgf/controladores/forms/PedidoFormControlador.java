package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.BarrioBC;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.bc.CiudadBC;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.controladores.tablas.PedidoTablaControlador;
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
	@Autowired
	private SgfConfigBC sgfConfigBC;
	@Autowired
	private BarrioBC barrioBC;
	@Autowired
	private CiudadBC ciudadBC;
	@Autowired
	private CamionBC camionBC;
	@Autowired
	private ClienteBC clienteBC;

	@Override
	protected Pedido getNuevaInstanciaBean() {

		Pedido pedido = new Pedido();
		pedido.setIva(sgfConfigBC.getConfig().getIva());
		return pedido;
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

		// algunas veces solo pedido solo tiene el id de la clase anidada
		// pero yo necesito mas informacion
		if (pedido.getCliente() != null) {
			pedido.setCliente(clienteBC.find(pedido.getCliente().getId()));;
		}
		if (pedido.getBarrioOrigen() != null) {
			pedido.setBarrioOrigen(barrioBC.find(pedido.getBarrioOrigen().getId()));
		}
		if (pedido.getCiudadOrigen() != null) {
			pedido.setCiudadOrigen(ciudadBC.find(pedido.getCiudadOrigen().getId()));
		}
		if (pedido.getBarrioDestino() != null) {
			pedido.setBarrioDestino(barrioBC.find(pedido.getBarrioDestino().getId()));
		}
		if (pedido.getCiudadDestino() != null) {
			pedido.setCiudadDestino(ciudadBC.find(pedido.getCiudadDestino().getId()));
		}
		if (pedido.getCamion() != null) {
			pedido.setCamion(camionBC.find(pedido.getCamion().getId()));
		}
		modelMap.addAttribute(getNombreObjeto(), pedido);
		super.addExtraAttributes(pedido, modelMap);
	}

}
