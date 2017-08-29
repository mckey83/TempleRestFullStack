package rest.ui.controller.rest;



import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.model.domain.ContactDomain;
import rest.model.mapper.UiDomain;
import rest.model.ui.ContactUi;
import rest.service.CrudRestService;

@RestController
@RequestMapping("/crud")
public class RestCrudController {

    public RestCrudController(CrudRestService crudRestService, UiDomain uiDomain) {       
        this.crudRestService = crudRestService;
        this.uiDomain = uiDomain;
    }

    @Autowired    
    private CrudRestService crudRestService;

    @Autowired
    private UiDomain uiDomain;	

    @GetMapping("/get")
    public ResponseEntity<?> findAll() {        
        List<ContactDomain> domainAll = crudRestService.findAll();        
        if(domainAll.size() == 0){
            return new ResponseEntity<>("there are no contacts", NOT_FOUND);
        }
        List<ContactUi> result = new ArrayList<>();
        for(ContactDomain domain: domainAll){		
            result.add(uiDomain.getUi(domain));
        }
        return new ResponseEntity<>(result, OK);        
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {        
        ContactDomain domain = crudRestService.getById(id);        
        if(domain == null){
            return new ResponseEntity<>(format("there are no contact id = %d", id), NOT_FOUND);
        }        
        return new ResponseEntity<>(uiDomain.getUi(domain), OK);    
    }

    @PutMapping(value = "/put")
    public ResponseEntity<?> save(@RequestBody ContactUi contact) { 
        ContactDomain domain = crudRestService.save(uiDomain.getDomain(contact));       
        if (domain == null){            
            return new ResponseEntity<>(format("error to name = %s", contact.getName()), BAD_REQUEST);
        }
        return new ResponseEntity<>(domain, OK); 
    }

       
    @PostMapping(value = "/post/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ContactUi contact) {        
        ContactDomain toUpdate = uiDomain.getDomain(contact);
        toUpdate.setId(id);        
        ContactDomain domain = crudRestService.save(toUpdate);       
        if (domain == null){            
            return new ResponseEntity<>(format("error to name = %s", contact.getName()), BAD_REQUEST);
        }
        return new ResponseEntity<>(domain, OK); 
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {		
        crudRestService.delete(id);		
    }

}
