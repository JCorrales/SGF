package py.una.sgf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import py.una.cnc.htroot.domain.Model;
import py.una.cnc.htroot.domain.Usuario;

/*
 **
 *
 * @author juan
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "seguro_id" }) })
@XmlRootElement
@Audited
@NamedQueries({ @NamedQuery(name = "SeguroNotificarUsuario.findAll", query = "SELECT s FROM SeguroNotificarUsuario s"),
		@NamedQuery(name = "SeguroNotificarUsuario.findById", query = "SELECT s FROM SeguroNotificarUsuario s WHERE s.id = :id") })
public class SeguroNotificarUsuario extends Model {

	private static final String SECUENCIA = "seguro_notificar_usuario_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA)
	private Long id;

	@JoinColumn(name = "seguro_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Seguro seguro;

	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Usuario usuario;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Seguro getSeguro() {

		return seguro;
	}

	public void setSeguro(Seguro seguro) {

		this.seguro = seguro;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

}
