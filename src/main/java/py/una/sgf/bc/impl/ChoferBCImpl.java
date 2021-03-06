package py.una.sgf.bc.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import py.una.cnc.htroot.bc.RolUsuarioBC;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.cnc.htroot.domain.RolUsuario;
import py.una.cnc.htroot.exception.BusinessLogicException;
import py.una.sgf.bc.ChoferBC;
import py.una.sgf.bc.UsuarioBC2;
import py.una.sgf.dao.ChoferDao;
import py.una.sgf.domain.Chofer;

@Component
@Scope("request")
public class ChoferBCImpl extends BusinessControllerImpl<Chofer> implements ChoferBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ChoferDao choferDao;
	@Autowired
	private UsuarioBC2 usuarioBC;
	@Autowired
	private RolUsuarioBC rolUsuarioBC;

	@Override
	public ChoferDao getDAOInstance() {

		return choferDao;
	}

	@Override
	@Transactional
	public void create(Chofer bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			// el chofer no es usuario del sistema
			bean.getUsuario().setActivo(false);
			bean.getUsuario().setCodigo(bean.getCedula());
			bean.getUsuario().setId(null);
			usuarioBC.create(bean.getUsuario());
		}
		super.create(bean);
	}

	@Override
	@Transactional
	public void edit(Chofer bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			bean.getUsuario().setCodigo(bean.getCedula());
			usuarioBC.edit(bean.getUsuario());
		}
		super.edit(bean);
	}

	@Override
	@Transactional
	public void destroy(Chofer obj) {

		List<RolUsuario> rolesUsuario = rolUsuarioBC.getDAOInstance().findEntitiesByCondition("WHERE usuario_id = ?",
				obj.getUsuario().getId());

		for (RolUsuario rolUsuario : rolesUsuario) {
			rolUsuarioBC.quitar(rolUsuario.getRol(), obj.getUsuario());
		}

		super.destroy(obj);
		usuarioBC.destroy(obj.getUsuario());
	}
}
