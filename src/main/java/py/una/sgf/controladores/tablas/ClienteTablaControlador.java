package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.domain.Cliente;
import py.una.sgf.registros.ClienteReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/cliente")
public class ClienteTablaControlador extends TablaControladorAncestro<Cliente> {

	@Autowired
	ClienteBC clienteBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "cedula", "nombre", "apellido", "ruc" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "cedula", "nombre", "apellido", "ruc" };
	}

	@Override
	public BusinessController<Cliente> getBusinessController() {

		return clienteBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		try {
			return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_clientes",
					"filter_clientes", DataTableSqlDs.WITHOUT_WHERE, ClienteReg.class);
		} catch (Exception e) {
			logger.error("Error: {}", e.getMessage());
		}
		return null;
	}

}
