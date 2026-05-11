package br.com.fiap.clyvo_java.model.pet.saude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_CLYVO_DOENCA")
@Schema(description = "Entidade que representa uma doença")
public class Doenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_doenca;

    @Column(name = "nm_doenca")
    @Size(max = 50)
    private String nm_doenca;

    @Size(max = 50)
    private String tipo;

    @Size(max = 1)
    private String contagiosidade;

    @Lob
    private String descricao;

    
    
    public Doenca() {}

	public Doenca(Long id_doenca, @Size(max = 50) String nm_doenca, @Size(max = 50) String tipo,
			@Size(max = 1) String contagiosidade, String descricao) {
		super();
		this.id_doenca = id_doenca;
		this.nm_doenca = nm_doenca;
		this.tipo = tipo;
		this.contagiosidade = contagiosidade;
		this.descricao = descricao;
	}

	public Long getId_doenca() {
		return id_doenca;
	}

	public void setId_doenca(Long id_doenca) {
		this.id_doenca = id_doenca;
	}

	public String getNm_doenca() {
		return nm_doenca;
	}

	public void setNm_doenca(String nm_doenca) {
		this.nm_doenca = nm_doenca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContagiosidade() {
		return contagiosidade;
	}

	public void setContagiosidade(String contagiosidade) {
		this.contagiosidade = contagiosidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
