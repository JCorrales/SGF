package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.InsumoBC;
import py.una.sgf.domain.Insumo;
import py.una.sgf.registros.InsumoReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/insumo")
public class InsumoTablaControlador extends TablaControladorAncestro<Insumo> {

	@Autowired
	InsumoBC insumoBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "nombre", "cantidad", "costo" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "nombre", "cantidad", "costo" };
	}

	@Override
	public BusinessController<Insumo> getBusinessController() {

		return insumoBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_insumos",
				"filter_insumos", DataTableSqlDs.WITHOUT_WHERE, InsumoReg.class);
	}

}
