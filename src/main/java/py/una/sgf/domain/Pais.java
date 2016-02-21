package py.una.sgf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import py.una.cnc.htroot.domain.Model;

/**
 * @author Diego Cerrano
 * @since 1.0
 * @version 1.0 Mayo, 2015
 *
 */

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "pais_codigo_uq", columnNames = { "codigo" }))
public class Pais extends Model {
	public static final int CODIGO_MAX_SIZE = 2;
	public static final int CODIGO_MIN_SIZE = 2;
	public static final int NOMBRE_MAX_SIZE = 100;
	public static final int NACIONALIDAD_MAX_SIZE = 50;
	private static final String SECUENCIA = "pais_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA)
	private Long id;

	@NotNull(message = "pais.codigo.not_null")
	@NotBlank(message = "pais.codigo.not_blank")
	@Size(max = CODIGO_MAX_SIZE, min = CODIGO_MIN_SIZE, message = "pais.codigo.size")
	private String codigo;

	@NotNull(message = "pais.nombre.not_null")
	@NotBlank(message = "pais.nombre.not_blank")
	@Size(max = NOMBRE_MAX_SIZE, message = "pais.nombre.size")
	private String nombre;

	@NotNull(message = "pais.nacionalidad.not_null")
	@NotBlank(message = "pais.nacionalidad.not_blank")
	@Size(max = NACIONALIDAD_MAX_SIZE, message = "pais.nacionalidad.size")
	private String nacionalidad;

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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
