package br.com.codenation.errordashboard.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}