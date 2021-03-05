package com.example.mercadolivre.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.mercadolivre.dto.NovaCaracteristicaRequest;
import com.example.mercadolivre.model.Categoria;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.utils.SenhaLimpa;


class FechaCompraControllerTest {

	@DisplayName("Não aceita abater")
	@ParameterizedTest
	@CsvSource({"0","-1","-100"})
	void testPostNovaCompra(int estoque) throws Exception{
		List<NovaCaracteristicaRequest> caracteristicas = List.of(
				new NovaCaracteristicaRequest("Key1", "value1"),
				new NovaCaracteristicaRequest("Key2", "value2"),
				new NovaCaracteristicaRequest("Key3", "value3"));
		
		Categoria categoria = new Categoria("categoria", null);
		Usuario dono = new Usuario("email@email.com.br",new SenhaLimpa("123456"));
		Produto produto = new Produto("nome", new BigDecimal(10), 10, "descricao", categoria, caracteristicas, dono);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> produto.abateEstoque(estoque));
	}
	
	@DisplayName("Verefica estoque")
	@ParameterizedTest
	@CsvSource({"1,1,true","-1,2,false","4,2,true"})
	void testPostNovaCompra2(int estoque, int quantidadePedida, boolean resultadoEsperado) throws Exception{
		List<NovaCaracteristicaRequest> caracteristicas = List.of(
				new NovaCaracteristicaRequest("Key1", "value1"),
				new NovaCaracteristicaRequest("Key2", "value2"),
				new NovaCaracteristicaRequest("Key3", "value3"));
		
		Categoria categoria = new Categoria("categoria", null);
		Usuario dono = new Usuario("email@email.com.br",new SenhaLimpa("123456"));
		Produto produto = new Produto("nome", new BigDecimal(10), estoque, "descricao", categoria, caracteristicas, dono);
		boolean resultado = produto.abateEstoque(quantidadePedida);
		
		Assertions.assertEquals(resultadoEsperado, resultado);
	}

}
