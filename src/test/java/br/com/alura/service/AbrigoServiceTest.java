package br.com.alura.service;

import br.com.alura.configuration.HttpClientConfiguration;
import br.com.alura.model.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {
    // a instancia da classe HttpClientConfiguration é uma simulação. (que obviamente não é o retorno comum).
    private HttpClientConfiguration configuration = mock(HttpClientConfiguration.class);
    // é declarado a classe que será testada aqui (com a informação previamente mockada)
    private AbrigoService abrigoService = new AbrigoService(configuration);
    private PetService petService = new PetService(configuration);
    //como é uma resposta de api externa, para fazer o teste é necessário simular.
    private HttpResponse<String> response = mock(HttpResponse.class);
    // criando uma instancia da entidade para o teste.
    private Abrigo abrigo = new Abrigo("Teste","21983177667","abrigo_teste@gmail.com");

    @Test
    public void mustShowAbrigosList() throws IOException, InterruptedException {
        abrigo.setId(0l);

        // resultados que devem ser esperados de ocorrer neste teste
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdAndName = "0 - Teste";

        // identificar as informações no sistema
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream  = new PrintStream(baos);
        System.setOut(printStream);

        //situacional para retornar as informações manipuladas
        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        when(configuration.fazerRequisicaoGet(anyString())).thenReturn(response);

        //teste do metodo em si
        abrigoService.listarAbrigos();

        // buscar informações no sistema
        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdAndNome = lines[1];

        //faz a 'condicional de testes' para que os casos estejam corretos
        Assertions.assertEquals(expectedAbrigosCadastrados,actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdAndName,actualIdAndNome);
    }
    @Test
    public void mustNotShowAbrigosList() throws IOException, InterruptedException {
        String expectedAbrigosCadastrados = "Não há abrigos cadastrados.";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream  = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(configuration.fazerRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigos();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];

        Assertions.assertEquals(expectedAbrigosCadastrados,actualAbrigosCadastrados);
    }

    @Test
    public void mustVerifyIfDoRequisitionPostIsCalled() throws IOException, InterruptedException {

        String userInput = String.format("Teste%spets.csv",
                System.lineSeparator());

        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(configuration.fazerRequisicaoPost(anyString(), any())).thenReturn(response);

        petService.importarPets();

        verify(configuration.fazerRequisicaoPost(anyString(), anyString()), atLeast(1));
    }
}
