package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.sgf.dao.SgfConfigDao;
import py.una.sgf.domain.SgfConfig;

@Repository
@Scope
public class SgfConfigDaoImpl extends SingletonDaoImpl<SgfConfig> implements SgfConfigDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
