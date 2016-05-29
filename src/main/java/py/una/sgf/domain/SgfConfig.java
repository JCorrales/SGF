package py.una.sgf.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import py.una.cnc.htroot.domain.Model;

/**
 */

@Entity
@Audited
public class SgfConfig extends Model {

	private static final String SECUENCIA = "sgf_config_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SECUENCIA)
	@SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
	private Long id;

	@NotNull(message = "sgf_config.iva.not_null")
	// @Min(value = 0L) float and double not supported
	private Float iva;

	@NotNull(message = "sgf_config.gananciaPorcentaje.not_null")
	@Min(value = 0L)
	private BigDecimal gananciaPorcentaje;

	@NotNull(message = "sgf_config.latitud_base.not_null")
	private BigDecimal latitudBase;

	@NotNull(message = "sgf_config.latitud_base.not_null")
	private BigDecimal longitudBase;

	@NotNull(message = "sgf_config.mail_host.not_null")
	@NotBlank(message = "sgf_config.mail_host.not_null")
	private String mailHost;

	@NotNull(message = "sgf_config.mail_remitente.not_null")
	@NotBlank(message = "sgf_config.mail_remitente.not_null")
	private String mailRemitente;

	@NotNull(message = "sgf_config.mail_pass.not_null")
	@NotBlank(message = "sgf_config.mail_pass.not_null")
	private String mailPass;

	@NotNull(message = "sgf_config.seguro_asunto.not_null")
	@NotBlank(message = "sgf_config.seguro_asunto.not_null")
	private String seguroAsunto;

	@NotNull(message = "sgf_config.seguro_menseaje.not_null")
	@NotBlank(message = "sgf_config.seguro_mensaje.not_null")
	private String seguroMensaje;

	@NotNull(message = "sgf_config.app_host.not_null")
	@NotBlank(message = "sgf_config.app_host.not_null")
	private String appHost;

	@NotNull(message = "sgf_config.app_host.not_null")
	@Min(value = 0L) // minutos
	private Integer resetPassTimeWait;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Float getIva() {

		return iva;
	}

	public void setIva(Float iva) {

		this.iva = iva;
	}

	public BigDecimal getGananciaPorcentaje() {

		return gananciaPorcentaje;
	}

	public void setGananciaPorcentaje(BigDecimal gananciaPorcentaje) {

		this.gananciaPorcentaje = gananciaPorcentaje;
	}

	public BigDecimal getLatitudBase() {

		return latitudBase;
	}

	public void setLatitudBase(BigDecimal latitudBase) {

		this.latitudBase = latitudBase;
	}

	public BigDecimal getLongitudBase() {

		return longitudBase;
	}

	public void setLongitudBase(BigDecimal longitudBase) {

		this.longitudBase = longitudBase;
	}

	public String getMailHost() {

		return mailHost;
	}

	public void setMailHost(String mailHost) {

		this.mailHost = mailHost;
	}

	public String getMailRemitente() {

		return mailRemitente;
	}

	public void setMailRemitente(String mailRemitente) {

		this.mailRemitente = mailRemitente;
	}

	public String getMailPass() {

		return mailPass;
	}

	public void setMailPass(String mailPass) {

		this.mailPass = mailPass;
	}

	public String getSeguroAsunto() {

		return seguroAsunto;
	}

	public void setSeguroAsunto(String seguroAsunto) {

		this.seguroAsunto = seguroAsunto;
	}

	public String getSeguroMensaje() {

		return seguroMensaje;
	}

	public void setSeguroMensaje(String seguroMensaje) {

		this.seguroMensaje = seguroMensaje;
	}

	public String getAppHost() {

		return appHost;
	}

	public void setAppHost(String appHost) {

		this.appHost = appHost;
	}

	public Integer getResetPassTimeWait() {

		return resetPassTimeWait;
	}

	public void setResetPassTimeWait(Integer resetPassTimeWait) {

		this.resetPassTimeWait = resetPassTimeWait;
	}

}
