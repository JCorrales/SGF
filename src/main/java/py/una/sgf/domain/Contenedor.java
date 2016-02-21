package py.una.sgf.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import py.una.cnc.htroot.domain.Model;

public class Contenedor extends Model {

	@NotNull(message = "contenedor.largo.not_null")
	@Min(1)
	private int largo;
	@NotNull(message = "contenedor.ancho.not_null")
	@Min(1)
	private int ancho;
	@NotNull(message = "contenedor.alto.not_null")
	@Min(1)
	private int alto;

	@Override
	public Long getId() {

		return null;
	}

	@Override
	public void setId(Long id) {

	}

	public int getLargo() {

		return largo;
	}

	public void setLargo(int largo) {

		this.largo = largo;
	}

	public int getAncho() {

		return ancho;
	}

	public void setAncho(int ancho) {

		this.ancho = ancho;
	}

	public int getAlto() {

		return alto;
	}

	public void setAlto(int alto) {

		this.alto = alto;
	}

}
