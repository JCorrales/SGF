package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.CamionDao;
import py.una.sgf.domain.Camion;

@Component
public class CamionConverter extends ModelConverter<Camion> {

	@Override
	protected CamionDao getDaoImpl() {

		return (CamionDao) ApplicationContextProvider.getBeanStatic("camionDaoImpl");
	}
}
