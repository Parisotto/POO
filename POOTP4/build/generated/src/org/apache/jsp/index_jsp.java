package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import net.parisotto.poo.cadastros.DataBase;
import net.parisotto.poo.cadastros.Clientes;
import net.parisotto.poo.cadastros.Fornecedores;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"pt-BR\">\n");
      out.write("    <head>\n");
      out.write("        <title>FATEC PG - ADS4 - POO - TP4</title>\n");
      out.write("        <meta charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/styles.css\">\n");
      out.write("<!--//\n");
      out.write("        <script>\n");
      out.write("           alert(\"");
      out.print( DataBase.getAction() );
      out.write("\");\n");
      out.write("        </script>\n");
      out.write("//-->\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!--// Cabeçalho fixo da página //-->\n");
      out.write("        <header>\n");
      out.write("            <h1><a href=\"index.jsp\"><img alt=\"logo Cadastro\" title=\"Home\" src=\"img/logo.png\"></a></h1>\n");
      out.write("            <nav>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"#\" id=\"mnuCli\">CLIENTES</a>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><a href=\"#alvoIncluirClientes\">Incluir</a></li>\n");
      out.write("                            <li><a href=\"#alvoListarClientes\">Listar</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    <li><a href=\"#\" id=\"mnuForn\">FORNECEDORES</a>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><a href=\"#alvoIncluirFornecedores\">Incluir</a></li>\n");
      out.write("                            <li><a href=\"#alvoListarFornecedores\">Listar</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("        <main>\n");
      out.write("            <span id=\"alvoListarClientes\"></span>\n");
      out.write("            <span id=\"alvoListarFornecedores\"></span>\n");
      out.write("            <span id=\"alvoIncluirClientes\"></span>\n");
      out.write("            <span id=\"alvoIncluirFornecedores\"></span>\n");
      out.write("            \n");
      out.write("            <div id=\"divClientes\">\n");

    //Testa se foi solicitado a edição de algum cliente
    if(request.getParameter("editarcli")!= null){
        c = clientes.get(Integer.parseInt(request.getParameter("i")));


      out.write("\n");
      out.write("            <!--// Formulário de Edição de Cliente //-->\n");
      out.write("            <section class=\"atualizavel\">\n");
      out.write("                <form>\n");
      out.write("                    <fieldset>\n");
      out.write("                        <legend>Editar Cadastro de Cliente - ID: ");
      out.print(request.getParameter("i"));
      out.write("</legend>\n");
      out.write("                        <label>Nome <input name=\"nome\" type=\"text\" value=\"");
      out.print( (c.getNome()!=null)?c.getNome():"" );
      out.write("\"></label>\n");
      out.write("                        <label>CPF <input name=\"cpf\" type=\"number\" value=\"");
      out.print( (c.getCpf()!=null)?c.getCpf():"" );
      out.write("\"></label>\n");
      out.write("                        <label>RG <input name=\"rg\" type=\"number\" value=\"");
      out.print( (c.getRg()!=null)?c.getRg():"" );
      out.write("\"></label>\n");
      out.write("                        <label>E-mail <input name=\"email\" type=\"email\" value=\"");
      out.print( (c.getEmail()!=null)?c.getEmail():"" );
      out.write("\"></label>\n");
      out.write("                        <label>Telefone <input name=\"telefone\" type=\"tel\" value=\"");
      out.print( (c.getTelefone()!=null)?c.getTelefone():"" );
      out.write("\"></label>\n");
      out.write("                        <label>Endereço <input name=\"endereco\" type=\"text\" value=\"");
      out.print( (c.getEndereco()!=null)?c.getEndereco():"" );
      out.write("\"></label>\n");
      out.write("                        <input name=\"i\" type=\"hidden\" value=\"");
      out.print(request.getParameter("i"));
      out.write("\">\n");
      out.write("                        <input name=\"atualizarcli\" type=\"submit\" title=\"Atualizar\" value=\"Atualizar\">\n");
      out.write("                    </fieldset>\n");
      out.write("                </form>\n");
      out.write("            </section>\n");

    }

      out.write("\n");
      out.write("            <!--// Tabela que lista todos os clientes cadastrados //-->\n");
      out.write("            <section class=\"lista\">\n");
      out.write("                <table>\n");
      out.write("                    <caption><span>LISTA DE CLIENTES</span></caption>\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>ID</th>\n");
      out.write("                            <th>NOME</th>\n");
      out.write("                            <th>CPF</th>\n");
      out.write("                            <th>RG</th>\n");
      out.write("                            <th>E-MAIL</th>\n");
      out.write("                            <th>TELEFONE</th>\n");
      out.write("                            <th>ENDEREÇO</th>\n");
      out.write("                            <th>OPERAÇÕES</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
 
    //Inclui uma linha a cada vez na tabela com cada um dos cadastrados
    int totCli = 0;
    for(Clientes cli: clientes){ 
        totCli++;

      out.write("            \n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( clientes.indexOf(cli));
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getNome() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getCpf() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getRg() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getEmail() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getTelefone() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( cli.getEndereco() );
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <form>\n");
      out.write("                                    <input type=\"hidden\" name=\"i\" value=\"");
      out.print( clientes.indexOf(cli));
      out.write("\">\n");
      out.write("                                    <input type=\"submit\" class=\"delete\" name=\"excluircli\" title=\"Excluir\" value=\"Excluir\">\n");
      out.write("                                    <input type=\"submit\" class=\"edite\" name=\"editarcli\" title=\"Editar\" value=\"Editar\">\n");
      out.write("                                </form>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");

    }

      out.write("            \n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th colspan=\"8\"><span>");
      out.print( totCli );
      out.print( (totCli>1)?" clientes cadastrados ":" cliente cadastrado " );
      out.write("no sistema</span></th>\n");
      out.write("                        </tr>\n");
      out.write("                    </tfoot>\n");
      out.write("                </table>\n");
      out.write("            </section>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"divFornecedores\">\n");

    //Testa se foi solicitado a edição de algum fornecedor
    if(request.getParameter("editarfor")!= null){
        f = fornecedores.get(Integer.parseInt(request.getParameter("i")));


      out.write("\n");
      out.write("            <!--// Formulário de Edição de Fornecedor //-->\n");
      out.write("            <section class=\"atualizavel\">\n");
      out.write("                <form>\n");
      out.write("                    <fieldset>\n");
      out.write("                        <legend>Editar Cadastro de Fornecedor - ID: ");
      out.print(request.getParameter("i"));
      out.write("</legend>\n");
      out.write("                        <label>Nome <input name=\"nome\" type=\"text\" value=\"");
      out.print( (f.getNome()!=null)?f.getNome():"" );
      out.write("\"></label>\n");
      out.write("                        <label>Razão Social <input name=\"razaosocial\" type=\"text\" value=\"");
      out.print( (f.getRazaosocial()!=null)?f.getRazaosocial():"" );
      out.write("\"></label>\n");
      out.write("                        <label>CNPJ <input name=\"cnpj\" type=\"number\" value=\"");
      out.print( (f.getCnpj()!=null)?f.getCnpj():"" );
      out.write("\"></label>\n");
      out.write("                        <label>E-mail <input name=\"email\" type=\"email\" value=\"");
      out.print( (f.getEmail()!=null)?f.getEmail():"" );
      out.write("\"></label>\n");
      out.write("                        <label>Telefone <input name=\"telefone\" type=\"tel\" value=\"");
      out.print( (f.getTelefone()!=null)?f.getTelefone():"" );
      out.write("\"></label>\n");
      out.write("                        <label>Endereço <input name=\"endereco\" type=\"text\" value=\"");
      out.print( (f.getEndereco()!=null)?f.getEndereco():"" );
      out.write("\"></label>\n");
      out.write("                        <input name=\"i\" type=\"hidden\" value=\"");
      out.print(request.getParameter("i"));
      out.write("\">\n");
      out.write("                        <input name=\"atualizarfor\" type=\"submit\" title=\"Atualizar\" value=\"Atualizar\">\n");
      out.write("                    </fieldset>\n");
      out.write("                </form>\n");
      out.write("            </section>\n");

    }

      out.write("\n");
      out.write("            <!--// Tabela que lista todos os fornecedores cadastrados //-->\n");
      out.write("            <section class=\"lista\">\n");
      out.write("                <table>\n");
      out.write("                    <caption><span>LISTA DE FORNECEDORES</span></caption>\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>ID</th>\n");
      out.write("                            <th>NOME</th>\n");
      out.write("                            <th>RAZÃO SOCIAL</th>\n");
      out.write("                            <th>CNPJ</th>\n");
      out.write("                            <th>E-MAIL</th>\n");
      out.write("                            <th>TELEFONE</th>\n");
      out.write("                            <th>ENDEREÇO</th>\n");
      out.write("                            <th>OPERAÇÕES</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
 
    //Inclui uma linha a cada vez na tabela com cada um dos cadastrados
    int totForn = 0;
    for(Fornecedores forn: fornecedores){ 
        totForn++;

      out.write("            \n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( fornecedores.indexOf(forn));
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getNome() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getRazaosocial() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getCnpj() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getEmail() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getTelefone() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( forn.getEndereco() );
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <form>\n");
      out.write("                                    <input type=\"hidden\" name=\"i\" value=\"");
      out.print( fornecedores.indexOf(forn));
      out.write("\">\n");
      out.write("                                    <input type=\"submit\" class=\"delete\" name=\"excluirfor\" title=\"Excluir\" value=\"Excluir\">\n");
      out.write("                                    <input type=\"submit\" class=\"edite\" name=\"editarfor\" title=\"Editar\" value=\"Editar\">\n");
      out.write("                                </form>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");

    }

      out.write("            \n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th colspan=\"8\"><span>");
      out.print( totForn );
      out.print( (totForn>1)?" fornecedores cadastrados ":" fornecedor cadastrado " );
      out.write("no sistema</span></th>\n");
      out.write("                        </tr>\n");
      out.write("                    </tfoot>\n");
      out.write("                </table>\n");
      out.write("            </section>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"divIncluirCli\">\n");
      out.write("                <section class=\"atualizavel\">\n");
      out.write("                    <form>\n");
      out.write("                        <fieldset>\n");
      out.write("                            <legend>Incluir Cliente</legend>\n");
      out.write("                            <label>Nome <input name=\"nome\" type=\"text\"  placeholder=\"Nome do cliente\"></label>\n");
      out.write("                            <label>CPF <input name=\"cpf\" type=\"number\" placeholder=\"CPF - somente números\"></label>\n");
      out.write("                            <label>RG <input name=\"rg\" type=\"number\" placeholder=\"RG - somente números\"></label>\n");
      out.write("                            <label>E-mail <input name=\"email\" type=\"email\" placeholder=\"Email: exemplo@email.com\"></label>\n");
      out.write("                            <label>Telefone <input name=\"telefone\" type=\"tel\" placeholder=\"Telefone - somente números\"></label>\n");
      out.write("                            <label>Endereço <input name=\"endereco\" type=\"text\" placeholder=\"Endereço: Rua Tal, 1001\"></label>\n");
      out.write("                            <input class=\"incluir\" name=\"incluircli\" type=\"submit\" title=\"Incluir\" value=\"Incluir\">\n");
      out.write("                        </fieldset>\n");
      out.write("                    </form>\n");
      out.write("                </section>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"divIncluirForn\">\n");
      out.write("                <section class=\"atualizavel\">\n");
      out.write("                    <form>\n");
      out.write("                        <fieldset>\n");
      out.write("                            <legend>Incluir Fornecedor</legend>\n");
      out.write("                            <label>Nome <input name=\"nome\" type=\"text\"  placeholder=\"Nome do Fornecedor\"></label>\n");
      out.write("                            <label>Razão Social <input name=\"razaosocial\" type=\"text\" placeholder=\"Razão Solcial do Fornecedor\"></label>\n");
      out.write("                            <label>CNPJ <input name=\"cnpj\" type=\"number\" placeholder=\"CNPJ - somente números\"></label>\n");
      out.write("                            <label>E-mail <input name=\"email\" type=\"email\" placeholder=\"Email: exemplo@email.com\"></label>\n");
      out.write("                            <label>Telefone <input name=\"telefone\" type=\"tel\" placeholder=\"Telefone - somente números\"></label>\n");
      out.write("                            <label>Endereço <input name=\"endereco\" type=\"text\" placeholder=\"Endereço: Rua Tal, 1001\"></label>\n");
      out.write("                            <input class=\"incluir\" name=\"incluirfor\" type=\"submit\" title=\"Incluir\" value=\"Incluir\">\n");
      out.write("                        </fieldset>\n");
      out.write("                    </form>\n");
      out.write("                </section>\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("\n");
      out.write("        <footer>\n");
      out.write("            <ul>\n");
      out.write("                <li id=\"denny\"><a href=\"http://fb.com/dennylira\" title=\"Denny Lira\" target=\"_blank\">Denny</a></li>\n");
      out.write("                <li id=\"parisotto\"><a href=\"http://fb.com/parisotto.com.br\" title=\"Edson Luiz Parisotto\" target=\"_blank\">Parisotto</a></li>\n");
      out.write("                <li id=\"fabricio\"><a href=\"http://fb.com/fabricioashua\" title=\"Fabrício Santos Guimarães \" target=\"_blank\">Fabrício</a></li>\n");
      out.write("                <li id=\"sandro\"><a href=\"#\" title=\"Sandro Jardelino \" target=\"_blank\">Sandro</a></li>\n");
      out.write("                <li id=\"wagner\"><a href=\"http://fb.com/wagner.ferreira.c.jr\" title=\"Wagner Ferreira\" target=\"_blank\">Wagner</a></li>\n");
      out.write("            </ul>\n");
      out.write("            <ul id=\"nomes\">\n");
      out.write("                <li>Denny</li>\n");
      out.write("                <li>Parisotto</li>\n");
      out.write("                <li>Fabrício</li>\n");
      out.write("                <li>Sandro</li>\n");
      out.write("                <li>Wagner</li>\n");
      out.write("            </ul>\n");
      out.write("        </footer>\n");
      out.write(" \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
