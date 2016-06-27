package py.una.sgf.dao;

import java.util.List;
import py.una.cnc.htroot.dao.Dao;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.domain.ViajePedido;
import py.una.sgf.registros.PedidoReg2;

public interface ViajePedidoDao extends Dao<ViajePedido> {

	public void agregar(Viaje viaje, Pedido pedido);

	public void quitar(Viaje viaje, Pedido pedido);

	public List<PedidoReg2> getPedidoList(Long viajeId);
}
