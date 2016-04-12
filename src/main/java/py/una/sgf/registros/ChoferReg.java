package py.una.sgf.registros;

public class ChoferReg {

	private Long id;

	private Long rownum;
	private String cedula;
	private String nombre;
	private String apellido;
	private String categoria;

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

	public String getCedula() {

		return cedula;
	}

	public void setCedula(String cedula) {

		this.cedula = cedula;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public String getApellido() {

		return apellido;
	}

	public void setApellido(String apellido) {

		this.apellido = apellido;
	}

	public String getCategoria() {

		return categoria;
	}

	public void setCategoria(String categoria) {

		this.categoria = categoria;
	}

}
