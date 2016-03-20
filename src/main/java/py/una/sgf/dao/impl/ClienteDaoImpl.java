package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.ClienteDao;
import py.una.sgf.domain.Cliente;

@Repository
@Scope("request")
public class ClienteDaoImpl extends DaoImpl<Cliente> implements ClienteDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
