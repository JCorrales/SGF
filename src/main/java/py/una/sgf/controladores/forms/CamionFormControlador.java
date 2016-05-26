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
import py.una.sgf.bc.CamionBC;
import py.una.sgf.bc.ChoferBC;
import py.una.sgf.controladores.tablas.CamionTablaControlador;
import py.una.sgf.dao.SeguroDao;
import py.una.sgf.domain.Camion;
import py.una.sgf.domain.Chofer;
import py.una.sgf.domain.Seguro;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/camion")
public class CamionFormControlador extends FormControladorAncestro<Camion> {

	@Autowired
	private CamionBC camionBC;
	@Autowired
	private CamionTablaControlador camionTablaControlador;
	@Autowired
	private SeguroDao seguroDao;
	@Autowired
	private ChoferBC choferBC;
	private boolean botonElegirHabilitado = false;
	private String urlModal = "/abm/camion/content_modal :: content";

	@Override
	protected Camion getNuevaInstanciaBean() {

		return new Camion();
	}

	@Override
	public BusinessController<Camion> getBusinessController() {

		return camionBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/camion/index";
	}

	@Override
	protected CamionTablaControlador getTablaControlador() {

		return camionTablaControlador;
	}

	@Override
	protected void addExtraAttributes(Camion bean, ModelMap modelMap) {

		if (bean.getId() != null) {
			Seguro seguro;
			Chofer chofer;
			seguro = seguroDao.findEntityByCondition(" WHERE camion_id = ? ", bean.getId());
			chofer = choferBC.getDAOInstance().findEntityByCondition("WHERE camion_id = ?", bean.getId());
			modelMap.addAttribute("seguro", seguro);
			modelMap.addAttribute("chofer", chofer);
		}

		super.addExtraAttributes(bean, modelMap);
	}

	@RequestMapping("modal")
	public String modal(Camion bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		index(bean, model);
		return urlModal;
	}

	@RequestMapping(value = "modal/op", params = Cons.Form.OPERACION_PARAM)
	public String modalOp(@Valid final Camion bean, final BindingResult result, final ModelMap model,
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
	protected void onGuardarFallido(Camion bean, ModelMap model, boolean isNewRecord) {

		setBotonElegirHabilitado(false);
		super.onGuardarFallido(bean, model, isNewRecord);
	}
}
