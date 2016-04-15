package py.una.sgf.domain;

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

/**
 */

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "insumo_nombre_uq", columnNames = { "nombre" }) )
public class Insumo extends Model {

	public static final int NOMBRE_MAX_SIZE = 30;
	private static final int DESCRIPCION_MAX_SIZE = 100;
	private static final String SECUENCIA = "insumo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "insumo.nombre.not_null")
	@NotBlank(message = "insumo.nombre.not_blank")
	@Size(max = NOMBRE_MAX_SIZE, message = "insumo.nombre.size")
	private String nombre;

	@Size(max = DESCRIPCION_MAX_SIZE, message = "insumo.descripcion.size")
	private String descripcion;

	@NotNull(message = "insumo.cantidad.not_null")
	@Min(value = 0, message = "insumo.cantidad.min")
	private Integer cantidad;

	@NotNull(message = "insumo.costo.not_null")
	@Min(value = 0, message = "insumo.costo.min")
	private Integer costo;

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;

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
