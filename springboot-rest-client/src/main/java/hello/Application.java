package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

   /* @Override
    public void run(String... strings) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }*/
    
    public void run(String... strings) throws Exception {
    	// WITHOUT AUTHENTICATION
/*
    	RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Employee emp = restTemplate.getForObject("localhost:8080/expensemeter/user", Employee.class);
        log.info(emp.toString());
        String uri = new String("http://" + mRESTServer.getHost() + ":8080/springmvc-resttemplate-test/api/{id}");
*/        
    	// USING BASIC AUTHENTICATION
    	
        Employee emp = new Employee();
        emp.setEmployeeId("1");
        emp.setEmployeeName("chandu");
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Basic Y2hhbmR1OmNoYW5kdQ==");
        headers.add("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);
        
        String uri = "http://localhost:8080/expensemeter/API/emp";
        Employee empRet = restTemplate.postForObject(uri, request, Employee.class);
        
        System.out.println("reponse = " + empRet.getEmployeeName());
       
        
    }
    
}