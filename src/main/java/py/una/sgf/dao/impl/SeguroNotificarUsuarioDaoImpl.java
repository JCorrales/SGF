package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.SeguroNotificarUsuarioDao;
import py.una.sgf.domain.SeguroNotificarUsuario;

@Repository
@Scope("request")
public class SeguroNotificarUsuarioDaoImpl extends DaoImpl<SeguroNotificarUsuario>
		implements SeguroNotificarUsuarioDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void create(SeguroNotificarUsuario obj) {

		SeguroNotificarUsuario objectInBD = findEntityByCondition(
				"WHERE usuario_id = " + obj.getUsuario().getId() + " AND seguro_id = " + obj.getSeguro().getId());
		if (objectInBD == null) {
			super.create(obj);
		}

	}

	@Override
	public void destroy(SeguroNotificarUsuario obj) {

		if (obj.getId() == null) {
			SeguroNotificarUsuario objInBD = new SeguroNotificarUsuario();
			objInBD = findEntityByCondition(
					"WHERE usuario_id = " + obj.getUsuario().getId() + " AND seguro_id = " + obj.getSeguro().getId());

			if (objInBD != null) {
				super.destroy(objInBD);
				getLogger().info("destruido objeto de la clase {} con id {}", obj.getClass().getCanonicalName(),
						objInBD.getId());
			}

		} else {
			super.destroy(obj);
		}

	}
}
