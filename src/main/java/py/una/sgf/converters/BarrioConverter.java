package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.BarrioDao;
import py.una.sgf.domain.Barrio;

@Component
public class BarrioConverter extends ModelConverter<Barrio> {

	@Override
	protected BarrioDao getDaoImpl() {

		return (BarrioDao) ApplicationContextProvider
				.getBeanStatic("barrioDaoImpl");
	}
}
