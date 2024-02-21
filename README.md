

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

Meu Controle Financeiro é uma [skill](https://www.amazon.com.br/gp/help/customer/display.html?nodeId=GG3RZLAA3RH83JAA) da Alexa que possibilita o gerenciamento de finanças por meio de simples comandos de voz. De maneira rápida e conveniente o usuário pode definir um orçamento para o mês e adicionar gastos realizados no dia-a-dia podendo classifica-los dentro destas categorias que o próprio usuário criará. Uma vez definido seu orçamento é possível resgatar o quanto resta para o limite do orçamento e também pode ser alertado quando ultrapassar o limite estabelecido. A skill facilita o processo de gereciamento de finanças visto que todas estas informações estão tão próximas quanto um comando de voz tudo graças a tecnologia da Alexa. O repositório em questão se trata do back-end do projeto, este faz a comunicação com o banco de dado, resgata e registra informações de requisições vindas da Alexa.





### Tecnologias Utilizadas

![Spring-Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-4169E1.svg?style=for-the-badge&logo=PostgreSQL&logoColor=white)




## Configuração do ambiente de desenvolvimento


### Pré-requisitos:
#### Instalação do [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (Java SE Development Kit)
#### Instalação do [Intellij](https://www.jetbrains.com/pt-br/idea/download/?section=windows) ou [Eclipse](https://www.eclipse.org/downloads/)
#### Instalação do [PostgreSQL](https://www.postgresql.org/download/)


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

## Requisitos

#### RF 001 - Adicionar transação
- Descrição: Usuário pode adicionar um gasto ou um embolso, informando com o 
que foi gasto e em que categoria esse se encontra
- Prioridade: Alta
- Entrada: Email do usuário, tipo de transação (INCOME, EXPENSE), categoria, título, data e custo.
- Saída: Confirmação do sucesso da adição da transação.
#### RF 002 - Verificar gasto mensal
- Descrição: - Usuário pode solicitar o quanto já foi gasto no mês levando em consideração o dinheiro que entrou e saiu
- Prioridade: Alta
- Entrada: Email do usuário e data atual
- Saída: Retorna o email do usuário e o total gasto no mês
#### RF 003 - Definir orçamento
- Descrição: Usuário pode definir o orçamento do mês e ser alertado quando esse orçamento for ultrapassado
- Prioridade: Alta
- Entrada: Usuário e orçamento
- Saída: Não possue saída
#### RF 004 - Criar Categoria
- Descrição: Usuário pode criar uma categoria para adicionar transações a essa categoria
- Prioridade: Média
- Entrada: Email do usuário e nome da categoria
- Saída: Não possue saída
#### RF 005 - Definir orçamento para categoria
- Descrição: Usuário pode definir um orçamento para uma categoria especifica
- Prioridade: Média
- Entrada: Email do usuário, nome da categoria e orçamento 
- Saída: Não possue saída
#### RF 006 - Recuperar saldo de uma categoria
- Descrição: Usuário pode resgatar os gastos realizados em uma determinada categoria
- Prioridade: Média
- Entrada: Email do usuário e nome da categoria
- Saída: Retorna o saldo da categoria solicitada
#### RF 007 - Cadastrar usuário
- Descrição: Usuário pode cadastrar seu email que está associado ao dispositivo Alexa
- Prioridade: Baixa
- Entrada: Email do usuário
- Saída: Não possue saída

## To-do
- [x] RF 001
- [x] RF 002
- [ ] RF 003
- [x] RF 004
- [ ] RF 005
- [ ] RF 006
- [x] RF 007
## Autores


| [<img src="https://avatars.githubusercontent.com/mightbehappyy" width="100px;"/><br /><sub><b>Pedro Luiz</b></sub>](https://www.linkedin.com/in/pedroluizdeassis/)<br /> | [<img src="https://avatars0.githubusercontent.com/lucas752" width="100px;"/><br /><sub><b>Lucas Monteiro</b></sub>](https://www.linkedin.com/in/lucas-monteiro321/)<br /> | [<img src="https://avatars0.githubusercontent.com/MateusVasc" width="100px;"/><br /><sub><b>Mateus Vasconcelos</b></sub>](https://github.com/MateusVasc)<br /> | [<img src="https://avatars0.githubusercontent.com/malbuq" width="100px;"/><br /><sub><b>Michael Albuquerque</b></sub>](https://github.com/Malbuq)<br /> |
|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------:|

