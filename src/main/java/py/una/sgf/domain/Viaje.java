
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
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
public class Viaje extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SECUENCIA = "viaje_id_seq";

	@Id
	@GeneratedValue(generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	private Date fechaLlegada;

	private Date fechaSalida = new Date();

	@NotNull(message = "viaje.camion.not_null")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_viaje_camion"))
	@ManyToOne
	private Camion camion;

	@NotNull(message = "viaje.chofer.not_null")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_viaje_chofer"))
	@ManyToOne
	private Chofer chofer;

	private Integer costo;

	private BigDecimal distancia;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Date getFechaLlegada() {

		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {

		this.fechaLlegada = fechaLlegada;
	}

	public Date getFechaSalida() {

		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {

		this.fechaSalida = fechaSalida;
	}

	public Camion getCamion() {

		return camion;
	}

	public void setCamion(Camion camion) {

		this.camion = camion;
		if (camion != null) {
			setChofer(camion.getChofer());
		}
	}

	public Chofer getChofer() {

		return chofer;
	}

	public void setChofer(Chofer chofer) {

		if (chofer != null) {
			this.chofer = chofer;
		}
	}

	public Integer getCosto() {

		return costo;
	}

	public void setCosto(Integer costo) {

		this.costo = costo;
	}

	public BigDecimal getDistancia() {

		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {

		this.distancia = distancia;
	}

}
