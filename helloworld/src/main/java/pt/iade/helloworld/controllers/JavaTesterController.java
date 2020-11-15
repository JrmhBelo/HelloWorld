package pt.iade.helloworld.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.helloworld.models.CurricularUnit;

@RestController
@RequestMapping(path="/api/java/tester")
public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);
    
    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();
 
    @GetMapping(path = "/author", produces= MediaType.APPLICATION_JSON_VALUE) //localhost:8080/api/java/tester/author
    public String getAuthor() {
        logger.info("Infomacao do autor");
        String name = "João Belo";
        int number = 20190848;
        String height = "1.83" ;
        String fan = "False" ;
        String favclub = "Sporting";
        if (fan == "True"){
            return "Hi I'm " + name +" and my number is " + number + "." + "\n" + "I´m " + height +"cm tall, and I´m not a fan of Football.";
        }else{ return "Hi I'm " + name +"and my number is" + number + "." + "\n" + "I´m " + height +"cm tall, and I´m a fan of Football." + "\n" + "I am also a " + favclub + " Supporter";
        }
    }
    @PostMapping(path = "/units")
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit "+unit.getName());
        units.add(unit);
        return unit;
    }
    
    @GetMapping(path = "/units",
    produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get "+units.size()+" Units");
        return units;
    } 


}















































//mvnw spring-boot:run
//localhost:8080/api/java/tester/author
//
//http://localhost:8080/saveUnit.html
//http://localhost:8080/api/java/tester/units