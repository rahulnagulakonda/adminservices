package com.interon.admin.util;

import org.springframework.stereotype.Component;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;

@Component
public class AwsUtils {

	private static AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();

	public String getParaValue(String paraName) {
		return ssm.getParameter(new GetParameterRequest().withWithDecryption(true).withName(paraName)).getParameter().getValue();		
	}
	
}
