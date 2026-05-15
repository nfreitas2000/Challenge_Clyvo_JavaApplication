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

import br.com.fiap.clyvo_java.dto.pet.saude.DoencaDTO;
import br.com.fiap.clyvo_java.model.pet.saude.Doenca;
import br.com.fiap.clyvo_java.repository.pet.saude.DoencaRepository;
import br.com.fiap.clyvo_java.service.caching.DoencaCachingService;
import br.com.fiap.clyvo_java.service.paginacao.DoencaPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doencas")
public class DoencaController {

    @Autowired
    private DoencaCachingService doencaCachingService;

    @Autowired
    private DoencaPageService doencaPageService;

    @Autowired
    private DoencaRepository doencaRepository;

    @Operation(description = "Realiza a busca de todas as doenças, utilizando caching",
            summary = "Retornar todas as doenças",
            tags = "Retorno de Informações de Doenças")
    @GetMapping("/todos")
    public List<Doenca> listarTodos() {
        return doencaCachingService.findAll();
    }

    @Operation(description = "Realiza a busca de doenças por ID, utilizando caching",
            summary = "Retornar doenças por ID",
            tags = "Retorno de Informações de Doenças")
    @GetMapping("/{id}")
    public Optional<Doenca> buscarPorId(@PathVariable Long id) {
        return doencaCachingService.findById(id);
    }

    @Operation(description = "Realiza a busca de doenças por nome, utilizando caching",
            summary = "Retornar doenças por nome",
            tags = "Retorno de Informações de Doenças")
    @GetMapping("/buscarNome")
    public List<Doenca> buscarPorNome(@RequestParam String nome) {
        return doencaCachingService.retornarDoencasPorNome(nome);
    }

    @Operation(description = "Realiza a busca de doenças paginadas",
            summary = "Retornar doenças paginadas",
            tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public Page<DoencaDTO> listarPaginado(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);
        return doencaPageService.paginar(pr);
    }

    @Operation(description = "Este endpoint tem como objetivo inserir novas doenças",
            summary = "Inserir doença",
            tags = "Doença CRUD")
    @PostMapping("/inserir")
    public Doenca inserirDoenca(@RequestBody @Valid Doenca doenca) {
        doencaRepository.save(doenca);
        doencaCachingService.removerCache();
        return doenca;
    }

    @Operation(description = "Este endpoint realiza a remoção de doenças",
            summary = "Remover doença",
            tags = "Doença CRUD")
    @DeleteMapping("/{id}")
    public Doenca removerDoenca(@PathVariable Long id) {

        Optional<Doenca> op = doencaRepository.findById(id);

        if (op.isPresent()) {
            doencaRepository.delete(op.get());
            doencaCachingService.removerCache();
            return op.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Este endpoint realiza a atualização de doenças",
            summary = "Atualizar doença",
            tags = "Doença CRUD")
    @PutMapping("/{id}")
    public Doenca atualizarDoenca(@PathVariable Long id,
                                  @RequestBody @Valid Doenca doenca) {

        Optional<Doenca> op = doencaCachingService.findById(id);

        if (op.isPresent()) {

            Doenca doencaBanco = op.get();
            doencaBanco.transferirDoenca(doenca);

            doencaRepository.save(doencaBanco);
            doencaCachingService.removerCache();

            return doencaBanco;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}