# ToolsChallenge

Este projeto é uma API de pagamentos desenvolvida em Java com Spring Boot. A API permite realizar operações de pagamento, estorno e consulta de transações.


## Funcionalidades

- **Realizar Pagamento**: Cria uma nova transação de pagamento.
- **Consultar Transação**: Busca uma transação por ID ou lista todas as transações.
- **Realizar Estorno**: Cancela uma transação de pagamento.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Lombok**: Biblioteca para reduzir boilerplate code.
- **Maven**: Gerenciador de dependências e build.

## Como Executar o Projeto

### Pré-requisitos

- Java 17 instalado.
- Maven instalado.

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/Thiago-Vilela97/ToolsChallenge.git

   
2. Navegue até o diretório do projeto:
    ```bash
   cd ToolsChallenge

3. Compile e execute o projeto com Maven:
    ```bash
   mvn spring-boot:run

4. Acesse a API no endereço:
    ```bash
    http://localhost:8080


### Endpoints da API   

 - POST /transacoes/pagamento: Realiza um pagamento.
    ```json
    {
      "cartao": "123456789",
      "descricao": {
        "valor": 500.50,
        "datahora": "01/05/2021 18:30:00",
        "estabelecimento": "PetShop"
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": 1
      }
    }

- GET /transacoes/{id}: Consulta uma transação por ID.
- GET /transacoes: Lista todas as transações.
- POST /transacoes/estorno/{id}: Realiza o estorno de uma transação.
