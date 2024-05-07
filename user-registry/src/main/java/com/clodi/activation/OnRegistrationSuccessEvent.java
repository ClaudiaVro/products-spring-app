package com.clodi.activation;

import com.clodi.entity.SimpleUser;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial") public class OnRegistrationSuccessEvent extends ApplicationEvent {

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
