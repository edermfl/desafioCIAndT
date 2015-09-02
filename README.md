# desafioCIAndT
<h2>Ferramentas</h2>
<ul>
<li>A aplicação roda em Tomcat 7.</br></li>
<li>Como havia a necessidade de persistir as rotas criadas, descidir por usar uma banco relaciona.</br></li>
<li>Usei o H2 pela facilidade de embarca-lo a aplicação.</br></li>
<li>O Hibernate foi usado como ORM para facilitar o acesso ao banco.</br></li>
<li>Para Serializar/Deserializar os objetos, usei o XStream, ferramenta que conheço bem e  é bem simples seu uso.</br></li>
<li>Usei o Jersey como implementação da JAX-RS para publicação dos webservice restful.</br></li>
<li>Usei o Eclipse como IDE de desenvolvimento, pelas facilidade de integração e plugins.</br></li>
<li>JUnit para os testes.</br></li>
<li>Google Chrome com Postman - Rest Client para teste dos webservices (não tive tempo hábil para automatizar).</br></li>
</ul>

<h2>Serviços Rest disponíveis</h2>

<b>Serviço: </b>Listar Rotas</br>
<b>Descrição: </b>Lista todas as rotas disponíveis</br>
<b>Método: </b>GET</br>
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas</br>
<b>Retorno: </b>XML
```
  <list>
      <rota>
          <id>147</id>
          <origem>A</origem>
          <destino>B</destino>
          <distancia>10.00</distancia>
          <steps>A B</steps>
      </rota>
  </list>
```
