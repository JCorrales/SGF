package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.domain.Seguro;
import py.una.sgf.registros.SeguroReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/seguro")
public class SeguroTablaControlador extends TablaControladorAncestro<Seguro> {

	@Autowired
	SeguroBC seguroBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "poliza", "aseguradora", "descripcion", "vencimiento" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "poliza", "aseguradora", "descripcion", "vencimiento" };
	}

	@Override
	public BusinessController<Seguro> getBusinessController() {

		return seguroBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_seguros",
				"filter_seguros", DataTableSqlDs.WITHOUT_WHERE, SeguroReg.class);
	}

}
