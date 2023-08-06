package com.valber.rest.controller;


import com.valber.domain.entity.Usuario;
import com.valber.exception.SenhaInvalidadExcepition;
import com.valber.rest.dto.CredenciaisDto;
import com.valber.rest.dto.TokenDTO;
import com.valber.security.jwt.JwtService;
import com.valber.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCript = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCript);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDto credenciasiDTO) {

        try{
            Usuario usuario =  Usuario.builder()
                            .login(credenciasiDTO.getLogin())
                            .senha(credenciasiDTO.getSenha()).build();

           UserDetails usuarioAuthenticado =  usuarioService.autenticar(usuario);
           String token = jwtService.gerarToken(usuario);

           return new TokenDTO(usuario.getLogin(), token);

        }catch (UsernameNotFoundException | SenhaInvalidadExcepition e){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
