package py.una.sgf.bc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.PaisBC;
import py.una.sgf.dao.PaisDao;
import py.una.sgf.domain.Pais;

@Component
@Scope("request")
public class PaisBCImpl extends BusinessControllerImpl<Pais> implements PaisBC {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private PaisDao paisDao;

    @Override
    public PaisDao getDAOInstance() {
        return paisDao;
    }

}
