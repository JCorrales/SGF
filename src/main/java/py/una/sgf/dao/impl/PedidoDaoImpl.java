package py.una.sgf.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.cnc.htroot.exception.BusinessLogicException;
import py.una.sgf.bc.ViajePedidoBC;
import py.una.sgf.dao.PedidoDao;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.ViajePedido;

@Repository
@Scope("request")
public class PedidoDaoImpl extends DaoImpl<Pedido> implements PedidoDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ViajePedidoBC viajePedidoBC;

	@Override
	public void destroy(Pedido obj) {

		List<ViajePedido> viajePedidoList = viajePedidoBC.getDAOInstance()
				.findEntitiesByCondition("WHERE pedido_id = ?", obj.getId());
		if (viajePedidoList.isEmpty()) {
			super.destroy(obj);
		} else {
			throw new BusinessLogicException("No se puede borrar pedido porque esta siendo referenciado por un viaje");
		}
	}

}
