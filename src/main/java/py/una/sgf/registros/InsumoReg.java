package py.una.sgf.registros;

public class InsumoReg {

	private Long id;
	private Long rownum;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	private Integer costo;

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

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public String getDescripcion() {

		return descripcion;
	}

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	public Integer getCantidad() {

		return cantidad;
	}

	public void setCantidad(Integer cantidad) {

		this.cantidad = cantidad;
	}

	public Integer getCosto() {

		return costo;
	}

	public void setCosto(Integer costo) {

		this.costo = costo;
	}

}
