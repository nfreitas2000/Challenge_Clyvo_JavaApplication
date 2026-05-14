package br.com.fiap.clyvo_java.model.pet.saude;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.Animal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "T_CLYVO_VACINA_ANIMAL")
@Schema(description = "Entidade que representa vacina aplicada em animal")
public class VacinaAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vacina_animal;

    @Lob
    @Schema(description = "Representa passos para a aplicação e se é necessário um retorno para mais doses")
    private String instrucao;

    @PastOrPresent
    @Schema(description = "Armazena a data em que a receita para a vacina foi gerada")
    private LocalDate dt_receita;

    @ManyToOne
    @JoinColumn(name = "Vacinas_id_vacina")
    @Schema(description = "Indica qual vacina será aplicada")
    private Vacina vacina;

    @ManyToOne
    @JoinColumn(name = "Animal_id_animal")
    @Schema(description = "Indica qual animal receberá a vacina")
    private Animal animal;

    public VacinaAnimal() {}

	public VacinaAnimal(Long id_vacina_animal, String instrucao, LocalDate dt_receita, Vacina vacina, Animal animal) {
		super();
		this.id_vacina_animal = id_vacina_animal;
		this.instrucao = instrucao;
		this.dt_receita = dt_receita;
		this.vacina = vacina;
		this.animal = animal;
	}

	public Long getId_vacina_animal() {
		return id_vacina_animal;
	}

	public void setId_vacina_animal(Long id_vacina_animal) {
		this.id_vacina_animal = id_vacina_animal;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}

	public LocalDate getDt_receita() {
		return dt_receita;
	}

	public void setDt_receita(LocalDate dt_receita) {
		this.dt_receita = dt_receita;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

    
}