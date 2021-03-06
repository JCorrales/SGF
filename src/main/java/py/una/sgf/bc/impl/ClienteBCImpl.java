package py.una.sgf.bc.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import py.una.cnc.htroot.bc.PermisoBC;
import py.una.cnc.htroot.bc.PermisoUsuarioBC;
import py.una.cnc.htroot.bc.RolBC;
import py.una.cnc.htroot.bc.RolUsuarioBC;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.cnc.htroot.domain.PermisoUsuario;
import py.una.cnc.htroot.domain.Rol;
import py.una.cnc.htroot.domain.RolUsuario;
import py.una.cnc.htroot.exception.BusinessLogicException;
import py.una.sgf.bc.ClienteBC;
import py.una.sgf.bc.UsuarioBC2;
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
	private UsuarioBC2 usuarioBC;
	@Autowired
	private RolUsuarioBC rolUsuarioBC;
	@Autowired
	private PermisoUsuarioBC permisoUsuarioBC;
	@Autowired
	private PermisoBC permisoBC;
	@Autowired
	private RolBC rolBC;

	@Override
	public ClienteDao getDAOInstance() {

		return clienteDao;
	}

	@Override
	@Transactional
	public void create(Cliente bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			// si ha fallado el guardado anterior
			bean.getUsuario().setId(null);
			usuarioBC.create(bean.getUsuario());
			PermisoUsuario clientePermiso = new PermisoUsuario();
			clientePermiso.setPermiso(permisoBC.findByCode("cliente"));
			clientePermiso.setUsuario(bean.getUsuario());
			RolUsuario rolCliente = new RolUsuario();
			rolCliente.setRol(rolBC.findByCode("cliente"));
			rolCliente.setUsuario(bean.getUsuario());
			permisoUsuarioBC.create(clientePermiso);
			rolUsuarioBC.create(rolCliente);
		}
		super.create(bean);
	}

	@Override
	@Transactional
	public void edit(Cliente bean) {

		if (bean.getUsuario() == null) {
			throw new BusinessLogicException("Usuario no debe ser nulo");
		} else {
			usuarioBC.edit(bean.getUsuario());
		}
		super.edit(bean);
	}

	@Override
	@Transactional
	public void destroy(Cliente obj) {

		List<Rol> roles = usuarioBC.getRoles(obj.getUsuario());

		for (Rol rol : roles) {
			rolUsuarioBC.quitar(rol, obj.getUsuario());
		}

		super.destroy(obj);
		usuarioBC.destroy(obj.getUsuario());
	}
}
