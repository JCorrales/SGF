package py.una.sgf.registros;

public class ClienteReg {

	private Long id;

	private Long rownum;
	private String cedula;
	private String nombre;
	private String apellido;
	private String ruc;
	private Boolean fisica;

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

	public String getRuc() {

		return ruc;
	}

	public void setRuc(String ruc) {

		this.ruc = ruc;
	}

	public Boolean getFisica() {

		return fisica;
	}

	public void setFisica(Boolean fisica) {

		this.fisica = fisica;
	}

}
