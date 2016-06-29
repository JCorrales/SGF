package py.una.sgf.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import py.una.cnc.htroot.main.AppObjects;
import py.una.cnc.htroot.main.CoreConfig;
import py.una.cnc.lib.db.dataprovider.SQLToObject;
import py.una.sgf.dao.ViajePedidoSingletonDao;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.domain.ViajePedido;
import py.una.sgf.registros.PedidoReg2;

@Repository
@Scope("session")
public class ViajePedidoSingletonDaoImpl extends SingletonDaoImpl<ViajePedido> implements ViajePedidoSingletonDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AppObjects appObjects;
	@Autowired
	private CoreConfig coreConfig;

	@Override
	@Transactional
	public void agregar(Viaje viaje, Pedido pedido) {

		ViajePedido objectFromBD = findEntityByCondition("WHERE pedido_id = ? AND viaje_id = ?", pedido.getId(),
				viaje.getId());
		if (objectFromBD == null) {
			ViajePedido viajePedido = new ViajePedido();
			viajePedido.setPedido(pedido);
			viajePedido.setViaje(viaje);
			super.create(viajePedido);
		}

	}

	@Override
	@Transactional
	public void quitar(Viaje viaje, Pedido pedido) {

		destroyEntitiesByCondition("WHERE pedido_id = ? AND viaje_id = ?", pedido.getId(), viaje.getId());

	}

	@Override
	public List<PedidoReg2> getPedidoList(Long viajeId) {

		String sql = appObjects.getSqlSource().get("select_viaje_pedidos");
		SQLToObject<PedidoReg2> sqlToObject = new SQLToObject<>(appObjects.getDataSourcePool());
		sqlToObject.setClassOfT(PedidoReg2.class);
		String dsName = coreConfig.getDefaultDataSource();

		try {
			return sqlToObject.createList(dsName, sql, viajeId, viajeId);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}

}
