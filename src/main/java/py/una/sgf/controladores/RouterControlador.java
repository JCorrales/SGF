
package py.una.sgf.controladores;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.sgf.bc.SgfConfigBC;

@Controller
@RequestMapping(value = "/router")
public class RouterControlador {

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

	@ModelAttribute("latitud")
	public BigDecimal getLatitud() {

		return new BigDecimal(sgfConfigBC.getConfig().getLatitudBase());
	}

	@ModelAttribute("longitud")
	public BigDecimal getLongitud() {

		return new BigDecimal(sgfConfigBC.getConfig().getLongitudBase());
	}

}
