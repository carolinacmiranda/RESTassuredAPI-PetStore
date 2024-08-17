# PetStore API Testing Project

## Descrição
Este projeto é destinado a testar a API pública no Swagger da PetStore utilizando o RestAssured, uma biblioteca Java para testes de APIs REST. </br>
O objetivo é demonstrar como realizar testes automatizados de endpoints utilizando técnicas e práticas de teste eficientes em um ambiente real de API.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação.
- **RestAssured**: Biblioteca utilizada para realizar chamadas e testes na API REST.
- **JUnit**: Framework de teste para organizar e executar os testes.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.

## Configuração do Ambiente
### Pré-requisitos
- Java JDK 8 ou superior instalado;
- Maven configurado e instalado;
- IDE de sua preferência.

### Instalação
1. Clone o repositório para sua máquina local usando:
   ```bash
   git clone https://github.com/carolinacmiranda/RESTassuredAPI-PetStore.git

2. Navegue até a pasta do projeto e instale as dependências com Maven:
   ```bash
   mvn install

## Estrutura do Projeto
  O projeto está organizado da seguinte maneira:
  
  - src/main/java/com/example: Contém as classes de suporte e configurações.
  - src/test/java/com/example: Contém as classes de teste.
    - BaseTest.java: Configurações iniciais para todos os testes, incluindo a configuração do RestAssured.
    - Constants.java: Constantes usadas nos testes, como URL base e timeout máximo.
    - PetStoreTest.java: Contém os testes específicos para as operações na API PetStore.

## Executando os Testes
  ```bash
   mvn test
