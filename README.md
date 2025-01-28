### Fatec-Centro Paula Souza
#### Estudo de Caso – _SIGVS_ - Sistema Integrado de Gestão de Vendas e Suprimentos
Time de desenvolvimento
- Joaquim da Silva Xavier
- Jose Bonifacio
- Isabel Cristina Leopoldina 
> O micros servico 1 (MS1) foi desenvolvido no Sprng Boot, recebe as informações de cliente do front-end, cadastra o cliente na camada de persistencia, em seguida o ms1 se conecta ao broker de mensagens (RabbitMQ) e envia os dados do cliente para fila.  
O resultado esperado é: (i) cadastro do cliente realizado no sistema de persistência, (ii) mensagem enviada na fila do sistema de mensagens.


1) Configurar variáveis de ambiente no Windows 11:
1.	Selecionar Iniciar
2.	Selecionar Configurações
3.	Selecionar Sistema
4.	Selecionar Sobre
5.	Descer até Configurações relacionadas e selecionar Configurações avançadas do sistema
6.	Selecionar Variáveis de Ambiente
7.	Fazer as alterações desejadas
2) Configurar o Message Broker on CloudAMQP - https://www.cloudamqp.com/
   1. Cadastro
   2. Create new instance - plano free Little Lemur
   3. Nome
   4. Regiao - aceita o default
    
4) 
2) Configurar o arquivo - application.properties
spring.mail.username=${SPRING_MAIL_USERNAME}

spring.mail.password=${SPRING_MAIL_PASSWORD}

