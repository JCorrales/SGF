package py.una.sgf.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.PaisBC;
import py.una.sgf.domain.Pais;
import py.una.sgf.registros.PaisReg;

@Controller
@Scope("request")
@RequestMapping(value = "/abm/pais")
public class PaisVistaControlador extends ControladorDatosGenerales<Pais> {
	@Autowired
	private DataTableSqlDs dataTableSqlDs;
	@Autowired
	private PaisBC paisBC;

	@Override
	protected Pais getNuevaInstanciaBean() {
		return new Pais();
	}

	@Override
	public BusinessController<Pais> getBusinessController() {
		return paisBC;
	}

	@Override
	public String getAbmUrl() {
		return "abm/pais/index";
	}

	@Override
	public String[] getReportColumns() {
		return new String[] { "codigo", "nombre", "nacionalidad" };
	}

	@Override
	public String[] getTableColumns() {
		return new String[] { "id", "codigo", "nombre", "nacionalidad" };
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart,
			Integer iDisplayEnd, String orderBy, String sSearch) {
		return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy,
				sSearch, "select_paises", "filter_paises",
				DataTableSqlDs.WITHOUT_WHERE, PaisReg.class);
	}
}
