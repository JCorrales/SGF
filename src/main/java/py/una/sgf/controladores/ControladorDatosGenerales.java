package py.una.sgf.controladores;

import org.springframework.web.bind.annotation.ModelAttribute;

import py.una.cnc.htroot.controllers.ControladorVistaAncestro;
import py.una.cnc.htroot.domain.Model;

public abstract class ControladorDatosGenerales<M extends Model> extends
ControladorVistaAncestro<M> {

	@Override
	@ModelAttribute("modulo")
	public String getNombreModulo() {

		return "dg";
	}
}
