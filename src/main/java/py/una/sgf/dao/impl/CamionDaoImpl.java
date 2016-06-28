package py.una.sgf.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.CamionDao;
import py.una.sgf.dao.ChoferSingletonDao;
import py.una.sgf.domain.Camion;
import py.una.sgf.domain.Chofer;

@Repository
@Scope("request")
public class CamionDaoImpl extends DaoImpl<Camion> implements CamionDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ChoferSingletonDao choferSingletonDao;

	@Override
	@Transactional
	public Camion find(Long id) {

		Chofer chofer = choferSingletonDao.findEntityByCondition("WHERE camion_id = ?", id);
		Camion camion = super.find(id);
		camion.setChofer(chofer);
		return camion;
	}

}
