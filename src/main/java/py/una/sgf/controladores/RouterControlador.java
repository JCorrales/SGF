
package py.una.sgf.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/router")
public class RouterControlador {

	@RequestMapping(value = "index")
	public String index() {

		return "abm/mapas/router-index";
	}

	@RequestMapping(value = "modal")
	public String modal() {

		return "abm/mapas/modal :: content";
	}

}
