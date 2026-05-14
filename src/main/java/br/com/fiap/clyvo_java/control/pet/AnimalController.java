package br.com.fiap.clyvo_java.control.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.clyvo_java.dto.pet.AnimalDTO;
import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.repository.pet.AnimalRepository;
import br.com.fiap.clyvo_java.service.caching.AnimalCachingService;
import br.com.fiap.clyvo_java.service.paginacao.AnimalPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalCachingService animalCachingService;
    
    @Autowired
    private AnimalPageService animalPageService;
    
    @Autowired
    private AnimalRepository animalRepository;


    @Operation(description = "Realiza a busca de todos os animais, utilizando caching",
			summary = "Retornar todos animais",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/todos")
    public List<Animal> listarTodos() {
        return animalCachingService.findAll();
    }

    @Operation(description = "Realiza a busca de animais por ID, utilizando caching",
			summary = "Retornar de animais por ID",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/{id}")
    public Optional<Animal> buscarPorId(@PathVariable Long id) {
        return animalCachingService.findById(id);
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais por nome",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/buscarNome")
    public List<Animal> buscarPorNome(@RequestParam String nome) {
    	return animalCachingService.retornarAnimaisPorNome(nome);
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais páginados",
			tags = "Retorno de Informações de Animais por Paginação")
    @GetMapping("/paginado")
    public Page<AnimalDTO> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    	PageRequest pr = PageRequest.of(page,size);
        return animalPageService.paginar(pr);
    }
    
    /*POST, PUT AND DELETE*/
    
	@Operation(description = "Este endpoint tem como objetivo inserir novos animais",
			summary = "Inserir nova música",
			tags = "Animal CRUD")
	@PostMapping(value = "/inserir")
	public Animal inserirMusica(@RequestBody @Valid Animal animal) {
		animalRepository.save(animal);
		animalCachingService.removerCache();
		return animal;
	}

	@Operation(description = "Este endpoint realiza a remoção de animais do sistema",
			summary = "Remover animais",
			tags = "Animal CRUD")
	@DeleteMapping(value = "/{id}")
	public Animal removerMusica(@PathVariable Long id) {
		Optional<Animal> op = animalRepository.findById(id);

		if (op.isPresent()) {
			animalRepository.delete(op.get());
			animalCachingService.removerCache();
			return op.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(description = "Este endpoint realiza a atualização de animais",
			summary = "Atualizar animais",
			tags = "Animal CRUD")
	@PutMapping(value = "/{id}")
	public Animal atualizarMusica(@PathVariable Long id, @RequestBody @Valid Animal animal) {
		Optional<Animal> op = animalCachingService.findById(id);

		if (op.isPresent()) {
			Animal animalBanco = op.get();
			animalBanco.transferirAnimal(animal);
			animalRepository.save(animalBanco);
			animalCachingService.removerCache();
			return animalBanco;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}