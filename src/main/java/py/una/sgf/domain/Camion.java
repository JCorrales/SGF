package py.una.sgf.domain;

import java.io.IOException;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import py.una.cnc.htroot.domain.Model;

@Entity
@Audited
@Table(uniqueConstraints = @UniqueConstraint(name = "camion_chapa_uq", columnNames = { "chapa" }))
public class Camion extends Model {

	public static final int CHAPA_MAX_SIZE = 20;
	public static final int CHAPA_MIN_SIZE = 2;
	private static final int MARCA_MAX_SIZE = 20;
	private static final int MODELO_MAX_SIZE = 20;
	private static final String SECUENCIA = "camion_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
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

	@NotNull(message = "camion.consumo_km.not_null")
	private BigDecimal consumoPorKm;

	@NotNull(message = "camion.dpreciacion_anual.not_null")
	private Float depreciacionAnual;

	@NotNull(message = "camion.mantenimiento_anual.not_null")
	private BigDecimal mantenimientoAnual;

	@NotNull(message = "camion.combustible.not_null")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_camion_combustible"))
	private Combustible combustible;

	@Lob
	@Basic(fetch = FetchType.LAZY, optional = true)
	private byte[] foto;

	@Transient
	private MultipartFile tmpFoto;

	@Override
	public Long getId() {

		return id;
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

	public BigDecimal getConsumoPorKm() {

		return consumoPorKm;
	}

	public void setConsumoPorKm(BigDecimal consumoPorKm) {

		this.consumoPorKm = consumoPorKm;
	}

	public Float getDepreciacionAnual() {

		return depreciacionAnual;
	}

	public void setDepreciacionAnual(Float depreciacionAnual) {

		this.depreciacionAnual = depreciacionAnual;
	}

	public BigDecimal getMantenimientoAnual() {

		return mantenimientoAnual;
	}

	public void setMantenimientoAnual(BigDecimal mantenimientoAnual) {

		this.mantenimientoAnual = mantenimientoAnual;
	}

	@Override
	public String toString() {

		return "Chapa: " + chapa + " Marca: " + marca + " Modelo: " + modelo;
	}

	@Override
	public byte[] getFoto() {

		return foto;
	}

	@Override
	public void setFoto(byte[] foto) {

		this.foto = foto;
	}

	public MultipartFile getTmpFoto() {

		return tmpFoto;
	}

	public void setTmpFoto(MultipartFile tmpFoto) {

		try {
			this.setFoto(tmpFoto.getBytes());
		} catch (IOException e) {
			// nunca deberia ocurrir
			throw new IllegalStateException("Error de acceso al archivo temporal");
		}
		this.tmpFoto = tmpFoto;
	}

	public Combustible getCombustible() {

		return combustible;
	}

	public void setCombustible(Combustible combustible) {

		this.combustible = combustible;
	}
}
