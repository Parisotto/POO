<%-- 
    Document   : index
    Created on : 22/10/2016, 13:13:13
    Author     : Parisotto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="net.parisotto.poo.cadastros.DataBase"%>
<%@page import="net.parisotto.poo.cadastros.Clientes"%>
<%@page import="net.parisotto.poo.cadastros.Fornecedores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<Clientes> clientes = DataBase.getClientes();
    ArrayList<Fornecedores> fornecedores = DataBase.getFornecedores();
    Clientes c = new Clientes();
    Fornecedores f = new Fornecedores();
    
    //Inclui valores de teste caso não haja nenhum
 /*
    if(clientes.isEmpty()){
        c.setNome("Clente de Teste");  
        c.setCpf("03303303303");
        c.setRg("10100100");
        c.setEmail("email@email.com");
        c.setTelefone("1343434343");
        c.setEndereco("Endereço do Cliente, 44");
        for(int i = 1; i <= 5; i++){
            clientes.add(c);
        }
        c = new Clientes();
    }
*/    
    //Teste para verificar se foi solicitado INCLUIR cliente ou fornecedor
    if(request.getParameter("incluircli")!= null){
        c.setNome(request.getParameter("nome"));  
        c.setCpf(request.getParameter("cpf"));
        c.setRg(request.getParameter("rg"));
        c.setEmail(request.getParameter("email"));
        c.setTelefone(request.getParameter("telefone"));
        c.setEndereco(request.getParameter("endereco"));
        if(c.getNome()!="") clientes.add(c);
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("incluirfor")!= null){
        f.setNome(request.getParameter("nome"));
        f.setEmail(request.getParameter("email"));
        f.setTelefone(request.getParameter("telefone"));
        fornecedores.add(f);
        response.sendRedirect(request.getRequestURI());
    }
    
    //Teste para verificar se foi solicitado EXCLUIR cliente ou fornecedor
    if(request.getParameter("excluircli") != null){
        clientes.remove(Integer.parseInt(request.getParameter("i")));
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("excluirfor") != null){
        fornecedores.remove(Integer.parseInt(request.getParameter("i")));
        response.sendRedirect(request.getRequestURI());
    }
    
    //Teste para verificar se foi solicitado ALTERAR cliente ou fornecedor
    if(request.getParameter("alterarcli")!= null){
        c = clientes.get(Integer.parseInt(request.getParameter("i")));
    } else if(request.getParameter("alterarfor")!= null){
        f = fornecedores.get(Integer.parseInt(request.getParameter("i")));
    } else if(request.getParameter("atualizarcli")!=null){
        c = clientes.get(Integer.parseInt(request.getParameter("i")));
        c.setNome(request.getParameter("nome"));  
        c.setCpf(request.getParameter("cpf"));
        c.setRg(request.getParameter("rg"));
        c.setEmail(request.getParameter("email"));
        c.setTelefone(request.getParameter("telefone"));
        c.setEndereco(request.getParameter("endereco"));
        if(c.getNome()!="") clientes.set(Integer.parseInt(request.getParameter("i")), c);
        response.sendRedirect(request.getRequestURI());
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FATEC PG - ADS4 - POO - TP4</title>
    </head>
    <body>
        <h1>CLIENTES</h1>
<!--//
        <fieldset>
            <legend>Incluir</legend>
            <form>
                <input type="text" name="nome">
                <input type="email" name="email">
                <input type="tel" name="telefone">
                <input type="submit" name="incluir" value="Incluir">
            </form>
        </fieldset>
//-->        
        <h2>Clientes</h2>
        <table>
            <tr>
                <th>Nome</th>
                <th>CPF</th>
                <th>RG</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Endereço</th>
                <th>OPERAÇÃO</th>
            </tr>
            <tr>
                <td>
                    <form>
                        <input name="nome" type="text" placeholder="Nome do cliente" value="<%= (c.getNome()!=null)?c.getNome():"" %>">
                </td>
                <td>
                        <input name="cpf" type="number" placeholder="CPF - somente números" value="<%= (c.getCpf()!=null)?c.getCpf():"" %>">
                </td>
                <td>
                        <input name="rg" type="number" placeholder="RG - somente números" value="<%= (c.getRg()!=null)?c.getRg():"" %>">
                </td>                
                <td>
                        <input name="email" type="email" placeholder="Email: exemplo@email.com" value="<%= (c.getEmail()!=null)?c.getEmail():"" %>">
                </td>                
                <td>
                        <input name="telefone" type="tel" placeholder="Telefone - somente números" value="<%= (c.getTelefone()!=null)?c.getTelefone():"" %>">
                </td>                
                <td>
                        <input name="endereco" type="text" placeholder="Endereço: Rua Tal, 1001" value="<%= (c.getEndereco()!=null)?c.getEndereco():"" %>">
                </td>
                <td>
                        <input type="hidden" name="i" value="<%=request.getParameter("i")%>">
                        <input name="<%=(request.getParameter("i")!=null)?"atualizarcli":"incluircli"%>" type="submit" value="<%=(request.getParameter("i")!=null)?"Atualizar":"Incluir"%>">
                    </form>
                </td>
            </tr>
<% 
    for(Clientes cli: clientes){ 
%>            
            <tr>
                <td><%= cli.getNome() %></td>
                <td><%= cli.getCpf() %></td>
                <td><%= cli.getRg() %></td>
                <td><%= cli.getEmail() %></td>
                <td><%= cli.getTelefone() %></td>
                <td><%= cli.getEndereco() %></td>
                <td>
                    <form>
                        <input type="hidden" name="i" value="<%= clientes.indexOf(cli)%>">
                        <input type="submit" name="excluircli" value="Excluir">
                        <input type="submit" name="alterarcli" value="Alterar">
                    </form>
                </td>
            </tr>
<%
    }
%>            
        </table>
        <br><br>
        <h2>FORNECEDORES</h2>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Razão Social</th>
                <th>CNPJ</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Endereço</th>
                <th>OPERAÇÃO</th>
            </tr>
<% 
    for(Fornecedores forn: fornecedores){ 
%>            
            <tr>
                <td><%= forn.getNome() %></td>
                <td><%= forn.getRazaosocial() %></td>
                <td><%= forn.getCnpj() %></td>
                <td><%= forn.getEmail() %></td>
                <td><%= forn.getTelefone() %></td>
                <td><%= forn.getEndereco() %></td>
                <td>
                    <form>
                        <input type="hidden" name="i" value="<%= fornecedores.indexOf(forn)%>">
                        <input type="submit" name="excluirfor" value="Excluir">
                        <input type="submit" name="alterarfor" value="Alterar">
                    </form>
                </td>
            </tr>
<%
    }
%>            
        </table>
    </body>
</html>
