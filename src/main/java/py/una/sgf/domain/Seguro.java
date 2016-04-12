package py.una.sgf.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "seguro_poliza_uq", columnNames = { "poliza" }) )
public class Seguro extends Model {

	private static final String SECUENCIA = "seguro_id_seq";
	private static final int ASEGURADORA_MAX_SIZE = 40;
	private static final int DESCRIPCION_MAX_SIZE = 250;
	private static final int POLIZA_MAX_SIZE = 40;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "seguro.poliza.not_null")
	@NotBlank(message = "seguro.poliza.not_blank")
	@Size(max = POLIZA_MAX_SIZE, message = "seguro.poliza.size")
	private String poliza;

	@NotNull(message = "seguro.aseguradora.not_null")
	@NotBlank(message = "seguro.aseguradora.not_blank")
	@Size(max = ASEGURADORA_MAX_SIZE, message = "seguro.aseguradora.size")
	private String aseguradora;

	@NotNull(message = "seguro.descripcion.not_null")
	@NotBlank(message = "seguro.descripcion.not_blank")
	@Size(max = DESCRIPCION_MAX_SIZE, message = "seguro.descripcion.size")
	private String descripcion;

	@NotNull(message = "seguro.vencimiento.not_null")
	@Temporal(TemporalType.DATE)
	private Date vencimiento;

	@Min(value = 0, message = "seguro.dias_antelacion.size")
	@Max(value = 5, message = "seguro.dias_antelacion.size")
	@NotNull(message = "seguro.dias_antelacion.not_null")
	private Short diasAntelacion = 2;

	// @Size(min = USUARIOS_MIN_SIZE, message =
	// "seguro.seguro_notificar_usuario.min_size")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seguro", fetch = FetchType.EAGER, targetEntity = SeguroNotificarUsuario.class)
	private List<SeguroNotificarUsuario> listNotificar = new ArrayList<SeguroNotificarUsuario>();

	@OneToOne(fetch = FetchType.EAGER)
	@NotNull(message = "seguro.camion.not_null")
	private Camion camion;

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;

	}

	public String getAseguradora() {

		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {

		this.aseguradora = aseguradora;
	}

	public String getDescripcion() {

		return descripcion;
	}

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	public Date getVencimiento() {

		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {

		this.vencimiento = vencimiento;
	}

	public List<SeguroNotificarUsuario> getListNotificar() {

		return listNotificar;
	}

	public void setListNotificar(List<SeguroNotificarUsuario> listNotificar) {

		this.listNotificar = listNotificar;
	}

	public String getPoliza() {

		return poliza;
	}

	public void setPoliza(String poliza) {

		this.poliza = poliza;
	}

	@Override
	public String toString() {

		return aseguradora;
	}

	public Short getDiasAntelacion() {

		return diasAntelacion;
	}

	public void setDiasAntelacion(Short diasAntelacion) {

		this.diasAntelacion = diasAntelacion;
	}

	public Camion getCamion() {

		return camion;
	}

	public void setCamion(Camion camion) {

		this.camion = camion;
	}

}
