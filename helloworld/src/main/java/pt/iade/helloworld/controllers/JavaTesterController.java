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
 

    @GetMapping(path = "/author", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor() {
        logger.info("Infomacao do autor");
        String name = "João Belo";
        int number = 20190848;
        String height = "1.83" ;
        String fan = "True" ;
        String favclub = "Sporting";
        if (fan == "False"){
            return "Hi I'm " + name +" and my number is " + number + "." + "\n" + "I´m " + height +"cm tall, and I´m not a fan of Football.";
        }else{ return "Hi I'm " + name +"and my number is" + number + "." + "\n" + "I´m " + height +"cm tall, and I´m a fan of Football." 
        + "\n" + "I am also a " + favclub + " Supporter";
        }
    }


///////////////////////////////////////////////////////////////COVID/////////////////////////////////////////////////////////////////////////////////


    @GetMapping(path = "/access/{student}/{covid}", produces= MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent,@PathVariable("covid") boolean hasCovid) {
        if (isStudent && hasCovid) {
            return false;   
        }
        else{
            return true;   
        } 
    }


    @GetMapping(path ="/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid, 
    @PathVariable("classType") String type){
        if (isStudent && type.equals("presential") && (hasCovid > 34.5 && hasCovid < 37.5)) {
            return true;
        }else{
            return false;
        }        
    }    


    @GetMapping(path= "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean isonFire, @PathVariable("numberOfCovids") double numCovids, 
    @PathVariable("powerShutdown") boolean ispowerShutdown ,@PathVariable("comeBackTime") double isComeBackTime){
        if (isonFire || (numCovids > 5) || ( ispowerShutdown && isComeBackTime > 15)){
            return true;
    }else{
        return false;
    }
}


///////////////////////////////////////////////////////////////////GRADES/////////////////////////////////////////////////////////////////////////


    private double grades[] = { 10.5, 12, 14.5 };
    private String ucs[] = { "FP", "POO", "BD" }; 


    @GetMapping(path = "/average", produces= MediaType.APPLICATION_JSON_VALUE)
    public double average() {
        double total = 0; 
        for (int x = 0;  x < grades.length;x= x+x){
            total = total + grades[x];
        }           
        double average = total / grades.length;
        return average;
    }

    @GetMapping(path ="/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public double max() {
        double Max = 0;
        for (int y = 1; y < grades.length; y = y + y) {
            if (grades[y] > Max) {
                Max = grades[y];
            }
        }
        return Max;
    }

     
    
///////////////////////////////////////////////////////////////////////UNITS/////////////////////////////////////////////////////////////////////


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