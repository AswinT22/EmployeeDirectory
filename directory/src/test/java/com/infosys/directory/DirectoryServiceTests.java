//package com.infosys.directory;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//
//import com.infosys.directory.utility.ClientErrorInformation;
//import com.infosys.directory.utility.ExceptionControllerAdvice;
//
//
//
//
//
//@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(value=DirectoryServiceTests.class,secure=false)
//@DisplayName("Spring boot 2 mockito2 Junit5 example")
//public class DirectoryServiceTests {
//   // private static final String MOCK_OUTPUT = "Mocked show label";
//	
//	@Autowired
//	private MockMvc mockMVC;
//    @MockBean
//    private	DirectoryService directoryService;
//    @Mock
//    ExceptionControllerAdvice eca;
//    //@Mocks
//   // private ShowService showService;
//   // @BeforeEach
////    void setMockOutput() {
////         when(textService.getText()).thenReturn(MOCK_OUTPUT);
////    }
//    @Test
//    @DisplayName("Mock the output of the text service using mockito")
//    public void contextLoads() {
//    	
////    	new Respons
////    	    "status": "BAD_REQUEST",
////    	    "timestamp": "28-07-2019 02:39:53",
////    	    "message": "Not a Valid URL",
////    	    "debugMessage": "Not a Valid URL",
////    	    "subErrors": null
////    	}
//    ResponseEntity<Object> res= eca.buildResponseEntity(new ClientErrorInformation(HttpStatus.NOT_FOUND, "Not a Valid URL",new Exception("Not a valid Url")));
//        
//    
//    assertEquals(directoryService.fallbackMethod(), res);
//    }
//}