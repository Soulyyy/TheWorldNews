package TheWorldNews.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import theworldnews.database.news.objects.*;

public class NavigationTag extends SimpleTagSupport{



public String displayNavigation(){
StringBuffer sb = new StringBuffer();
sb.append("<div id=\"header\">");


sb.append("<!-- The World Log In & Settings -->");
sb.append("<div id=\"logoButtons\">");

sb.append("<header class=\"Head\"><a class=\"Head\" href=\"../Index.jsp\">THE WORLD</a></header>");


sb.append("<button id=\"settings\"></button>");


sb.append("<button id=\"toggleLogin\">Log In</button>");
sb.append("<button id=\"logoutButton\">Log Out</button>");

sb.append("</div>");

sb.append("<div id=\"search\">");


sb.append("<form action=\"search\" class=\"form-wrapper\">");
sb.append("<p><input type=\"text\" id=\"searchBox\" placeholder=\"Search\"></p>");
sb.append("</form>");

sb.append("</div>");



sb.append("<div id=\"loginContainer\">");
sb.append("<div id=\"login\">");
sb.append("<p><input type=\"text\" id=\"userName\" name=\"login\" value=\"\" placeholder=\"Username or Email\"></p>");
sb.append("<p><input type=\"password\" id=\"password\" name=\"password\" value=\"\" placeholder=\"Password\"></p>");

sb.append("<a href=\"../html/registerUser.html\" id=\"regi\">Not a User? Click here to register</a>");
sb.append("<p class=\"submit\"><button id=\"loginbutton\">Log In</button></p>");
sb.append("</div>");
sb.append("<div>");
sb.append("<button id=\"authorize-button\">Log In With Google</button>");
sb.append("</div>");
sb.append("</div>");



sb.append("<!-- Navigation -->");

sb.append("<div id=\"navigation\">");

sb.append("<ul>");
sb.append("<li><a href=\"News.jsp\">News</a> </li>");
sb.append("<li><a href=\"Business.jsp\">Business</a></li>");
sb.append("<li><a href=\"Sports.jsp\">Sports</a></li>");
sb.append("<li><a href=\"Science.jsp\">Science</a></li>");
sb.append("<li><a href=\"Arts.jsp\">Arts</a></li>");
sb.append("<li><a href=\"FashionStyle.jsp\">Fashion &amp; Style</a></li>");
sb.append("</ul>");

sb.append("</div>");


sb.append("</div>");



return sb.toString();
}

StringBuffer sb = new StringBuffer();

@Override
public void doTag() throws JspException, IOException {

JspWriter out = getJspContext().getOut();

out.println(displayNavigation());
}
}

