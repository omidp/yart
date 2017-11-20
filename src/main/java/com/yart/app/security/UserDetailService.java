package com.yart.app.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yart.util.SecurityUserContext;

public class UserDetailService implements UserDetailsService
{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if("admin".equals(username))
            return new User(1L, true, username, "admin");
        else 
            throw new UsernameNotFoundException("user should be admin");
    }

    public static class User implements UserDetails, SecurityUserContext
    {

        private Long id;
        private boolean enabled;
        private String username;
        private String password;

        public User()
        {
        }

        public User(Long id, boolean enabled, String username, String password)
        {
            this.id = id;
            this.enabled = enabled;
            this.username = username;
            this.password = password;
        }

        public void setId(Long id)
        {
            this.id = id;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        @Override
        public Long getId()
        {
            return id;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities()
        {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getPassword()
        {
            return password;
        }

        @Override
        public String getUsername()
        {
            return username;
        }

        @Override
        public boolean isAccountNonExpired()
        {
            return true;
        }

        @Override
        public boolean isAccountNonLocked()
        {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired()
        {
            return true;
        }

        @Override
        public boolean isEnabled()
        {
            return true;
        }

    }

}
