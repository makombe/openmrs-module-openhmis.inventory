package org.openmrs.module.openhmis.inventory.api.util;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.APIException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.inventory.web.ModuleWebConstants;
import org.openmrs.util.OpenmrsClassLoader;

public class IdGeneratorUtil {
	
	private static final Log LOG = LogFactory.getLog(IdGeneratorUtil.class);
	
	
	public static boolean isOperationNumberAutoGenerated() {
		AdministrationService administrationService = Context.getAdministrationService();
		return Boolean.parseBoolean(administrationService.getGlobalProperty(ModuleWebConstants.AUTO_GENERATE_OPERATION_NUMBER_PROPERTY));
	}
	
	public static String getNewStockOperationIdentifier(PatientIdentifierType patientIdentifierType) throws APIException{
		try {
			if (patientIdentifierType == null) {
				return null;
			}
			Object identifierSourceService = Context.getService(Class.forName(
			    "org.openmrs.module.idgen.service.IdentifierSourceService", true, OpenmrsClassLoader.getInstance()));
			Method method = identifierSourceService.getClass().getMethod("generateIdentifier",
			    new Class[] { PatientIdentifierType.class, String.class });
			String id = (String) method.invoke(identifierSourceService, new Object[] { patientIdentifierType, "inventory module" });
			return id;
		}
		catch (Exception e) {
			LOG.error("error generating Stock Operation ID", e);
		}
		
		return null;
	}
}