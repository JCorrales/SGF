package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.controladores.tablas.CamionTablaControlador;
import py.una.sgf.domain.Camion;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/camion")
public class CamionFormControlador extends FormControladorAncestro<Camion> {

	@Autowired
	private CamionBC camionBC;
	@Autowired
	private CamionTablaControlador camionTablaControlador;

	@Override
	protected Camion getNuevaInstanciaBean() {

		return new Camion();
	}

	@Override
	public BusinessController<Camion> getBusinessController() {

		return camionBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/camion/index";
	}

	@Override
	protected CamionTablaControlador getTablaControlador() {

		return camionTablaControlador;
	}

}
