package py.una.sgf.bc;

import java.util.List;
import py.una.cnc.htroot.bc.UsuarioBC;
import py.una.cnc.htroot.domain.Rol;
import py.una.cnc.htroot.domain.Usuario;

public interface UsuarioBC2 extends UsuarioBC {

	@Override
	Usuario getUsuarioPorCodigo(String codigo);

	@Override
	Usuario findByHash(String hash);

	@Override
	byte[] getFoto(Usuario usuario, Long recordId);

	public void cambiarPass(Usuario usuario);

	public List<Rol> getRoles(Usuario usuario);
}
