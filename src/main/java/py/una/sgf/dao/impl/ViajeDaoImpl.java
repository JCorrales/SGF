package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.ViajeDao;
import py.una.sgf.domain.Viaje;

@Repository
@Scope("request")
public class ViajeDaoImpl extends DaoImpl<Viaje> implements ViajeDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
