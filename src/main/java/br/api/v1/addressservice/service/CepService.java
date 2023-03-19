package br.api.v1.addressservice.service;

import br.api.v1.addressservice.client.ViaCepClient;
import br.api.v1.addressservice.model.dto.CepResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CepService {

    private final ViaCepClient viaCepClient;

    @Inject
    public CepService(@Singleton ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public CepResponse searchCep(String cep){
        return this.viaCepClient.searchCep(cep);
    }
}
