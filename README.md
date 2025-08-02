
# Transação API

Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. A API foi desenvolvida com Java e Spring Boot.



## Requisitos do Sistema

Para rodar esta aplicação, você precisa de:

- Java: JDK 21 ou superior.
- Maven: Versão 3.8.1 ou superior.
- Git: Para clonar o repositório.
- Docker (opcional): Caso queira rodar a aplicação em um container.


## Rodando Localmente

Clone o projeto

```bash
  git clone https://github.com/isadorabello/desafio-itau.git
```

Entre no diretório do projeto

```bash
  cd desafio-itau
```

Compile o Projeto

```bash
   mvn clean install
```

Inicie o servidor

```bash
  mvn spring-boot:run
```


## Como Rodar em um Container (Opcional)

Crie a Imagem Docker Certifique-se de que o Docker está instalado e execute:

```bash
  docker build -t desafio-itau
```

Execute o Container

```bash
  docker build -t desafio-itau
```


## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação|
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu|

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervalo`      | `integer` | **Obrigatório**. O intervalo das últimas transações. O padrão default é 60s |


## Rodando os testes

Para rodar os testes, rode o seguinte comando:

```bash
    mvn test
```

