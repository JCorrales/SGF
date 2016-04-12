package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.ChoferBC;
import py.una.sgf.controladores.tablas.ChoferTablaControlador;
import py.una.sgf.domain.Chofer;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/chofer")
public class ChoferFormControlador extends FormControladorAncestro<Chofer> {

	@Autowired
	private ChoferBC choferBC;
	@Autowired
	private ChoferTablaControlador choferTablaControlador;

	@Override
	protected Chofer getNuevaInstanciaBean() {

		return new Chofer();
	}

	@Override
	public BusinessController<Chofer> getBusinessController() {

		return choferBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/chofer/index";
	}

	@Override
	protected ChoferTablaControlador getTablaControlador() {

		return choferTablaControlador;
	}

}
