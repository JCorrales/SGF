
package py.una.sgf.controladores;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.ControladorBase;
import py.una.cnc.htroot.domain.Model;
import py.una.sgf.bc.SgfConfigBC;

@Controller
@RequestMapping(value = "/router")
@Scope("session")
public class RouterControlador extends ControladorBase<Model> {

	@Autowired
	private SgfConfigBC sgfConfigBC;

	@RequestMapping(value = "index")
	public String index() {

		return "abm/mapas/router-index";
	}

	@RequestMapping(value = "modal")
	public String modal() {

		return "abm/mapas/modal :: content";
	}

	@RequestMapping(value = "viaje_modal")
	public String viajeModal() {

		return "abm/mapas/ruta_viaje :: content";
	}

	@ModelAttribute("latitud")
	public BigDecimal getLatitud() {

		return new BigDecimal(sgfConfigBC.getConfig().getLatitudBase());
	}

	@ModelAttribute("longitud")
	public BigDecimal getLongitud() {

		return new BigDecimal(sgfConfigBC.getConfig().getLongitudBase());
	}

	@Override
	public BusinessController<Model> getBusinessController() {

		return null;
	}

}
