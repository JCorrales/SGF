package py.una.sgf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.envers.Audited;
import py.una.cnc.htroot.domain.Model;

/**
 *
 * @author Juan Corrales
 * @Since 1.0
 * @Version 1.0 23 de jun. de 2016
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "pedido_id", "viaje_id" }) })
@Audited
public class ViajePedido extends Model {

	private static final String SECUENCIA = "viaje_pedido_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@JoinColumn(name = "viaje_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Viaje viaje;

	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Pedido pedido;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Viaje getViaje() {

		return viaje;
	}

	public void setViaje(Viaje viaje) {

		this.viaje = viaje;
	}

	public Pedido getPedido() {

		return pedido;
	}

	public void setPedido(Pedido pedido) {

		this.pedido = pedido;
	}

}
