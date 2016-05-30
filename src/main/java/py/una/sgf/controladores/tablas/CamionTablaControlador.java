package py.una.sgf.controladores.tablas;

import java.io.IOException;
import javax.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.domain.Camion;
import py.una.sgf.registros.CamionReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/camion")
public class CamionTablaControlador extends TablaControladorAncestro<Camion> {

	@Autowired
	CamionBC camionBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "chapa", "marca", "modelo", "aseguradora" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "chapa", "marca", "modelo", "aseguradora" };
	}

	@Override
	public BusinessController<Camion> getBusinessController() {

		return camionBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		if (sSearch != null && sSearch.contains("#MODAL#")) {
			sSearch = sSearch.replace("#MODAL#", "");
			return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch,
					"select_camiones_con_chofer", "filter_camiones", DataTableSqlDs.WITHOUT_WHERE, CamionReg.class);
		}
		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_camiones",
				"filter_camiones", DataTableSqlDs.WITHOUT_WHERE, CamionReg.class);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "modal/rest", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody DataTableModel<?> getDataTableModalRest(
			@Nullable @RequestParam(value = "iDisplayStart", required = false, defaultValue = "0") Integer iDisplayStart,
			@Nullable @RequestParam(value = "iDisplayLength", required = false, defaultValue = "10") Integer iDisplayLength,
			@Nullable @RequestParam(value = "sEcho", required = false) String sEcho,
			@Nullable @RequestParam(value = "sSearch", required = false) String sSearch,
			@Nullable @RequestParam(value = "iSortCol_0", required = false, defaultValue = "0") Integer orderColumnIndex,
			@Nullable @RequestParam(value = "sSortDir_0", required = false, defaultValue = "asc") String orderDir,
			@Nullable @RequestParam(required = false) String param1,
			@Nullable @RequestParam(required = false) String param2,
			@Nullable @RequestParam(required = false) String param3, Model model) throws IOException {

		if (sSearch == null) {
			sSearch = "";
		}
		sSearch = sSearch + "#MODAL#";
		return super.getDataTableRest(iDisplayStart, iDisplayLength, sEcho, sSearch, orderColumnIndex, orderDir, param1,
				param2, param3, model);

	}

}
