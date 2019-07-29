package com.infosys.directory.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.entity.Employee;
import com.infosys.directory.exceptions.InvalidValueException;
import com.infosys.directory.model.EmployeeModel;
import com.infosys.directory.model.QueryModel;
import com.infosys.directory.repository.CustomRepositoryInterface;
import com.infosys.directory.repository.EmployeeRepository;
import com.infosys.directory.utility.EmployeeServiceUtil;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("adminServiceTest")
public class TestAdminService {
	
	List<EmployeeDTO> empListDTO=Arrays.asList(
			
			new EmployeeDTO("Adam",LocalDate.of(1980, 2, 17),"Male",new BigInteger(String.valueOf(6000000)),"IT"),
			
			new EmployeeDTO("Adam",LocalDate.of(1980, 2, 17),"Female",new BigInteger(String.valueOf(6000000)),"IT")
			
			
			
		);
	@Mock
	EmployeeRepository employeeRepo;
	@Mock
	CustomRepositoryInterface custRepo;
	@Mock
	EmployeeServiceUtil employeeServiceUtil;
	
	@Mock
	DirectoryConfiguration config;

	@InjectMocks
	AdminService adminService;
	
	  @BeforeEach
	    void setMockOutput() {
		   
	   
	    MockitoAnnotations.initMocks(this);
	    Mockito.lenient().when(config.validDepartments()).thenReturn(new String[] {"IT", "ADMIN", "FINANCE", "HR", "CORE"});
		
		Mockito.lenient().when(config.validMetrics()).thenReturn(new String[] {"MONTH","YEAR","DATEOFBIRTH","GENDER","SALARY","DEPARTMENT","NAME"});
		
	 }
	  @Test
      @DisplayName("add")
      public void testadd() throws Exception {
		  
		  
		  EmployeeModel employee=new EmployeeModel("Add Test",LocalDate.of(1996, 2, 17),"Female","40000","HR");
		  EmployeeDTO emp=EmployeeDTO.modelOf(employee);
		  Mockito.lenient().when(employeeServiceUtil.validateEmployee(Mockito.any(EmployeeDTO.class))).thenReturn(true);
		 Mockito.lenient().when(employeeServiceUtil.isValidMetric(emp.getDepartment().toUpperCase(),config.validDepartments())).thenReturn(true);
		  assertEquals(true, adminService.add(employee));
	  
	  
	  }
	  
	  @Test
      @DisplayName("addEmployees")
      public void testaddEmployees() throws Exception {
		  
		  
		  Mockito.lenient().when(employeeServiceUtil.validateEmployee(Mockito.any(EmployeeDTO.class))).thenReturn(true);
		  assertEquals(0, adminService.addEmployees(empListDTO).size());
	  }
	  @Test
      @DisplayName("processQuery")
	  public void testprocessQuery() throws Exception {
		  
		  Mockito.lenient().when(custRepo.getData("gender","Male")).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
					.filter(x->x.getGender().toUpperCase().equals("MALE")) .collect(Collectors.toList()));
				  List<Employee> emp=empListDTO.parallelStream().map(x->x.createEntity()).collect(Collectors.toList());
				 // emp.parallelStream().map(x->x.setSalary(new BigInteger( "30000"))
				  
				empListDTO=  empListDTO.parallelStream().filter(x->x.getGender().toUpperCase().equals("MALE")).collect(Collectors.toList());
				empListDTO.parallelStream().forEach(x->x.setSalary(new BigInteger("30000")));
				//.map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
				Mockito.lenient().when(employeeServiceUtil.isValidQuery(Mockito.any(QueryModel.class))).thenReturn(true);
		  Mockito.lenient().when(employeeServiceUtil.updateEmployeeList(Mockito.anyListOf(Employee.class),Mockito.anyString(), Mockito.anyString()))
				  .thenReturn(empListDTO.parallelStream().map(x->x.createEntity()).collect(Collectors.toList()));
		  QueryModel query=new QueryModel("gender","Male", "salary", "30000");
		  assertEquals(empListDTO.size(),adminService.processQuery(query).size());
		  
	  }
	  
	  @Test
      @DisplayName("processQueries")
	  public void testprocessQueries() throws Exception {
			Mockito.lenient().when(employeeServiceUtil.isValidQuery(Mockito.any(QueryModel.class))).thenReturn(true);
			  Mockito.lenient().when(employeeServiceUtil.updateEmployeeList(Mockito.anyListOf(Employee.class),Mockito.anyString(), Mockito.anyString()))
					  .thenReturn(empListDTO.parallelStream().map(x->x.createEntity()).collect(Collectors.toList()));
		 
		//  Mockito.lenient().when(adminService.processQuery(Mockito.any(QueryModel.class))).thenReturn(empListDTO);
		  
		  QueryModel[] queries=new QueryModel[] {new QueryModel("gender","Male", "salary", "30000"),new QueryModel("name","DALE", "gender", "Female")};
		  assertEquals(0,adminService.processQueries(queries).size());
		  
	  }

}
