package py.una.sgf.view.wrappers;

import java.util.ArrayList;
import java.util.List;
import py.una.sgf.registros.UsuarioPickerReg;

public class UsuarioSeguro {

	private List<UsuarioPickerReg> usuarioList = new ArrayList<UsuarioPickerReg>();
	private Long seguroId;

	public List<UsuarioPickerReg> getUsuarioList() {

		return usuarioList;
	}

	public void setUsuarioList(List<UsuarioPickerReg> usuarioList) {

		this.usuarioList = usuarioList;
	}

	public Long getSeguroId() {

		return seguroId;
	}

	public void setSeguroId(Long seguroId) {

		this.seguroId = seguroId;
	}

}
