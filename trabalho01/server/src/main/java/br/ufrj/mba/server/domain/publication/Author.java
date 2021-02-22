package br.ufrj.mba.server.domain.publication;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "author",
    propOrder = {"id", "cpf", "name"})
public class Author {

  @Id
  @Column(name = "ID_AUTHOR")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "CPF")
  @XmlElement(required = true)
  private String cpf;

  @Column(name = "NAME")
  @XmlElement(required = true)
  private String name;

  public Author(String cpf, String name) {
    this.cpf = cpf;
    this.name = name;
  }
}
