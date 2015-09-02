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

<h2>Serviços Disponíveis</h2>

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

<b>Serviço: </b>Registrar Rotas</br>
<b>Descrição: </b>Recebe um XML contendo rotas e registra as rotas na aplicação</br>
<b>Método: </b>PUT</br>
<b>Parametros HEADERS: </b>content-type = text/xml</br>
<b>BODY MESSAGE: </b>
```
  <list>
      <rota>
          <origem>A</origem>
          <destino>B</destino>
          <distancia>10.00</distancia>
      </rota>
  </list>
```
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas</br>
<b>Retorno: </b>XML
```
  <retorno>
    <sucesso>Rota [A B]: A - B (10).</sucesso>
  </retorno>
```
<b>Serviço: </b>Registrar Rota</br>
<b>Descrição: </b>Registra uma rota</br>
<b>Método: </b>POST</br>
<b>Parametros form-urlencoded: </b>[origem = X, destino = Z, distancia = 10]</br>
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas</br>
<b>Retorno: </b>XML
```
  <retorno>
    <sucesso>Rota [X Z]: X - Z (10).</sucesso>
  </retorno>
```
<b>Serviço: </b>Melhor Rota</br>
<b>Descrição: </b>Define qual melhor rota seguir</br>
<b>Método: </b>PUT</br>
<b>Parametros HEADERS: </b>content-type = text/xml</br>
<b>BODY MESSAGE: </b>
```
  <melhorRota>
      <origem>A</origem>
      <destino>D</destino>
      <autonomia>10.00</autonomia>
      <valorLitro>2.50</valorLitro>
  </melhorRota>
```
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas/melhorRota</br>
<b>Retorno: </b>XML
```
<melhorCusto>
    <rota>A B D</rota>
    <custo>6.25</custo>
</melhorCusto>
```
<b>Serviço: </b>Limpar Rotas</br>
<b>Descrição: </b>Apaga todas as rotas cadastradas</br>
<b>Método: </b>DELETE</br>
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas/limpar</br>
<b>Retorno: </b>XML
```
<sucesso/>
```
