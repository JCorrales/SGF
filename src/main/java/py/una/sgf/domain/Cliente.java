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
@Table(name = "cliente", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "cedula" }, name = "cliente_cedula_uk"),
		@UniqueConstraint(columnNames = { "usuario_id" }, name = "cliente_usuario_uk") })
@Audited
public class Cliente extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "id_cliente_seq")
	@SequenceGenerator(name = "id_cliente_seq", sequenceName = "id_cliente_seq", allocationSize = 1)
	private Long id;

	@NotNull(message = "cliente.cedula.not_null")
	@NotBlank(message = "cliente.cedula.not_blank")
	private String cedula;

	@NotNull(message = "cliente.ruc.not_null")
	@NotBlank(message = "cliente.ruc.not_blank")
	private String ruc;

	@NotNull(message = "cliente.fisica.not_null")
	private Boolean fisica = true;

	@NotNull(message = "cliente.usuario.not_null")
	@OneToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_cliente_usuario") )
	@Valid
	private Usuario usuario = new Usuario();

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

		this.getUsuario().setCodigo(cedula);
		this.cedula = cedula;
	}

	public String getRuc() {

		return ruc;
	}

	public void setRuc(String ruc) {

		this.ruc = ruc;
	}

	public Boolean getFisica() {

		return fisica;
	}

	public void setFisica(Boolean fisica) {

		this.fisica = fisica;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

}
