package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.CiudadDao;
import py.una.sgf.domain.Ciudad;

@Repository
@Scope("request")
public class CiudadDaoImpl extends DaoImpl<Ciudad> implements CiudadDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
