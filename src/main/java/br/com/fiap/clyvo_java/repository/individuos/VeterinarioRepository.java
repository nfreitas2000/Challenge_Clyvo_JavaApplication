package br.com.fiap.clyvo_java.repository.individuos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.clyvo_java.model.individuos.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

}
