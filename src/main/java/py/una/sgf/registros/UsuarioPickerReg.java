package py.una.sgf.registros;

public class UsuarioPickerReg {

	private Long id;
	private Long rownum;
	private String codigo;
	private String nombre;
	private String apellido;
	private String email;
	private boolean selected;

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

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(String codigo) {

		this.codigo = codigo;
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

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public boolean isSelected() {

		return selected;
	}

	public void setSelected(boolean selected) {

		this.selected = selected;
	}

}
