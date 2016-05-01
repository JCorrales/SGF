package py.una.sgf.bc.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import py.una.cnc.htroot.bc.UsuarioBC;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.cnc.htroot.dao.UsuarioDao;
import py.una.cnc.htroot.domain.Rol;
import py.una.cnc.htroot.domain.Usuario;
import py.una.cnc.htroot.exception.BusinessLogicException;
import py.una.cnc.htroot.exception.ObjectErrorException;
import py.una.cnc.htroot.main.AppObjects;
import py.una.cnc.htroot.main.CoreConfig;
import py.una.cnc.htroot.main.Message;
import py.una.cnc.htroot.util.Util;
import py.una.cnc.lib.db.dataprovider.SQLToObject;
import py.una.sgf.controladores.CredencialesUsuarioControlador2;

@Component
@Scope("session")
public class UsuarioBCImpl2 extends BusinessControllerImpl<Usuario> implements UsuarioBC {

	@Autowired
	private Util util;
	@Autowired
	private Message msg;
	@Autowired
	private CredencialesUsuarioControlador2 credenciales;
	@Autowired
	private AppObjects appObjects;
	@Autowired
	private CoreConfig coreConfig;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Usuario getUsuarioPorCodigo(String codigo) {

		return usuarioDao.findByCode(codigo);
	}

	@Override
	public Usuario findByHash(String hash) {

		return usuarioDao.findByHash(hash);
	}

	@Override
	public void create(Usuario usuario) {

		ObjectErrorException errors = new ObjectErrorException();
		encriptarPassword(usuario, errors);
		verificarImagen(usuario);
		if (!errors.isEmpty()) {
			throw errors;
		}
		super.create(usuario);

		enviarMail(usuario);
	}

	@Override
	public void edit(Usuario usuario) {

		verificarImagen(usuario);
		// aqui nunca cambiamos el password
		Usuario tmp = usuarioDao.find(usuario.getId());
		usuario.setPassword(tmp.getPassword());
		super.edit(usuario);
	}

	private void encriptarPassword(Usuario usuario, ObjectErrorException errors) {

		if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
			errors.add("password", "usuario.password.not_blank");
		}

		usuario.setPassword(util.getEncodedPass(msg.get("password.encoder", "MD5"), usuario.getPassword()));
	}

	@Override
	public byte[] getFoto(Usuario usuario, Long recordId) {

		return usuarioDao.getFoto(usuario, recordId);
	}

	@Override
	public UsuarioDao getDAOInstance() {

		return usuarioDao;
	}

	private void verificarImagen(Usuario usuario) {

		ObjectErrorException errors = new ObjectErrorException();

		Usuario tmp = usuarioDao.find(usuario.getId());

		/**
		 * Cuando no nos envian una foto: Si es un nuevo usuario dejamos el
		 * campo foto en null o vacio. Si no es un nuevo usuario usamos la foto
		 * anterior
		 */
		byte[] bytes = usuario.getFoto();

		if ((bytes == null || bytes.length == 0)) {
			if (tmp != null) {// se esta editando usuario
				usuario.setFoto(tmp.getFoto());
			} else {// se esta creando
				usuario.setFoto(null);
			}

		} else {
			if (!util.isImage(bytes)) {
				errors.add("foto", "file.uploaded.noimage");
				throw errors;
			}
		}
	}
	/*
	 * private void verificarPassword(Usuario usuario) {
	 *
	 * Usuario tmp = usuarioDao.find(usuario.getId()); /** Si se edita la
	 * contraseña, entonces viene sin encriptar. Si viene null o vacía, entonces
	 * no se modificó contraseña y debemos recuperar la antigua, ya que para no
	 * mostrar la contraseña en la vista, se puso usuario.setPassword(null). Si
	 * es nuevo registro no se hace nada
	 */
	/*
	 * if (usuario.getPassword() == null ||
	 * usuario.getPassword().trim().length() == 0) {
	 *
	 * if (tmp != null) { usuario.setPassword(tmp.getPassword()); } } else { if
	 * (usuario.getId() != null) {
	 * usuario.setPassword(util.getEncodedPass(msg.get("password.encoder",
	 * "MD5"), usuario.getPassword())); } } }
	 */

	/**
	 * Recibe el usuario con todos sus datos. El password debe estar sin
	 * encriptar
	 */
	@Override
	public void cambiarPass(Usuario usuario) {

		usuario.setPassword(util.getEncodedPass(msg.get("password.encoder", "MD5"), usuario.getPassword()));
		usuarioDao.edit(usuario);
	}

	// envio el mail en un hilo y dejo que se cree el usuario para evitar
	// errores en hibernate
	private void enviarMail(Usuario usuario) {

		Thread thread = new Thread() {

			@Override
			public void run() {

				credenciales.cambiarPass(usuario.getCodigo(), new ModelMap());
			}
		};
		thread.start();
	}

	@Override
	public List<Rol> getRoles(Usuario usuario) {

		String sql = appObjects.getSqlSource().get("select_domain_roles_usuario");
		SQLToObject<Rol> sqlToObject = new SQLToObject<>(appObjects.getDataSourcePool());
		sqlToObject.setClassOfT(Rol.class);
		String dsName = coreConfig.getDefaultDataSource();

		List<Rol> roles = null;
		try {
			roles = sqlToObject.createList(dsName, sql, usuario.getId());
		} catch (SQLException exception) {
			throw new BusinessLogicException(exception.getMessage());
		}

		return roles;
	}
}
