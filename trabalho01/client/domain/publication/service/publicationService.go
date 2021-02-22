package service

import (
	"context"
	"encoding/xml"
	"github.com/hooklift/gowsdl/soap"
	"time"
)

var _ time.Time
var _ xml.Name

type AnyType struct {
	InnerXML string `xml:",innerxml"`
}

type AnyURI string

type NCName string

// * Struct que representa o request da publicação
type GetPublicationRequest struct {
	XMLName xml.Name `xml:"http://mba.ufrj.br/find-publication getPublicationRequest"`

	Title string `xml:"title,omitempty" json:"title,omitempty"`
}

// * Struct que representa o response da publicação
type GetPublicationResponse struct {
	XMLName xml.Name `xml:"http://mba.ufrj.br/find-publication getPublicationResponse"`

	Publication []*Publication `xml:"publications,omitempty" json:"publications,omitempty"`
}

// * Struct que representa a publicação
type Publication struct {
	XMLName xml.Name `xml:"http://mba.ufrj.br/find-publication publications"`

	Id int32 `xml:"id,omitempty" json:"id,omitempty"`

	Title string `xml:"title,omitempty" json:"title,omitempty"`

	FirstPage int32 `xml:"firstPage,omitempty" json:"firstPage,omitempty"`

	LastPage int32 `xml:"lastPage,omitempty" json:"lastPage,omitempty"`

	Year int32 `xml:"year,omitempty" json:"year,omitempty"`

	Authors []*Author `xml:"authors,omitempty" json:"authors,omitempty"`
}

// * Struct que representa o autor
type Author struct {
	XMLName xml.Name `xml:"authors"`

	Id int32 `xml:"id,omitempty" json:"id,omitempty"`

	Cpf string `xml:"cpf,omitempty" json:"cpf,omitempty"`

	Name string `xml:"name,omitempty" json:"name,omitempty"`
}

// Funcões que serão utilizadas para através de uma Struct de request, retornar uma Struct de response.
type PublicationsPort interface {
	GetPublication(request *GetPublicationRequest) (*GetPublicationResponse, error)

	GetPublicationContext(ctx context.Context, request *GetPublicationRequest) (*GetPublicationResponse, error)
}

type publicationsPort struct {
	client *soap.Client
}

func NewPublicationsPort(client *soap.Client) PublicationsPort {
	return &publicationsPort{
		client: client,
	}
}

// Metodo principal, responsável por através da biblioteca gowsdl, enviar o request e receber o response.
// Uma vez que o contexto não é definido ele usa o contexto em background padrão, um contexto que nunca é cancelado, e nao tem deadline.
// Para que uma ação sai da aplicação e va aguardar a resposta, é necessário definir um contexto.
func (service *publicationsPort) GetPublicationContext(ctx context.Context, request *GetPublicationRequest) (*GetPublicationResponse, error) {
	response := new(GetPublicationResponse)
	err := service.client.CallContext(ctx, "''", request, response)
	if err != nil {
		return nil, err
	}

	return response, nil
}

func (service *publicationsPort) GetPublication(request *GetPublicationRequest) (*GetPublicationResponse, error) {
	return service.GetPublicationContext(
		context.Background(),
		request,
	)
}
