package py.una.sgf.bc.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.ViajePedidoBC;
import py.una.sgf.dao.ViajePedidoDao;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.domain.ViajePedido;
import py.una.sgf.registros.PedidoReg2;

@Component
@Scope("request")
public class ViajePedidoBCImpl extends BusinessControllerImpl<ViajePedido> implements ViajePedidoBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ViajePedidoDao viajePedidoDao;

	@Override
	public ViajePedidoDao getDAOInstance() {

		return viajePedidoDao;
	}

	@Override
	public void agregar(Viaje viaje, Pedido pedido) {

		getDAOInstance().agregar(viaje, pedido);

	}

	@Override
	public void quitar(Viaje viaje, Pedido pedido) {

		getDAOInstance().quitar(viaje, pedido);

	}

	public List<PedidoReg2> getPedidoList(Long viajeId) {

		return getDAOInstance().getPedidoList(viajeId);
	}

}
