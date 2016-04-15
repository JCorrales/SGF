package py.una.sgf.controladores.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.sgf.bc.InsumoBC;
import py.una.sgf.controladores.tablas.InsumoTablaControlador;
import py.una.sgf.domain.Insumo;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/insumo")
public class InsumoFormControlador extends FormControladorAncestro<Insumo> {

	@Autowired
	private InsumoBC insumoBC;
	@Autowired
	private InsumoTablaControlador insumoTablaControlador;

	@Override
	protected Insumo getNuevaInstanciaBean() {

		return new Insumo();
	}

	@Override
	public BusinessController<Insumo> getBusinessController() {

		return insumoBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/insumo/index";
	}

	@Override
	protected InsumoTablaControlador getTablaControlador() {

		return insumoTablaControlador;
	}

}
