package py.una.sgf.registros;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SeguroReg {

	private Long id;
	private Long rownum;
	private String poliza;
	private String aseguradora;
	private String descripcion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date vencimiento;

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

	public String getAseguradora() {

		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {

		this.aseguradora = aseguradora;
	}

	public String getDescripcion() {

		if (descripcion.length() > 55) {
			return descripcion.substring(0, 55) + "... ";
		}
		return descripcion;
	}

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	public Date getVencimiento() {

		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {

		this.vencimiento = vencimiento;
	}

	public String getPoliza() {

		return poliza;
	}

	public void setPoliza(String poliza) {

		this.poliza = poliza;
	}
}
