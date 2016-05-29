package py.una.sgf.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Corrales
 * @Since 1.0
 * @Version 1.0 28 de may. de 2016
 */
@Repository
public class SgfMessage {

	@Autowired
	private MessageSource messageSource;

	public String get(String msg) {

		return messageSource.getMessage(msg, null, msg, LocaleContextHolder.getLocale());
	}

	public String get(String msg, String defaultMsg) {

		return messageSource.getMessage(msg, null, defaultMsg, LocaleContextHolder.getLocale());
	}
}
