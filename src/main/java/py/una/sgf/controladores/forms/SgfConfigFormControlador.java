package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.domain.SgfConfig;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/sgf_config")
public class SgfConfigFormControlador extends FormControladorAncestro<SgfConfig> {

	@Autowired
	private SgfConfigBC sgfConfigBC;

	@Override
	public String getAbmUrl() {

		return "abm/sgf_config/index";
	}

	@Override
	protected SgfConfig getNuevaInstanciaBean() {

		return new SgfConfig();
	}

	@Override
	public BusinessController<SgfConfig> getBusinessController() {

		return sgfConfigBC;
	}

	@Override
	protected void addExtraAttributes(SgfConfig bean, ModelMap modelMap) {

		modelMap.addAttribute(getNombreObjeto(), sgfConfigBC.getConfig());

	}

	@Override
	public boolean isBotonBorrarHabilitado() {

		return false;
	}

	@Override
	public boolean isBotonCrearHabilitado() {

		return false;
	}

}
