package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.CombustibleDao;
import py.una.sgf.domain.Combustible;

@Component
public class CombustibleConverter extends ModelConverter<Combustible> {

	@Override
	protected CombustibleDao getDaoImpl() {

		return (CombustibleDao) ApplicationContextProvider.getBeanStatic("combustibleDaoImpl");
	}
}
