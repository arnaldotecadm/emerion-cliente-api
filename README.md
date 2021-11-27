##Para rodar Localmente o Projeto
para rodar localmente o projeto, deve ser alterado o arquivo de propriedades com as informações conexão ou podem ser passadas atraves de VM options.
Ex:
-Dspring.datasource.url=jdbc:firebirdsql://seuIp:suaPorta/caminhoParaSeuBanco.fdb
-Dspring.datasource.driverClassName=org.firebirdsql.jdbc.FBDriver
-Dspring.datasource.username=seuUsuario
-Dspring.datasource.password=suaSenha
-Dspring.jpa.database-platform=org.hibernate.dialect.FirebirdDialect
