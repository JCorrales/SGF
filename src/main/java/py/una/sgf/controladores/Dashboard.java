package py.una.sgf.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.main.SesionUsuario;

@Controller
@Scope("request")
public class Dashboard {

	@Autowired
	private SesionUsuario sesionUsuario;

	@RequestMapping("/")
	public String index() {

		return "redirect:inicio";
	}

	@RequestMapping("/inicio")
	public String inicio(ModelMap model) {

		model.addAttribute("sesionUsuario", sesionUsuario);
		model.addAttribute("usuarioActual", sesionUsuario.getUsuario());
		return "index";
	}
}
