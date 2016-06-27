package py.una.sgf.bc;

import java.util.List;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.sgf.domain.Pedido;
import py.una.sgf.domain.Viaje;
import py.una.sgf.domain.ViajePedido;
import py.una.sgf.registros.PedidoReg2;

public interface ViajePedidoBC extends BusinessController<ViajePedido> {

	public void agregar(Viaje viaje, Pedido pedido);

	public void quitar(Viaje viaje, Pedido pedido);

	public List<PedidoReg2> getPedidoList(Long viajeId);
}
