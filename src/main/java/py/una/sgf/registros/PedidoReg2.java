package py.una.sgf.registros;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoReg2 {

	private Long id;
	private Long rownum;
	private String cliente;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechapedido;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaentrega;
	private Integer costo;
	private Integer precio;
	private String direccion;
	private String ciudadDestino;
	private String ciudadOrigen;
	private String camion;
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

	public String getCliente() {

		return cliente;
	}

	public void setCliente(String cliente) {

		this.cliente = cliente;
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

	public Integer getCosto() {

		return costo;
	}

	public void setCosto(Integer costo) {

		this.costo = costo;
	}

	public Integer getPrecio() {

		return precio;
	}

	public void setPrecio(Integer precio) {

		this.precio = precio;
	}

	public String getDireccion() {

		return direccion;
	}

	public void setDireccion(String direccion) {

		this.direccion = direccion;
	}

	public String getCiudadDestino() {

		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {

		this.ciudadDestino = ciudadDestino;
	}

	public String getCamion() {

		return camion;
	}

	public void setCamion(String camion) {

		this.camion = camion;
	}

	public String getCiudadOrigen() {

		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {

		this.ciudadOrigen = ciudadOrigen;
	}

	public boolean isSelected() {

		return selected;
	}

	public void setSelected(boolean selected) {

		this.selected = selected;
	}

}
