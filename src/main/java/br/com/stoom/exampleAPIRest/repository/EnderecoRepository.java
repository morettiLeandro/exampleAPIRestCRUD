package br.com.stoom.exampleAPIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stoom.exampleAPIRest.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	Endereco findById(long id);

}
