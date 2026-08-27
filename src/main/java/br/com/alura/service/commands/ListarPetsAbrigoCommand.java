package br.com.alura.service.commands;

import br.com.alura.configuration.HttpClientConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsAbrigoCommand implements Command {

    @Override
    public void execute() {
        try{
            HttpClientConfiguration configuration = new HttpClientConfiguration();
            PetService petService = new PetService(configuration);

            petService.listarPets();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
