package br.ufrj.mba.server.domain.publication;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PUBLICATION")
@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "publication",
    propOrder = {"id", "title", "firstPage", "lastPage", "year", "authors"})
public class Publication {

  @Id
  @Column(name = "ID_PUBLICATION")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "TITLE")
  @XmlElement(required = true)
  private String title;

  @Column(name = "FIRST_PAGE")
  @XmlElement(required = true)
  private int firstPage;

  @Column(name = "LAST_PAGE")
  @XmlElement(required = true)
  private int lastPage;

  @Column(name = "YEAR")
  @XmlElement(required = true)
  private int year;

  @XmlElement(required = true)
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "PUBLICATION_AUTHOR",
      joinColumns = {@JoinColumn(name = "ID_PUBLICATION", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "ID_AUTHOR", nullable = false, updatable = false)})
  private Set<Author> authors;
}
