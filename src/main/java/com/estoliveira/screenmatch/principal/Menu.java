package com.estoliveira.screenmatch.principal;

import com.estoliveira.screenmatch.model.DadosEpisodio;
import com.estoliveira.screenmatch.model.Episodio;
import com.estoliveira.screenmatch.model.Serie;
import com.estoliveira.screenmatch.model.Temporada;
import com.estoliveira.screenmatch.service.ConsumoApi;
import com.estoliveira.screenmatch.service.ConverteDados;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e-> e.titulo().toUpperCase())
                .forEach(System.out::println)
        );
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.listaEpsodio().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).toList();

        episodios.forEach(System.out::println);
//
//        System.out.println("Digite o ep que vc quer");
//        var nomeEp = leitura.nextLine();
//
//        Optional<Episodio> ep = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(nomeEp.toUpperCase()))
//                .findFirst();

//        if(ep.isPresent()){
//            System.out.println("EP buscado encontrado:\n"+ep.get().getTemporada());
//        }else{
//            System.out.println("EP buscado não encontrado!");
//        }

        //ep.ifPresent(epi -> System.out.println("EP buscado encontrado:\n"+epi.getTemporada()));

        Map<Integer,Double> estatisticaEp = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(estatisticaEp);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Media: "+est.getAverage()+"\nMax: "+est.getMax()+"\nMin:"+est.getMin());

        //Infinite Stream
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .map(n -> n * 2)
                .forEach(System.out::println);

        //flatMap
        List<List<String>> list = List.of(
                List.of("a", "b"),
                List.of("c", "d")
        );

        Stream<String> stream = list.stream()
                .flatMap(Collection::stream);

        stream.forEach(System.out::println);

        //Reduce
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Optional<Integer> result = numbers.stream().reduce(Integer::sum);
        result.ifPresent(System.out::println); //prints 15


//        System.out.println("Digita o ano da seria que deseja:");
//        int ano = leitura.nextInt();
//
//        LocalDate localDate2 = LocalDate.of(ano,1,1);
//        episodios.stream()
//                .filter(e ->e.getDataLancamento() != null && e.getDataLancamento().isAfter(localDate2))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
//                ));


//        List<String> nomes = Arrays.asList("João", "Maria", "Pedro", "Ana");
//
//        nomes.stream()
//                .filter(n-> n.length() ==4)
//                .map(n -> n.replace("a","e"))
//                .forEach(nome -> System.out.println("Olá, " + nome + "!"));
    }
}