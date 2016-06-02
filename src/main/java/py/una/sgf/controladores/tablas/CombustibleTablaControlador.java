package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.CombustibleBC;
import py.una.sgf.domain.Combustible;
import py.una.sgf.registros.CombustibleReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/combustible")
public class CombustibleTablaControlador extends TablaControladorAncestro<Combustible> {

	@Autowired
	CombustibleBC combustibleBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "codigo", "nombre", "precio" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "codigo", "nombre", "precio" };
	}

	@Override
	public BusinessController<Combustible> getBusinessController() {

		return combustibleBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_combustibles",
				"filter_combustibles", DataTableSqlDs.WITHOUT_WHERE, CombustibleReg.class);
	}

}
