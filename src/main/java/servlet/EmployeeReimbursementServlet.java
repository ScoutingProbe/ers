package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Employee;
import model.Reimbursement;
import service.ReimbursementService;

@MultipartConfig
public class EmployeeReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		String inUsername = employee.getUsername();
		String inCategory = request.getParameter("category");
		
		Part part = request.getPart("image");
		InputStream input = part.getInputStream();
		
		ReimbursementService.createReimbursement(inUsername, "pending", input, inCategory);
		
		request.getRequestDispatcher("employee-read-reimbursement.do").forward(request, response);
	}

}
