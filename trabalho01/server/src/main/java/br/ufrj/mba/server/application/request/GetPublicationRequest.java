package br.ufrj.mba.server.application.request;

import javax.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"title"})
@XmlRootElement(name = "getPublicationRequest")
public class GetPublicationRequest {

  @XmlElement(required = true)
  protected String title;
}
