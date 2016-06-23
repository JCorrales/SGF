package py.una.sgf.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
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
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_barrio_origen"))
	private Barrio barrioOrigen;

	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_ciudad_origen"))
	@ManyToOne
	@NotNull(message = "pedido.ciudad_origen.not_null")
	private Ciudad ciudadOrigen;

	@NotNull(message = "pedido.camion.not_null")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_camion"))
	@ManyToOne
	private Camion camion;

	@NotNull(message = "pedido.fecha_pedido.not_null")
	private Date fechaPedido = new Date();

	private Date fechaEntrega;

	private Float iva;

	private Integer costo;

	private BigDecimal distancia;

	private Integer precio;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_barrio_destino"))
	private Barrio barrioDestino;

	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_ciudad_destino"))
	@ManyToOne
	@NotNull(message = "pedido.ciudad_destino.not_null")
	private Ciudad ciudadDestino;

	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_coordenada_origen"))
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull(message = "pedido.ciudad_origen.not_null")
	private Coordenada origen;

	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_coordenada_destino"))
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull(message = "pedido.ciudad_destino.not_null")
	private Coordenada destino;

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

	public Barrio getBarrioOrigen() {

		return barrioOrigen;
	}

	public void setBarrioOrigen(Barrio barrioOrigen) {

		this.barrioOrigen = barrioOrigen;
		if (barrioOrigen != null) {
			this.ciudadOrigen = barrioOrigen.getCiudad();
		}
	}

	public Ciudad getCiudadOrigen() {

		return ciudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {

		if (ciudadOrigen != null) {
			this.ciudadOrigen = ciudadOrigen;
		}
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

	public BigDecimal getDistancia() {

		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {

		this.distancia = distancia;
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

	public Barrio getBarrioDestino() {

		return barrioDestino;
	}

	public void setBarrioDestino(Barrio barrioDestino) {

		this.barrioDestino = barrioDestino;
		if (barrioDestino != null) {
			this.ciudadDestino = barrioDestino.getCiudad();
		}
	}

	public Ciudad getCiudadDestino() {

		return ciudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {

		if (ciudadDestino != null) {
			this.ciudadDestino = ciudadDestino;
		}
	}

	public Coordenada getOrigen() {

		return origen;
	}

	public void setOrigen(Coordenada origen) {

		this.origen = origen;
	}

	public Coordenada getDestino() {

		return destino;
	}

	public void setDestino(Coordenada destino) {

		this.destino = destino;
	}

}
