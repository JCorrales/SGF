package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.ChoferDao;
import py.una.sgf.domain.Chofer;

@Repository
@Scope("request")
public class ChoferDaoImpl extends DaoImpl<Chofer> implements ChoferDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
