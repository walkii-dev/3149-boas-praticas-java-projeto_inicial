package br.com.alura.service.commands;

import br.com.alura.configuration.HttpClientConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarNovoAbrigoCommand implements Command {

    @Override
    public void execute() {
        try{
            HttpClientConfiguration configuration = new HttpClientConfiguration();
            AbrigoService abrigoService = new AbrigoService(configuration);

            abrigoService.cadastrarAbrigo();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
