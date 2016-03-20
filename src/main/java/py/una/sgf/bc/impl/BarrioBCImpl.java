package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.BarrioBC;
import py.una.sgf.dao.BarrioDao;
import py.una.sgf.domain.Barrio;

@Component
@Scope("request")
public class BarrioBCImpl extends BusinessControllerImpl<Barrio> implements BarrioBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BarrioDao barrioDao;

	@Override
	public BarrioDao getDAOInstance() {

		return barrioDao;
	}

}
