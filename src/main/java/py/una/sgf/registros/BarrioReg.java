package py.una.sgf.registros;

public class BarrioReg {

	private Long id;
	private Long rownum;
	private String codigo;
	private String nombre;
	private String ciudad;
	private Float latitud;
	private Float longitud;

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

	public String getCiudad() {

		return ciudad;
	}

	public void setCiudad(String ciudad) {

		this.ciudad = ciudad;
	}

	public Float getLatitud() {

		return latitud;
	}

	public void setLatitud(Float latitud) {

		this.latitud = latitud;
	}

	public Float getLongitud() {

		return longitud;
	}

	public void setLongitud(Float longitud) {

		this.longitud = longitud;
	}

}
