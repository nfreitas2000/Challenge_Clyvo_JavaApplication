package br.com.fiap.clyvo_java.control.pet.saude;

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

import br.com.fiap.clyvo_java.dto.pet.saude.DoencaAnimalDTO;
import br.com.fiap.clyvo_java.model.pet.saude.DoencaAnimal;
import br.com.fiap.clyvo_java.repository.pet.saude.DoencaAnimalRepository;
import br.com.fiap.clyvo_java.service.caching.DoencaAnimalCachingService;
import br.com.fiap.clyvo_java.service.paginacao.DoencaAnimalPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doencasAnimais")
public class DoencaAnimalController {

    @Autowired
    private DoencaAnimalCachingService doencaAnimalCachingService;

    @Autowired
    private DoencaAnimalPageService doencaAnimalPageService;

    @Autowired
    private DoencaAnimalRepository doencaAnimalRepository;

    @Operation(
        description = "Realiza a busca de todas as doenças dos animais",
        summary = "Retornar doenças dos animais",
        tags = "Retorno de Informações de Doenças e Animais"
    )
    @GetMapping("/todos")
    public List<DoencaAnimal> listarTodos() {
        return doencaAnimalCachingService.findAll();
    }

    @Operation(
        description = "Realiza a busca de doenças dos animais por ID",
        summary = "Retornar doença animal por ID",
        tags = "Retorno de Informações de Doenças e Animais"
    )
    @GetMapping("/{id}")
    public Optional<DoencaAnimal> buscarPorId(@PathVariable Long id) {
        return doencaAnimalCachingService.findById(id);
    }


    @Operation(
        description = "Realiza a busca paginada de doenças dos animais",
        summary = "Retornar doenças paginadas",
        tags = "Retorno de Informações por Paginação"
    )
    @GetMapping("/paginado")
    public Page<DoencaAnimalDTO> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size);
        return doencaAnimalPageService.paginar(pr);
    }

    /* POST, PUT E DELETE */

    @Operation(
        description = "Este endpoint insere novas doenças dos animais",
        summary = "Inserir doença animal",
        tags = "Doenca e Animal CRUD"
    )
    @PostMapping("/inserir")
    public DoencaAnimal inserirDoencaAnimal(@RequestBody @Valid DoencaAnimal doencaAnimal) {
        doencaAnimalRepository.save(doencaAnimal);
        doencaAnimalCachingService.removerCache();
        return doencaAnimal;
    }

    @Operation(
        description = "Este endpoint remove doenças dos animais",
        summary = "Remover doença animal",
        tags = "Doenca e Animal CRUD"
    )
    @DeleteMapping("/{id}")
    public DoencaAnimal removerDoencaAnimal(@PathVariable Long id) {
    	
        Optional<DoencaAnimal> op =doencaAnimalRepository.findById(id);

        if (op.isPresent()) {

            doencaAnimalRepository.delete(op.get());
            doencaAnimalCachingService.removerCache();
            return op.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
        description = "Este endpoint atualiza doenças dos animais",
        summary = "Atualizar doença animal",
        tags = "Doenca e Animal CRUD"
    )
    @PutMapping("/{id}")
    public DoencaAnimal atualizarDoencaAnimal(@PathVariable Long id, @RequestBody @Valid DoencaAnimal doencaAnimal) {

        Optional<DoencaAnimal> op =
                doencaAnimalCachingService.findById(id);

        if (op.isPresent()) {
            DoencaAnimal doencaAnimalBanco = op.get();
            doencaAnimalBanco.transferirDoencaAnimal(doencaAnimal);
            doencaAnimalRepository.save(doencaAnimalBanco);
            doencaAnimalCachingService.removerCache();
            return doencaAnimalBanco;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}