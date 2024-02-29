<h1 align="center">
  <br>
  <a href=""><img src="https://i.imgur.com/faHDgpK.png" alt="MCF" width="600"></a>
  <br>
  <br>
</h1>

<br />
<div align="center">

# Meu Controle Financeiro

  <p align="center">
    Gerenciamento financeiro simples, rápido e inteligente
    <br />
    <br />
    <a href="https://github.com/mightbehappyy/MeuControleFinanceiro-JIPE/issues">Reportar Bug</a>
    ·
    <a href="https://github.com/mightbehappyy/MeuControleFinanceiro-JIPE/issues">Solicitar Funcionalidade</a>
  </p>
</div>

## Sobre o Projeto

Meu Controle Financeiro é uma [skill](https://www.amazon.com.br/gp/help/customer/display.html?nodeId=GG3RZLAA3RH83JAA) da Alexa que possibilita o gerenciamento de finanças por meio de simples comandos de voz. De maneira rápida e conveniente, o usuário pode definir um orçamento para o mês e adicionar gastos realizados no dia-a-dia, podendo classificá-los dentro destas categorias que o próprio usuário criará. Uma vez definido seu orçamento, é possível resgatar o quanto resta para o limite do orçamento e também pode ser alertado quando ultrapassar o limite estabelecido. A skill facilita o processo de gerenciamento de finanças, visto que estas informações estão tão próximas quanto um comando de voz, tudo graças à tecnologia da Alexa. O repositório em questão se trata do back-end do projeto, este faz a comunicação com o banco de dados, resgata e registra informações de requisições vindas da Alexa.

## Tecnologias Utilizadas

![Spring-Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-4169E1.svg?style=for-the-badge&logo=PostgreSQL&logoColor=white)

## Configuração do ambiente de desenvolvimento

<h3>Pré-requisitos: </h3>

**Instalação do [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (Java SE Development Kit)**

**Instalação do [Intellij](https://www.jetbrains.com/pt-br/idea/download/?section=windows) ou [Eclipse](https://www.eclipse.org/downloads/)**

**Instalação do [PostgreSQL](https://www.postgresql.org/download/)**

### Clone o repositório via Git bash:

```
git clone https://github.com/mightbehappyy/MeuControleFinanceiro-JIPE.git
```

### Defina as variáveis de Ambiente

```
DATABASE_PASSWORD
DATABASE_URL
DATABASE_USERNAME
```

## Como usar
**[1. URL da api](#url-da-api-em-produção)**

**[2. Convenções](#convenções)**


**[3. Endpoints-Usuário](#usuário)**

- **[3.1 Criar Usuário](#post-criar-usuário)**
- **[3.2 Deletar Usuário](#delete-endpoint-deletar-usuário)**
- **[3.3 Encontrar usuário por amazonId](#get-encontrar-um-usuário-por-amazonid)**

**[4. Endpoints-Categorias](#categorias)**

- **[4.1 Encontrar categorias por amazonId](#get-encontrar-todas-as-categorias-de-um-usuário)**
- **[4.2 Criar categoria](#post-criar-categoria)**
- **[4.3 Atualizar orçamento de uma categoria](#patch-atualizar-orçamento-de-uma-categoria)**

**[5. Endpoints-Transação](#transação)**

- **[5.1 Criar transação](#post-adicionar-transação)**
- **[5.2 Gasto mensal](#get-encontrar-gasto-mensal)**
- **[5.3 Encontrar transações de um período](#get-encontrar-gastos-em-um-período-específico)**
- **[5.4 Encontrar transações de um período e por um tipo](#get-encontrar-gastos-em-um-período-específico-e-por-tipo-de-gasto-específico)**
- **[5.5 Encontrar gastos no período de um mês de uma categoria especificada](#get-encontrar-gastos-no-período-de-um-mês-de-uma-categoria-especificada)**


Esta secção é dedicada a instrução de como utilizar a API do Meu Controle Financeiro pelo lado do cliente (Alexa).
Os endpoints serão descritos logo abaixo como também o formato de suas entradas.
## URL da API em produção

```
https://meu-controle-financeiro-161352531094.herokuapp.com
```
## Convenções:

>@RequestBody: Indica que naquele exemplo, está sendo usado um JSON no BODY da requisição
>
>
> Exemplo: 
>  ```
>  {
>    "amazonId": "ABCDEF",
>    "senha": "senha123"
>  }
>  ```
 
> @PathVariable: Indica que naquele exemplo, está sendo usada uma variável no PATH da requisição
>
>
> Exemplo:
>  ```
>  person/{id}
>  ```

> @RequestParams: Indica que naquele exemplo, está sendo usada uma variável nos parametros de QUERY 
>
>
> Exemplo:
> ```
> /person?name="Pedro"
> ```
> OBS: No Postman/Insomnia para fazer uma requisição com query params você utilizará o Multipart Form e o 
> preencherá com os
nomes das variáveis e seus valores


## Usuário

### `POST` Criar usuário

> ```
> /api/user
> ```
>
> Exemplo de utilização
>
> _@RequestBody_
>
> ```
> {
>  "amazonId": "ABCDEF"
> }
> ```

### `DELETE` Endpoint Deletar usuário

> ```
> /api/user/{amazonId}
> ```
>
> Exemplo de utilização
>
> _@PathVariable_
>
> ```
> /api/user/"ABCDEF"
> ```

### `GET` Encontrar um usuário por amazonId

> ```
> /api/user/{amazonId}
> ```
>
> Exemplo de utilização
>
> _@PathVariable_
>
> ```
> /api/user/ABCDEF
> ```

## Categorias

### `GET` Encontrar todas as categorias de um usuário

> ```
> /api/category/user/{amazonId}
> ```
>
> Exemplo de utilização
>
> _@PathVariable_
>
> ```
> /api/category/user/ABCDEF
> ```

### `POST` Criar categoria

> ```
> /api/category
> ```
>
> Exemplo de utilização
>
> _@RequestBody_
>
> ```
> {
> 	"name": "categoria3",
> 	"amazonId": "ABCDEF"
> }
> ```

### `PATCH` Atualizar orçamento de uma categoria

> ```
> /api/category
> ```
>
> Exemplo de utilização
>
> _@RequestBody_
>
> ```
> {
> "amazonId": "ABCDE",
> "categoryName": "categoria",
> "newCategoryBudget": 100
> }
> ```
 

## Transação

### `POST` Adicionar transação

> ```
> /api/transaction
> ```
>
> Exemplo de utilização
>
> _@RequestBody_
>
> ```
> {
> "title": "Biscoito super mega hiper caro",
> "cost": 30000.0,
> "date": "2023-02-05T10:36:40-03:00",
> "type": "EXPENSE",
> "category": "categoria",
> "amazonId": "ABCDEF"
> }
> ```

### `GET` Encontrar gasto mensal

> ```
> /api/transaction
> ```
>
> Exemplo de utilização
>
> _@RequestParam_
>
> ```
> currentDate: 2023-02-02T10:36:40-03:00,
> amazonId: "ABCDEF"
> ```

### `GET` Encontrar gastos em um período específico

> ```
> /api/transaction/daterange
> ```
>
> Exemplo de utilização
>
> _@RequestParam_
>
> ```
> dateStart: 2018-09-28T10:36:40-03:00,
> dateEnd: 2020-11-29T10:36:40-03:00,
> amazonId: ABCDEF
> ```

### `GET` Encontrar gastos em um período específico e por tipo de gasto específico

> ```
> /api/transaction/daterange/INCOME
> ```
> @PathVariable
> 
> Caso seja de gastos, ao invés de INCOME, será EXPENSE
>
> Exemplo de utilização
>
> _@RequestParam_
>
> ```
> dateStart: 2018-09-28T10:36:40-03:00,
> dateEnd: 2020-11-29T10:36:40-03:00,
> amazonId: ABCDEF
> ```

### `GET` Encontrar gastos no período de um mês de uma categoria especificada

> ```
> /api/transaction/by-category
> ```
>
> Exemplo de utilização
>
> _@RequestParam_
>
> ``` 
> currentDate: 2018-09-28T10:36:40-03:00,
> amazonId: ABCDEF
> category: categoria
> ```

## Requisitos

**RF 001 - Adicionar transação**

- Descrição: Usuário pode adicionar um gasto ou um embolso, informando com o
  que foi gasto e em que categoria esse se encontra
- Prioridade: Alta
- Entrada: amazonId do usuário, tipo de transação (INCOME, EXPENSE), categoria, título, data e custo.
- Saída: Confirmação do sucesso da adição da transação.

**RF 002 - Verificar gasto mensal**

- Descrição: - Usuário pode solicitar o quanto já foi gasto no mês levando em consideração o dinheiro que entrou e saiu
- Prioridade: Alta
- Entrada: amazonId do usuário e data atual
- Saída: Retorna o amazonId do usuário e o total gasto no mês

**RF 003 - Definir orçamento**

- Descrição: Usuário pode definir o orçamento do mês e ser alertado quando esse orçamento for ultrapassado
- Prioridade: Alta
- Entrada: Usuário e orçamento
- Saída: Não possue saída

**RF 004 - Criar Categoria**

- Descrição: Usuário pode criar uma categoria para adicionar transações a essa categoria
- Prioridade: Média
- Entrada: AmazonId do usuário e nome da categoria
- Saída: Não possue saída

**RF 005 - Definir orçamento para categoria**

- Descrição: Usuário pode definir um orçamento para uma categoria especifica
- Prioridade: Média
- Entrada: AmazonId do usuário, nome da categoria e orçamento
- Saída: Não possue saída

**RF 006 - Recuperar saldo de uma categoria**

- Descrição: Usuário pode resgatar os gastos realizados em uma determinada categoria
- Prioridade: Média
- Entrada: AmazonId do usuário e nome da categoria
- Saída: Retorna o saldo da categoria solicitada

**RF 007 - Cadastrar usuário**

- Descrição: Usuário pode cadastrar seu amazonId que está associado ao dispositivo Alexa
- Prioridade: Baixa
- Entrada: AmazonId do usuário
- Saída: Não possue saída

## To-do

- [x] RF 001
- [x] RF 002
- [x] RF 003
- [x] RF 004
- [x] RF 005
- [x] RF 006
- [x] RF 007

## Autores

| [<img src="https://avatars.githubusercontent.com/mightbehappyy" width="100px;"/><br /><sub><b>Pedro Luiz</b></sub>](https://www.linkedin.com/in/pedroluizdeassis/)<br /> | [<img src="https://avatars0.githubusercontent.com/lucas752" width="100px;"/><br /><sub><b>Lucas Monteiro</b></sub>](https://www.linkedin.com/in/lucas-monteiro321/)<br /> | [<img src="https://avatars0.githubusercontent.com/MateusVasc" width="100px;"/><br /><sub><b>Mateus Vasconcelos</b></sub>](https://github.com/MateusVasc)<br /> | [<img src="https://avatars0.githubusercontent.com/malbuq" width="100px;"/><br /><sub><b>Michael Albuquerque</b></sub>](https://github.com/Malbuq)<br /> |
| :----------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------: |
