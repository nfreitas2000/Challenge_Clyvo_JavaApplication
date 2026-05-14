package br.com.fiap.clyvo_java.repository.pet.saude;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.clyvo_java.model.pet.saude.Doenca;

public interface DoencaRepository extends JpaRepository<Doenca, Long>  {

}
