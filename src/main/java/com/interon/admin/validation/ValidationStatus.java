package com.interon.admin.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.interon.admin.enums.Status;

@Component
public class ValidationStatus {
	
	private Status status;
	private List<String> messages;
	
	public ValidationStatus() {
		super();
	}

	public ValidationStatus(List<String> messages) {
		super();
		this.messages = messages;
	}

	public ValidationStatus(Status status, List<String> messages) {
		super();
		this.status = status;
		this.messages = messages;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
