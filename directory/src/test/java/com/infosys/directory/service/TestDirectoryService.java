package com.infosys.directory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.math.BigInteger;
import java.time.LocalDate;

import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import com.infosys.directory.DirectoryConfig;
import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.exceptions.InvalidValueException;
import com.infosys.directory.repository.CustomRepositoryInterface;
import com.infosys.directory.repository.EmployeeRepository;
import com.infosys.directory.service.DirectoryService;
import com.infosys.directory.utility.EmployeeServiceUtil;





//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
//@WebMvcTest(value=DirectoryService.class,secure=false)
@DisplayName("directoryServiceTest")
//@ContextConfiguration(classes = DirectoryConfig.class) 
public class TestDirectoryService {

	
	List<EmployeeDTO> empListDTO=Arrays.asList(
		
		new EmployeeDTO("Adam",LocalDate.of(1980, 2, 17),"Male",new BigInteger(String.valueOf(6000000)),"IT"),
		
		new EmployeeDTO("Adam",LocalDate.of(1980, 2, 17),"Female",new BigInteger(String.valueOf(6000000)),"IT")
		
		
		
	);
	
	
	//private MockMvc mockMvc;
	
	
	@Mock
	EmployeeRepository employeeRepo;
	@Mock
	CustomRepositoryInterface custRepo;
	
	
	
	
	@Mock
	EmployeeServiceUtil employeeServiceUtil;
	
	@Mock
	DirectoryConfiguration config;

	@InjectMocks
	DirectoryService directoryService;

   @BeforeEach
    void setMockOutput() {
	   
   
    MockitoAnnotations.initMocks(this);
    Mockito.lenient().when(config.validDepartments()).thenReturn(new String[] {"IT", "ADMIN", "FINANCE", "HR", "CORE"});
	
	Mockito.lenient().when(config.validMetrics()).thenReturn(new String[] {"MONTH","YEAR","DATEOFBIRTH","GENDER","SALARY","DEPARTMENT","NAME"});
	
 }
//    @Test
//    @DisplayName("fallbackMethod")
//    public void fallbackMethodLoads() {
//
//    assertEquals(new Exception("Not a Valid URL").getMessage(),directoryService.fallbackMethod().getMessage());
//    }


      @Test
      @DisplayName("fetchEmployees")
      public void testfetchEmployees() {

    	  
    	 
    	    when(employeeRepo.findAll()).thenReturn(empListDTO.parallelStream().map(x->x.createEntity()).collect(Collectors.toList()));

    	  assertEquals(empListDTO.size(),directoryService.fetchAllEmployees().size());


      }

    

			@Test
			@DisplayName("fetchAllEmployeesByDepartment")
			public void testfetchAllEmployeesByDepartment() throws InvalidValueException{
				
				Mockito.lenient().when(employeeRepo.findByDepartment("IT")).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
						.filter(x->x.getDepartment().toUpperCase().equals("IT")) .collect(Collectors.toList()));

				//Mockito.lenient().when(config.validDepartments()).thenReturn(new String[] {"IT", "ADMIN", "FINANCE", "HR", "CORE"});
				Mockito.lenient(). when( employeeServiceUtil.isValidMetric("IT",config.validDepartments())).thenReturn(true);
				
				  
//				  try {
					assertEquals(empListDTO.size(),directoryService.fetchAllEmployeesByDepartment("IT").size());
//				} catch (InvalidValueException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			
			
			}
			
			@Test
			@DisplayName("fetchAllEmployeeByDepartmentAndSalary")
			public void testfetchAllEmployeeByDepartmentAndSalary()throws InvalidValueException
			{

				Mockito.lenient().when(employeeRepo.findByDepartmentAndSalaryGreaterThan("IT",new BigInteger(String.valueOf(2000))))
				
				
				.thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
						.filter(x->x.getDepartment().toUpperCase().equals("IT")) .collect(Collectors.toList()));
				Mockito.lenient(). when( employeeServiceUtil.isValidMetric("IT",config.validDepartments())).thenReturn(true);
				
				
//				try {
					assertEquals(empListDTO.size(),directoryService.fetchAllEmployeeByDepartmentAndSalary("IT", "2000").size());
//				} catch (InvalidValueException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			@Test
			@DisplayName("fetchAllEmployeeByDepartmentAndAge")
			public void testfetchAllEmployeeByDepartmentAndAge() throws InvalidValueException{
				
				
				Mockito.lenient().when(employeeRepo.findByDepartment("IT")).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
						.filter(x->x.getDepartment().toUpperCase().equals("IT")) .collect(Collectors.toList()));
				Mockito.lenient(). when( employeeServiceUtil.isValidMetric("IT",config.validDepartments())).thenReturn(true);
				
				
				List <EmployeeDTO> testEmpl=empListDTO.parallelStream().filter(x->x.getAge()>18).collect(Collectors.toList());
//				try {
					assertEquals(testEmpl.size(),directoryService.fetchAllEmployeeByDepartmentAndAge("IT", 18).size());
//				} catch (InvalidValueException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
			
			
			
			@Test
			@DisplayName("fetchAllEmployeeByDepartmentAndMetric")
			public void testfetchAllEmployeeByDepartmentAndMetric()throws InvalidValueException{
				
				Mockito.lenient().when(custRepo.getData("gender","Male")).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
						.filter(x->x.getGender().toUpperCase().equals("MALE")) .collect(Collectors.toList()));
				List <EmployeeDTO> testEmpl=empListDTO.parallelStream().filter(x->x.getGender().equals("Male") && x.getDepartment().toUpperCase()
						.equals("IT")).collect(Collectors.toList());
				
					
				Mockito.lenient().when( employeeServiceUtil.isValidMetric("IT",config.validDepartments())).thenReturn(true);
					Mockito.lenient(). when( employeeServiceUtil.isValidMetric("gender",config.validMetrics())).thenReturn(true);
					Mockito.lenient(). when(employeeServiceUtil.isValidN("Male", "gender")).thenReturn(true);
//					Mockito.lenient().when(employeeRepo.findByDepartment("it")).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
//							.filter(x->x.getDepartment().toUpperCase().equals("IT")) .collect(Collectors.toList()));
					assertEquals(testEmpl.size(),directoryService.fetchAllEmployeeByDepartmentAndMetric("IT","Male", "gender").size());
				
				
			}
			
			
			
}




//DirectoryConfiguration config=Mockito.spy(DirectoryConfiguration.class);//()

//Mockito.lenient().when(config).validDepartments()).thenReturn(new String[] {"IT","CORE"});
//String orMatcher = or(eq("poppy"), endsWith("y"));
// when(employeeRepo.findByDepartment(anyString())).thenReturn(empListDTO.parallelStream().map(x->x.createEntity())	
//			 .collect(Collectors.toList()));
  // when(list.add("abc")).thenReturn(false);
//empListDTO=mockedEmployeeRepository.findAll().parallelStream().map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
////directoryService=new DirectoryService(mockedEmployeeRepository);
//directoryService=new DirectoryService(mockedEmployeeRepository, custRepo, expectionHandler, config, employeeServiceUtil);

//mockMvc=MockMvcBuilders
//.standaloneSetup(directoryService)
//.setControllerAdvice(new ExceptionControllerAdvice())
//.build();


//final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
//
////here we need to setup a dummy application context that only registers the GlobalControllerExceptionHandler
//final StaticApplicationContext applicationContext = new StaticApplicationContext();
//applicationContext.registerBeanDefinition("advice", new RootBeanDefinition(ExceptionControllerAdvice.class));
//
////set the application context of the resolver to the dummy application context we just created
//exceptionHandlerExceptionResolver.setApplicationContext(applicationContext);
//
////needed in order to force the exception resolver to update it's internal caches
//exceptionHandlerExceptionResolver.afterPropertiesSet();
//
//mockMvc = MockMvcBuilders.standaloneSetup(directoryService).setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();

//User userToReturnFromRepository = new User();
//userToReturnFromRepository.setAuthToken(YOUR_TOKEN);
//when(mockedUserRepository.save(any(UserDto.class)).thenReturn(userToReturnFromRepository);
//
//UserDto found = userService.create(userDtoRequest);
//	  List<Employee> empListDTO=new ArrayList<EmployeeDTO>();
//Mockito.doReturn(empListDTO).when().forEach(x->EmployeeDTO.valueOf(x));
//			.parallelStream()
//			.map().collect(Collectors.toList()));
//DirectoryService directoryService=new DirectoryService();
