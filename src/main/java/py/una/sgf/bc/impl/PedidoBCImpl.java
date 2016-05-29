package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.BarrioBC;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.dao.PedidoDao;
import py.una.sgf.domain.Pedido;

@Component
@Scope("request")
public class PedidoBCImpl extends BusinessControllerImpl<Pedido> implements PedidoBC {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PedidoDao pedidoDao;
	@Autowired
	private BarrioBC barrioBC;
	@Autowired
	private SeguroBC seguroBC;
	@Autowired
	private CamionBC camionBC;

	@Override
	public PedidoDao getDAOInstance() {

		return pedidoDao;
	}

	@Override
	// @Transactional
	public void create(Pedido pedido) {

		beforeSave(pedido);
		super.create(pedido);
	}

	@Override
	// @Transactional
	public void edit(Pedido pedido) {

		beforeSave(pedido);
		super.edit(pedido);
	}

	private void beforeSave(Pedido pedido) {

		checkBarrioCiudad(pedido);
		// BigDecimal costo = new BigDecimal(0);
		// BigDecimal precio = new BigDecimal(0);

		// BigDecimal distancia = pedido.getDistancia();
		Float iva = pedido.getIva();
		// BigDecimal mantenimiento =
		// pedido.getCamion().getMantenimientoAnual();
		Float depreciacion = pedido.getCamion().getDepreciacionAnual();
		// BigDecimal consumo = pedido.getCamion().getConsumoPorKm();
		// BigDecimal sueldo = pedido.getCamion().getChofer().getSueldoNeto();

	}

	private void checkBarrioCiudad(Pedido pedido) {

		if (pedido.getBarrio() != null) {
			if (!pedido.getBarrio().getCiudad().getId().equals(pedido.getCiudad().getId())) {
				pedido.setBarrio(null);
			}
		}
	}

}
