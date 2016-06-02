package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.CombustibleDao;
import py.una.sgf.domain.Combustible;

@Repository
@Scope("request")
public class CombustibleDaoImpl extends DaoImpl<Combustible> implements CombustibleDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
