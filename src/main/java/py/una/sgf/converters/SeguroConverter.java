package py.una.sgf.converters;

import org.springframework.stereotype.Component;
import py.una.cnc.htroot.converters.ModelConverter;
import py.una.cnc.htroot.main.ApplicationContextProvider;
import py.una.sgf.dao.SeguroDao;
import py.una.sgf.domain.Seguro;

@Component
public class SeguroConverter extends ModelConverter<Seguro> {

	@Override
	protected SeguroDao getDaoImpl() {

		return (SeguroDao) ApplicationContextProvider.getBeanStatic("seguroDaoImpl");
	}
}
