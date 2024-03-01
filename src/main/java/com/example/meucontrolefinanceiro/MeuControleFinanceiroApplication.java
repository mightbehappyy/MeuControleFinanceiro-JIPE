package com.example.meucontrolefinanceiro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Meu controle financeiro API",
		version = "0.1",
		description = "Meu Controle Financeiro é uma skill da Alexa que possibilita o gerenciamento "
				+ "de finanças por meio de simples comandos de voz. De maneira rápida e conveniente"
				+ ", o usuário pode definir um orçamento para o mês e adicionar gastos realizados "
				+ "no dia-a-dia, podendo classificá-los dentro destas categorias que o próprio "
				+ "usuário criará. Uma vez definido seu orçamento, é possível resgatar o quanto "
				+ "resta para o limite do orçamento e também pode ser alertado quando ultrapassar "
				+ "o limite estabelecido. A skill facilita o processo de gerenciamento de finanças, "
				+ "visto que estas informações estão tão próximas quanto um comando de voz, tudo "
				+ "graças à tecnologia da Alexa. Aqui você pode encontrar todos a documentação de "
				+ "todos os endpoints da API"))
public class MeuControleFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuControleFinanceiroApplication.class, args);
	}

}
