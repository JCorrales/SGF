package py.una.sgf.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "barrio_codigo_uq", columnNames = { "codigo" }) )
public class Barrio extends Model {

	public static final int CODIGO_MAX_SIZE = 3;
	public static final int NOMBRE_MAX_SIZE = 50;
	private static final String SECUENCIA = "barrio_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA)
	private Long id;

	@NotNull(message = "barrio.codigo.not_null")
	@NotBlank(message = "barrio.codigo.not_blank")
	@Size(max = CODIGO_MAX_SIZE, min = CODIGO_MAX_SIZE, message = "barrio.codigo.size")
	private String codigo;

	@NotNull(message = "barrio.ciudad.not_null")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_ciudad_barrio") )
	private Ciudad ciudad;

	@NotNull(message = "barrio.nombre.not_null")
	@NotBlank(message = "barrio.nombre.not_blank")
	@Size(max = NOMBRE_MAX_SIZE, message = "barrio.nombre.size")
	private String nombre;

	private Float longitud;

	private Float latitud;

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

	public Ciudad getCiudad() {

		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {

		this.ciudad = ciudad;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public Float getLongitud() {

		return longitud;
	}

	public void setLongitud(Float longitud) {

		this.longitud = longitud;
	}

	public Float getLatitud() {

		return latitud;
	}

	public void setLatitud(Float latitud) {

		this.latitud = latitud;
	}

	@Override
	public String toString() {

		return nombre;
	}

}
