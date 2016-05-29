package py.una.sgf.bc;

import py.una.cnc.htroot.bc.BusinessController;
import py.una.sgf.domain.SgfConfig;

public interface SgfConfigBC extends BusinessController<SgfConfig> {

	public SgfConfig getConfig();
}
