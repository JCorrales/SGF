package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.dao.SeguroDao;
import py.una.sgf.domain.Seguro;

@Component
@Scope("request")
public class SeguroBCImpl extends BusinessControllerImpl<Seguro> implements SeguroBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SeguroDao seguroDao;

	@Override
	public SeguroDao getDAOInstance() {

		return seguroDao;
	}

}
