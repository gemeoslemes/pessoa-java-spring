package br.com.api.pessoa.services;

import br.com.api.pessoa.model.Pessoa;
import br.com.api.pessoa.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return repository.findById(id);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa) {
        var entity = repository.findById(pessoa.getId()).orElseThrow(() ->
                new EntityNotFoundException());

        entity.setNome(pessoa.getNome());
        entity.setCpf(pessoa.getCpf());
        entity.setSobreNome(pessoa.getSobreNome());
        entity.setEndereco(pessoa.getEndereco());

        return repository.save(entity);
    }

    public void delete(Pessoa pessoa) {
        var entity = repository.findById(pessoa.getId()).orElseThrow(
                () -> new EntityNotFoundException());
        repository.delete(entity);
    }
}
