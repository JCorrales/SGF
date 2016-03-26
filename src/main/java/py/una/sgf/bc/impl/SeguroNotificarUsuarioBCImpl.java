package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.SeguroNotificarUsuarioBC;
import py.una.sgf.dao.SeguroNotificarUsuarioDao;
import py.una.sgf.domain.SeguroNotificarUsuario;

@Component
@Scope("request")
public class SeguroNotificarUsuarioBCImpl extends BusinessControllerImpl<SeguroNotificarUsuario>
		implements SeguroNotificarUsuarioBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SeguroNotificarUsuarioDao seguroNotificarUsuarioDao;

	@Override
	public SeguroNotificarUsuarioDao getDAOInstance() {

		return seguroNotificarUsuarioDao;
	}

}
