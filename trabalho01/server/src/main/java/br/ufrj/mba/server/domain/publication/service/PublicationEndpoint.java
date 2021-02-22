package br.ufrj.mba.server.domain.publication.service;

import br.ufrj.mba.server.application.request.GetPublicationRequest;
import br.ufrj.mba.server.application.response.GetPublicationResponse;
import br.ufrj.mba.server.domain.publication.repository.PublicationRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PublicationEndpoint {
  private static final String NAMESPACE_URI = "http://mba.ufrj.br/find-publication";
  private final PublicationRepository publicationRepository;

  /**
   * Endpoint que recebe o request, procura na base de dados e efetua a resposta.
   *
   * @param request Objeto que contém o envelope soap com a propriedade title.
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPublicationRequest")
  @ResponsePayload
  public GetPublicationResponse getPublication(@RequestPayload GetPublicationRequest request) {
    GetPublicationResponse response = new GetPublicationResponse();

    if (Objects.nonNull(request.getTitle()) && request.getTitle().trim().length() > 0) {
      response.setPublications(
          publicationRepository.findAllByTitleContainsIgnoreCase(request.getTitle()));
    }

    return response;
  }
}
