package br.com.fiap.clyvo_java.control.individuos;

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

import br.com.fiap.clyvo_java.dto.individuos.VeterinarioDTO;
import br.com.fiap.clyvo_java.model.individuos.Veterinario;
import br.com.fiap.clyvo_java.repository.individuos.VeterinarioRepository;
import br.com.fiap.clyvo_java.service.caching.VeterinarioCachingService;
import br.com.fiap.clyvo_java.service.paginacao.VeterinarioPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioCachingService veterinarioCachingService;

    @Autowired
    private VeterinarioPageService veterinarioPageService;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Operation(description = "Realiza a busca de todos os veterinários, utilizando caching",
            summary = "Retornar todos os veterinários",
            tags = "Retorno de Informações de Veterinários")
    @GetMapping("/todos")
    public List<Veterinario> listarTodos() {
        return veterinarioCachingService.findAll();
    }

    @Operation(description = "Realiza a busca de veterinários por ID, utilizando caching",
            summary = "Retornar veterinários por ID",
            tags = "Retorno de Informações de Veterinários")
    @GetMapping("/{id}")
    public Optional<Veterinario> buscarPorId(@PathVariable Long id) {
        return veterinarioCachingService.findById(id);
    }

    @Operation(description = "Realiza a busca de veterinários por nome, utilizando caching",
            summary = "Retornar veterinários por nome",
            tags = "Retorno de Informações de Veterinários")
    @GetMapping("/buscarNome")
    public List<Veterinario> buscarPorNome(@RequestParam String nome) {
        return veterinarioCachingService.retornarVeterinariosPorNome(nome);
    }

    @Operation(description = "Realiza a busca de veterinários paginados",
            summary = "Retornar veterinários paginados",
            tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public Page<VeterinarioDTO> listarPaginado(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);
        return veterinarioPageService.paginar(pr);
    }

    @Operation(description = "Este endpoint tem como objetivo inserir novos veterinários",
            summary = "Inserir veterinário",
            tags = "Veterinário CRUD")
    @PostMapping("/inserir")
    public Veterinario inserirVeterinario(@RequestBody @Valid Veterinario veterinario) {
        veterinarioRepository.save(veterinario);
        veterinarioCachingService.removerCache();
        return veterinario;
    }

    @Operation(description = "Este endpoint realiza a remoção de veterinários",
            summary = "Remover veterinário",
            tags = "Veterinário CRUD")
    @DeleteMapping("/{id}")
    public Veterinario removerVeterinario(@PathVariable Long id) {

        Optional<Veterinario> op = veterinarioRepository.findById(id);

        if (op.isPresent()) {
            veterinarioRepository.delete(op.get());
            veterinarioCachingService.removerCache();
            return op.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Este endpoint realiza a atualização de veterinários",
            summary = "Atualizar veterinário",
            tags = "Veterinário CRUD")
    @PutMapping("/{id}")
    public Veterinario atualizarVeterinario(@PathVariable Long id, @RequestBody @Valid Veterinario veterinario) {

        Optional<Veterinario> op = veterinarioCachingService.findById(id);

        if (op.isPresent()) {

            Veterinario veterinarioBanco = op.get();
            veterinarioBanco.transferirVeterinario(veterinario);

            veterinarioRepository.save(veterinarioBanco);
            veterinarioCachingService.removerCache();

            return veterinarioBanco;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}