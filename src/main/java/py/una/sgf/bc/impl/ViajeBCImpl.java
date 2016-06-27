package py.una.sgf.bc.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.bc.ViajeBC;
import py.una.sgf.bc.ViajePedidoBC;
import py.una.sgf.dao.ViajeDao;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.domain.ViajePedido;

@Component
@Scope("request")
public class ViajeBCImpl extends BusinessControllerImpl<Viaje> implements ViajeBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ViajeDao viajeDao;
	@Autowired
	private ViajePedidoBC viajePedidoBC;
	@Autowired
	private PedidoBC pedidoBC;

	@Override
	public ViajeDao getDAOInstance() {

		return viajeDao;
	}

	@Override
	public void create(Viaje obj) {

		super.create(obj);
		obj.setCosto(calCosto(obj));
	}

	@Override
	public void edit(Viaje obj) {

		obj.setCosto(calCosto(obj));
		super.edit(obj);
	}

	@Override
	public void destroy(Viaje obj) {

		List<ViajePedido> viajePedidoList = viajePedidoBC.getDAOInstance().findEntitiesByCondition("WHERE viaje_id = ?",
				obj.getId());
		for (int i = 0; i < viajePedidoList.size(); i++) {
			viajePedidoBC.destroy(viajePedidoList.get(i));
		}

		super.destroy(obj);
	}

	private Integer calCosto(Viaje obj) {

		List<ViajePedido> viajePedidoList = viajePedidoBC.getDAOInstance().findEntitiesByCondition("WHERE viaje_id = ?",
				obj.getId());
		Integer costo = new Integer(0);
		for (ViajePedido viajePedido : viajePedidoList) {
			Pedido pedido = pedidoBC.find(viajePedido.getPedido().getId());
			costo = costo + pedido.getCosto();
		}
		return costo;
	}

}
