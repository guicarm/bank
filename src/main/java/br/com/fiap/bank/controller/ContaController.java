package br.com.fiap.bank.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("conta")
@Slf4j
public class ContaController {
    
    @Autowired 
    ContaRepository repository;
   
    // Cadastrar conta
    @PostMapping
    @ResponseStatus(CREATED)
    public Conta create(@RequestBody Conta conta){
        log.info("Conta cadastrada {}", conta);
        return repository.save(conta);
    }

    // Retorna todas as contas cadastradas
    @GetMapping
    public List<Conta> index(){
        return repository.findAll();
    }


    // Retorna a conta cadastrada de acordo com seu id
    @GetMapping("{numero}")
    public ResponseEntity<Conta> show(@PathVariable Long numero){
        log.info("Buscando conta com numero {}", numero);
 
            return repository
                            .findById(numero)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }



    // Retorna a conta cadastrada de acordo com seu cpf
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> show(@PathVariable String cpf){
        log.info("Buscando conta com cpf {}", cpf);
 
            return repository
                            .findByCpf(cpf)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());                  
    }                       
     
    

   // Recebe o id (numero) de uma conta e encerra ela
    @PutMapping("{numero}")
    public Conta update(@PathVariable Long numero){
        log.info("Encerrando a conta {}", numero);
 
        var conta = verificarSeContaExiste(numero);
        conta.setAtiva(false);
        return repository.save(conta);
 
    }

    private Conta verificarSeContaExiste(Long numero) {
       return repository
                .findById(numero)
                .orElseThrow(() -> new ResponseStatusException(
                                            NOT_FOUND,
                                            "Não existe conta com o número informado.")
                            );
    }                     
}
