package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.CamionDao;
import py.una.sgf.domain.Camion;

@Repository
@Scope("request")
public class CamionDaoImpl extends DaoImpl<Camion> implements CamionDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
