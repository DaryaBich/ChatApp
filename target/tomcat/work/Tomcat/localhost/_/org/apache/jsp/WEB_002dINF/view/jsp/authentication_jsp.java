/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-05-13 10:31:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class authentication_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("    background: radial-gradient(40% 50%, #FAECD5, #CAE4D8);\r\n");
      out.write("    font-family: cursive;\r\n");
      out.write("    font-weight: 1000;\r\n");
      out.write("      font-size: 30px;\r\n");
      out.write("   }\r\n");
      out.write("   label{\r\n");
      out.write("   color:#8f0101;}\r\n");
      out.write("form {\r\n");
      out.write("    width:400px;\r\n");
      out.write("     height: 500px;\r\n");
      out.write("     margin: 200px auto 0 auto;\r\n");
      out.write("     text-align: center;\r\n");
      out.write("     color=white\r\n");
      out.write("}\r\n");
      out.write("input[type=text], input[type=password] {\r\n");
      out.write("     width: 300px;\r\n");
      out.write("     height:60px;\r\n");
      out.write("     font-size: 30px;\r\n");
      out.write("     margin-bottom: 25px;\r\n");
      out.write("     border-radius: 10px;\r\n");
      out.write("     padding-left: 80px;\r\n");
      out.write("     border-color: red;\r\n");
      out.write("}\r\n");
      out.write("button {\r\n");
      out.write("    background-color: #d32f2f;\r\n");
      out.write("      border: none;\r\n");
      out.write("      padding: 30px;\r\n");
      out.write("      width: 300px;\r\n");
      out.write("      border-radius: 3px;\r\n");
      out.write("      box-shadow: 1px 5px 30px -5px rgba(0, 0, 0, 0.6);\r\n");
      out.write("      color: #fff;\r\n");
      out.write("      margin-top: 60px;\r\n");
      out.write("      cursor: pointer;\r\n");
      out.write("      border-radius: 10px;\r\n");
      out.write("      font-size: 20px;\r\n");
      out.write("      font-family: cursive;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <form action=\"/chatapp/check\" method=\"POST\">\r\n");
      out.write("     <label>Login\r\n");
      out.write("       <input type=\"text\" name=\"login\"><br/>\r\n");
      out.write("     </label>\r\n");
      out.write("     <label>Password\r\n");
      out.write("       <input type=\"password\" name=\"password\"><br/>\r\n");
      out.write("     </label>\r\n");
      out.write("     <button type=\"submit\" class=\"btn btn-default btn-lg\">Sign in</button>\r\n");
      out.write("   </form>\r\n");
      out.write(" </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
