package br.com.alura.service.commands;

import br.com.alura.configuration.HttpClientConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ImportarPetsAbrigoCommand implements Command {

    @Override
    public void execute() {
        try{
            HttpClientConfiguration configuration = new HttpClientConfiguration();
            PetService petService = new PetService(configuration);

            petService.importarPets();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
