package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.controladores.tablas.ClienteTablaControlador;
import py.una.sgf.domain.Cliente;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/cliente")
public class ClienteFormControlador extends FormControladorAncestro<Cliente> {

	@Autowired
	private ClienteBC clienteBC;
	@Autowired
	private ClienteTablaControlador clienteTablaControlador;

	@Override
	protected Cliente getNuevaInstanciaBean() {

		return new Cliente();
	}

	@Override
	public BusinessController<Cliente> getBusinessController() {

		return clienteBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/cliente/index";
	}

	@Override
	protected ClienteTablaControlador getTablaControlador() {

		return clienteTablaControlador;
	}

}
