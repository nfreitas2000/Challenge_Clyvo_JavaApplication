package br.com.fiap.clyvo_java.control.pet.consultas;

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

import br.com.fiap.clyvo_java.dto.pet.consultas.HistoricoDTO;
import br.com.fiap.clyvo_java.model.pet.consultas.Historico;
import br.com.fiap.clyvo_java.repository.pet.consultas.HistoricoRepository;
import br.com.fiap.clyvo_java.service.caching.HistoricoCachingService;
import br.com.fiap.clyvo_java.service.paginacao.HistoricoPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoCachingService historicoCachingService;

    @Autowired
    private HistoricoPageService historicoPageService;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Operation(
        description = "Realiza a busca de todos os históricos",
        summary = "Retornar todos os históricos",
        tags = "Retorno de Informações de Históricos"
    )
    @GetMapping("/todos")
    public List<Historico> listarTodos() {

        return historicoCachingService.findAll();

    }

    @Operation(
        description = "Realiza a busca de históricos por ID",
        summary = "Retornar histórico por ID",
        tags = "Retorno de Informações de Históricos"
    )
    @GetMapping("/{id}")
    public Optional<Historico> buscarPorId(@PathVariable Long id) {

        return historicoCachingService.findById(id);

    }

    @Operation(
        description = "Realiza a busca paginada de históricos",
        summary = "Retornar históricos paginados",
        tags = "Retorno de Informações por Paginação"
    )
    @GetMapping("/paginado")
    public Page<HistoricoDTO> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);

        return historicoPageService.paginar(pr);
    }

    /* POST, PUT E DELETE */

    @Operation(
        description = "Este endpoint insere novos históricos",
        summary = "Inserir histórico",
        tags = "Historico CRUD"
    )
    @PostMapping("/inserir")
    public Historico inserirHistorico(
            @RequestBody @Valid Historico historico) {

        historicoRepository.save(historico);

        historicoCachingService.removerCache();

        return historico;
    }

    @Operation(
        description = "Este endpoint remove históricos",
        summary = "Remover histórico",
        tags = "Historico CRUD"
    )
    @DeleteMapping("/{id}")
    public Historico removerHistorico(@PathVariable Long id) {

        Optional<Historico> op = historicoRepository.findById(id);

        if (op.isPresent()) {

            historicoRepository.delete(op.get());

            historicoCachingService.removerCache();

            return op.get();

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

    @Operation(
        description = "Este endpoint atualiza históricos",
        summary = "Atualizar histórico",
        tags = "Historico CRUD"
    )
    @PutMapping("/{id}")
    public Historico atualizarHistorico(
            @PathVariable Long id,
            @RequestBody @Valid Historico historico) {

        Optional<Historico> op =
                historicoCachingService.findById(id);

        if (op.isPresent()) {

            Historico historicoBanco = op.get();

            historicoBanco.transferirHistorico(historico);

            historicoRepository.save(historicoBanco);

            historicoCachingService.removerCache();

            return historicoBanco;

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

}