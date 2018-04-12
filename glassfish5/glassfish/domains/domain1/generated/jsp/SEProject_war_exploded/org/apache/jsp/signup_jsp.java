package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signup_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!doctype html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <title>Sign up Page</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"stylesheets/css/signup.css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form method=\"post\" action=\"/SEProject_war_exploded/signup\">\r\n");
      out.write("    <div class=\"container text-center\">\r\n");
      out.write("        <h1> Chat Application </h1>\r\n");
      out.write("        <img src=\"image/HomepageIcon.jpg\" class=\"img-rounded\" alt=\"logo\">\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container text-center\">\r\n");
      out.write("        <h1>Sign Up</h1>\r\n");
      out.write("        <p>Please fill in this form to create an account.</p>\r\n");
      out.write("        <hr>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("\r\n");
      out.write("        <div id=\"error\" class=");
      out.print( request.getAttribute("error_message") != null ? "show" : "hide");
      out.write("\r\n");
      out.write("        >\r\n");
      out.write("            <span id=\"123\">\r\n");
      out.write("                ");
      out.print(request.getAttribute("error_message"));
      out.write("\r\n");
      out.write("            </span>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-4\">\r\n");
      out.write("            <label class=\"control-label col-sm-offset-3\">Name: </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <input type=\"text\" class=\"form-control\" placeholder=\"Enter your name here\" name=\"name\" autofocus value=\"");
      out.print(
                request.getAttribute("nickname")!=null?request.getAttribute("nickname"):""
            );
      out.write("\"> <br>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-4\">\r\n");
      out.write("            <label class=\"control-label col-sm-offset-3\">Email: </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <input type=\"text\" class=\"form-control\" placeholder=\"Enter your email here\" name=\"email\" autofocus value=\"");
      out.print(
                request.getAttribute("email")!=null?request.getAttribute("email"):""
            );
      out.write("\"> <br>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-4\">\r\n");
      out.write("            <label class=\"control-label col-sm-offset-3\">Password: </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <input type=\"password\" class=\"form-control\" placeholder=\"Enter your password here\" name=\"password\" autofocus\r\n");
      out.write("                   value=\"");
      out.print(
                request.getAttribute("password")!=null?request.getAttribute("password"):""
            );
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-4\">\r\n");
      out.write("            <label class=\"control-label col-sm-offset-3\">Confirm Password: </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <input type=\"password\" class=\"form-control\" placeholder=\"Enter your password again\" name=\"confirmpassword\"\r\n");
      out.write("                   autofocus value=\"");
      out.print(
                request.getAttribute("confirm_password")!=null?request.getAttribute("confirm_password"):""
            );
      out.write("\"> <br>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-4\"></div>\r\n");
      out.write("        <div class=\"col-sm-6\">\r\n");
      out.write("            <input type=\"submit\" class=\"btn btn-primary btn-lg\"></input>\r\n");
      out.write("            <a href=\"/SEProject_war_exploded/home\" class=\"btn btn-danger btn-lg\">Cancel</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-group\">\r\n");
      out.write("        <div class=\"col-sm-7\" style=\"text-align:right\">\r\n");
      out.write("            Already Registered?\r\n");
      out.write("            <a href=\"/SEProject_war_exploded/home\"> Sign In</a>\r\n");
      out.write("            <br> <br>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
