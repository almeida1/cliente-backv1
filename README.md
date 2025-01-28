### Fatec-Centro Paula Souza
#### Estudo de Caso – _SIGVS_ - Sistema Integrado de Gestão de Vendas e Suprimentos
Time de desenvolvimento
- Joaquim da Silva Xavier
- Jose Bonifacio
- Isabel Cristina Leopoldina 
> O micros servico 1 (MS1) foi desenvolvido no Sprng Boot, recebe as informações de cliente do front-end, cadastra o cliente na camada de persistencia, em seguida o ms1 se conecta ao broker de mensagens (RabbitMQ) e envia os dados do cliente para fila.  
O resultado esperado é: (i) cadastro do cliente realizado no sistema de persistência, (ii) mensagem enviada na fila do sistema de mensagens.

#### Requisitos de configuração do ambiente
1) Configurar variáveis de ambiente no Windows 11:
1.	Selecionar Iniciar
2.	Selecionar Configurações
3.	Selecionar Sistema
4.	Selecionar Sobre
5.	Descer até Configurações relacionadas e selecionar Configurações avançadas do sistema
6.	Selecionar Variáveis de Ambiente
7.	Fazer as alterações desejadas
2) Configurar o Message Broker on CloudAMQP neste exemplo será utilizada a default exchange- https://www.cloudamqp.com/
   1. Cadastro
   2. Create new instance - plano free Little Lemur
   3. Nome
   4. Regiao - aceita o default
   5. Obter a URL de conexão - Overview - AMPQ Details
   6. Consultar o RabbitMQ Manager - visualizar a dinamica de publicação e envio das msg
    
3) Configurar o arquivo - application.properties

- spring.mail.username=${SPRING_MAIL_USERNAME}
- spring.mail.password=${SPRING_MAIL_PASSWORD}
- spring.rabbitmq.addresses=${SPRING_RABBITMQ_ADDRESS}   (servico 1 user)
- broker.queue.email.name=default.email (servico 1 e 2)
