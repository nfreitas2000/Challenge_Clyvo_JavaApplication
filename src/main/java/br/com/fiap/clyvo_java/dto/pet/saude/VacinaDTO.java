package br.com.fiap.clyvo_java.dto.pet.saude;

import br.com.fiap.clyvo_java.model.pet.saude.Vacina;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por representar os dados da vacina")
public class VacinaDTO {

    @Schema(description = "Nome da vacina", example = "V10")
    private String nm_vacina;

    @Schema(description = "Tipo da vacina", example = "Canina")
    private String tipo;

    @Schema(description = "Descrição da vacina", example = "Vacina múltipla canina")
    private String descricao;

    @Schema(description = "Quantidade de doses disponíveis", example = "10")
    private Integer qtd_vacina;

    public VacinaDTO() {
    }

    public VacinaDTO(String nm_vacina, String tipo, String descricao, Integer qtd_vacina) {
        this.nm_vacina = nm_vacina;
        this.tipo = tipo;
        this.descricao = descricao;
        this.qtd_vacina = qtd_vacina;
    }

    public VacinaDTO(Vacina vacina) {
        this.nm_vacina = vacina.getNm_vacina();
        this.tipo = vacina.getTipo();
        this.descricao = vacina.getDescricao();
        this.qtd_vacina = vacina.getQtd_vacina();
    }

    public String getNm_vacina() {
        return nm_vacina;
    }

    public void setNm_vacina(String nm_vacina) {
        this.nm_vacina = nm_vacina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtd_vacina() {
        return qtd_vacina;
    }

    public void setQtd_vacina(Integer qtd_vacina) {
        this.qtd_vacina = qtd_vacina;
    }
}