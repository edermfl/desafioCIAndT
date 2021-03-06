# Desafio de Desenvolvimento CI&T
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

<h3>Serviço: Listar Rotas</h3>
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

<h3>Serviço: Registrar Rotas</h3>
<b>Descrição: </b>Recebe um XML contendo rotas e registra as rotas na aplicação</br>
<b>Método: </b>PUT</br>
<b>Parametros HEADERS: </b>content-type = text/xml</br>
<b>BODY MESSAGE: </b>XML
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
<h3>Serviço: Registrar Rota</h3>
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
<i><b>Observação: </b>foi criado também HTML, para inclusão manual de rotas, usando o método POST. Disponível em: http://localhost:8080/desafioCIAndT</i>

<h3>Serviço: Melhor Rota</h3>
<b>Descrição: </b>Define qual melhor rota seguir</br>
<b>Método: </b>PUT</br>
<b>Parametros HEADERS: </b>content-type = text/xml</br>
<b>BODY MESSAGE: </b>XML
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
<h3>Serviço: Limpar Rotas</h3>
<b>Descrição: </b>Apaga todas as rotas cadastradas</br>
<b>Método: </b>DELETE</br>
<b>URL: </b>http://localhost:8080/desafioCIAndT/rest/rotas/limpar</br>
<b>Retorno: </b>XML
```
<sucesso/>
```

<h2>Gaps conhecidos</h2>
Existe um problema, vide o cenário abaixo:
```
Dado que você tenha criado a seguinte rota: A B 20
Quando solicitar a melhor rota para: A B 10 1
Então receberei o melhor custo: A B 10
Quando solicitar a melhor rota para: B A 10 1
Então receberei a mensagem: "Não foi encontrado rota para a origem B e destino A informado."
```
