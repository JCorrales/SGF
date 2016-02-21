package py.una.sgf.bc.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.packing.Constantes;
import com.packing.GA.Resultado;
import com.packing.interfaces.implementation.Globals;
import com.packing.interfaces.implementation.PackingController;
import com.packing.slide.Contenedor;
import com.packing.slide.Coordenada;
import com.packing.slide.ManejadorPaquetes;
import com.packing.slide.PackingInput;
import com.packing.slide.Paquete;
import com.packing.slide.Rotacion;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.cnc.htroot.dao.Dao;
import py.una.cnc.htroot.exception.DAOException;
import py.una.cnc.htroot.main.SesionUsuario;
import py.una.sgf.bc.CargadorBC;
import py.una.sgf.domain.PaqueteEntrada;
import py.una.sgf.view.wrappers.Cargador;

@Component
@Scope("session")
public class CargadorBCImpl extends BusinessControllerImpl<Cargador> implements CargadorBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SesionUsuario sesionUsuario;

	@Override
	public Resultado cargar(Contenedor contenedor, List<PaqueteEntrada> paquetesEntrada) {

		try {
			PackingController packing = new PackingController();
			List<PackingInput> instancias = new ArrayList<>();
			Constantes.VERSION = 2;

			// ************ regularizar contenedor
			int largo = contenedor.getLargo();
			int ancho = contenedor.getAncho();
			int alto = contenedor.getAlto();
			Rotacion rotacionInicial = Rotacion.SinRotacion;
			// Chequeo que la mayor medida sea en el ancho
			int aux;
			if (ancho < largo) {
				// aux=largo;
				// largo=ancho;
				// ancho=aux;
				rotacionInicial = Rotacion.GiroSobreY;
			}
			if (ancho < alto) {
				// aux=alto;
				// alto=ancho;
				// ancho=aux;
				if (rotacionInicial == Rotacion.SinRotacion) {
					rotacionInicial = Rotacion.Abajo;
				} else {
					rotacionInicial = Rotacion.GiroSobreYAbajo;
				}
			}
			// Guardo en una variable global la rotacin para poder deshacerla al
			// final de terminado el algoritmo
			Globals.rotacionInicial = rotacionInicial;
			// *************** fin de regularizar contenedor

			// ************* regularizar paquetes
			List<Paquete> paquetes = new ArrayList<>();
			Set<Rotacion> rotacionesPermitidas;
			String[] colores = new String[paquetesEntrada.get(0).getTotal()];

			int id = -1;// para que tengan diferentes id
			for (PaqueteEntrada paqueteEntrada : paquetesEntrada) {
				for (int i = 1; i <= paqueteEntrada.getCantidad(); i++) {
					Paquete paquete = new Paquete(paqueteEntrada.getLargoActual(), paqueteEntrada.getAltoActual(),
							paqueteEntrada.getAnchoActual(), ++id);
					colores[id] = paqueteEntrada.getColor();
					rotacionesPermitidas = new HashSet<Rotacion>();
					rotacionesPermitidas.add(Rotacion.SinRotacion);
					if (paqueteEntrada.isRotacionLateral()) {
						rotacionesPermitidas.add(Rotacion.Abajo);
						rotacionesPermitidas.add(Rotacion.AbajoIzquierda);
					}
					if (paqueteEntrada.isRotacionLongitudinal()) {
						rotacionesPermitidas.add(Rotacion.GiroSobreY);
						rotacionesPermitidas.add(Rotacion.GiroSobreYAbajo);
					}
					if (paqueteEntrada.isRotacionVertical()) {
						rotacionesPermitidas.add(Rotacion.Izquierda);
					}
					paquete.setRotacionesPermitidas(rotacionesPermitidas);
					paquetes.add(paquete);
				}
			}
			sesionUsuario.addObject("colores", colores);
			// ************* fin de regularizar paquetes

			PackingInput packinInput = new PackingInput(paquetes, contenedor);
			instancias.add(packinInput);

			ManejadorPaquetes mp = ManejadorPaquetes.getInstance();
			Resultado resultado = packing.ejecutar(1, instancias, mp, 1, 0);
			trasladarCoordenadas(contenedor);
			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void trasladarCoordenadas(Contenedor contenedor) {

		List<Paquete> empaquetados = contenedor.getEmpaquetados();
		int index = 0;
		for (Paquete p : empaquetados) {
			Coordenada c = p.getVertice();
			c.setX(c.getX() + (p.getLargo() / 2));// se trunca
			c.setY(c.getY() + (p.getAlto() / 2));
			c.setZ(c.getZ() + (p.getAncho() / 2));
			p.setVertice(c);
			empaquetados.set(index, p);
			index++;
		}

	}

	@Override
	public void create(Cargador obj) {

		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Cargador obj) {

		// TODO Auto-generated method stub

	}

	@Override
	public void destroy(Cargador obj) {

		// TODO Auto-generated method stub

	}

	@Override
	public Cargador find(Long id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cargador findByCode(String code) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cargador> findEntities(boolean activo) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cargador> findEntities() throws DAOException {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Cargador> getDAOInstance() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userCanSelect() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userCanInsert() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userCanUpdate() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userCanDelete() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNewRecord() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNewRecord(boolean newRecord) {

		// TODO Auto-generated method stub

	}

	@Override
	public void save(Cargador bean) {

		// TODO Auto-generated method stub

	}

	@Override
	public String getPermissionBaseName() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermissionRequired() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPermissionRequired(boolean permissionRequired) {

		// TODO Auto-generated method stub

	}

	@Override
	public Cargador getLastBeanRetrieved() {

		// TODO Auto-generated method stub
		return null;
	}
}
