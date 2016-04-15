package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.InsumoBC;
import py.una.sgf.dao.InsumoDao;
import py.una.sgf.domain.Insumo;

@Component
@Scope("request")
public class InsumoBCImpl extends BusinessControllerImpl<Insumo> implements InsumoBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private InsumoDao insumoDao;

	@Override
	public InsumoDao getDAOInstance() {

		return insumoDao;
	}

}
