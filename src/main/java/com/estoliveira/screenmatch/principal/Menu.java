package com.estoliveira.screenmatch.principal;

import com.estoliveira.screenmatch.model.Epsodio;
import com.estoliveira.screenmatch.model.Serie;
import com.estoliveira.screenmatch.model.Temporada;
import com.estoliveira.screenmatch.service.ConsumoApi;
import com.estoliveira.screenmatch.service.ConverteDados;

import java.util.*;

public class Menu {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibMenu(){
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        Serie dados = conversor.obterDados(json, Serie.class);
        System.out.println(dados);

        List<Temporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dados.temporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            Temporada dadosTemporada = conversor.obterDados(json, Temporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(t -> t.listaEpsodio().stream()
                .filter(e -> !e.avaliacao().equals("N/A"))
                .sorted(Comparator.comparing(Epsodio::avaliacao))
                .limit(5)
                .forEach(e -> System.out.println("Titulo: "+e.titulo()+" Avaliacao: "+e.avaliacao()))
        );
//        List<String> nomes = Arrays.asList("João", "Maria", "Pedro", "Ana");
//
//        nomes.stream()
//                .filter(n-> n.length() ==4)
//                .map(n -> n.replace("a","e"))
//                .forEach(nome -> System.out.println("Olá, " + nome + "!"));
    }
}