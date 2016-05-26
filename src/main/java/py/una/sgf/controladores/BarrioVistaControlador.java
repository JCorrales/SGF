package py.una.sgf.controladores;

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
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.htroot.main.Cons;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.BarrioBC;
import py.una.sgf.domain.Barrio;
import py.una.sgf.registros.BarrioReg;

@Controller
@Scope("request")
@RequestMapping(value = "/abm/barrio")
public class BarrioVistaControlador extends ControladorDatosGenerales<Barrio> {

	@Autowired
	private DataTableSqlDs dataTableSqlDs;
	@Autowired
	private BarrioBC barrioBC;
	private boolean botonElegirHabilitado = false;
	private String urlModal = "/abm/barrio/content_modal :: content";

	@Override
	protected Barrio getNuevaInstanciaBean() {

		return new Barrio();
	}

	@Override
	public BusinessController<Barrio> getBusinessController() {

		return barrioBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/barrio/index";
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "codigo", "nombre", "ciudad", "latitud", "longitud" };
	}

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "codigo", "nombre", "ciudad" };
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_barrios",
				"filter_barrios", DataTableSqlDs.WITHOUT_WHERE, BarrioReg.class);
	}

	@RequestMapping("modal")
	public String modal(Barrio bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		index(bean, model);
		return urlModal;
	}

	@RequestMapping(value = "modal/op", params = Cons.Form.OPERACION_PARAM)
	public String modalOp(@Valid final Barrio bean, final BindingResult result, final ModelMap model,
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
	protected void onGuardarFallido(Barrio bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		super.onGuardarFallido(bean, model);
	}
}
