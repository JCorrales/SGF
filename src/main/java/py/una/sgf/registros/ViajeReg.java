package py.una.sgf.registros;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ViajeReg {

	private Long id;
	private Long rownum;
	private String camion;
	private String chofer;
	private BigDecimal distancia;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaSalida;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaLlegada;
	private Integer costo;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getRownum() {

		return rownum;
	}

	public void setRownum(Long rownum) {

		this.rownum = rownum;
	}

	public String getCamion() {

		return camion;
	}

	public void setCamion(String camion) {

		this.camion = camion;
	}

	public String getChofer() {

		return chofer;
	}

	public void setChofer(String chofer) {

		this.chofer = chofer;
	}

	public Integer getCosto() {

		return costo;
	}

	public void setCosto(Integer costo) {

		this.costo = costo;
	}

	public BigDecimal getDistancia() {

		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {

		this.distancia = distancia;
	}

	public Date getFechaSalida() {

		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {

		this.fechaSalida = fechaSalida;
	}

	public Date getFechaLlegada() {

		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {

		this.fechaLlegada = fechaLlegada;
	}

}
