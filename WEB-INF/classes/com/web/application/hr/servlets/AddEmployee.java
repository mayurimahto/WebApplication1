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
			//validations applied for check
			//1. designationCode is correct
			//2. PAN Number should not exist
			//3. Aadhar Card Number should not exist


			DesignationDAO designationDAO=new DesignationDAO();
			EmployeeDAO employeeDAO=new EmployeeDAO();
			try
			{
				boolean designationCodeExists=designationDAO.designationCodeExists(designationCode);
				boolean panNumberExists=employeeDAO.panNumberExists(panNumber);
				boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
				if(designationCodeExists==false || panNumberExists==true || aadharCardNumberExists==true)
				//send back form with proper error message
				//copy code of GetEmployeeAddForm with few changes
				//add form resent because of validation problems
				//also need to ensure that all fields are filled with the data that user fed


pw.println("<!DOCTYPE HTML>");
pw.println("<html>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var firstInvalidComponent=null;");
pw.println("var valid=true;");
pw.println("var name=frm.name.value.trim();");
pw.println("var nameErrorSection=document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML='';");
pw.println("if(name.length==0)");
pw.println("{");
pw.println("nameErrorSection.innerHTML='Name Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.name;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("}");
pw.println("");
pw.println("var designationCode=frm.designationCode.value;");
pw.println("var designationCodeErrorSection=document.getElementById('designationCodeErrorSection');	");
pw.println("designationCodeErrorSection.innerHTML='';");
pw.println("if(designationCode==\"-1\")");
pw.println("{");
pw.println("designationCodeErrorSection.innerHTML='Select Designation';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.designationCode;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("}");
pw.println("");
pw.println("var dateOfBirth=frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');	");
pw.println("dateOfBirthErrorSection.innerHTML='';");
pw.println("if(dateOfBirth.length==0)");
pw.println("{");
pw.println("dateOfBirthErrorSection.innerHTML='Select Date of Birth';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.dateOfBirth;");
pw.println("}");
pw.println("");
pw.println("var genderErrorSection=document.getElementById('genderErrorSection');	");
pw.println("genderErrorSection.innerHTML='';");
pw.println("if(frm.gender[0].checked==false && frm.gender[1].checked==false)");
pw.println("{");
pw.println("genderErrorSection.innerHTML='Select Gender';");
pw.println("valid=false;");
pw.println("}");
pw.println("	");
pw.println("var basicSalary=frm.basicSalary.value.trim();");
pw.println("var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');	");
pw.println("basicSalaryErrorSection.innerHTML='';");
pw.println("if(basicSalary.length==0)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Salary Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("v='0123456789.';");
pw.println("var e=0;");
pw.println("var isBasicSalaryValid=true;");
pw.println("while(e<basicSalary.length)");
pw.println("{");
pw.println("if(v.indexOf(basicSalary.charAt(e))==-1)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic Salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("isBasicSalaryValid=false;");
pw.println("break;");
pw.println("}");
pw.println("e++;");
pw.println("}");
pw.println("if(isBasicSalaryValid)");
pw.println("{");
pw.println("var dot=basicSalary.indexOf('.');");
pw.println("if(dot!=-1)");
pw.println("{");
pw.println("var numberOfFractions=basicSalary.length-(dot+1);");
pw.println("if(numberOfFractions>2)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic Salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("isBasicSalaryValid=false;");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("");
pw.println("var panNumber=frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection=document.getElementById('panNumberErrorSection');	");
pw.println("panNumberErrorSection.innerHTML='';");
pw.println("if(panNumber.length==0)");
pw.println("{");
pw.println("panNumberErrorSection.innerHTML='PAN Number Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.panNumber;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("}");
pw.println("");
pw.println("var aadharCardNumber=frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection=document.getElementById('aadharCardNumberErrorSection');	");
pw.println("aadharCardNumberErrorSection.innerHTML='';");
pw.println("if(aadharCardNumber.length==0)");
pw.println("{");
pw.println("aadharCardNumberErrorSection.innerHTML='Aadhar Card Number Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.aadharCardNumber;");
pw.println("if(!valid)firstInvalidComponent.focus();");
pw.println("}");
pw.println("");
pw.println("return valid;");
pw.println("}");
pw.println("");
pw.println("function cancelAddition()");
pw.println("{");
pw.println("document.getElementById('cancelAdditionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/WebApplication1/images/logo.png' style='float:left;width:7hw;height:7vh'>");
pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>HR Application</div>");
pw.println("</div>");
pw.println("<!-- header ends here-->");
pw.println("");
pw.println("<!--content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("");
pw.println("<!--left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/WebApplication1/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br>");
pw.println("<a href='/WebApplication1/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!--left panel ends here -->");
pw.println("");
pw.println("");
pw.println("<!--right panel starts here -->");
pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Employee (Add Module)</h2>");
pw.println("");
pw.println("<form method='post' action='/WebApplication1/addEmployee' onsubmit='return validateForm(this)'>");
pw.println("");
pw.println("");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");
pw.println("Name");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='name' name='name' maxLength='50' size='51' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span>");

pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Designation");
pw.println("</td>");
pw.println("<td>");
pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");



List<DesignationDTO>designations=designationDAO.getAll();
int code;
String title;
for(DesignationDTO designation:designations)
{
	code=designation.getCode();
	title=designation.getTitle();
		
	if(code!=designationCode)
	{
	pw.println("<option value='"+code+"'>"+title+"</option>");
	}
	else
	{
	pw.println("<option selected value='"+code+"'>"+title+"</option>");
	}		
}


pw.println("</select>");

if(designationCodeExists==false)
{
pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid Designation</span>");
}
else
{
pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
}

pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Date of Birth");
pw.println("</td>");
pw.println("<td>");

pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");

pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Gender");
pw.println("</td>");
pw.println("<td>");


if(gender.equals("M")==false)
{
pw.println("<input type='radio' id='male' name='gender' value='M'>");
}
else
{
pw.println("<input checked type='radio' id='male' name='gender' value='M'>");
}

pw.println("Male");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");


if(gender.equals("F")==false)
{
pw.println("<input type='radio' id='female' name='gender' value='F'>");
}
else
{
pw.println("<input checked type='radio' id='female' name='gender' value='F'>");
}


pw.println("Female");
pw.println("<span id='genderErrorSection' style='color:red'></span>");

pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Indian?");
pw.println("</td>");
pw.println("<td>");

if(isIndian.equals("Y")==false)
{
pw.println("<input type='checkbox' name='isIndian' id='isIndian' value='Y'>");
}
else
{
pw.println("<input checkedtype='checkbox' name='isIndian' id='isIndian' value='Y'>");
}


pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Basic Salary");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' style='text-align:right'name='basicSalary' id='basicSalary' maxLength='12' size='13' value='"+basicSalary.toPlainString()+"'>");

pw.println("<span id='basicSalaryErrorSection' style='color:red'></span>");

pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("PAN Number");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxLength='15' size='16' value='"+panNumber+"'>");


if(panNumberExists==true)
{
pw.println("<span id='panNumberErrorSection' style='color:red'>Pan Number Exists</span>");
}
else
{
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
}

pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("Aadhar Card Number");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxLength='15' size='16' value='"+aadharCardNumber+"'>");

if(aadharCardNumberExists==true)
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar Card Exists</span>");
}
else
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>");
pw.println("<button type='submit'>Add</button>");
pw.println("</td>");
pw.println("<td>");
pw.println("<button type='Button' onclick='cancelAddition()'>Cancel</button>");
pw.println("</td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</form>");
pw.println("");
pw.println("</div>");
pw.println("<!-- right panel ends here -->");
pw.println("");
pw.println("</div>");
pw.println("<!--content-section ends here-->");
pw.println("");
pw.println("<!--footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>&copy; Mayuri Mahto 2025</div>");
pw.println("<!--footer ends here-->");
pw.println("");
pw.println("</div> ");
pw.println("<!--Main container ends here-->");
pw.println("");
pw.println("<form action='/WebApplication1/employeesView' id='cancelAdditionForm'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");



 
			}
			catch(DAOException daoException)
			{
				System.out.println(daoException.getMessage());
			}


			EmployeeDTO employee=new EmployeeDTO();
			employee.setName(name);
			employee.setDesignationCode(designationCode);
			employee.setDateOfBirth(dateOfBirth);
			employee.setGender(gender);
			employee.setIsIndian(isIndian.equals("Y"));
			employee.setBasicSalary(basicSalary);
			employee.setPANNumber(panNumber);
			employee.setAadharCardNumber(aadharCardNumber);

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
