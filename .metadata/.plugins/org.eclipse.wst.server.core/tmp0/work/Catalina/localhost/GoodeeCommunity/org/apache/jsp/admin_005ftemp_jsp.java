/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-08-11 09:28:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005ftemp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1596615471827L));
    _jspx_dependants.put("jar:file:/C:/MVC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GoodeeCommunity/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fn.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/MVC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GoodeeCommunity/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\r\n");
      out.write("\t\r\n");
      out.write("\t<script\r\n");
      out.write("\tsrc=\"https://kit.fontawesome.com/fbff03f786.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\t\t<title>관리자 카테고리별 글 가져오기</title>\t\t\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<div id=\"container\">\r\n");
      out.write("        <!-- Main Navbar -->\r\n");
      out.write("        <nav class=\"navbar\" id=\"navbar\">\r\n");
      out.write("            <div class=\"navbar__logo\">\r\n");
      out.write("                <a href=\"index.jsp\">\r\n");
      out.write("                    <img src=\"image/logo.PNG\" alt=\"로고\">\r\n");
      out.write("                    <h1>구디 커뮤니티</h1>\r\n");
      out.write("                </a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"search-input\">\r\n");
      out.write("                <form class=\"searchbar\" action=\"#\" method=\"get\">\r\n");
      out.write("                    <input\r\n");
      out.write("                      class=\"search\"\r\n");
      out.write("                      type=\"text\"\r\n");
      out.write("                      name=\"search\"\r\n");
      out.write("                    />\r\n");
      out.write("                    <button><i class=\"fas fa-search\"></i></button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\t\r\n");
      out.write("            \r\n");
      out.write("            <button class=\"write\" onclick=\"location.href='writeView'\">\r\n");
      out.write("                글쓰기\r\n");
      out.write("            </button>\r\n");
      out.write("    \r\n");
      out.write("            <ul class=\"navbar__menu\">\r\n");
      out.write("                <h2>전체 게시판</h2>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"mngboard.jsp\" id=\"mngboard\">\r\n");
      out.write("                        <span>게시글 관리</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"mngcomment\" id=\"mngcomment\">\r\n");
      out.write("                        <span>댓글 관리</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"membermanagement.jsp\" id=\"mngmember\">\r\n");
      out.write("                        <span>회원 관리</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#\" id=\"ask\">\r\n");
      out.write("                        <span>문의사항 내역</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"top-util\">\r\n");
      out.write("                <div class=\"inner\">\r\n");
      out.write("                    <button type=\"button\" class=\"profile\">\r\n");
      out.write("                        <div class=\"profile-img\"></div>\r\n");
      out.write("                    </button>\r\n");
      out.write("                    <button class=\"login\" onclick=\"location.href='login.jsp'\">로그인</button>\r\n");
      out.write("                    <button onclick=\"location.href='photo_test.jsp'\">포토임시확인용</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("       <div id=\"contents\">\r\n");
      out.write("            <div class=\"section-top\">\r\n");
      out.write("                <div class=\"img-swipe\">\r\n");
      out.write("                    <!-- 이미지 필요!!! -->\r\n");
      out.write("                    \t메인 이미지 영역입니다.\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"helplist\">\r\n");
      out.write("                \t\t문의사항 목록 영역입니다.\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"section-bot\">\r\n");
      out.write("                <div class=\"board-swipe\">\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <button class=\"key-color\">전체 게시판</button>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <button>공지사항</button>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <button>인기 글</button>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"list-box\">\r\n");
      out.write("                    <!-- 게시글 영역 -->\r\n");
      out.write("                    게시글 영역입니다.<br>\r\n");
      out.write("                    ul\r\n");
      out.write("                     li ....\r\n");
      out.write("                    <div class=\"list-paging\">\r\n");
      out.write("                      <button id=\"1\" class=\"on\">1</button>\r\n");
      out.write("                      <button id=\"2\">2</button>\r\n");
      out.write("                      <button id=\"3\">3</button>\r\n");
      out.write("                      <button id=\"4\">4</button>\r\n");
      out.write("                      <button id=\"5\">5</button>\r\n");
      out.write("                      <button id=\"next\">next</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>  \r\n");
      out.write("    </div>\r\n");
      out.write("        \r\n");
      out.write("\t</body>\r\n");
      out.write("\t\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\r\n");
      out.write("\t</script>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
