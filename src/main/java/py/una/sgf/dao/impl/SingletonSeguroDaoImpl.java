package py.una.sgf.dao.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.sgf.dao.SingletonSeguroDao;
import py.una.sgf.domain.Seguro;
import py.una.sgf.notificadores.SeguroVencimiento;

@Repository
@Scope
public class SingletonSeguroDaoImpl extends SingletonDaoImpl<Seguro> implements SingletonSeguroDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SeguroVencimiento seguroVencimiento;

	@Override
	public void create(Seguro object) {

		super.create(object);
		seguroVencimiento.actualizarDatos();
	}

	@Override
	@Transactional
	public void edit(Seguro object) {

		super.edit(object);
		seguroVencimiento.actualizarDatos();
	}

	@Override
	public void destroy(Seguro obj) {

		super.destroy(obj);
		seguroVencimiento.actualizarDatos();
	}
}
