package com.web.application.hr.servlets;
import com.web.application.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class AddEmployee extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			
			String name=request.getParameter("name");
			int designationCode=Integer.parseInt(request.getParameter("designationCode"));
			Date dateOfBirth=simpleDateFormat.parse(request.getParameter("dateOfBirth"));
			String gender=request.getParameter("gender");
			String isIndian=request.getParameter("isIndian");
			if(isIndian==null)isIndian="N";
			BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
			String panNumber=request.getParameter("panNumber");
			String aadharCardNumber=request.getParameter("aadharCardNumber");
			//validations need to be applied for chec
			//1. designationCode is correct
			//2. PAN Number should not exist
			//3. Aadhar Card Number should not exist

			EmployeeDTO employee=new EmployeeDTO();
			employee.setName(name);
			employee.setDesignationCode(designationCode);
			employee.setDateOfBirth(dateOfBirth);
			employee.setGender(gender);
			employee.setIsIndian(isIndian.equals("Y"));
			employee.setBasicSalary(basicSalary);
			employee.setPANNumber(panNumber);
			employee.setAadharCardNumber(aadharCardNumber);

			EmployeeDAO employeeDAO=new EmployeeDAO();
			try
			{
				employeeDAO.add(employee);
				//the following code has been picked up from AddDesignation code with few changes
				pw.println("<!DOCTYPE HTML>");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<title>HR Application</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<!-- Main container starts here -->");
				pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");

				pw.println("<!-- header starts here -->");
				pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
				pw.println("<img src='/WebApplication1/images/logo.png' style='float:left;width:7hw;height:7vh'>");
				pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>HR Application</div>");
				pw.println("</div>");
				pw.println("<!-- header ends here-->");

				pw.println("<!--content-section starts here -->");
				pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");

				pw.println("<!--left panel starts here -->");
				pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
				pw.println("<a href='/WebApplication1/designationsView'>Designations</a><br>");
				pw.println("<a href='/WebApplication1/employeesView'>Employees</a><br>");
				pw.println("<a href='/WebApplication1/index.html'>Home</a>");
				pw.println("</div>");
				pw.println("<!--left panel ends here -->");


				pw.println("<!--right panel starts here -->");
				pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
		

				pw.println("<h3>Notification</h3>");
				pw.println("Employee added<br>Add more employees?<br>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<td>");
				pw.println("<form action='/WebApplication1/getEmployeeAddForm'>");
				pw.println("<button type='submit'>Yes</button>");
				pw.println("</form>");
				pw.println("</td>");
				pw.println("<td>");
				pw.println("<form action='/WebApplication1/employeesView'>");
				pw.println("<button type='submit'>No</button>");
				pw.println("</form>");
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</table>");

				pw.println("</div>");
				pw.println("<!-- right panel ends here -->");

				pw.println("</div>");
				pw.println("<!--content-section ends here-->");

				pw.println("<!--footer starts here -->");
				pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>&copy; Mayuri Mahto 2025</div>");
				pw.println("<!--footer ends here-->");

				pw.println("</div>"); 
				pw.println("<!--Main container ends here-->");
				pw.println("</body>");
				pw.println("</html>");
			}
			catch(DAOException daoException)
			{
				//recreate form with error message
				//and send back the page
			}
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		
		doGet(request, response);
	}
		
}
