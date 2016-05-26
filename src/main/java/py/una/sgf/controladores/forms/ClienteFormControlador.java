package py.una.sgf.controladores.forms;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.cnc.htroot.main.Cons;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.controladores.tablas.ClienteTablaControlador;
import py.una.sgf.domain.Cliente;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/cliente")
public class ClienteFormControlador extends FormControladorAncestro<Cliente> {

	@Autowired
	private ClienteBC clienteBC;
	@Autowired
	private ClienteTablaControlador clienteTablaControlador;
	private boolean botonElegirHabilitado = false;
	private String urlModal = "/abm/cliente/content_modal :: content";

	@Override
	protected Cliente getNuevaInstanciaBean() {

		return new Cliente();
	}

	@Override
	public BusinessController<Cliente> getBusinessController() {

		return clienteBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/cliente/index";
	}

	@Override
	protected ClienteTablaControlador getTablaControlador() {

		return clienteTablaControlador;
	}

	@RequestMapping("modal")
	public String modal(Cliente bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		index(bean, model);
		return urlModal;
	}

	@RequestMapping(value = "modal/op", params = Cons.Form.OPERACION_PARAM)
	public String modalOp(@Valid final Cliente bean, final BindingResult result, final ModelMap model,
			@RequestParam(Cons.Form.OPERACION_PARAM) String operacionParam) {

		boolean editandoPreviamente = false;
		if (operacionParam.equals(Cons.Form.GUARDAR_PARAM)) {
			setBotonElegirHabilitado(true);// onGuardarFallido: false
			editandoPreviamente = false;
		} else if (operacionParam.equals(Cons.Form.CANCELAR_PARAM)) {
			if (editandoPreviamente) {
				setBotonElegirHabilitado(true);
				editandoPreviamente = false;
			}
		} else if (operacionParam.equals(Cons.Form.MODIFICAR_REGISTRO)) {
			editandoPreviamente = true;
		} else {
			editandoPreviamente = false;
		}
		super.procesarForm(bean, result, model, operacionParam);
		return urlModal;
	}

	@RequestMapping(value = "modal/{id}")
	public String modalEdicionRegistro(ModelMap model, @PathVariable Long id) {

		super.edicionRegistro(model, id);
		setBotonElegirHabilitado(true);
		return urlModal;
	}

	public boolean isBotonElegirHabilitado() {

		return botonElegirHabilitado;
	}

	public void setBotonElegirHabilitado(boolean botonElegirHabilitado) {

		this.botonElegirHabilitado = botonElegirHabilitado;
	}

	@Override
	protected void onGuardarFallido(Cliente bean, ModelMap model, boolean isNewRecord) {

		setBotonElegirHabilitado(false);
		super.onGuardarFallido(bean, model, isNewRecord);
	}

}
