package com.web.application.hr.servlets;
import com.web.application.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ConfirmDeleteDesignation extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int code=0;
			try
			{
				code=Integer.parseInt(request.getParameter("code"));
			}
			catch(NumberFormatException number)
			{
				//send back view page
				//address bar m hi agar kuch galat salat code karde toh
				//designationsView


				sendBackView(response);
				return;
			}
			DesignationDAO designationDAO=new DesignationDAO();
			try
			{
				DesignationDTO designationDTO=designationDAO.getByCode(code);
				PrintWriter pw=response.getWriter();
				response.setContentType("text/html");

				pw.println("<!DOCTYPE HTML>");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<title>HR Application</title>");
				pw.println("<script>");

				pw.println("function cancelDeletion()");
				pw.println("{");
				pw.println("document.getElementById('cancelDeletionForm').submit();");
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
				pw.println("<h2>Designation (Delete Module)</h2>");

				pw.println("<form method='post' action='/WebApplication1/deleteDesignation'>");


				pw.println("Designation : ");

				pw.println("<input type='hidden' id='code' name='code' value='"+code+"'>");

				pw.println("<b>"+designationDTO.getTitle()+"</b><br><br>");

				pw.println("Are you sure you want to delete this designation ? <br><br>");				

				pw.println("<button type='submit'>Yes</button>");

				pw.println("<button type='button' onclick='cancelDeletion()'>No</button>");

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

				pw.println("<form id='cancelDeletionForm' action='/WebApplication1/designationsView'>");
				pw.println("</form>");

				pw.println("</body>");
				pw.println("</html>");
				pw.println("}");
			}
			catch(DAOException daoException)
			{
				//send back view page
				// when getByCode is called and code is not found
				//designationsView
				sendBackView(response);
				return;
			}
		}catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		doGet(request, response);
	}

	private void sendBackView(HttpServletResponse response)
	{
		try
		{
			//code from DesignationView.java

			DesignationDAO designationDAO;
			designationDAO=new DesignationDAO();
			List<DesignationDTO> designations;
			designations=designationDAO.getAll();
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");

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
			pw.println("<a href='/WebApplication1/index.html'><img src='/WebApplication1/images/logo.png' style='float:left;width:7hw;height:7vh'></a>");
			pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>HR Application</div>");
			pw.println("</div>");
			pw.println("<!-- header ends here-->");

			pw.println("<!--content-section starts here -->");
			pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");

			pw.println("<!--left panel starts here -->");
			pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
			pw.println("<b>Designations</b></br>");
			pw.println("<a href='/WebApplication1/employeesView'>Employees</a></br>");
			pw.println("<a href='/WebApplication1/index.html'>Home</a>");
			pw.println("</div>");
			pw.println("<!--left panel ends here -->");

			pw.println("<!--right panel starts here -->");
			pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;overflow:scroll;border:1px solid black'>");
			pw.println("<h2>Designations</h2>");
			pw.println("<table border='1'>");
			pw.println("<thead>");
			pw.println("<tr>");
			pw.println("<th colspan='4' style='text-align:right;padding:5px'>");
			pw.println("<a href='/WebApplication1/AddDesignation.html'>Add new designation</a>");
			pw.println("</th>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
			pw.println("<th style='width:200px;text-align:center'>Designation</th>");
			pw.println("<th style='width:100px;text-align:center'>Edit</th>");
			pw.println("<th style='width:100px;text-align:center'>Delete</th>");
			pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");
			

			int x;
			DesignationDTO designationDTO;
			int code;
			String title;
			int sno=0;
			for(x=0;x<designations.size();x++)
			{
			sno++;
			designationDTO=designations.get(x);
			code=designationDTO.getCode();
			title=designationDTO.getTitle();
			pw.println("<tr>");
			pw.println("<td style='text-align:right'>"+sno+"</td>");
			pw.println("<td>"+title+"</td>");
			pw.println("<td style='text-align:center'><a href='/WebApplication1/editDesignation?code="+code+"'>Edit</a></td>");
			pw.println("<td style='text-align:center'><a href='/WebApplication1/confirmDeleteDesignation?code="+code+"'>Delete</a></td>");
			pw.println("</tr>");
			}


			pw.println("</tbody>");
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
			System.out.println(daoException.getMessage()); //remove after testing and setup 500(internal error page)
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage()); //remove after testing and setup 500(internal error page)
		}

	}
}