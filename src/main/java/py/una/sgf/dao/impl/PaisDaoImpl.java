package py.una.sgf.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import py.una.cnc.htroot.dao.impl.DaoImpl;
import py.una.sgf.dao.PaisDao;
import py.una.sgf.domain.Pais;

@Repository
@Scope("request")
public class PaisDaoImpl extends DaoImpl<Pais> implements PaisDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}