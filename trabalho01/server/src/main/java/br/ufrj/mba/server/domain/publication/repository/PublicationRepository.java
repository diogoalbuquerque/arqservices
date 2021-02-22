package br.ufrj.mba.server.domain.publication.repository;

import br.ufrj.mba.server.domain.publication.Publication;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
  /**
   * Procurar as publicações que contenham a palavra no título ignorando o case.
   *
   * @param title Título da publicação que deseja encontrar.
   */
  List<Publication> findAllByTitleContainsIgnoreCase(String title);
}
