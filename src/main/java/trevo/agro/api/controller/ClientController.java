package trevo.agro.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trevo.agro.api.client.DadosCadastrosClient;

@RestController
@RequestMapping ("/client")
public class ClientController {
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastrosClient dados) {
        System.out.println("Dados recebidos: " + dados);
    }

}
