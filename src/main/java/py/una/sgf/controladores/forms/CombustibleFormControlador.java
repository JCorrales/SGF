package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.CombustibleBC;
import py.una.sgf.controladores.tablas.CombustibleTablaControlador;
import py.una.sgf.domain.Combustible;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/combustible")
public class CombustibleFormControlador extends FormControladorAncestro<Combustible> {

	@Autowired
	private CombustibleBC combustibleBC;
	@Autowired
	private CombustibleTablaControlador combustibleTablaControlador;

	@Override
	protected Combustible getNuevaInstanciaBean() {

		return new Combustible();
	}

	@Override
	public BusinessController<Combustible> getBusinessController() {

		return combustibleBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/combustible/index";
	}

	@Override
	protected CombustibleTablaControlador getTablaControlador() {

		return combustibleTablaControlador;
	}

}
