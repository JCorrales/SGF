package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_camiones",
				"filter_camiones", DataTableSqlDs.WITHOUT_WHERE, CamionReg.class);
	}

}
