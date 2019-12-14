package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.exceptions.UserEmailExistsException;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.mappers.UserMapper;
import br.com.codenation.errordashboard.service.interfaces.UserServiceInterface;
import br.com.codenation.errordashboard.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserDAO userDAO;

    protected UserMapper userMapper;

    public UserService(UserDAO userDAO) {

        this.userDAO = userDAO;
    }

    public UserRepositoryUserDetails getUserDetails(String email) {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        public UserRepositoryUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getUsername() {
            return super.getEmail();
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

        @Override
        public String getPassword() {
            return super.getPasswordHash();
        }
    }

    public UserDTO createUser(String name, String email, String password) {

        emailValidate(email);

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordEncoder.hash(password))
                .lastSeen(LocalDateTime.now())
                .build();

        User userSaved;
        userSaved = userDAO.save(user);

        return User.toUserDto(userSaved);
    }

    public void emailValidate(String email) {
        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserEmailExistsException();
        }
    }
}
