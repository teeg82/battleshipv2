package gameRules.validators.errorHandlers;

import gameRules.validators.GridValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * This class represents error messages received during validation. This is
 * typically used in conjuction with the GridValidator to control placement of
 * ships on the grid when they violate game rules.
 * @author Paul Richter
 * @version 1.0
 * @since March 12, 2010;
 * @see GridValidator
 */
public class GenericGridError {

	/**A list of error messages */
	private Vector<String> errorMessages;
	
	public GenericGridError(){
		errorMessages = new Vector<String>(5,1);
	}
	
	/**
	 * Retrieves a list of error messages received during validation.
	 * @return A list of string error messages
	 */
	public List<String> getErrorMessages(){
		return new ArrayList<String>(errorMessages);
	}
	
	/**
	 * Returns the number of errors received during validation.
	 * @return Number of error messages
	 */
	public Integer getErrorCount(){
		return errorMessages.size();
	}
	
	/**
	 * Adds a new error message to the list of messages.
	 * @param message The specific error message to be added to the list
	 */
	public void addErrorMessage(String message){
		errorMessages.add(message);
	}
}