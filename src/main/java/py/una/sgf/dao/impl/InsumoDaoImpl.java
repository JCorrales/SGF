package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.InsumoDao;
import py.una.sgf.domain.Insumo;

@Repository
@Scope("request")
public class InsumoDaoImpl extends DaoImpl<Insumo> implements InsumoDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
