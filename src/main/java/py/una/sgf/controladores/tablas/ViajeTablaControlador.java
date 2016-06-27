package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.ViajeBC;
import py.una.sgf.domain.Viaje;
import py.una.sgf.registros.ViajeReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/viaje")
public class ViajeTablaControlador extends TablaControladorAncestro<Viaje> {

	@Autowired
	ViajeBC insumoBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "camion", "chofer", "fechaSalida", "costo" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "camion", "chofer", "fechaSalida", "costo" };
	}

	@Override
	public BusinessController<Viaje> getBusinessController() {

		return insumoBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_viajes",
				"filter_viajes", DataTableSqlDs.WITHOUT_WHERE, ViajeReg.class);
	}

}
