

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;


public class testSteps {
	
	
	public program program;
	
	public testSteps(program Program){
		
		this.program=Program;
	}
	
	//AddActivityProjectLeader
	
	
	@Given ("^that the projectleader is identified by his ID$")
	public void thatTheProjectleaderIsIdentifiedByHisID(String ID)
	{
		//New employee with ID;
		//Check if he is a part of the system;
	}
	
 	@Given ("^he has chosen a project that he is leading$")
 	public void heHasChosenAProjectThatHeIsLeading(project Project)
 	{
 		project currentActivity = Project;
 	}
 	
	@When ("^he adds an acitivity in the project$")
	public void heAddsAnActivityInTheProject()
	
	
	
	
	@Then the activity is added in the project. 

}
