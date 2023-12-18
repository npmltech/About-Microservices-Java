# About-Microservices-Java

## 📋 Introdução

Projeto de Microsserviços com Java Spring Boot.

Trabalho da pós-graduação do Instituto INFNET - Matéria de Microsserviços.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Ter instalada a versão do Java JDK 17 ou superior;
* Ter instalado o Apache Maven 3.x.x;
* Utilizar uma IDE para Java - Eclipse, IntelliJ ou Visual Studio Code;
* Possuir o Docker + Docker Compose devidamente instalados em sua máquina.

## 🚀 Realizando o Setup

- O Projeto se encontra no diretório: **About-Microservices-Java-Web**

1. Clone o repositório;

2. Inicie a base de dados em container encontrado na pasta *docker-compose.yml*;

3. Execute o comando:
```bash
mvn clean && mvn spring-boot:run
```

4. O serviço roda na porta: 8088.

## ⚙️ Das APIs:

GET: http://localhost:8088/ - Retorna a lista de alunos

GET_BY_ID: http://localhost:8088/{id} - Retorna dados de um aluno usando Id na pesquisa

POST: http://localhost:8088/ - Inclui um aluno
*Request Body:*
```json
{
    "nomeAluno": string,
    "numMatricula": long,
    "numSalaAula": long,
    "ativo": boolean
}
```
PUT: http://localhost:8088/{id} - Altera os dados de um aluno por Id

DELETE: http://localhost:8089/{id} - Exclui um aluno por Id
