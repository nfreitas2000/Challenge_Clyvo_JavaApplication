package br.com.fiap.clyvo_java.control.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.clyvo_java.dto.pet.AnimalDTO;
import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.service.AnimalService;
import br.com.fiap.clyvo_java.service.paginacao.AnimalServicePaginado;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService service;
    
    @Autowired
    private AnimalServicePaginado servicePaginacao;

    @Operation(description = "Realiza a busca de todos os animais, utilizando caching",
			summary = "Retornar todos animais",
			tags = "Retorno de Informações de Animais")
    @GetMapping
    public List<Animal> listarTodos() {
        return service.findAll();
    }

    @Operation(description = "Realiza a busca de animais por ID, utilizando caching",
			summary = "Retornar de animais por ID",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/{id}")
    public Optional<Animal> buscarPorId(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais por nome",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/buscarNome")
    public List<Animal> buscarPorNome(@RequestParam String nome) {
    	return service.retornarAnimaisPorNome(nome);
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais por nome",
			tags = "Retorno de Informações de Animais por Paginação")
    @GetMapping("/paginado")
    public Page<AnimalDTO> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    	PageRequest pr = PageRequest.of(page,size);
        return servicePaginacao.paginar(pr);
    }

}