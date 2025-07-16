package com.web.application.hr.servlets;
import com.web.application.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.io.*;

public class AddDesignation extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter pw=null;
		String title="";
		try
		{
			pw=response.getWriter();
			title=request.getParameter("title");
			DesignationDTO designation=new DesignationDTO();
			designation.setTitle(title);
			DesignationDAO designationDAO=new DesignationDAO();
			designationDAO.add(designation);

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
			pw.println("Designation added<br>Add more designations?<br>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<td>");
			pw.println("<form action='/WebApplication1/AddDesignation.html'>");
			pw.println("<button type='submit'>Yes</button>");
			pw.println("</form>");
			pw.println("</td>");
			pw.println("<td>");
			pw.println("<form action='/WebApplication1/designationsView'>");
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
			//pw=response.getWriter();
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>HR Application</title>");
			pw.println("<script>");
			pw.println("function validateForm(frm)");
			pw.println("{");
			pw.println("var title=frm.title.value.trim();");
			pw.println("var titleErrorSection=document.getElementById('titleErrorSection');");	
			pw.println("titleErrorSection.innerHTML='';");
			pw.println("if(title.length==0)");
			pw.println("{");
			pw.println("titleErrorSection.innerHTML='Required';");
			pw.println("frm.title.focus();");
			pw.println("return false;");
			pw.println("}");
			pw.println("return true;");
			pw.println("}");
			pw.println("function cancelAddition()");
			pw.println("{");
			pw.println("document.getElementById('cancelAdditionForm').submit();");
			pw.println("}");

			pw.println("</script>");
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
			pw.println("<b>Designations</b><br>");
			pw.println("<a href='/WebApplication1/employeesView'>Employees</a><br>");
			pw.println("<a href='/WebApplication1/index.html'>Home</a>");
			pw.println("</div>");
			pw.println("<!--left panel ends here -->");


			pw.println("<!--right panel starts here -->");
			pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
			pw.println("<h2>Designation (Add Module)</h2>");


			pw.println("<div style='color:red'>"+daoException.getMessage()+"</div>");

			pw.println("<form action='/WebApplication1/addDesignation' onsubmit='return validateForm(this)'>");
			pw.println("Designation");


			pw.println("<input type='text' id='title' name='title' maxLength='35' size='36' value='"+title+"'>");


			pw.println("<span id='titleErrorSection' style='color:red'></span><br>");
			pw.println("<button type='submit'>Add</button>");
			pw.println("<button type='button' onclick='cancelAddition()'>Cancel</button>");
			pw.println("</form>");

			pw.println("<form action='/WebApplication1/designationsView' id='cancelAdditionForm'>");
			pw.println("</form>");

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