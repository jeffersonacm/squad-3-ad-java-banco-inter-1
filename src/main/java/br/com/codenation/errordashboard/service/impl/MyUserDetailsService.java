package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1;

        private UserRepositoryUserDetails(User user){
            super(user);
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.getRoles();
        }

        @Override
        public String getPassword() {
            return super.getPasswordHash();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}