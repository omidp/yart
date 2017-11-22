package com.yart.framework.security;

import java.io.Serializable;

public interface SecurityUserContext
{

    
    public String getUsername();
    public boolean isEnabled();
    public Long getId();
    
    
    
}
