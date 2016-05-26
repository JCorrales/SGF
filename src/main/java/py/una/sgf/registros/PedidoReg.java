package py.una.sgf.registros;

import java.util.Date;

public class PedidoReg {

	private Long id;
	private Long rownum;
	private String cliente;
	private String ciudad;
	private Date fechapedido;
	private Date fechaentrega;
	private String costo;
	private String precio;
	private String direccion;
	private String barrio;
	private String camion;

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

	public String getCliente() {

		return cliente;
	}

	public void setCliente(String cliente) {

		this.cliente = cliente;
	}

	public String getCiudad() {

		return ciudad;
	}

	public void setCiudad(String ciudad) {

		this.ciudad = ciudad;
	}

	public Date getFechapedido() {

		return fechapedido;
	}

	public void setFechapedido(Date fechapedido) {

		this.fechapedido = fechapedido;
	}

	public Date getFechaentrega() {

		return fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {

		this.fechaentrega = fechaentrega;
	}

	public String getCosto() {

		return costo;
	}

	public void setCosto(String costo) {

		this.costo = costo;
	}

	public String getPrecio() {

		return precio;
	}

	public void setPrecio(String precio) {

		this.precio = precio;
	}

	public String getDireccion() {

		return direccion;
	}

	public void setDireccion(String direccion) {

		this.direccion = direccion;
	}

	public String getBarrio() {

		return barrio;
	}

	public void setBarrio(String barrio) {

		this.barrio = barrio;
	}

	public String getCamion() {

		return camion;
	}

	public void setCamion(String camion) {

		this.camion = camion;
	}

}
