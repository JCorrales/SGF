package py.una.sgf.controladores.forms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.bc.UsuarioBC;
import py.una.cnc.htroot.controllers.FormControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.htroot.domain.Usuario;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.bc.SeguroNotificarUsuarioBC;
import py.una.sgf.controladores.tablas.SeguroTablaControlador;
import py.una.sgf.domain.Seguro;
import py.una.sgf.domain.SeguroNotificarUsuario;
import py.una.sgf.registros.UsuarioPickerReg;
import py.una.sgf.view.wrappers.UsuarioSeguro;

/**
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/abm/seguro")
public class SeguroFormControlador extends FormControladorAncestro<Seguro> {

	@Autowired
	private SeguroBC seguroBC;
	@Autowired
	private SeguroTablaControlador seguroTablaControlador;
	@Autowired
	private DataTableSqlDs dataTableSqlDs;
	@Autowired
	private UsuarioBC usuarioBC;
	@Autowired
	private SeguroNotificarUsuarioBC seguroNotificarUsuarioBC;

	@Override
	protected Seguro getNuevaInstanciaBean() {

		return new Seguro();
	}

	@Override
	public BusinessController<Seguro> getBusinessController() {

		return seguroBC;
	}

	@Override
	public String getAbmUrl() {

		return "abm/seguro/index";
	}

	@Override
	protected SeguroTablaControlador getTablaControlador() {

		return seguroTablaControlador;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "usuarios")
	public String getUsuarios(ModelMap modelMap, @RequestParam Long seguroId) {

		UsuarioSeguro usuarioSeguro = new UsuarioSeguro();
		usuarioSeguro.setSeguroId(seguroId);

		List<Object> params = new ArrayList<Object>();
		params.add(seguroId);

		DataTableModel<?> usuarioList = dataTableSqlDs.getDataTable(0, -1, "2", params, "", "select_usuarios_picker",
				"filter_usuarios_picker", DataTableSqlDs.WITH_WHERE, UsuarioPickerReg.class);

		usuarioSeguro.setUsuarioList((List<UsuarioPickerReg>) usuarioList.getAaData());
		modelMap.addAttribute("usuarioSeguro", usuarioSeguro);

		return "abm/seguro/modal :: content";
	}

	@RequestMapping(value = "actualizar_usuarios", method = RequestMethod.POST)
	public String actualizarUsuarios(ModelMap modelMap, UsuarioSeguro usuarioSeguro) {

		Seguro seguro = seguroBC.find(usuarioSeguro.getSeguroId());

		logger.info("Actualizando usuarios a notificar del seguro : {}", usuarioSeguro.getSeguroId());
		for (UsuarioPickerReg up : usuarioSeguro.getUsuarioList()) {
			Usuario usuario = usuarioBC.find(up.getId());
			SeguroNotificarUsuario seguroNotificarUsuario = new SeguroNotificarUsuario();
			seguroNotificarUsuario.setSeguro(seguro);
			seguroNotificarUsuario.setUsuario(usuario);

			if (up.isSelected()) {
				seguroNotificarUsuarioBC.create(seguroNotificarUsuario);
			} else {
				seguroNotificarUsuarioBC.destroy(seguroNotificarUsuario);
			}
		}
		return getUsuarios(modelMap, usuarioSeguro.getSeguroId());
	}

}
