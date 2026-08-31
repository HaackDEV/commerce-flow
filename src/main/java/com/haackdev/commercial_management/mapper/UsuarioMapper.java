package com.haackdev.commercial_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import com.haackdev.commercial_management.entity.Usuario;
import com.haackdev.commercial_management.dto.request.CadastroUsuarioRequest;
import com.haackdev.commercial_management.dto.response.CadastroUsuarioResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true) // ignora a senha do request (feito no service)
    Usuario requestToUsuario(CadastroUsuarioRequest request);

    CadastroUsuarioResponse usuarioToCadastroUsuarioResponse(Usuario usuario);
}