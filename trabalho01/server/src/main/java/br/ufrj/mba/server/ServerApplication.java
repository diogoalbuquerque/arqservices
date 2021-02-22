package br.ufrj.mba.server;

import br.ufrj.mba.server.domain.publication.Author;
import br.ufrj.mba.server.domain.publication.Publication;
import br.ufrj.mba.server.domain.publication.repository.PublicationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class);
  }

  @Bean
  public CommandLineRunner loadData(PublicationRepository publicationRepository) {
    return (args) -> {
      Author author1 = new Author("309.334.130-25", "Leonardo Guerreiro Azevedo");

      Publication publication1 = new Publication();
      publication1.setTitle(
          "A Cloud-based Architecture for the Internet of Things Targeting Industrial Engine Remote Monitoring");
      publication1.setFirstPage(1);
      publication1.setLastPage(6);
      publication1.setYear(2015);
      publication1.setAuthors(
          Set.of(
              author1,
              new Author("569.155.290-20", "Ricardo Luis Ohta"),
              new Author("092.210.450-64", "Alécio Binotto"),
              new Author("826.887.160-18", "Ademir Ferreira da Silva"),
              new Author("097.626.840-01", "Marcelo dos Santos")));

      Publication publication2 = new Publication();
      publication2.setTitle(
          "Implementation of aspect-oriented business process models with web services");
      publication2.setFirstPage(1);
      publication2.setLastPage(24);
      publication2.setYear(2020);
      publication2.setAuthors(
          Set.of(
              author1,
              new Author("036.205.670-69", "Hercules Sant Ana da Silva Jose"),
              new Author("840.819.040-73", "Claudia Cappelli"),
              new Author("629.779.490-11", "Flavia Maria Santoro")));

      Publication publication3 = new Publication();
      publication3.setTitle("The case for devops in scientific applications");
      publication3.setFirstPage(1398);
      publication3.setLastPage(1404);
      publication3.setYear(2015);
      publication3.setAuthors(
          Set.of(
              author1,
              new Author("820.768.110-10", "Maximilien De Bayser"),
              new Author("695.133.320-39", "Renato Cerqueira")));

      Publication publication4 = new Publication();
      publication4.setTitle("Analysis of tools for rest contract specification in swagger/openapi");
      publication4.setFirstPage(201);
      publication4.setLastPage(208);
      publication4.setYear(2020);
      publication4.setAuthors(
          Set.of(
              author1,
              new Author("508.186.390-40", "Jéssica Santos"),
              new Author("098.690.030-36", "Elton Soares"),
              new Author("223.289.700-10", "Raphael Thiago"),
              new Author("561.117.970-34", "Viviane Silva Thiago")));

      List<Publication> publicationList = new ArrayList<>();
      publicationList.add(publication1);
      publicationList.add(publication2);
      publicationList.add(publication3);
      publicationList.add(publication4);

      publicationRepository.saveAll(publicationList);
    };
  }
}
