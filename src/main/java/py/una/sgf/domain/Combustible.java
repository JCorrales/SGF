package py.una.sgf.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "combustible_codigo_uq", columnNames = { "codigo" }))
public class Combustible extends Model {

	public static final int CODIGO_MAX_SIZE = 5;
	public static final int NOMBRE_MAX_SIZE = 50;
	private static final String SECUENCIA = "combustible_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "combustible.codigo.not_null")
	@NotBlank(message = "combustible.codigo.not_blank")
	@Size(max = CODIGO_MAX_SIZE, message = "combustible.codigo.size")
	private String codigo;

	@NotNull(message = "combustible.nombre.not_null")
	@NotBlank(message = "combustible.nombre.not_blank")
	@Size(max = NOMBRE_MAX_SIZE, message = "combustible.nombre.size")
	private String nombre;

	@NotNull(message = "combustible.precio.not_null")
	@Min(value = 0, message = "combustible.precio.min")
	private BigDecimal precio;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
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
