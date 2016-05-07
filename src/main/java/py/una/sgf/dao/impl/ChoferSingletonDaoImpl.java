package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.sgf.dao.ChoferSingletonDao;
import py.una.sgf.domain.Chofer;

@Repository
@Scope
public class ChoferSingletonDaoImpl extends SingletonDaoImpl<Chofer> implements ChoferSingletonDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
