package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.SeguroDao;
import py.una.sgf.domain.Seguro;

@Repository
@Scope("request")
public class SeguroDaoImpl extends DaoImpl<Seguro> implements SeguroDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
