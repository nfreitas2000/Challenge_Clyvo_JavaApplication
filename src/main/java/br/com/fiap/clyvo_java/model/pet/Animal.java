package br.com.fiap.clyvo_java.model.pet;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.individuos.Responsavel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_CLYVO_ANIMAL")
@Schema(description = "Entidade que representa um animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_animal;

    @Column(name = "nm_animal")
    @Size(max = 50)
    @Schema(description = "Este atributo representa o nome do animal")
    private String nm_animal;

    @Schema(description = "Este atributo representa a idade do animal")
    private Integer idade;

    @Size(max = 50)
    @Schema(description = "Armazena a especie do animal")
    private String especie;

    @Size(max = 50)
    @Schema(description = "Armazena a raça do animal")
    private String raca;

    @Size(max = 1)
    @Schema(description = "Representa o sexo do animal")
    private String sexo;

    @PastOrPresent
    @Schema(description = "Armazena a data de nascimento do animal")
    private LocalDate dt_nascimento;

    @Schema(description = "Armazena o peso atual do animal")
    private Double peso;

    @ManyToOne
    @JoinColumn(name = "Tutor_id_tutor")
    @Schema(description = "Este atributo representa quando esse historico foi criado")
    private Responsavel responsavel;

    public Animal() {}

	public Animal(Long id_animal, @Size(max = 50) String nm_animal, Integer idade, @Size(max = 50) String especie,
			@Size(max = 50) String raca, @Size(max = 1) String sexo, LocalDate dt_nascimento, Double peso,
			Responsavel responsavel) {
		super();
		this.id_animal = id_animal;
		this.nm_animal = nm_animal;
		this.idade = idade;
		this.especie = especie;
		this.raca = raca;
		this.sexo = sexo;
		this.dt_nascimento = dt_nascimento;
		this.peso = peso;
		this.responsavel = responsavel;
	}
	
	public void transferirAnimal(Animal animal) {
		this.nm_animal = animal.getNm_animal();
		this.idade = animal.getIdade();
		this.especie = animal.getEspecie();
		this.raca = animal.getRaca();
		this.sexo = animal.getSexo();
		this.dt_nascimento = animal.getDt_nascimento();
		this.peso = animal.getPeso();
		this.responsavel = animal.getResponsavel();
	}

	public Long getId_animal() {
		return id_animal;
	}

	public void setId_animal(Long id_animal) {
		this.id_animal = id_animal;
	}

	public String getNm_animal() {
		return nm_animal;
	}

	public void setNm_animal(String nm_animal) {
		this.nm_animal = nm_animal;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

    
    
    
}
