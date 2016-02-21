package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.CiudadBC;
import py.una.sgf.dao.CiudadDao;
import py.una.sgf.domain.Ciudad;

@Component
@Scope("request")
public class CiudadBCImpl extends BusinessControllerImpl<Ciudad> implements
CiudadBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CiudadDao ciudadDao;

	@Override
	public CiudadDao getDAOInstance() {
		return ciudadDao;
	}

}
