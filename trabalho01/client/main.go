package main

import (
	publicationService "br.ufrj.mba.client/domain/publication/service"
	"fmt"
	"github.com/hooklift/gowsdl/soap"
	"log"
	"os"
)

func main() {
	argsWithoutProg := os.Args[1:]

	// Definindo endereço do serviço
	client := soap.NewClient("http://localhost:8080/ws/publications.wsdl")
	service := publicationService.NewPublicationsPort(client)

	// Iterando sobre os argumentos passados por linha de comando
	for i, title := range argsWithoutProg {
		response, err := service.GetPublication(&publicationService.GetPublicationRequest{
			Title: title,
		})
		if err != nil {
			log.Fatalf("An error has occurred: %v", err)
		}

		if response.Publication == nil {
			fmt.Printf("No publications found for the title: %v\n", title)
		} else {
			fmt.Printf("========== RESULT (%v) FOR (%v) ==========\n", i+1, title)
			printResponse(*response)
			fmt.Println("============================================")
		}
	}

}

// Funcão de impressão do resultado
func printResponse(response publicationService.GetPublicationResponse) {
	for _, publication := range response.Publication {
		fmt.Printf("\nID: \t\t%v\nTITLE: \t\t%v\nFIRST PAGE: \t%v\nLAST PAGE:\t%v\nYEAR: \t\t%v\n", publication.Id, publication.Title, publication.FirstPage, publication.LastPage, publication.Year)
		for _, author := range publication.Authors {
			fmt.Printf("\tID: \t\t%v\n\tCPF: \t\t%v\n\tNAME\t\t%v\n\n", author.Id, author.Cpf, author.Name)
		}
	}
}
