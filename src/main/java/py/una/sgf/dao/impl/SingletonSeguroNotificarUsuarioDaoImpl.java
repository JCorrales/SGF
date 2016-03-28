package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.sgf.dao.SingletonSeguroNotificarUsuarioDao;
import py.una.sgf.domain.SeguroNotificarUsuario;

@Repository
@Scope
public class SingletonSeguroNotificarUsuarioDaoImpl extends SingletonDaoImpl<SeguroNotificarUsuario>
		implements SingletonSeguroNotificarUsuarioDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
}
