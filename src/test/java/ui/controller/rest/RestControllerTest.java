package ui.controller.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import rest.Main;
import rest.model.domain.ContactDomain;
import rest.model.mapper.UiDomainImpl;
import rest.service.CrudRestService;
import rest.ui.controller.rest.RestCrudController;
import util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

    private MockMvc mockMvc;
    
	private final Gson gson = new Gson();	
	
	private final String PATH_GET = "/crud/get";	
	private final String PATH_PUT = "/crud/put";
	
	private final ResultMatcher IS_OK =  MockMvcResultMatchers.status().isOk();
	private final ResultMatcher IS_NOT_FOUND = MockMvcResultMatchers.status().isNotFound();
	private final ResultMatcher IS_BAD_REQUEST = MockMvcResultMatchers.status().isBadRequest();

	@Mock
	private CrudRestService crudRestService;

	@Before
	public void setup(){        
		mockMvc = MockMvcBuilders.standaloneSetup(new RestCrudController(crudRestService, new UiDomainImpl())).build();
	}       

	@Test
	public void getNoContactUiAllTest() throws Exception {        
		given(crudRestService.findAll()).willReturn(new ArrayList<ContactDomain>());   
		String expect = "there are no contacts";
		MockHttpServletRequestBuilder requestGet = getGetRequestBuilder(PATH_GET); 
		request(expect, IS_NOT_FOUND, requestGet);    
	}

	@Test
	public void getContactUiAllTest() throws Exception {
		given(crudRestService.findAll()).willReturn(TestUtils.getContactDomainAll());        
		String expect =  gson.toJson(TestUtils.getContactUiAll());
		MockHttpServletRequestBuilder requestGet = getGetRequestBuilder(PATH_GET); 
		request(expect, IS_OK, requestGet);
	}

	@Test
	public void getByIdContactUiTest() throws Exception {
		Long id = 1l;
		given(crudRestService.getById(id)).willReturn(TestUtils.getDomainFirstContact());        
		String expect = gson.toJson(TestUtils.getUiFirstContact());
		String path = PATH_GET+"/"+id;
        MockHttpServletRequestBuilder requestGet = getGetRequestBuilder(path); 
		request(expect, IS_OK, requestGet);      
	} 
	
	@Test
	public void getNoByIdContactUiTest() throws Exception {
		Long id = -1l;
		String expect = "there are no contact id = -1";
		String path = PATH_GET+"/"+id;
		MockHttpServletRequestBuilder requestGet = getGetRequestBuilder(path); 
		request(expect, IS_NOT_FOUND, requestGet);      
	} 	

	@Test
	public void putContactUiTest() throws Exception{       
		when(crudRestService.save(any(ContactDomain.class))).thenReturn(new ContactDomain(1l, "Alex", "+380501234561,+380937654321")); 
		String expected = gson.toJson(TestUtils.getDomainFirstContact());
		String contentUi = gson.toJson(TestUtils.getUiFirstContact());
		MockHttpServletRequestBuilder requestPut = getPutRequestBuilder(contentUi);
		request(expected, IS_OK, requestPut);			
	}
    
	@Test
	public void putNotContactUiTest() throws Exception{		
	    String contentUi = "";
		MockHttpServletRequestBuilder requestPut = getPutRequestBuilder(contentUi);
		request(contentUi, IS_BAD_REQUEST, requestPut);
	}
	
	private void request(String expected, ResultMatcher resultMatcher, MockHttpServletRequestBuilder requestBuilder) throws Exception {
        this.mockMvc.perform(requestBuilder)
        .andExpect(resultMatcher) 
        .andExpect(content().string(expected))  
        .andDo(MockMvcResultHandlers.print());
    }
	
    private MockHttpServletRequestBuilder getGetRequestBuilder(String path) {
        return MockMvcRequestBuilders.get(path);
    }
	
	private MockHttpServletRequestBuilder getPutRequestBuilder(String contentUi) {
        return MockMvcRequestBuilders.put(PATH_PUT)
        .contentType(MediaType.APPLICATION_JSON)
        .content(contentUi);
    } 
}




