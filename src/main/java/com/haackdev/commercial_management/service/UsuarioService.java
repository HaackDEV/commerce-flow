package com.haackdev.commercial_management.service;

import com.haackdev.commercial_management.dto.request.CadastroUsuarioRequest;
import com.haackdev.commercial_management.dto.response.CadastroUsuarioResponse;
import com.haackdev.commercial_management.entity.Usuario;
import com.haackdev.commercial_management.entity.enums.RoleUsuario;
import com.haackdev.commercial_management.mapper.UsuarioMapper;
import com.haackdev.commercial_management.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public CadastroUsuarioResponse cadastrar(CadastroUsuarioRequest request) {
        Usuario usuario = usuarioMapper.requestToUsuario(request);
        usuario.setId(null);
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setRole(RoleUsuario.ROLE_REPRESENTANTE);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToCadastroUsuarioResponse(usuario);
    }

}
