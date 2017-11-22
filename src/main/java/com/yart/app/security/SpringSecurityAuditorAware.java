package com.yart.app.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yart.app.domain.User;

/**
 *
 * @author Omid Pourhadi
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<User>
{

    public User getCurrentAuditor()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
        {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && principal.equals("anonymousUser"))
        {

            return null;
        }
        return (User) principal;
    }
}