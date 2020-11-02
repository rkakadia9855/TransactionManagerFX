/**
 * This class represents the profile of the account holder
 * @author John Juarez, Rudra Kakadia
 */
package application;
public class Profile {
    private String fname;
    private String lname;
    
    /**
     * initializes the first name and last name 
     * @param fname - the first name of this profile
     * @param lname - the last name of this profile
     */
    public Profile(String fname, String lname) {
      this.fname = fname;
      this.lname = lname;
    }
    
    /**
     * getter for first name
     * @return the first name
     */
    public String getFName() {
      return this.fname;
    }
    
    /**
     * getter for last name
     * @return the last name
     */
    public String getLName() {
      return this.lname;
    }
    
    @Override
    /**
     * constructs the string representation of this profile
     * @return the string value of this profile
     */
    public String toString() {
      return (fname + " " +lname);
    }

    /**
     * checks if this profile is the same as the profile passed in the argument
     * @param holder - the second profile
     * @return true if same, false otherwise
     */
    public boolean equals(Profile holder) {
      if(this.fname.equals(holder.fname) && this.lname.equals(holder.lname)) {
        return true;
      }
      else {
        return false;
      }
    }
}
