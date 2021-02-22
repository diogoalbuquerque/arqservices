package br.ufrj.mba.server.application;

import br.ufrj.mba.server.application.request.GetPublicationRequest;
import br.ufrj.mba.server.application.response.GetPublicationResponse;
import br.ufrj.mba.server.domain.publication.Publication;
import javax.xml.bind.annotation.XmlRegistry;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@XmlRegistry
public class ObjectFactory {

  public GetPublicationRequest createGetPublicationRequest() {
    return new GetPublicationRequest();
  }

  public GetPublicationResponse createGetPublicationResponse() {
    return new GetPublicationResponse();
  }

  public Publication createPublication() {
    return new Publication();
  }
}
