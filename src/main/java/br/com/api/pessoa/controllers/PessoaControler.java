package br.com.api.pessoa.controllers;

import br.com.api.pessoa.model.Pessoa;
import br.com.api.pessoa.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaControler {

    @Autowired
    private PessoaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> listAllPerson() {
        List<Pessoa> pessoaList = service.findAll();
        return ResponseEntity.ok(pessoaList);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findById(@PathVariable("id") Long id) {
        Optional<Pessoa> optionalPessoa = service.findById(id);
        if (optionalPessoa.isPresent()) {
            return ResponseEntity.ok(optionalPessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = service.createPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa) {
        Pessoa update = service.update(pessoa);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Pessoa id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
