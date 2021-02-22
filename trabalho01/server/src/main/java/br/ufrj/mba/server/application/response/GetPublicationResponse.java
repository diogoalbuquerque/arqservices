package br.ufrj.mba.server.application.response;

import br.ufrj.mba.server.domain.publication.Publication;
import java.util.List;
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
    propOrder = {"publications"})
@XmlRootElement(name = "getPublicationResponse")
public class GetPublicationResponse {

  @XmlElement(required = true)
  protected List<Publication> publications;
}
