package com.clodi.activation;

import org.springframework.context.ApplicationEvent;

import com.clodi.entity.SimpleUser;

@SuppressWarnings("serial")
public class OnRegistrationSuccessEvent extends ApplicationEvent {

	private SimpleUser user;

	public OnRegistrationSuccessEvent(SimpleUser user) {
		super(user);
		this.user = user;
	}

	public SimpleUser getUser() {
		return user;
	}

	public void setUser(SimpleUser user) {
		this.user = user;
	}

}
