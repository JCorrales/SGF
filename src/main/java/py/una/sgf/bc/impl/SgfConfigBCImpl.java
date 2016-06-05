package py.una.sgf.bc.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import py.una.cnc.htroot.bc.impl.BusinessControllerImpl;
import py.una.sgf.bc.SgfConfigBC;
import py.una.sgf.dao.SgfConfigDao;
import py.una.sgf.domain.SgfConfig;
import py.una.sgf.util.SgfMessage;

@Component
@Scope
public class SgfConfigBCImpl extends BusinessControllerImpl<SgfConfig> implements SgfConfigBC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SgfConfigDao sgfConfigDao;
	@Autowired
	private SgfMessage msg;

	@Override
	public SgfConfigDao getDAOInstance() {

		return sgfConfigDao;
	}

	@Override
	public SgfConfig getConfig() {

		List<SgfConfig> sgfConfigList = findEntities();
		SgfConfig sgfConfig = null;
		if (sgfConfigList == null || sgfConfigList.isEmpty()) {
			sgfConfig = crearDefault();
		} else {
			return sgfConfigList.get(0);
		}

		return sgfConfig;
	}

	private SgfConfig crearDefault() {

		SgfConfig sgfConfig = new SgfConfig();
		sgfConfig.setAppHost(msg.get("app.host"));
		sgfConfig.setGananciaPorcentaje(new BigDecimal("10"));
		sgfConfig.setIva(new Float(10));
		sgfConfig.setLatitudBase("-25.282875876175524");
		sgfConfig.setLongitudBase("-57.634894251823425");
		sgfConfig.setMailHost(msg.get("mail.host"));
		sgfConfig.setMailPass(msg.get("mail.password"));
		sgfConfig.setMailRemitente(msg.get("mail.remitente"));
		sgfConfig.setResetPassTimeWait(24 * 60);
		sgfConfig.setSeguroAsunto(msg.get("seguro.mail.asunto"));
		sgfConfig.setSeguroMensaje(msg.get("seguro.mail.mensaje"));
		super.create(sgfConfig);
		return sgfConfig;
	}

	@Override
	public void edit(SgfConfig obj) {

		if (obj.getMailPass() == null || obj.getMailPass().trim().length() == 0) {
			obj.setMailPass(getConfig().getMailPass());
		}
		super.edit(obj);
	};

	@Override
	public void destroy(SgfConfig obj) {

		return;
	}

	@Override
	public void create(SgfConfig obj) {

		return;
	}

}
