package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.BarrioDao;
import py.una.sgf.domain.Barrio;

@Repository
@Scope("request")
public class BarrioDaoImpl extends DaoImpl<Barrio> implements BarrioDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
