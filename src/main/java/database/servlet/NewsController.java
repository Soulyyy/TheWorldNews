package database.servlet;

public class NewsController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       
        String image = request.getParameter("image");//not really image?
        String header = request.getParameter("header");
        String content = request.getParameter("content");
        //Assign prime number for each article group, multiply if in more than one, unique factorization defines
        int articleGroups = request.getParameter("articleGroup");
		
		
		//Need to implement all methods in interface
		String idString = req.getParameter("id");
		if (idString != null) {
			List<User> allUsers= datastore.findAllUsers();
			resp.getWriter().write(gson.toJson(allUsers));
		} else {
			int id = Integer.parseInt(idString);
	        User user = datastore.findUserById(id);
	        resp.getWriter().write(gson.toJson(user));
		}
	}
}
