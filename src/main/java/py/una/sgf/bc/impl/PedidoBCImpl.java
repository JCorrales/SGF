package py.una.sgf.bc.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.BarrioBC;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.bc.ChoferBC;
import py.una.sgf.bc.PedidoBC;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.dao.PedidoDao;
import py.una.sgf.domain.Chofer;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Seguro;

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
	@Autowired
	private ChoferBC choferBC;
	@Autowired
	private SgfConfigBC sgfConfigBC;

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
		BigDecimal costo = new BigDecimal(0);
		BigDecimal precio = new BigDecimal(0);
		BigDecimal ganancia = sgfConfigBC.getConfig().getGananciaPorcentaje();

		BigDecimal distancia = pedido.getDistancia();
		Float iva = pedido.getIva();
		BigDecimal mantenimiento = pedido.getCamion().getMantenimientoAnual();
		Float depreciacion = pedido.getCamion().getDepreciacionAnual();
		BigDecimal consumo = pedido.getCamion().getConsumoPorKm();
		Integer sueldo = getSueldo(pedido);
		Integer seguro = getSeguro(pedido);

		costo = consumo.multiply(distancia);// consumo*distancia
		costo = costo
				.add(costo.divide(new BigDecimal(100), RoundingMode.HALF_UP).multiply(new BigDecimal(depreciacion)));// +depreciacion
		costo = costo.add(mantenimiento.divide(new BigDecimal(365), RoundingMode.HALF_UP));// +mantenimiento
		costo = costo.add(new BigDecimal(sueldo / 30));
		costo = costo.add(new BigDecimal(seguro / 30));
		costo = costo.add(new BigDecimal(iva / 10));
		pedido.setCosto(costo.intValue());
		precio = costo.divide(new BigDecimal(100), RoundingMode.HALF_UP);

		pedido.setPrecio(precio.multiply(ganancia).intValue());
	}

	private Integer getSueldo(Pedido pedido) {

		Chofer chofer = null;
		chofer = choferBC.getDAOInstance().findEntityByCondition("WHERE camion_id = ?", pedido.getCamion().getId());
		return chofer.getSueldoNeto();
	}

	private Integer getSeguro(Pedido pedido) {

		Seguro seguro = null;
		seguro = seguroBC.getDAOInstance().findEntityByCondition("WHERE camion_id = ?", pedido.getCamion().getId());
		return seguro.getCosto();
	}

	private void checkBarrioCiudad(Pedido pedido) {

		if (pedido.getBarrio() != null) {
			if (!pedido.getBarrio().getCiudad().getId().equals(pedido.getCiudad().getId())) {
				pedido.setBarrio(null);
			}
		}
	}

}
