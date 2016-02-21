package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.PaisDao;
import py.una.sgf.domain.Pais;

@Component
public class PaisConverter extends ModelConverter<Pais> {

	@Override
	protected PaisDao getDaoImpl() {

		return (PaisDao) ApplicationContextProvider
				.getBeanStatic("paisDaoImpl");
	}
}
