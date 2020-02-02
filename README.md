# exampleAPIRestCRUD
Desafio Stoom

Pré Requisitos:
- Banco de dados:
  - O banco de dados utilizado, foi o postgreSQL, é necessario criar os schemas db-exampleAPIRest e db-exampleAPIRest-test(os testes sao executados nesse outro schema)
  - Os usuarios utilizados, foram user:db123 (schema db-exampleAPIRest) e user-test:db123(schema db-exampleAPIRest-test)
 OBS: Mas isso pode ser configurado atraves dos arquivos "application.properties" "application-test.properties"
 
 Funcionamento:
 mvn install
 
 APIs:
  - GET: http://localhost:8080/api/enderecos
    retorna um JSON array com todos os endereços salvos no banco
  
  - GET: http://localhost:8080/api/enderecos/{id}
    retorna o JSON contendo com o endereço corresponde ao id da URL 
    
  - POST: http://localhost:8080/api/enderecos
    necessario enviar um json dentro do body
    salva endereço no banco de dados
 
 - PUT: http://localhost:8080/api/enderecos
    necessario enviar um json dentro do body
    atualiza o endereço enviado no body
  
  - DELETE: http://localhost:8080/api/enderecos/{id}
    deleta o registro do banco com o id enviado na url
