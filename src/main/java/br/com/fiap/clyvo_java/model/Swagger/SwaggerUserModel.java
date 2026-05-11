package br.com.fiap.clyvo_java.model.swagger;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_CLYVO_SWAGGER_USER")
public class SwaggerUserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fk_pessoa")
	private SwaggerPessoa pessoa;
	private String rm;
	private String senha;
	private String permissao;
	private LocalDate dataCriacao;


	public SwaggerUserModel() {

	}

	public Long getId() {
		return id;
	}

	public SwaggerUserModel(Long id, SwaggerPessoa pessoa, String rm, String senha, String permissao, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.rm = rm;
		this.senha = senha;
		this.permissao = permissao;
		this.dataCriacao = dataCriacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SwaggerPessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(SwaggerPessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}