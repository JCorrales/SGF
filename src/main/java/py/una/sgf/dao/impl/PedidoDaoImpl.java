package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.PedidoDao;
import py.una.sgf.domain.Pedido;

@Repository
@Scope("request")
public class PedidoDaoImpl extends DaoImpl<Pedido> implements PedidoDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
