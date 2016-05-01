package py.una.sgf.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;
import py.una.cnc.htroot.domain.Usuario;

@Entity
@Table(name = "chofer", uniqueConstraints = { @UniqueConstraint(columnNames = { "cedula" }, name = "chofer_cedula_uk"),
		@UniqueConstraint(columnNames = { "usuario_id" }, name = "chofer_usuario_uk"),
		@UniqueConstraint(columnNames = { "camion_id" }, name = "chofer_camion_uk") })
@Audited
public class Chofer extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SECUENCIA = "id_chofer_seq";

	@Id
	@GeneratedValue(generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "chofer.cedula.not_null")
	@NotBlank(message = "chofer.cedula.not_blank")
	private String cedula;

	@NotNull(message = "chofer.categoria.not_null")
	@NotBlank(message = "chofer.categoria.not_blank")
	private String categoria;

	@NotNull(message = "chofer.persona_referencia.not_null")
	@NotBlank(message = "chofer.persona_referencia.not_blank")
	private String personaReferencia;

	@NotNull(message = "chofer.usuario.not_null")
	@OneToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_chofer_usuario") )
	@Valid
	private Usuario usuario = new Usuario();

	@NotNull(message = "chofer.camion.not_null")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_camion_usuario") )
	@OneToOne
	private Camion camion;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public String getCedula() {

		return cedula;
	}

	public void setCedula(String cedula) {

		this.usuario.setCodigo(cedula);
		this.cedula = cedula;
	}

	public String getCategoria() {

		return categoria;
	}

	public void setCategoria(String categoria) {

		this.categoria = categoria;
	}

	public String getPersonaReferencia() {

		return personaReferencia;
	}

	public void setPersonaReferencia(String personaReferencia) {

		this.personaReferencia = personaReferencia;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	public Camion getCamion() {

		return camion;
	}

	public void setCamion(Camion camion) {

		this.camion = camion;
	}

}
