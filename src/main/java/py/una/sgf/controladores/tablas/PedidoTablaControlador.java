package py.una.sgf.controladores.tablas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.cnc.htroot.controllers.TablaControladorAncestro;
import py.una.cnc.htroot.dataprovider.DataTableSqlDs;
import py.una.cnc.lib.db.dataprovider.DataTableModel;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.domain.Pedido;
import py.una.sgf.registros.PedidoReg;

@Controller
@Scope("session")
@RequestMapping(value = "/abm/pedido")
public class PedidoTablaControlador extends TablaControladorAncestro<Pedido> {

	@Autowired
	PedidoBC pedidoBC;

	@Autowired
	DataTableSqlDs dataTableSqlDs;

	@Override
	public String[] getTableColumns() {

		return new String[] { "id", "cliente", "ciudadDestino", "fechapedido", "costo" };
	}

	@Override
	public String[] getReportColumns() {

		return new String[] { "cliente", "ciudadDestino", "fechapedido", "costo", "precio" };
	}

	@Override
	public BusinessController<Pedido> getBusinessController() {

		return pedidoBC;
	}

	@Override
	protected DataTableModel<?> getDataTable(Integer iDisplayStart, Integer iDisplayEnd, String orderBy,
			String sSearch) {

		try {
			return dataTableSqlDs.getDataTable(iDisplayStart, iDisplayEnd, orderBy, sSearch, "select_pedidos",
					"filter_pedidos", DataTableSqlDs.WITHOUT_WHERE, PedidoReg.class);
		} catch (Exception e) {
			logger.error("Error: {}", e.getMessage());
		}
		return null;
	}

}
