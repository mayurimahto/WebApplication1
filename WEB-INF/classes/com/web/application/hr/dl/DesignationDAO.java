package com.web.application.hr.dl;
import java.sql.*;
import java.util.*;

public class DesignationDAO
{
	public void add(DesignationDTO designation) throws DAOException
	{
		try
		{
			Connection connection=DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement=connection.prepareStatement("select * from designation where title=?");
			preparedStatement.setString(1,designation.getTitle());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()==true)
			{
				resultSet.close();
				preparedStatement.close();
				throw new DAOException("Designation : "+designation.getTitle()+" exists.");	
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("insert into designation(title) values(?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,designation.getTitle());
			preparedStatement.executeUpdate();
			resultSet=preparedStatement.getGeneratedKeys();
			resultSet.next();
			int code=resultSet.getInt(1);
			resultSet.close();
			preparedStatement.close();
			connection.close();
			designation.setCode(code);
		}
		catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage()); //remove after testing
		}
	}
	public List<DesignationDTO>getAll() throws DAOException
	{
		List<DesignationDTO> designations;
		designations=new LinkedList<>();
		try
		{	
			Connection connection=DAOConnection.getConnection();
			Statement statement;
			statement=connection.createStatement();
			ResultSet resultSet;
			resultSet=statement.executeQuery("select * from designation order by title");
			DesignationDTO designationDTO;
			int code;
			String title;
			while(resultSet.next())
			{
				code=resultSet.getInt("code");
				title=resultSet.getString("title").trim(); //trim is vvv imp
				designationDTO=new DesignationDTO();
				designationDTO.setCode(code);
				designationDTO.setTitle(title);
				designations.add(designationDTO);
			}
			resultSet.close();
			statement.close();
			connection.close();	
		}
		catch(Exception exception)
		{
			throw new DAOException(exception.getMessage());
		}
		return designations;
	}

	public DesignationDTO getByCode(int code) throws DAOException
	{
		try
		{
			Connection connection=DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement=connection.prepareStatement("select * from designation where code=?");
			preparedStatement.setInt(1,code);
			ResultSet resultSet;
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()==false)
			{
				//this could happen in cases when many users are on the web and delete the record that we are trying to fetch 
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid designation code : "+code);
			}
			DesignationDTO designationDTO=new DesignationDTO();
			designationDTO.setCode(resultSet.getInt("code"));
			designationDTO.setTitle(resultSet.getString("title").trim());
			resultSet.close();
			preparedStatement.close();
			connection.close();
			return designationDTO;
		}
		catch(Exception exception)
		{
			throw new DAOException(exception.getMessage());
		}
	}

	public void update(DesignationDTO designationDTO) throws DAOException
	{
		try
		{
			int code=designationDTO.getCode();
			String title=designationDTO.getTitle();
			Connection connection=DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement=connection.prepareStatement("select * from designation where code=?");
			preparedStatement.setInt(1,code);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()==false)
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid designation code : "+code);
			}
			// This condition when title matches but code is different
			resultSet.close();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("select * from designation where title=? and code!=?");
			preparedStatement.setString(1,title);
			preparedStatement.setInt(2,code);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()==true)
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException(title+" exists.");
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
			preparedStatement.setString(1,title);
			preparedStatement.setInt(2,code);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		}
		catch(Exception exception)
		{
			throw new DAOException(exception.getMessage());
		}
	}
}