package py.una.sgf.registros;

public class CamionReg {

	private Long id;
	private Long rownum;
	private String chapa;
	private String marca;
	private String modelo;
	private int anio;
	private String aseguradora;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getRownum() {

		return rownum;
	}

	public void setRownum(Long rownum) {

		this.rownum = rownum;
	}

	public String getChapa() {

		return chapa;
	}

	public void setChapa(String chapa) {

		this.chapa = chapa;
	}

	public String getMarca() {

		return marca;
	}

	public void setMarca(String marca) {

		this.marca = marca;
	}

	public String getModelo() {

		return modelo;
	}

	public void setModelo(String modelo) {

		this.modelo = modelo;
	}

	public int getAnio() {

		return anio;
	}

	public void setAnio(int anio) {

		this.anio = anio;
	}

	public String getAseguradora() {

		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {

		this.aseguradora = aseguradora;
	}

}
