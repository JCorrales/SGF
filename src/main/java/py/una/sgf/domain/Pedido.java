package py.una.sgf.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
public class Pedido extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SECUENCIA = "pedido_id_seq";

	@Id
	@GeneratedValue(generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "pedido.cliente.not_null")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;

	@NotNull(message = "pedido.direccion.not_null")
	@NotBlank(message = "pedido.direccion.not_blank")
	private String direccion;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_barrio"))
	private Barrio barrio;

	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_ciudad"))
	@ManyToOne
	private Ciudad ciudad;

	@NotNull(message = "pedido.camion.not_null")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_camion"))
	@ManyToOne
	private Camion camion;

	@NotNull(message = "pedido.fecha_pedido.not_null")
	private Date fechaPedido = new Date();

	private Date fechaEntrega;

	private Float iva;

	private BigDecimal costo;

	private BigDecimal distancia;

	private BigDecimal precio;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Cliente getCliente() {

		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public String getDireccion() {

		return direccion;
	}

	public void setDireccion(String direccion) {

		this.direccion = direccion;
	}

	public Barrio getBarrio() {

		return barrio;
	}

	public void setBarrio(Barrio barrio) {

		this.barrio = barrio;
		if (barrio != null && barrio.getCiudad() != null) {
			this.ciudad = barrio.getCiudad();
		}
	}

	public Ciudad getCiudad() {

		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {

		this.ciudad = ciudad;
	}

	public Camion getCamion() {

		return camion;
	}

	public void setCamion(Camion camion) {

		this.camion = camion;
	}

	public Date getFechaPedido() {

		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {

		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {

		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {

		this.fechaEntrega = fechaEntrega;
	}

	public Float getIva() {

		return iva;
	}

	public void setIva(Float iva) {

		this.iva = iva;
	}

	public BigDecimal getCosto() {

		return costo;
	}

	public void setCosto(BigDecimal costo) {

		this.costo = costo;
	}

	public BigDecimal getPrecio() {

		return precio;
	}

	public void setPrecio(BigDecimal precio) {

		this.precio = precio;
	}

	public BigDecimal getDistancia() {

		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {

		this.distancia = distancia;
	}

}
