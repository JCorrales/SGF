package py.una.sgf.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.CiudadBC;
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

}
