package br.com.cast.avaliacao.security;

import br.com.cast.avaliacao.model.UserSystem;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserOAuthSecurity extends User {


    private UserSystem userSystem;

    public UserOAuthSecurity(final UserSystem userSystem, Collection<? extends GrantedAuthority> authorities) {
        super(userSystem.getLogin(), userSystem.getPassword(), authorities);
        this.userSystem = userSystem;
    }
}
