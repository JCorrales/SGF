package py.una.sgf.registros;

import java.math.BigDecimal;

public class CombustibleReg {

	private Long id;
	private Long rownum;
	private String codigo;
	private String nombre;
	private BigDecimal precio;

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

	public BigDecimal getPrecio() {

		return precio;
	}

	public void setPrecio(BigDecimal precio) {

		this.precio = precio;
	}

}
