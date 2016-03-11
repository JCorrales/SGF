package py.una.sgf.controladores.tablas;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.packing.slide.Contenedor;
import com.packing.slide.PackingInput;
import com.packing.slide.Paquete;
import py.una.cnc.htroot.main.SesionUsuario;
import py.una.sgf.registros.Estadisticas;
import py.una.sgf.registros.PaqueteJSON;

@Controller
@RequestMapping("/abm/cargador")
@Scope("session")
public class CargadorRenderControlador {

	@Autowired
	SesionUsuario sesionUsuario;

	@RequestMapping(value = "rest", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<PaqueteJSON> getPaquetesRest(Model model) {

		Contenedor contenedor = (Contenedor) sesionUsuario.getObject("contenedor");
		String[] colores = (String[]) sesionUsuario.getObject("colores");
		List<PaqueteJSON> em = new ArrayList<>();

		if (contenedor == null || colores == null) {
			return em;
		}

		// el primero es el contenedor

		PaqueteJSON contenedorJSON = new PaqueteJSON();
		contenedorJSON.setAlto(contenedor.getAlto());
		contenedorJSON.setAncho(contenedor.getAncho());
		contenedorJSON.setLargo(contenedor.getLargo());
		contenedorJSON.setX(contenedor.getLargo() / 2);
		contenedorJSON.setY(contenedor.getAlto() / 2);
		contenedorJSON.setZ(contenedor.getAncho() / 2);
		contenedorJSON.setColor("#00000f");
		em.add(contenedorJSON);

		for (Paquete paquete : contenedor.getEmpaquetados()) {
			PaqueteJSON empaquetado = new PaqueteJSON();
			empaquetado.setAlto(paquete.getAlto());
			empaquetado.setAncho(paquete.getAncho());
			empaquetado.setLargo(paquete.getLargo());
			empaquetado.setX(paquete.getVertice().getX());
			empaquetado.setY(paquete.getVertice().getY());
			empaquetado.setZ(paquete.getVertice().getZ());
			empaquetado.setColor(colores[paquete.getId()]);
			em.add(empaquetado);
		}
		return em;
	}

	@RequestMapping(value = "render", method = RequestMethod.GET)
	public String render(Model model) {

		Contenedor contenedor = (Contenedor) sesionUsuario.getObject("contenedor");
		String[] colores = (String[]) sesionUsuario.getObject("colores");

		if (contenedor == null || colores == null) {
			return "redirect:/abm/cargador";
		}
		return "/abm/cargador/render";
	}

	@RequestMapping(value = "estadisticas", method = RequestMethod.GET)
	public String estadisticas(Model model) {

		PackingInput packingInput = (PackingInput) sesionUsuario.getObject("packingInput");

		String[] colores = (String[]) sesionUsuario.getObject("colores");
		if (packingInput == null || colores == null) {
			return "redirect:/abm/cargador/";
		}

		Estadisticas estadisticas = (Estadisticas) sesionUsuario.getObject("estadisticas");
		model.addAttribute("estadisticas", estadisticas);
		return "/abm/cargador/estadisticas :: content";
	}
}
