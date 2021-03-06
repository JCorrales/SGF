package py.una.sgf.bc.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import py.una.sgf.registros.Estadisticas;
import py.una.sgf.registros.PaqueteStats;
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
	private boolean algunaRotacion = false;
	private Estadisticas estadisticas;

	@Override
	public Resultado cargar(py.una.sgf.domain.Contenedor contenedorEntrada, List<PaqueteEntrada> paquetesEntrada) {

		try {
			PackingController packing = new PackingController();
			List<PackingInput> instancias = new ArrayList<>();
			Constantes.VERSION = 2;

			estadisticas = new Estadisticas();
			setEstadisticas(contenedorEntrada, paquetesEntrada);

			Contenedor contenedor = regularizarContenedor(contenedorEntrada);
			List<Paquete> paquetes = regularizarPaquetes(paquetesEntrada);

			Contenedor contenedor2 = new Contenedor(contenedor.getAncho(), contenedor.getLargo(), contenedor.getAlto());
			List<Paquete> paquetes2 = clonarPaquetes(paquetes);

			// primera aproximacion
			PackingInput packingInput = new PackingInput(paquetes, contenedor);
			instancias.add(packingInput);

			ManejadorPaquetes mp = ManejadorPaquetes.getInstance();
			Resultado resultado = packing.ejecutar(1, instancias, mp, 1, 0);
			// segunda aproximacion
			if (algunaRotacion) {
				getLogger().info("preparando segundo intento sin rotaciones");
				PackingInput packingInput2 = new PackingInput(paquetes2, contenedor2);
				instancias.add(1, packingInput2);
				PackingController packing2 = new PackingController();
				Resultado resultado2 = packing2.ejecutar(1, instancias, mp, 1, 1);

				if (resultado2.getValor() >= resultado.getValor()) {
					getLogger().info("cambiando resultados");
					resultado = resultado2;
					packingInput = packingInput2;
					contenedor = contenedor2;
				}
			}

			trasladarCoordenadas(contenedor);
			sesionUsuario.addObject("contenedor", contenedor);
			sesionUsuario.addObject("packingInput", packingInput);
			sesionUsuario.addObject("resultado", resultado);
			completarEstadisticas(packingInput, resultado);
			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void trasladarCoordenadas(Contenedor contenedor) {

		List<Paquete> empaquetados = contenedor.getEmpaquetados();
		for (int i = 0; i < empaquetados.size(); i++) {
			Paquete p = empaquetados.get(i);
			Coordenada c = p.getVertice();
			c.setX(c.getX() + (p.getLargo() / 2));// se trunca
			c.setY(c.getY() + (p.getAlto() / 2));
			c.setZ(c.getZ() + (p.getAncho() / 2));
			p.setVertice(c);
		}

	}

	private Contenedor regularizarContenedor(py.una.sgf.domain.Contenedor contenedorEntrada) {

		int largo = contenedorEntrada.getLargo();
		int ancho = contenedorEntrada.getAncho();
		int alto = contenedorEntrada.getAlto();

		com.packing.slide.Contenedor contenedor = new com.packing.slide.Contenedor(ancho, largo, alto);

		Rotacion rotacionInicial = Rotacion.SinRotacion;
		// Chequeo que la mayor medida sea en el ancho
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
		return contenedor;
	}

	private List<Paquete> regularizarPaquetes(List<PaqueteEntrada> paquetesEntrada) {

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
					algunaRotacion = true;
				}
				if (paqueteEntrada.isRotacionLongitudinal()) {
					rotacionesPermitidas.add(Rotacion.GiroSobreY);
					rotacionesPermitidas.add(Rotacion.GiroSobreYAbajo);
					algunaRotacion = true;
				}
				if (paqueteEntrada.isRotacionVertical()) {
					rotacionesPermitidas.add(Rotacion.Izquierda);
					algunaRotacion = true;
				}
				paquete.setRotacionesPermitidas(rotacionesPermitidas);
				paquetes.add(paquete);
			}
		}
		sesionUsuario.addObject("colores", colores);
		return paquetes;
	}

	private List<Paquete> clonarPaquetes(List<Paquete> paquetes) {

		List<Paquete> paquetes2 = new ArrayList<>();
		for (Paquete paquete : paquetes) {
			Paquete paquete2 = new Paquete(paquete.getLargo(), paquete.getAlto(), paquete.getAncho(), paquete.getId());
			Set<Rotacion> rotaciones = new HashSet<>();
			rotaciones.add(Rotacion.SinRotacion);
			paquete2.setRotacionesPermitidas(rotaciones);
			paquetes2.add(paquete2);
		}
		return paquetes2;
	}

	private void setEstadisticas(py.una.sgf.domain.Contenedor contenedor, List<PaqueteEntrada> paquetesEntrada) {

		estadisticas.setContenedor(contenedor);
		for (PaqueteEntrada paqueteEntrada : paquetesEntrada) {
			PaqueteStats paqueteStats = new PaqueteStats();
			paqueteStats.setAlto(paqueteEntrada.getAltoActual());
			paqueteStats.setAncho(paqueteEntrada.getAnchoActual());
			paqueteStats.setLargo(paqueteEntrada.getLargoActual());
			paqueteStats.setColor(paqueteEntrada.getColor());
			paqueteStats.setTotal(paqueteEntrada.getCantidad());
			estadisticas.getPaqueteStatsList().add(paqueteStats);
		}
	}

	private void completarEstadisticas(PackingInput packingInput, Resultado resultado) {

		estadisticas.setUtilizacion(resultado.getValor());
		// necesito los colores pues los tipo de paquete se indentifica por
		// su color
		String[] colores = (String[]) sesionUsuario.getObject("colores");

		// uso un hashmap intentado que el proceso sea mas rapido
		HashMap<String, Integer> empaquetados = new HashMap<>();
		// inicializo el hashmap
		for (int i = 0; i < colores.length; i++) {
			Integer cantidad = 0;// solo para aclarar
			empaquetados.put(colores[i], cantidad);
		}
		getLogger().info("cantidad de tipos de paquete: " + empaquetados.size());

		// sumar los paquetes que estan dentro del contenedor
		Coordenada fuera = new Coordenada(-1, -1, -1);
		for (Paquete paquete : packingInput.getPaquetes()) {
			Integer cantidad = empaquetados.get(colores[paquete.getId()]);
			if (!paquete.getVertice().equals(fuera)) {
				empaquetados.put(colores[paquete.getId()], ++cantidad);
			}
		}

		for (int i = 0; i < estadisticas.getPaqueteStatsList().size(); i++) {
			PaqueteStats paquete = estadisticas.getPaqueteStatsList().get(i);
			int cantidad = empaquetados.get(paquete.getColor());
			paquete.setEmpaquetados(cantidad);
		}

		sesionUsuario.addObject("estadisticas", estadisticas);
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
