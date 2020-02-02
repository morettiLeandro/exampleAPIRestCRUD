package br.com.stoom.exampleAPIRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stoom.exampleAPIRest.models.Endereco;
import br.com.stoom.exampleAPIRest.repository.EnderecoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class RepositoryUnitTests {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Test
	public void insert_endereco() {
		Endereco ec = new Endereco();
		ec.setCity("City A");
		ec.setComplement("Complemento");
		ec.setCountry("Brazil");
		ec.setNeighbourhood("vizinhança");
		ec.setNumber( new BigDecimal(510));
		ec.setState("MG");
		ec.setStreetName("Pouso Alegre");
		ec.setZipcode("5510-321");
		ec = enderecoRepository.save(ec);
		assertEquals(1, enderecoRepository.findAll().size());
		assertEquals(ec, enderecoRepository.findById(ec.getId()));
	}
	
	@Test
	public void read_endereco() {
		Endereco ec = new Endereco();
		Endereco ec2 = new Endereco();
		ec.setCity("City A");
		ec.setComplement("Complemento");
		ec.setCountry("Brazil");
		ec.setNeighbourhood("vizinhança");
		ec.setNumber( new BigDecimal(510));
		ec.setState("MG");
		ec.setStreetName("Pouso Alegre");
		ec.setZipcode("5510-321");
		ec = enderecoRepository.save(ec);
		ec2 = enderecoRepository.findById(ec.getId());
		assertEquals(ec, ec2);
	}
	
	@Test
	public void update_endereco() {
		Endereco ec = new Endereco();
		Endereco ec2 = new Endereco();
		ec.setCity("City A");
		ec.setComplement("Complemento");
		ec.setCountry("Brazil");
		ec.setNeighbourhood("vizinhança");
		ec.setNumber( new BigDecimal(510));
		ec.setState("MG");
		ec.setStreetName("Pouso Alegre");
		ec.setZipcode("5510-321");
		ec = enderecoRepository.save(ec);
		ec.setCity("City B");
		ec = enderecoRepository.save(ec);
		
		ec2 = enderecoRepository.findById(ec.getId());
		assertEquals("City B", ec2.getCity());
	}
	
	@Test
	public void delete_endereco() {
		Endereco ec = new Endereco();
		ec.setCity("City A");
		ec.setComplement("Complemento");
		ec.setCountry("Brazil");
		ec.setNeighbourhood("vizinhança");
		ec.setNumber( new BigDecimal(510));
		ec.setState("MG");
		ec.setStreetName("Pouso Alegre");
		ec.setZipcode("5510-321");
		ec = enderecoRepository.save(ec);
		assertEquals(1, enderecoRepository.findAll().size());
		
		enderecoRepository.delete(ec);
		assertEquals(0, enderecoRepository.findAll().size());
	}
	
}
