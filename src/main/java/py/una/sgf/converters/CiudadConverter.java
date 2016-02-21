package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.CiudadDao;
import py.una.sgf.domain.Ciudad;

@Component
public class CiudadConverter extends ModelConverter<Ciudad> {

	@Override
	protected CiudadDao getDaoImpl() {

		return (CiudadDao) ApplicationContextProvider
				.getBeanStatic("ciudadDaoImpl");
	}
}
