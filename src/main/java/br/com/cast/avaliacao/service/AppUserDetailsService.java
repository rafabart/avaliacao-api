package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.UserSystem;
import br.com.cast.avaliacao.repository.UserSystemRepository;
import br.com.cast.avaliacao.security.UserOAuthSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

        Optional<UserSystem> userSystemOptional = userSystemRepository.findByLogin(login);

        final UserSystem userSystem = userSystemOptional.orElseThrow(
                () -> new UsernameNotFoundException("Usuário e/ou senha incorretos")
        );

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new UserOAuthSecurity(userSystem, authorities);
    }
}
