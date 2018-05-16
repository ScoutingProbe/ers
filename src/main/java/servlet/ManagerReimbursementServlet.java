package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Manager;
import model.Reimbursement;
import service.ReimbursementService;

public class ManagerReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		int reimbursementid = Integer.parseInt(request.getParameter("id"));
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String managername = manager.getUsername();
		
		ReimbursementService.updateReimbursement(reimbursementid, status, managername);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements();
		request.getSession().setAttribute("reimbursements", reimbursements);
		request.getRequestDispatcher("manager-update-reimbursement.do").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String inUsername = manager.getUsername();
		String inCategory = request.getParameter("category");
		
		Part part = request.getPart("image");
		InputStream image = part.getInputStream();
		
		ReimbursementService.createReimbursement(inUsername, "pending", image, inCategory);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements(inUsername);
		request.getSession().setAttribute("reimbursements", reimbursements);
		
		request.getRequestDispatcher("manager-update-reimbursement.do").forward(request, response);
	}

}
