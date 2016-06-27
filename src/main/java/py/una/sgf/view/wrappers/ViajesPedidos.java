package py.una.sgf.view.wrappers;

import java.util.List;
import py.una.sgf.registros.PedidoReg2;

public class ViajesPedidos {

	private Long viajeId;
	private List<PedidoReg2> pedidosList;

	public Long getViajeId() {

		return viajeId;
	}

	public void setViajeId(Long viajeId) {

		this.viajeId = viajeId;
	}

	public List<PedidoReg2> getPedidosList() {

		return pedidosList;
	}

	public void setPedidosList(List<PedidoReg2> pedidosList) {

		this.pedidosList = pedidosList;
	}

}
