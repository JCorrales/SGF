package py.una.sgf.registros;

public class PaqueteStats {

	private int ancho;// z
	private int alto;// y
	private int largo;// x
	private String color;// identificador
	private int total;
	private int empaquetados;

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

	public int getLargo() {

		return largo;
	}

	public void setLargo(int largo) {

		this.largo = largo;
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

		this.total = total;
	}

	public int getEmpaquetados() {

		return empaquetados;
	}

	public void setEmpaquetados(int empaquetados) {

		this.empaquetados = empaquetados;
	}
}
