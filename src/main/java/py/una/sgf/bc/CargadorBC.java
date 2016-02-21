package py.una.sgf.bc;

import java.util.List;
import com.packing.GA.Resultado;
import py.una.cnc.htroot.bc.BusinessController;
import py.una.sgf.domain.PaqueteEntrada;
import py.una.sgf.view.wrappers.Cargador;

public interface CargadorBC extends BusinessController<Cargador> {

	Resultado cargar(com.packing.slide.Contenedor contenedor2, List<PaqueteEntrada> paquetesArray);
}
