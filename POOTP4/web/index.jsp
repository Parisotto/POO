<%-- 
    Document   : home
    Created on : 23/10/2016, 10:15:29
    Author     : Parisotto
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.parisotto.poo.cadastros.DataBase"%>
<%@page import="net.parisotto.poo.cadastros.Clientes"%>
<%@page import="net.parisotto.poo.cadastros.Fornecedores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Clientes> clientes = DataBase.getClientes();
    ArrayList<Fornecedores> fornecedores = DataBase.getFornecedores();
    Clientes c = new Clientes();
    Fornecedores f = new Fornecedores();
    
    //Teste para verificar se foi solicitado INCLUIR cliente ou fornecedor
    if(request.getParameter("incluircli")!= null){
        c.setNome(request.getParameter("nome"));  
        c.setCpf(request.getParameter("cpf"));
        c.setRg(request.getParameter("rg"));
        c.setEmail(request.getParameter("email"));
        c.setTelefone(request.getParameter("telefone"));
        c.setEndereco(request.getParameter("endereco"));
        if(c.getNome()!="") clientes.add(c);
        DataBase.setAction(1);
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("incluirfor")!= null){
        f.setNome(request.getParameter("nome"));
        f.setRazaosocial(request.getParameter("razaosocial"));
        f.setCnpj(request.getParameter("cnpj"));
        f.setEmail(request.getParameter("email"));
        f.setTelefone(request.getParameter("telefone"));
        f.setEndereco(request.getParameter("endereco"));
        if(f.getNome()!="") fornecedores.add(f);
        DataBase.setAction(2);
        response.sendRedirect(request.getRequestURI());
    }
    
    //Teste para verificar se foi solicitado EXCLUIR cliente ou fornecedor
    if(request.getParameter("excluircli") != null){
        clientes.remove(Integer.parseInt(request.getParameter("i")));
        DataBase.setAction(3);
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("excluirfor") != null){
        fornecedores.remove(Integer.parseInt(request.getParameter("i")));
        DataBase.setAction(4);
        response.sendRedirect(request.getRequestURI());
    }
    
    //Teste para verificar se foi solicitado a ATUALIZAÇÃO de algum campo
    if(request.getParameter("atualizarcli")!=null){
        c = clientes.get(Integer.parseInt(request.getParameter("i")));
        c.setNome(request.getParameter("nome"));  
        c.setCpf(request.getParameter("cpf"));
        c.setRg(request.getParameter("rg"));
        c.setEmail(request.getParameter("email"));
        c.setTelefone(request.getParameter("telefone"));
        c.setEndereco(request.getParameter("endereco"));
        clientes.set(Integer.parseInt(request.getParameter("i")), c);
        //DataBase.setAction(5);
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("atualizarfor")!=null){
        f = fornecedores.get(Integer.parseInt(request.getParameter("i")));
        f.setNome(request.getParameter("nome"));  
        f.setRazaosocial(request.getParameter("razaosocial"));
        f.setCnpj(request.getParameter("cnpj"));
        f.setEmail(request.getParameter("email"));
        f.setTelefone(request.getParameter("telefone"));
        f.setEndereco(request.getParameter("endereco"));
        if(f.getNome()!="") fornecedores.set(Integer.parseInt(request.getParameter("i")), f);
        //DataBase.setAction(6);
        response.sendRedirect(request.getRequestURI());
    }
    
    //Inclui valores de teste caso não haja nenhum
    if(clientes.isEmpty()){
        for(int i=0; i<5; i++){
            c.setNome("Cliente de Teste");  
            c.setCpf("03303303303");
            c.setRg("10100100");
            c.setEmail("email@email.com");
            c.setTelefone("1343434343");
            c.setEndereco("Endereço do Cliente, 44");
            clientes.add(c);
            c = new Clientes();
        }
    }

    if(fornecedores.isEmpty()){
        for(int i=0; i<5; i++){
            f.setNome("Fornecedor de Teste");  
            f.setCnpj("03303303303");
            f.setRazaosocial("Razão Social Fornecedor S.A.");
            f.setEmail("email@email.com");
            f.setTelefone("1343434343");
            f.setEndereco("Endereço do Fornecedor, 88");
            fornecedores.add(f);
            f = new Fornecedores();
        }
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>FATEC PG - ADS4 - POO - TP4</title>
        <meta charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
<!--//
        <script>
           alert("<%= DataBase.getAction() %>");
        </script>
//-->
    </head>
    <body>
        <!--// Cabeçalho fixo da página //-->
        <header>
            <h1><a href="index.jsp"><img alt="logo Cadastro" title="Home" src="img/logo.png"></a></h1>
            <nav>
                <ul>
                    <li><a href="#" id="mnuCli">CLIENTES</a>
                        <ul>
                            <li><a href="#alvoIncluirClientes">Incluir</a></li>
                            <li><a href="#alvoListarClientes">Listar</a></li>
                        </ul>
                    </li>
                    <li><a href="#" id="mnuForn">FORNECEDORES</a>
                        <ul>
                            <li><a href="#alvoIncluirFornecedores">Incluir</a></li>
                            <li><a href="#alvoListarFornecedores">Listar</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>
        <main>
            <span id="alvoListarClientes"></span>
            <span id="alvoListarFornecedores"></span>
            <span id="alvoIncluirClientes"></span>
            <span id="alvoIncluirFornecedores"></span>
            
            <div id="divClientes">
<%
    //Testa se foi solicitado a edição de algum cliente
    if(request.getParameter("editarcli")!= null){
        c = clientes.get(Integer.parseInt(request.getParameter("i")));

%>
            <!--// Formulário de Edição de Cliente //-->
            <section class="atualizavel">
                <form>
                    <fieldset>
                        <legend>Editar Cadastro de Cliente - ID: <%=request.getParameter("i")%></legend>
                        <label>Nome <input name="nome" type="text" value="<%= (c.getNome()!=null)?c.getNome():"" %>"></label>
                        <label>CPF <input name="cpf" type="number" value="<%= (c.getCpf()!=null)?c.getCpf():"" %>"></label>
                        <label>RG <input name="rg" type="number" value="<%= (c.getRg()!=null)?c.getRg():"" %>"></label>
                        <label>E-mail <input name="email" type="email" value="<%= (c.getEmail()!=null)?c.getEmail():"" %>"></label>
                        <label>Telefone <input name="telefone" type="tel" value="<%= (c.getTelefone()!=null)?c.getTelefone():"" %>"></label>
                        <label>Endereço <input name="endereco" type="text" value="<%= (c.getEndereco()!=null)?c.getEndereco():"" %>"></label>
                        <input name="i" type="hidden" value="<%=request.getParameter("i")%>">
                        <input name="atualizarcli" type="submit" title="Atualizar" value="Atualizar">
                    </fieldset>
                </form>
            </section>
<%
    }
%>
            <!--// Tabela que lista todos os clientes cadastrados //-->
            <section class="lista">
                <table>
                    <caption><span>LISTA DE CLIENTES</span></caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOME</th>
                            <th>CPF</th>
                            <th>RG</th>
                            <th>E-MAIL</th>
                            <th>TELEFONE</th>
                            <th>ENDEREÇO</th>
                            <th>OPERAÇÕES</th>
                        </tr>
                    </thead>
                    <tbody>

<% 
    //Inclui uma linha a cada vez na tabela com cada um dos cadastrados
    int totCli = 0;
    for(Clientes cli: clientes){ 
        totCli++;
%>            
                        <tr>
                            <td><%= clientes.indexOf(cli)%></td>
                            <td><%= cli.getNome() %></td>
                            <td><%= cli.getCpf() %></td>
                            <td><%= cli.getRg() %></td>
                            <td><%= cli.getEmail() %></td>
                            <td><%= cli.getTelefone() %></td>
                            <td><%= cli.getEndereco() %></td>
                            <td>
                                <form>
                                    <input type="hidden" name="i" value="<%= clientes.indexOf(cli)%>">
                                    <input type="submit" class="delete" name="excluircli" title="Excluir" value="Excluir">
                                    <input type="submit" class="edite" name="editarcli" title="Editar" value="Editar">
                                </form>
                            </td>
                        </tr>
<%
    }
%>            
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="8"><span><%= totCli %><%= (totCli>1)?" clientes cadastrados ":" cliente cadastrado " %>no sistema</span></th>
                        </tr>
                    </tfoot>
                </table>
            </section>
            </div>
            <div id="divFornecedores">
<%
    //Testa se foi solicitado a edição de algum fornecedor
    if(request.getParameter("editarfor")!= null){
        f = fornecedores.get(Integer.parseInt(request.getParameter("i")));

%>
            <!--// Formulário de Edição de Fornecedor //-->
            <section class="atualizavel">
                <form>
                    <fieldset>
                        <legend>Editar Cadastro de Fornecedor - ID: <%=request.getParameter("i")%></legend>
                        <label>Nome <input name="nome" type="text" value="<%= (f.getNome()!=null)?f.getNome():"" %>"></label>
                        <label>Razão Social <input name="razaosocial" type="text" value="<%= (f.getRazaosocial()!=null)?f.getRazaosocial():"" %>"></label>
                        <label>CNPJ <input name="cnpj" type="number" value="<%= (f.getCnpj()!=null)?f.getCnpj():"" %>"></label>
                        <label>E-mail <input name="email" type="email" value="<%= (f.getEmail()!=null)?f.getEmail():"" %>"></label>
                        <label>Telefone <input name="telefone" type="tel" value="<%= (f.getTelefone()!=null)?f.getTelefone():"" %>"></label>
                        <label>Endereço <input name="endereco" type="text" value="<%= (f.getEndereco()!=null)?f.getEndereco():"" %>"></label>
                        <input name="i" type="hidden" value="<%=request.getParameter("i")%>">
                        <input name="atualizarfor" type="submit" title="Atualizar" value="Atualizar">
                    </fieldset>
                </form>
            </section>
<%
    }
%>
            <!--// Tabela que lista todos os fornecedores cadastrados //-->
            <section class="lista">
                <table>
                    <caption><span>LISTA DE FORNECEDORES</span></caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOME</th>
                            <th>RAZÃO SOCIAL</th>
                            <th>CNPJ</th>
                            <th>E-MAIL</th>
                            <th>TELEFONE</th>
                            <th>ENDEREÇO</th>
                            <th>OPERAÇÕES</th>
                        </tr>
                    </thead>
                    <tbody>
<% 
    //Inclui uma linha a cada vez na tabela com cada um dos cadastrados
    int totForn = 0;
    for(Fornecedores forn: fornecedores){ 
        totForn++;
%>            
                        <tr>
                            <td><%= fornecedores.indexOf(forn)%></td>
                            <td><%= forn.getNome() %></td>
                            <td><%= forn.getRazaosocial() %></td>
                            <td><%= forn.getCnpj() %></td>
                            <td><%= forn.getEmail() %></td>
                            <td><%= forn.getTelefone() %></td>
                            <td><%= forn.getEndereco() %></td>
                            <td>
                                <form>
                                    <input type="hidden" name="i" value="<%= fornecedores.indexOf(forn)%>">
                                    <input type="submit" class="delete" name="excluirfor" title="Excluir" value="Excluir">
                                    <input type="submit" class="edite" name="editarfor" title="Editar" value="Editar">
                                </form>
                            </td>
                        </tr>
<%
    }
%>            
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="8"><span><%= totForn %><%= (totForn>1)?" fornecedores cadastrados ":" fornecedor cadastrado " %>no sistema</span></th>
                        </tr>
                    </tfoot>
                </table>
            </section>
            </div>
            <div id="divIncluirCli">
                <section class="atualizavel">
                    <form>
                        <fieldset>
                            <legend>Incluir Cliente</legend>
                            <label>Nome <input name="nome" type="text"  placeholder="Nome do cliente"></label>
                            <label>CPF <input name="cpf" type="number" placeholder="CPF - somente números"></label>
                            <label>RG <input name="rg" type="number" placeholder="RG - somente números"></label>
                            <label>E-mail <input name="email" type="email" placeholder="Email: exemplo@email.com"></label>
                            <label>Telefone <input name="telefone" type="tel" placeholder="Telefone - somente números"></label>
                            <label>Endereço <input name="endereco" type="text" placeholder="Endereço: Rua Tal, 1001"></label>
                            <input class="incluir" name="incluircli" type="submit" title="Incluir" value="Incluir">
                        </fieldset>
                    </form>
                </section>
            </div>
            <div id="divIncluirForn">
                <section class="atualizavel">
                    <form>
                        <fieldset>
                            <legend>Incluir Fornecedor</legend>
                            <label>Nome <input name="nome" type="text"  placeholder="Nome do Fornecedor"></label>
                            <label>Razão Social <input name="razaosocial" type="text" placeholder="Razão Solcial do Fornecedor"></label>
                            <label>CNPJ <input name="cnpj" type="number" placeholder="CNPJ - somente números"></label>
                            <label>E-mail <input name="email" type="email" placeholder="Email: exemplo@email.com"></label>
                            <label>Telefone <input name="telefone" type="tel" placeholder="Telefone - somente números"></label>
                            <label>Endereço <input name="endereco" type="text" placeholder="Endereço: Rua Tal, 1001"></label>
                            <input class="incluir" name="incluirfor" type="submit" title="Incluir" value="Incluir">
                        </fieldset>
                    </form>
                </section>
            </div>
        </main>

        <footer>
            <ul>
                <li id="denny"><a href="http://fb.com/dennylira" title="Denny Lira" target="_blank">Denny</a></li>
                <li id="parisotto"><a href="http://fb.com/parisotto.com.br" title="Edson Luiz Parisotto" target="_blank">Parisotto</a></li>
                <li id="fabricio"><a href="http://fb.com/fabricioashua" title="Fabrício Santos Guimarães " target="_blank">Fabrício</a></li>
                <li id="sandro"><a href="#" title="Sandro Jardelino " target="_blank">Sandro</a></li>
                <li id="wagner"><a href="http://fb.com/wagner.ferreira.c.jr" title="Wagner Ferreira" target="_blank">Wagner</a></li>
            </ul>
            <ul id="nomes">
                <li>Denny</li>
                <li>Parisotto</li>
                <li>Fabrício</li>
                <li>Sandro</li>
                <li>Wagner</li>
            </ul>
        </footer>
 
    </body>
</html>
