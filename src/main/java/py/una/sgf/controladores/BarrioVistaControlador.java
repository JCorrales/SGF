package py.una.sgf.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
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
}
