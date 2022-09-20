package com.airlinq.Project_Informica.service.roles_service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.airlinq.Project_Informica.dao.DaoRoles;
import com.airlinq.Project_Informica.entities.Role_Access;
import com.airlinq.Project_Informica.exception.ResourceNotFoundException;

/**
 * The RolesServiceImpl class defines all the RolesService interface methods.
 * All the methods in this class will be invoked by the controller class
 * for accepting the request and giving the response.
 * 
 * @author Ankit Sharma
 * @version 1.0
 *
 */

@Service
public class RolesServiceImpl implements RolesService{
	
	String qry;
	
	@Autowired
	private DaoRoles daoRoles;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRolesAccess userRolesAccess;

	
	/**
	 * The function getAllRolesDetails fetches all roles details from the database.
	 */
	@Override
	public ResponseEntity<List<Role_Access>> getAllRolesDetails() {
		
		if(userRolesAccess.permission("getAllRolesDetails") != true) {
			throw new ResourceNotFoundException("You do not have permission for this API");
		}
		
		List<Role_Access> datalist = daoRoles.findAll();
		return new ResponseEntity<>(datalist, HttpStatus.OK);
	}

	/**
	 * The function getRolesDetails fetches particular role details from the database.
	 */
	@Override
	public ResponseEntity<Object> getRolesDetails(String user_email) {
		
		if(userRolesAccess.permission("getRolesDetails") != true) {
			throw new ResourceNotFoundException("You do not have permission for this API");
		}
		
		qry = "Select * from role_access where user_email = " + user_email + ";";
		
		List<Map<String, Object>> roles_details = jdbcTemplate.queryForList(qry);
		
		if(roles_details.isEmpty() == true) {

			throw new ResourceNotFoundException("User not found");
		}
		return new ResponseEntity<>(roles_details.get(0), HttpStatus.OK);
		
	}

	/**
	 * The function addRolesDetails adds new role in the database.
	 */
	@Override
	public ResponseEntity<Role_Access> addRolesDetails(Role_Access roleAccess) {
		System.out.print("hss");
		System.out.println(userRolesAccess.permission("addRolesDetails"));
		if(userRolesAccess.permission("addRolesDetails") != true) {
			throw new ResourceNotFoundException("You do not have permission for this API");
		}
		
		daoRoles.save(roleAccess);
		return new ResponseEntity<>(roleAccess,HttpStatus.OK);
		
	}

	/**
	 * The function deleteRolesDetails deletes the role from the database.
	 */
	@Override
	public ResponseEntity<String> deleteRolesDetails(String role_id) {

		if(userRolesAccess.permission("deleteRolesDetails") != true) {
			throw new ResourceNotFoundException("You do not have permission for this API");
		}
		qry = "Delete from role_access where role_id = " + Integer.parseInt(role_id) +";"; 
		jdbcTemplate.execute(qry);
		return new ResponseEntity<>("User Deleted!",HttpStatus.OK);
		
	}

}
