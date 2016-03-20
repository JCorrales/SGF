package py.una.sgf.bc.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.UsuarioBC;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.cnc.htroot.domain.RolUsuario;
import py.una.cnc.htroot.exception.BusinessLogicException;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.dao.ClienteDao;
import py.una.sgf.domain.Cliente;

@Component
@Scope("request")
public class ClienteBCImpl extends BusinessControllerImpl<Cliente> implements ClienteBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private UsuarioBC usuarioBC;

	@Override
	public ClienteDao getDAOInstance() {

		return clienteDao;
	}

	@Override
	public void create(Cliente bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			usuarioBC.create(bean.getUsuario());
		}
		super.create(bean);
	}

	@Override
	public void edit(Cliente bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			usuarioBC.edit(bean.getUsuario());
		}
		super.edit(bean);
	}

	@Override
	public void destroy(Cliente obj) {

		List<RolUsuario> roles = usuarioBC.getRoles(obj.getUsuario());

		for (int i = 0; i < roles.size(); i++) {
			roles.remove(i);
		}

		usuarioBC.destroy(obj.getUsuario());
		super.destroy(obj);
	}

}
