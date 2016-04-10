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
 */

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "camion_chapa_uq", columnNames = { "chapa" }) )
public class Camion extends Model {

	public static final int CHAPA_MAX_SIZE = 20;
	public static final int CHAPA_MIN_SIZE = 2;
	private static final int MARCA_MAX_SIZE = 20;
	private static final int MODELO_MAX_SIZE = 20;
	private static final String SECUENCIA = "camion_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA)
	private Long id;

	@NotNull(message = "camion.chapa.not_null")
	@NotBlank(message = "camion.chapa.not_blank")
	@Size(max = CHAPA_MAX_SIZE, min = CHAPA_MIN_SIZE, message = "camion.chapa.size")
	private String chapa;

	@NotNull(message = "camion.marca.not_null")
	@NotBlank(message = "camion.marca.not_blank")
	@Size(max = MARCA_MAX_SIZE, message = "camion.marca.size")
	private String marca;

	@NotNull(message = "camion.modelo.not_null")
	@NotBlank(message = "camion.modelo.not_blank")
	@Size(max = MODELO_MAX_SIZE, message = "camion.modelo.size")
	private String modelo;

	@NotNull(message = "camion.anio.not_null")
	private Integer anio;

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;

	}

	public String getChapa() {

		return chapa;
	}

	public void setChapa(String chapa) {

		this.chapa = chapa;
	}

	public String getMarca() {

		return marca;
	}

	public void setMarca(String marca) {

		this.marca = marca;
	}

	public String getModelo() {

		return modelo;
	}

	public void setModelo(String modelo) {

		this.modelo = modelo;
	}

	public Integer getAnio() {

		return anio;
	}

	public void setAnio(Integer anio) {

		this.anio = anio;
	}

}
