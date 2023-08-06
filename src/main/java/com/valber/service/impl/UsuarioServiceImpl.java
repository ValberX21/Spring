package com.valber.service.impl;

import com.valber.domain.entity.Usuario;
import com.valber.domain.repository.UsuarioRepository;
import com.valber.exception.SenhaInvalidadExcepition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user =  loadUserByUsername(usuario.getLogin());
        boolean senhasBatem =  encoder.matches(usuario.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw  new SenhaInvalidadExcepition();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Usuario usu =  usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuairo nao encontrado"));


       String[] roles = usu.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usu.getLogin())
                .password(usu.getSenha())
                .roles(roles)
                .build();
//        if(!username.equals("Mara")){
//            throw new UsernameNotFoundException("Usuario nao encontrado na base");
//        }
//
//        return User.builder()
//                .username("Mara")
//                .password(encoder.encode("123"))
//                .roles("USER", "ADMIN")
//                .build();
    }
}
