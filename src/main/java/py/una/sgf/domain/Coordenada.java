package py.una.sgf.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
public class Coordenada extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SECUENCIA = "coordenada_id_seq";

	@Id
	@GeneratedValue(generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "coordenada.longitud.not_null")
	private String longitud;

	@NotNull(message = "coordenada.latitud.not_null")
	private String latitud;

	public String getLongitud() {

		return longitud;
	}

	public void setLongitud(String longitud) {

		this.longitud = longitud;
	}

	public String getLatitud() {

		return latitud;
	}

	public void setLatitud(String latitud) {

		this.latitud = latitud;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;

	}

}
