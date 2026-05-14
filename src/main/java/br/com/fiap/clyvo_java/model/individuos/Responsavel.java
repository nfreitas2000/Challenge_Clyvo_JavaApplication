package br.com.fiap.clyvo_java.model.individuos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Schema(description = "Essa entidade tem por objetivo representar a tabela de responsavel no banco de dados relacional")
@Entity
@Table(name = "T_CLYVO_RESPONSAVEL")
public class Responsavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty(message = "O ID do responsavel é um campo obrigatório")
	private Long id_responsavel;
	
	@Column(name = "nm_responsavel")
	@NotEmpty(message = "O nome do responsavel é um campo obrigatório")
	@Size(min = 1, max = 50, message = "O nome do responsavel deve possuir ao menos 1 caracter e, no máximo, 50 caracteres")
	@Schema(description = "Esse atributo representa o nome do responsavel", example = "Rodrigo Alves da Silva")
	private String nm_responsavel;
	
	@CPF
	@Size(max = 11, message = "O CPF do responsavel deve possuir no máximo 11 caracteres")
	@Schema(description = "Este atributo representa o CPF (Cadastro de Pessoa Fisica) do responsavel", example = "11111111111")
	private String cpf;
	
	@NotEmpty(message = "A data de nascimento é um campo obrigatório")
	@Past(message = "A data de nascimento do responsavel deve ser uma data passada")
	@Schema(description = "Este atributo representa a data de nascimento do responsavel", example = "1995-01-01")
	private LocalDate dt_nascimento;
	
	@Email
	@NotEmpty(message = "O email de contato do responsavel é um campo obrigatório")
	@Size(min = 1, max = 50, message = "O e-mail de contato do responsavel deve possuir ao menos 1 caracter e, no máximo, 50 caracteres")
	@Schema(description = "Este atributo representa o email de contato do responsavel", example = "rodrigo@gmail.com")
	private String email;
	
	@NotEmpty(message = "O número de telefone do responsavel deve ser um campo obrigatório")
	@Schema(description = "Este atributo representa o telefone de contato do responsavel", example = "+55 (11) 11111-1111")
	@Size(min = 1, max = 20, message = "O telefone de contato do responsavel deve possuir ao menos 1 caracter e, no máximo, 20 caracteres")
	private String num_celular;
	
	
	public Responsavel() {}
	
	public Responsavel(Long id_responsavel, String nm_responsavel, @CPF String cpf, LocalDate dt_nascimento, String email,
			String num_celular) {
		super();
		this.id_responsavel = id_responsavel;
		this.nm_responsavel = nm_responsavel;
		this.cpf = cpf;
		this.dt_nascimento = dt_nascimento;
		this.email = email;
		this.num_celular = num_celular;
	}
	
	public Long getId_responsavel() {
		return id_responsavel;
	}
	public void setId_responsavel(Long id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
	public String getNm_responsavel() {
		return nm_responsavel;
	}
	public void setNm_responsavel(String nm_responsavel) {
		this.nm_responsavel = nm_responsavel;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNum_celular() {
		return num_celular;
	}
	public void setNum_celular(String num_celular) {
		this.num_celular = num_celular;
	}
}