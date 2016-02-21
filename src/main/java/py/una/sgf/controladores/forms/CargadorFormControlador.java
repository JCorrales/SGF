package py.una.sgf.controladores.forms;

import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.packing.GA.Resultado;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.cnc.htroot.main.SesionUsuario;
import py.una.cnc.lib.core.util.AppLogger;
import py.una.sgf.bc.CargadorBC;
import py.una.sgf.domain.Contenedor;
import py.una.sgf.domain.PaqueteEntrada;
import py.una.sgf.view.wrappers.Cargador;

@Controller
@Scope("session")
@RequestMapping("/abm/cargador")
public class CargadorFormControlador extends FormControladorAncestro<Cargador> {

	@Autowired
	CargadorBC cargadorBC;

	@Autowired
	SesionUsuario sesionUsuario;

	AppLogger logger = new AppLogger(CargadorFormControlador.class);

	@Override
	public BusinessController<Cargador> getBusinessController() {

		return cargadorBC;
	}

	@Override
	public String getAbmUrl() {

		return "/abm/cargador/index";
	}

	@Override
	protected Cargador getNuevaInstanciaBean() {

		Cargador cargador = new Cargador();
		cargador.setContenedor(new Contenedor());
		ArrayList<PaqueteEntrada> paquetes = new ArrayList<PaqueteEntrada>();
		paquetes.add(new PaqueteEntrada());
		cargador.setPaquetesEntrada(paquetes);
		return cargador;
	}

	@Override
	@RequestMapping(value = "op", params = OPERACION_PARAM)
	public String procesarForm(@Valid final Cargador bean, final BindingResult result, final ModelMap model,
			@RequestParam(OPERACION_PARAM) String operacionParam) {

		if (operacionParam.equals("calcular")) {
			int largo = bean.getContenedor().getLargo();
			int ancho = bean.getContenedor().getAncho();
			int alto = bean.getContenedor().getAlto();
			/*
			 * List<PaqueteEntrada> paquetesArray = new
			 * ArrayList<PaqueteEntrada>(); for (int i = 0; i <
			 * bean.getPaquetesEntrada().length; i++) {
			 * paquetesArray.add(bean.getPaquetesEntrada()[i]); }
			 */
			com.packing.slide.Contenedor contenedor2 = new com.packing.slide.Contenedor(ancho, largo, alto);
			Resultado resultado = cargadorBC.cargar(contenedor2, bean.getPaquetesEntrada());

			sesionUsuario.addObject("contenedor", contenedor2);
			sesionUsuario.addObject("resultado", resultado);
			return "abm/cargador/render";
		} else if (operacionParam.equals("agregar")) {
			bean.getPaquetesEntrada().add(new PaqueteEntrada());
			model.addAttribute(getNombreObjeto(), bean);
			addExtraAttributes(bean, model);
		}
		return getAbmUrl();
	}

	@Override
	public String getPermissionBaseName() {

		return "cargador";
	}

	@Override
	public String getNombreObjeto() {

		return "cargador";

	}

}
