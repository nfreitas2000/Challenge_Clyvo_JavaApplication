package br.com.fiap.clyvo_java.model.pet.saude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_CLYVO_VACINA")
@Schema(description = "Entidade que representa uma vacina")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vacina;

    @Column(name = "nm_vacina")
    @Size(max = 50)
    @Schema(description = "Este atributo armazena o nome da vacina")
    private String nm_vacina;

    @Size(max = 50)
    @Schema(description = "Representa o tipo da vacina")
    private String tipo;

    @Lob
    @Schema(description = "Apresenta uma breve descrição da vacina")
    private String descricao;


    public Vacina() {}

	public Vacina(Long id_vacina, @Size(max = 50) String nm_vacina, @Size(max = 50) String tipo, String descricao) {
		super();
		this.id_vacina = id_vacina;
		this.nm_vacina = nm_vacina;
		this.tipo = tipo;
		this.descricao = descricao;
	}
	
	public void transferirVacina(Vacina vacina) {
		this.nm_vacina = vacina.getNm_vacina();
		this.tipo = vacina.getTipo();
		this.descricao = vacina.getDescricao();
	}

	public Long getId_vacina() {
		return id_vacina;
	}

	public void setId_vacina(Long id_vacina) {
		this.id_vacina = id_vacina;
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
}