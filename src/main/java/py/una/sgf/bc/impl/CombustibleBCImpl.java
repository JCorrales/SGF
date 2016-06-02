package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.CombustibleBC;
import py.una.sgf.dao.CombustibleDao;
import py.una.sgf.domain.Combustible;

@Component
@Scope("request")
public class CombustibleBCImpl extends BusinessControllerImpl<Combustible> implements CombustibleBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CombustibleDao combustibleDao;

	@Override
	public CombustibleDao getDAOInstance() {

		return combustibleDao;
	}

}
