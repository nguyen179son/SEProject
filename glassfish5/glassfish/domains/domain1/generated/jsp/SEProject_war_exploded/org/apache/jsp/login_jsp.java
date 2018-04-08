package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!doctype html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>Log in page</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/css/login.css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"container-fluid text-center\">\n");
      out.write("    <h1> Chat Application </h1>\n");
      out.write("    <img src=\"image/HomepageIcon.jpg\" class=\"img-rounded\" alt=\"logo\">\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container-fluid text-center\">\n");
      out.write("    <form action=\"login\" method=\"get\">\n");
      out.write("\n");
      out.write("\n");
      out.write("        <input type=\"text\" class=\"form-control\" placeholder=\"User name here\" name=\"user_name\" required autofocus\n");
      out.write("               value=\"");
      out.print(request.getAttribute("user_name")!=null?request.getAttribute("user_name"):"");
      out.write("\"/>\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        <input type=\"password\" class=\"form-control\" placeholder=\"Your password here\" name=\"password\" required\n");
      out.write("               value=\"");
      out.print(request.getAttribute("password")!=null?request.getAttribute("password"):"");
      out.write("\"/> <br>\n");
      out.write("        <input type=\"submit\" class=\"btn btn-primary\" name=\"Login\" value=\"Log in\"/> <br>\n");
      out.write("    </form>\n");
      out.write("    <div id=\"message\" class=");
      out.print( request.getAttribute("error_message") != null ? "show" : "hide");
      out.write(">\n");
      out.write("        Wrong email or password\n");
      out.write("    </div>\n");
      out.write("    <label class=\"signup\">\n");
      out.write("        <a href=\"/SEProject_war_exploded/signup\"> Sign up if you are a new user</a>\n");
      out.write("    </label>\n");
      out.write("\n");
      out.write("</div>\n");
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
