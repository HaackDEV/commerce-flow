package com.haackdev.commercial_management.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.haackdev.commercial_management.dto.response.ClienteResponse;
import com.haackdev.commercial_management.entity.Cliente;

public class ClienteMapperTest {

    private final ClienteMapper mapper = new ClienteMapperImpl();

    // O response precisa devolver todos os campos que o request aceita.
    // Sem isso, o front não consegue preencher o formulário de edição
    // e o PUT grava null por cima dos campos que não vieram.
    @Test
    public void clienteToClienteResponseDeveDevolverTodosOsCampos() {
        Cliente cliente = new Cliente(1L, "Loja de Calcados Silva LTDA", "Silva Sports", "12345678000199",
                "Rua das Flores, 123", "São Paulo", "SP", "11999999999", "contato@silvasports.com",
                "João Silva", "Maria Silva", "30/60/90");

        ClienteResponse response = mapper.clienteToClienteResponse(cliente);

        assertEquals(1L, response.id());
        assertEquals("Loja de Calcados Silva LTDA", response.razaoSocial());
        assertEquals("Silva Sports", response.nomeFantasia());
        assertEquals("12345678000199", response.cnpj());
        assertEquals("Rua das Flores, 123", response.enderecoCompleto());
        assertEquals("São Paulo", response.cidade());
        assertEquals("SP", response.estado());
        assertEquals("11999999999", response.telefoneGeral());
        assertEquals("contato@silvasports.com", response.emailGeral());
        assertEquals("João Silva", response.contatoDesenvolvimento());
        assertEquals("Maria Silva", response.contatoCompras());
        assertEquals("30/60/90", response.condicoesPagamento());
    }
}
