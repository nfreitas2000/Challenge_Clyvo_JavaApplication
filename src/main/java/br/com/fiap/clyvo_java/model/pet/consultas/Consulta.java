package br.com.fiap.clyvo_java.model.pet.consultas;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.individuos.Veterinario;
import br.com.fiap.clyvo_java.model.pet.Animal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "T_CLYVO_CONSULTA")
@Schema(description = "Entidade que representa uma consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consulta;

    @PastOrPresent(message = "A data de nascimento do veterinário deve ser uma data passada ou presente")
	@Schema(description = "Este atributo representa a data em que o responsável realizou o agendamento de uma consulta", example = "2000-01-01")
    private LocalDate dt_agendamento;

    @NotNull
	@Schema(description = "Este atributo representa a data em que o uma consulta será realizada", example = "2000-01-01")
    private LocalDate dt_consulta;

    @ManyToOne
    @JoinColumn(name = "Animal_id_animal")
    @Schema(description = "Este atributo representa sobre qual animal a consulta será realizada")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "Veterinario_id_veterinario")
    @Schema(description = "Este atributo representa qual veterinário será responsável pela consulta")
    private Veterinario veterinario;

    public Consulta() {}

	public Consulta(Long id_consulta, LocalDate dt_agendamento, LocalDate dt_consulta, Animal animal,
			Veterinario veterinario) {
		super();
		this.id_consulta = id_consulta;
		this.dt_agendamento = dt_agendamento;
		this.dt_consulta = dt_consulta;
		this.animal = animal;
		this.veterinario = veterinario;
	}

	public Long getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(Long id_consulta) {
		this.id_consulta = id_consulta;
	}

	public LocalDate getDt_agendamento() {
		return dt_agendamento;
	}

	public void setDt_agendamento(LocalDate dt_agendamento) {
		this.dt_agendamento = dt_agendamento;
	}

	public LocalDate getDt_consulta() {
		return dt_consulta;
	}

	public void setDt_consulta(LocalDate dt_consulta) {
		this.dt_consulta = dt_consulta;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

    
}
