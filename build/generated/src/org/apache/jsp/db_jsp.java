package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class db_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <title>Internal System Database Set Up</title>\n");
      out.write("  <link rel=\"stylesheet\" href=\"modal/jquery-ui.css\">\n");
      out.write("  <script src=\"assets/js/jquery-1.8.3.min.js\"></script>\n");
      out.write("  <script src=\"assets/jquery-ui/jquery-ui-1.10.1.custom.min.js\"></script>\n");
      out.write("  <link rel=\"stylesheet\" href=\"assets/jquery-ui/jquery-ui-1.10.1.custom.min.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"css/btn.css\">\n");
      out.write("  <link rel=\"shortcut icon\" href=\"images/header.jpg\"/>\n");
      out.write("  <script>\n");
      out.write("  $(document).ready(function(){\n");
      out.write("       $(\"#success\").hide();\n");
      out.write("    var dialog, form,\n");
      out.write("       emailRegex = /^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,\n");
      out.write("      name = $( \"#user\" ),\n");
      out.write("      password = $( \"#password\" ),\n");
      out.write("      allFields = $( [] ).add( name ).add( password ),\n");
      out.write("      tips = $( \".validateTips\" );\n");
      out.write("\n");
      out.write(" \n");
      out.write("    function EditConnection(){\n");
      out.write("        $(\".validateTips1\").html(\"<font color=\\\"black\\\"><b>Saving Details...</b><img src=\\\"images/utube.gif\\\"></font>\"); \n");
      out.write("      var valid = true;\n");
      out.write("      allFields.removeClass( \"ui-state-error\" );\n");
      out.write("      if ( valid ) {\n");
      out.write("        dialog.dialog( \"open\" );\n");
      out.write("        var hostname,database,user,password;\n");
      out.write("        hostname=$(\"#hostname\").val();\n");
      out.write("        database=$(\"#database\").val();\n");
      out.write("        user=$(\"#user\").val();\n");
      out.write("        password=$(\"#password\").val();\n");
      out.write("//        alert(\"username : \"+username+\" password : \"+password);\n");
      out.write("       \n");
      out.write("       if(hostname==\"\"){\n");
      out.write("           $(\".validateTips\").html(\"<font color=\\\"red\\\"><b>Please enter hostname...</b></font>\"); \n");
      out.write("           \n");
      out.write("            }\n");
      out.write("            else if(database==\"\"){\n");
      out.write("             $(\".validateTips\").html(\"<font color=\\\"red\\\"><b>Please enter database name...</b></font>\");    \n");
      out.write("                \n");
      out.write("            } \n");
      out.write("            else if(user==\"\"){\n");
      out.write("             $(\".validateTips\").html(\"<font color=\\\"red\\\"><b>Please enter database user...</b></font>\");    \n");
      out.write("                \n");
      out.write("            }\n");
      out.write("              else{  \n");
      out.write("              $.ajax({\n");
      out.write("        url:\"updatedbpword?hostname=\"+hostname+\"&&database=\"+database+\"&&user=\"+user+\"&&password=\"+password,\n");
      out.write("        type:\"post\",\n");
      out.write("        dataType:\"html\",\n");
      out.write("        success:function(data){\n");
      out.write("            data=$.trim(data);\n");
      out.write(" \n");
      out.write("if(data==\"success\"){\n");
      out.write("    $(\".validateTips1\").html(\"<font color=\\\"green\\\"><b>Success : Correct connection credentials...</b></font>\"); \n");
      out.write("\n");
      out.write("    setInterval(function(){ $(\".validateTips\").show(); $(\".validateTips\").html(\"<font color=\\\"blue\\\"><a href='index.jsp' class='buttonx'>Click Here to Log In</a></font>\");\n");
      out.write("        $(\".validateTips1\").fadeOut(30000);\n");
      out.write("    }, 1);\n");
      out.write("//    window.location.href=\"index.jsp\";\n");
      out.write("}\n");
      out.write("else{\n");
      out.write("$(\".validateTips1\").html(\"<font color=\\\"red\\\"><b>Failed:</b></font>\");\n");
      out.write("setInterval(function(){ $(\".validateTips2\").html(\"<font color=\\\"red\\\"><b>Wrong Connection credentials...</b></font>\");$(\".validateTips2\").fadeOut(10000); }, 100);\n");
      out.write("}\n");
      out.write("        }\n");
      out.write("    });\n");
      out.write("       }\n");
      out.write("    \n");
      out.write("      }\n");
      out.write("      return valid;\n");
      out.write("    }\n");
      out.write(" \n");
      out.write("    dialog = $( \"#dialog-form\" ).dialog({\n");
      out.write("      autoOpen: false,\n");
      out.write("//      height: 300,\n");
      out.write("      width: 450,\n");
      out.write("      modal: true,\n");
      out.write("      buttons: {\n");
      out.write("        \"Save\": EditConnection\n");
      out.write("      }, \n");
      out.write("   closeOnEscape: false,\n");
      out.write("   open: function(event, ui) { $(\".ui-dialog-titlebar-close\", ui.dialog || ui).hide(); }\n");
      out.write("\n");
      out.write("      \n");
      out.write("    });\n");
      out.write(" \n");
      out.write("    form = dialog.find( \"form\" ).on( \"submit\", function( event ) {\n");
      out.write("      event.preventDefault();\n");
      out.write("//      addUser();\n");
      out.write("    });\n");
      out.write("      dialog.dialog( \"open\" );  \n");
      out.write("  });\n");
      out.write("  </script>\n");
      out.write("  <style>\n");
      out.write("      .ui-dialog-titlebar-close {\n");
      out.write("  visibility: hidden;\n");
      out.write("}\n");
      out.write("  </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write(" \n");
      out.write("    <div id=\"dialog-form\" title=\"Enter Database Connection Credentials....\" hidden=\"true\">\n");
      out.write(" <!--<div contenteditable=true>-->\n");
      out.write(" <p class=\"validateTips1\" style=\"text-align: center;\"></p>\n");
      out.write(" <p class=\"validateTips2\" style=\"text-align: center;\"></p>\n");
      out.write(" <p class=\"validateTips3\" style=\"text-align: center;\"></p>\n");
      out.write(" <p class=\"validateTips\" style=\"text-align: center;\"></p>\n");
      out.write("  <form action=\"\" method=\"post\">\n");
      out.write("                    <h4 align=\"center\">Database Configuration</h4>\n");
      out.write("                    <table  cellpadding=\"8px\" cellspacing=\"6px\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td style=\"text-align: right;\">Host name:   <img src=\"images/blguide.png\" title=\"enter the hostname followed by a ':' then port number e.g. localhost:3306. If there is no port number, just enter the host name alone e.g. localhost\"/></td>\n");
      out.write("                            <td style=\"text-align: right\">\n");
      out.write("\n");
      out.write("                                <!--  username -->\n");
      out.write("                                <input id=\"hostname\" type=text required name=\"hostname\" id=\"hostname\" placeholder=\"e.g localhost:3306\" value=\"localhost:3306\" autofocus class=\"textbox\"/></td>\n");
      out.write("\n");
      out.write("                        </tr>\n");
      out.write("                        <tr> <td style=\"text-align: right;\">Database : <img src=\"images/blguide.png\" title=\"enter the database name e.g internal_system\"/></td>\n");
      out.write("                            <td style=\"text-align: right;\"><input id=\"database\"  type=text required name=\"database\" value=\"internal_system\" placeholder=\"e.g internal_system\"  class=\"textbox\"/></td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        <tr> <td style=\"text-align: right;\">User:   <img src=\"images/blguide.png\" title=\"enter a database user name name e.g root\"/></td>\n");
      out.write("                            <td style=\"text-align: right;\"><input id=\"user\"  type=text required name=\"user\" value=\"root\" placeholder=\"e.g root\"  class=\"textbox\"/></td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        <tr><td style=\"text-align: right;\">Password:   <img src=\"images/blguide.png\" title=\"enter the database password\"/></td> \n");
      out.write("                            <!--password-->\n");
      out.write("                            <td style=\"text-align: right;\"><input id=\"password\"  type=password  name=\"password\" placeholder=\"Password\" class=\"textbox\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        \n");
      out.write("                    </table>\n");
      out.write("                \n");
      out.write("                </form>\n");
      out.write("  </div>  \n");
      out.write("    <div id=\"success\">\n");
      out.write("        <p style=\"color: red; \">Error While connecting to the database................</p>\n");
      out.write("        </div>\n");
      out.write("</body>\n");
      out.write("</html>");
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
