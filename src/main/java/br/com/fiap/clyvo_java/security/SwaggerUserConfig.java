package br.com.fiap.clyvo_java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.clyvo_java.model.Swagger.SwaggerUserModel;
import br.com.fiap.clyvo_java.repository.SwaggerUserRepository;

@Configuration
public class SwaggerUserConfig {
	
	@Autowired
	private SwaggerUserRepository repU;
	
	@Bean
	public UserDetailsService gerarUsuario() throws Exception {
	
		return rm -> {
			
			SwaggerUserModel usuario = repU.findByRm(rm)
								  .orElseThrow(
										  () -> new UsernameNotFoundException("Usuário não foi localizado"));
			
			return User.builder()
					.username(usuario.getRm())
					.password(usuario.getSenha())
					.roles(usuario.getPermissao())
					.build();
		};
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
