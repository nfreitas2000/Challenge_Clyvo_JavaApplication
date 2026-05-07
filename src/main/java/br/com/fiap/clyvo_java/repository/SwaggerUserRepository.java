package br.com.fiap.clyvo_java.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.clyvo_java.model.Swagger.SwaggerUserModel;

public interface SwaggerUserRepository extends JpaRepository<SwaggerUserModel, Long> {
	
	Optional<SwaggerUserModel> findByRm(String rm);

}