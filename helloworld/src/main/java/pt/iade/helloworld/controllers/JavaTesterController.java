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


    @GetMapping(path = "/matchingrade", produces = MediaType.APPLICATION_JSON_VALUE)   
    public double matchinGrade(){
        String name = units.get(units.size()-1).getName();      
        double finalGrade = 0;
        for(int i=0; i < units.size(); i++)
            if(units.get(i).getName().equals(name)){
                finalGrade = units.get(i).getGrade();
            }
        return finalGrade;
    }


    @GetMapping(path = "/guessGrade",produces = MediaType.APPLICATION_JSON_VALUE) 
    public double guessGrade() {                 
        String name;                             
        name = "BD";
        int position = 0;
        for(int i=0; i<ucs.length; i = i + 1) {
            if (ucs[i].equals(name)) {
                position = i;
                break;
            }
        }
        double gradeFinal = grades[position];
        return gradeFinal;
    }


    @GetMapping(path = "/limits", produces = MediaType.APPLICATION_JSON_VALUE)     
    public int limits() {
        double min = 9.5;
        double max = 14;
        int times = 0;
        for(int i=0; i<grades.length; i = i + 1) {
            if (grades[i] > min && grades[i] < max){
                times = times + 1;
            }
        }
    return times;
    }


    @GetMapping(path = "/textstring", produces = MediaType.APPLICATION_JSON_VALUE)
    public String textstring() {
        String text = "";
        for(int i = 0; i < ucs.length; i = i + 1) {
            text += ucs[i] + ":" + grades[i] + "\n";
        }
        return text;
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



    @GetMapping(path = "/calcaverage", produces= MediaType.APPLICATION_JSON_VALUE)
    public double calcAverage() {
        double credits = 0;
        double total = 0;
        for(int i = 0; i < units.size(); i = i + 1) {
            total += units.get(i).getGrade() * units.get(i).getEcts();
            credits += units.get(i).getEcts();
        }
        return total/credits; 
    }

    @GetMapping(path = "/calcmax", produces = MediaType.APPLICATION_JSON_VALUE)
    public double calcMax() {
        double max = 0;
        for(int i = 0; i < units.size(); i = i + 1)
            if(units.get(i).getGrade() > max){
                max = units.get(i).getGrade();
            }
        return max;
    }

    @GetMapping(path = "/calcgrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double calcGrade() {
        String name = units.get(units.size()-1).getName();      
        double finalGrade = 0;
        for(int i = 0; i < units.size(); i = i + 1)
            if(units.get(i).getName().equals(name)){
                finalGrade = units.get(i).getGrade();
            }
        return finalGrade;
    }

    @GetMapping(path = "/calcsemester", produces = MediaType.APPLICATION_JSON_VALUE)
    public String calcSemester() {
        int givenSemester = 3;                          
        String finalUcs = "";
        for(int i = 0; i < units.size(); i = i + 1)
            if(units.get(i).getSemester() == givenSemester){
                finalUcs += units.get(i).getName() + " ";
            }
        return finalUcs;
    }

    @GetMapping(path = "/calclimit", produces = MediaType.APPLICATION_JSON_VALUE)
    public int calcLimit() {
        double min = 9.5;                                       
        double max = 14;
        int numberOutput = 0;
        for(int i = 0; i < units.size(); i = i + 1)
            if(units.get(i).getGrade() > min && units.get(i).getGrade() < max){
                numberOutput += 1;
            }
        return numberOutput;
    }
}


////////////////////////////////////////////////////////////////////mvnw spring-boot:run//////////////////////////////////////////////////////////
