package py.una.sgf.bc.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.SeguroBC;
import py.una.sgf.dao.SeguroDao;
import py.una.sgf.dao.SeguroNotificarUsuarioDao;
import py.una.sgf.domain.Seguro;
import py.una.sgf.domain.SeguroNotificarUsuario;
import py.una.sgf.notificadores.SeguroVencimiento;

@Component
@Scope("request")
public class SeguroBCImpl extends BusinessControllerImpl<Seguro> implements SeguroBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SeguroDao seguroDao;

	@Autowired
	private SeguroVencimiento seguroVencimiento;

	@Autowired
	private SeguroNotificarUsuarioDao notificarDao;

	@Override
	public SeguroDao getDAOInstance() {

		return seguroDao;
	}

	@Override
	@Transactional
	public void create(Seguro obj) {

		super.create(obj);
		seguroVencimiento.actualizarDatos();
	}

	@Override
	@Transactional
	public void edit(Seguro obj) {

		super.edit(obj);
		seguroVencimiento.actualizarDatos();
	}

	@Override
	@Transactional
	public void destroy(Seguro obj) {

		obj = seguroDao.find(obj.getId());// para cargar la lista
		for (SeguroNotificarUsuario notificar : obj.getListNotificar()) {
			notificarDao.destroy(notificar);
		}
		super.destroy(obj);
		seguroVencimiento.actualizarDatos();
	}

}
