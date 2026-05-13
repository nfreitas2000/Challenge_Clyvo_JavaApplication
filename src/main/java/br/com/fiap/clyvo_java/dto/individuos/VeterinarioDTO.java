package br.com.fiap.clyvo_java.dto.individuos;

public class VeterinarioDTO {
	
	    private String nm_veterinario;
	    private String email;
	    private String num_celular;
	    private String especialidade;

	    public VeterinarioDTO() {}

		public VeterinarioDTO(String nm_veterinario, String email, String num_celular, String especialidade) {
			super();
			this.nm_veterinario = nm_veterinario;
			this.email = email;
			this.num_celular = num_celular;
			this.especialidade = especialidade;
		}
		
		public String getNm_veterinario() {
			return nm_veterinario;
		}

		public void setNm_veterinario(String nm_veterinario) {
			this.nm_veterinario = nm_veterinario;
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

		public String getEspecialidade() {
			return especialidade;
		}

		public void setEspecialidade(String especialidade) {
			this.especialidade = especialidade;
		}

	    
}
