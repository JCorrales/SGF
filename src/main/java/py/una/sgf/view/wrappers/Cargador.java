package py.una.sgf.view.wrappers;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import py.una.cnc.htroot.domain.Model;
import py.una.sgf.domain.Contenedor;
import py.una.sgf.domain.PaqueteEntrada;

public class Cargador extends Model {

	@NotNull(message = "cargador.contenedor.not_null")
	private Contenedor contenedor;
	@NotNull(message = "cargador.paqueteEntrada.not_null")
	private ArrayList<PaqueteEntrada> paquetesEntrada;

	@Override
	public Long getId() {

		return null;
	}

	@Override
	public void setId(Long id) {

	}

	public Contenedor getContenedor() {

		return contenedor;
	}

	public void setContenedor(Contenedor contenedor) {

		this.contenedor = contenedor;
	}

	public ArrayList<PaqueteEntrada> getPaquetesEntrada() {

		return paquetesEntrada;
	}

	public void setPaquetesEntrada(ArrayList<PaqueteEntrada> paquetesEntrada) {

		this.paquetesEntrada = paquetesEntrada;
	}

}
