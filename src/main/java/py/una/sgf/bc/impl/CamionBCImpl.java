package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.CamionBC;
import py.una.sgf.dao.CamionDao;
import py.una.sgf.domain.Camion;

@Component
@Scope("request")
public class CamionBCImpl extends BusinessControllerImpl<Camion> implements CamionBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CamionDao camionDao;

	@Override
	public CamionDao getDAOInstance() {

		return camionDao;
	}

}
