package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.sgf.dao.ViajeSingletonDao;
import py.una.sgf.domain.Viaje;

@Repository
@Scope
public class ViajeSingletonDaoImpl extends SingletonDaoImpl<Viaje> implements ViajeSingletonDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
