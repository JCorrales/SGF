package py.una.sgf.controladores;

import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.htroot.main.Cons;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.CiudadBC;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.domain.Ciudad;
import py.una.sgf.registros.CiudadReg;

@Controller
@Scope("request")
@RequestMapping(value = "/abm/ciudad")
public class CiudadVistaControlador extends ControladorDatosGenerales<Ciudad> {

	@Autowired
	private DataTableSqlDs dataTableSqlDs;
	@Autowired
	private CiudadBC ciudadBC;
	private boolean botonElegirHabilitado = false;
	private String urlModal = "/abm/ciudad/content_modal :: content";

	@Autowired
	private SgfConfigBC sgfConfigBC;

	@Override
	protected Ciudad getNuevaInstanciaBean() {

		return new Ciudad();
	}

	@Override
	public BusinessController<Ciudad> getBusinessController() {

		return ciudadBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/ciudad/index";
	}

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "codigo", "nombre" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "codigo", "nombre", "latitud", "longitud" };
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_ciudades",
				"filter_ciudades", DataTableSqlDs.WITHOUT_WHERE, CiudadReg.class);
	}

	@RequestMapping("modal")
	public String modal(Ciudad bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		index(bean, model);
		return urlModal;
	}

	@RequestMapping(value = "modal/op", params = Cons.Form.OPERACION_PARAM)
	public String modalOp(@Valid final Ciudad bean, final BindingResult result, final ModelMap model,
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
	protected void onGuardarFallido(Ciudad bean, ModelMap model) {

		setBotonElegirHabilitado(false);
		super.onGuardarFallido(bean, model);
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
