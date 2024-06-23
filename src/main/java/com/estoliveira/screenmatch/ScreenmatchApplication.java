package com.estoliveira.screenmatch;

import com.estoliveira.screenmatch.model.Serie;
import com.estoliveira.screenmatch.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var mapper = new ObjectMapper();
		var consumoApi = new ConsumoApi();
		var jsonResult = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		//System.out.println(jsonResult);
		Serie s =null;
        try {
            s = mapper.readValue(jsonResult,Serie.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
		System.out.println("Name: "+s.titulo()+"\nDescription: "+s.descricao()+"\nRating:"+s.avaliacao());
    }
}
