package py.una.sgf.registros;

public class HistorialConexionUsuario {
	private String fecha;
	private String ip;
	private Long rownum;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getRownum() {
		return rownum;
	}

	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}

}
