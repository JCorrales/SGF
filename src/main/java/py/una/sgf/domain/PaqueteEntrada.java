package py.una.sgf.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import py.una.cnc.htroot.domain.Model;

public class PaqueteEntrada extends Model {

	@NotNull(message = "paquete_entrada.ancho_actual.not_null")
	@Min(1)
	private int anchoActual;

	@NotNull(message = "paquete_entrada.largo_actual.not_null")
	@Min(1)
	private int largoActual;

	@NotNull(message = "paquete_entrada.alto_actual.not_null")
	@Min(1)
	private int altoActual;

	@NotNull(message = "paquete_entrada.cantidad.not_null")
	@Min(1)
	private int cantidad;

	@NotNull(message = "paquete_entrada.rotacion_lateral.not_null")
	private boolean rotacionLateral = true;

	@NotNull(message = "paquete_entrada.rotacion_longitudinal.not_null")
	private boolean rotacionLongitudinal = true;

	@NotNull(message = "paquete_entrada.rotacion_vertical.not_null")
	private boolean rotacionVertical = true;

	@NotNull(message = "paquete_entrada.color.not_null")
	private String color;

	private static int total = 0;

	public int getAnchoActual() {

		return anchoActual;
	}

	public void setAnchoActual(int anchoActual) {

		this.anchoActual = anchoActual;
	}

	public int getLargoActual() {

		return largoActual;
	}

	public void setLargoActual(int largoActual) {

		this.largoActual = largoActual;
	}

	public int getAltoActual() {

		return altoActual;
	}

	public void setAltoActual(int altoActual) {

		this.altoActual = altoActual;
	}

	public int getCantidad() {

		return cantidad;
	}

	public void setCantidad(int cantidad) {

		setTotal(getTotal() + cantidad);
		this.cantidad = cantidad;
	}

	public boolean isRotacionLateral() {

		return rotacionLateral;
	}

	public void setRotacionLateral(boolean rotacionLateral) {

		this.rotacionLateral = rotacionLateral;
	}

	public boolean isRotacionLongitudinal() {

		return rotacionLongitudinal;
	}

	public void setRotacionLongitudinal(boolean rotacionLongitudinal) {

		this.rotacionLongitudinal = rotacionLongitudinal;
	}

	public boolean isRotacionVertical() {

		return rotacionVertical;
	}

	public void setRotacionVertical(boolean rotacionVertical) {

		this.rotacionVertical = rotacionVertical;
	}

	@Override
	public Long getId() {

		return null;
	}

	@Override
	public void setId(Long id) {

	}

	public String getColor() {

		return color;
	}

	public void setColor(String color) {

		this.color = color;
	}

	public int getTotal() {

		return total;
	}

	public void setTotal(int total) {

		// en lugar de this.total, para que el compiler deje de plagearse
		PaqueteEntrada.total = total;
	}

}
