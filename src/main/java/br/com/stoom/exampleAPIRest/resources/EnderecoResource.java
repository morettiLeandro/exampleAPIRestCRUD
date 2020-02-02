package br.com.stoom.exampleAPIRest.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import br.com.stoom.exampleAPIRest.repository.EnderecoRepository;
import br.com.stoom.exampleAPIRest.models.Endereco;
@RestController
@RequestMapping(value="/api")
public class EnderecoResource {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@GetMapping("/enderecos")
	public List<Endereco> listaEnderecos(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/enderecos/{id}")
	public Endereco getEndereco(@PathVariable(value="id") long id){
		return enderecoRepository.findById(id);
	}
	
	@PostMapping("/enderecos")
	public Endereco saveEndereco(@RequestBody Endereco endereco){
		//TODO: verificar campos nulos
		
		final String apiKey = "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String url = endereco.getStreetName()
				+ ","+endereco.getNumber()
				+ ","+endereco.getZipcode()
				+ ","+endereco.getCity()
				+ ","+endereco.getState()
				+ ","+endereco.getCountry();
		
	    try {
	    	System.out.println("########## - "+"https://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(url,"UTF-8")+ ",+CA&key="+apiKey);
	    	HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(url,"UTF-8")+ ",+CA&key="+apiKey);
	    	
	    	ResponseHandler<String> handler = new BasicResponseHandler();
	    	
			HttpResponse response = httpclient.execute(request);
			String body = handler.handleResponse(response);
			
			JSONObject json = new JSONObject(body);
			JSONObject location = new JSONObject(json.getJSONArray("results").get(0).toString())
					.getJSONObject("geometry").getJSONObject("location");
			
			endereco.setLatitude(location.getBigDecimal("lat").toString());
			endereco.setLongitude(location.getBigDecimal("lng").toString());
			
		} catch (ClientProtocolException e) {
			System.out.println("Error 1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error 2");
			e.printStackTrace();
		}
		
		return enderecoRepository.save(endereco);
	}
	
	@PutMapping("/enderecos")
	public Endereco updateEndereco(@RequestBody Endereco endereco){
		//TODO: verificar campos nulos, inclusive id
		
		return enderecoRepository.save(endereco);
	}
	
	@DeleteMapping("/enderecos/{id}")
	public void deleteEndereco(@PathVariable(value="id") long id){
		enderecoRepository.deleteById(id);
	}
}
