package com.estoliveira.screenmatch;

import com.estoliveira.screenmatch.model.Epsodio;
import com.estoliveira.screenmatch.model.Serie;
import com.estoliveira.screenmatch.model.Temporada;
import com.estoliveira.screenmatch.principal.Menu;
import com.estoliveira.screenmatch.service.ConsumoApi;
import com.estoliveira.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Menu menu = new Menu();
		menu.exibMenu();
	}
//		var mapper = new ObjectMapper();
//		var consumoApi = new ConsumoApi();
//		String serieUrl ="https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c";
//		String epUrl= "https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c&season=1&episode=2";
//
//
//		var jsonResultSerie = consumoApi.obterDados(serieUrl);
//		var jsonResultEp = consumoApi.obterDados(epUrl);
//
//		Serie s =null;
//        try {
//            s = mapper.readValue(jsonResultSerie,Serie.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//		System.out.println("Name: "+s.titulo()+"\nDescription: "+s.descricao()+"\nRating:"+s.avaliacao());
//
//		List<Temporada> todasTemporada = new ArrayList<>();
//
//		for(int i=1;i <= s.temporadas();i++){
//			//System.out.println("valor de int= "+i);
//			String temporadaUrl= "https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c&season="+i;
//			String jsonResultTemporada = consumoApi.obterDados(temporadaUrl);
//			todasTemporada.add(new ConverteDados().obterDados(jsonResultTemporada, Temporada.class));
//		}
//		System.out.println("-------------------------------\nTodas temporadas:\n"+todasTemporada.toString());
//
//		//Epsodio ep = new ConverteDados().obterDados(jsonResultEp,Epsodio.class);
//		//System.out.println("-----------------------\nName: "+ep.titulo()+"\nNumero do EP: "+ep.numero()+"\nRating:"+ep.avaliacao());
//    }
}
