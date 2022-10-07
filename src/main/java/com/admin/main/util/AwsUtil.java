package com.admin.main.util;

import org.springframework.stereotype.Component;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;

@Component
public class AwsUtil {

	private static AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();

	public static String getParaValue(String paraName) {
		return ssm.getParameter(new GetParameterRequest().withWithDecryption(true).withName(paraName)).getParameter().getValue();		
	}
	
}
