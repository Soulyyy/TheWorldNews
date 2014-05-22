package theworldnews.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NavigationTag extends SimpleTagSupport {

	public String displayNavigation() {
		StringBuilder htmlText = new StringBuilder();
		htmlText.append("<div id=\"header\">");

		htmlText.append("<!-- The World Log In & Settings -->");
		htmlText.append("<div id=\"logoButtons\">");

		htmlText.append("<header class=\"Head\"><a class=\"Head\" href=\"../Index.jsp\">THE WORLD</a></header>");

		htmlText.append("<button id=\"settings\"></button>");

		htmlText.append("<button id=\"toggleLogin\">Log In</button>");
		htmlText.append("<button id=\"logoutButton\">Log Out</button>");

		htmlText.append("</div>");

		htmlText.append("<div id=\"search\">");

		htmlText.append("<form method=\"post\" action=\"/searchText\" class=\"form-wrapper\">");
		htmlText.append("<p><input type=\"text\" id=\"searchBox\" name=\"searchBox\" placeholder=\"Search\"></p>");
		htmlText.append("</form>");

		htmlText.append("</div>");

		htmlText.append("<div id=\"loginContainer\">");
		htmlText.append("<div id=\"login\">");
		htmlText.append("<p><input type=\"text\" id=\"userName\" name=\"login\" value=\"\" placeholder=\"Username or Email\"></p>");
		htmlText.append("<p><input type=\"password\" id=\"password\" name=\"password\" value=\"\" placeholder=\"Password\"></p>");

		htmlText.append("<a href=\"../html/registerUser.html\" id=\"regi\">Not a User? Click here to register</a>");
		htmlText.append("<p class=\"submit\"><button id=\"loginbutton\">Log In</button></p>");
		htmlText.append("</div>");
		htmlText.append("<div>");
		htmlText.append("<button id=\"authorize-button\">Log In With Google</button>");
		htmlText.append("</div>");
		htmlText.append("</div>");

		htmlText.append("<!-- Navigation -->");

		htmlText.append("<div id=\"navigation\">");

		htmlText.append("<ul>");
		htmlText.append("<li><a href=\"News.jsp\">News</a> </li>");
		htmlText.append("<li><a href=\"Business.jsp\">Business</a></li>");
		htmlText.append("<li><a href=\"Sports.jsp\">Sports</a></li>");
		htmlText.append("<li><a href=\"Science.jsp\">Science</a></li>");
		htmlText.append("<li><a href=\"Arts.jsp\">Arts</a></li>");
		htmlText.append("<li><a href=\"FashionStyle.jsp\">Fashion &amp; Style</a></li>");
		htmlText.append("</ul>");

		htmlText.append("</div>");

		htmlText.append("</div>");

		return htmlText.toString();
	}

	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().println(displayNavigation());
	}
}
