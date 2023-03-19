package br.api.v1.addressservice.client;

import br.api.v1.addressservice.model.dto.CepResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

@Client("https://viacep.com.br/ws")
public interface ViaCepClient {

    @Get("/{cep}/json")
    CepResponse searchCep(@PathVariable String cep);

}
