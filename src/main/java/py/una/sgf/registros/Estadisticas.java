package py.una.sgf.registros;

import java.util.ArrayList;
import java.util.List;
import py.una.sgf.domain.Contenedor;

public class Estadisticas {

	List<PaqueteStats> paqueteStatsList = new ArrayList<>();
	Contenedor contenedor;
	Double utilizacion;

	public Contenedor getContenedor() {

		return contenedor;
	}

	public void setContenedor(Contenedor contenedor) {

		this.contenedor = contenedor;
	}

	public List<PaqueteStats> getPaqueteStatsList() {

		return paqueteStatsList;
	}

	public void setPaqueteStatsList(List<PaqueteStats> paqueteStatsList) {

		this.paqueteStatsList = paqueteStatsList;
	}

	public Double getUtilizacion() {

		return utilizacion;
	}

	public void setUtilizacion(Double utilizacion) {

		this.utilizacion = utilizacion;
	}

}
