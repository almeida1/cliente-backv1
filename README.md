### Fatec-Centro Paula Souza
#### Estudo de Caso – _SIGVS_ - Sistema Integrado de Gestão de Vendas e Suprimentos 
Time de desenvolvimento
- Joaquim da Silva Xavier
- Jose Bonifacio
- Isabel Cristina Leopoldina 
> O micros servico 1 (MS1) foi desenvolvido no Sprng Boot, recebe as informações de cliente do front-end, cadastra o cliente na camada de persistencia, em seguida se conecta ao broker de mensagens (RabbitMQ) e envia os dados do cliente para fila. O resultado esperado é: (i) cadastro do cliente realizado no sistema de persistência, (ii) mensagem enviada na fila do sistema de mensagens.

#### Requisitos de configuração do ambiente
1) Configurar variáveis de ambiente no Windows 11:
   - Nome da variavel: SPRING_MAIL_USERNAME      Valor da variavel: e-mail
   - Nome da variavel: SPRING_MAIL_PASSWORD      Valor da variavel: senha de aplicativo
   - Nome da variavel: SPRING_RABBITMQ_ADDRESS   Valor da variavel: URL de conexão

2) Configurar o Message Broker on CloudAMQP - https://www.cloudamqp.com/
    


