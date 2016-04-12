package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.ChoferBC;
import py.una.sgf.domain.Chofer;
import py.una.sgf.registros.ChoferReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/chofer")
public class ChoferTablaControlador extends TablaControladorAncestro<Chofer> {

	@Autowired
	ChoferBC choferBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "cedula", "nombre", "apellido", "categoria" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "cedula", "nombre", "apellido", "categoria" };
	}

	@Override
	public BusinessController<Chofer> getBusinessController() {

		return choferBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_choferes",
				"filter_choferes", DataTableSqlDs.WITHOUT_WHERE, ChoferReg.class);
	}

}
