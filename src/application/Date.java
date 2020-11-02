/**
 * This class represents the date on which the account is opened
 * @author John Juarez, Rudra Kakadia
 */
package application;
public class Date implements Comparable<Date> {
  
  private int year;
  private int month;
  private int day;
  
  /**
   * Intializes the date fields
   * @param month - the month on which account was opened
   * @param day - the day on which account was opened
   * @param year - the year on which account was opened
   */
  public Date(int month, int day,  int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  /**
   * getter for the day on which account was opened
   * @return - the day on which an account was opened
   */
  public int getDay() {
    return this.day;
  }
  
  /**
   * getter for the month on which account was opened
   * @return - the month on which an account was opened
   */
  public int getMonth() {
    return this.month;
  }
  
  /**
   * getter for the year on which account was opened
   * @return - the year on which an account was opened
   */
  public int getYear() {
    return this.year;
  }

  @Override
  /**
   * Compares this date with the date passed in argument
   * @param date - the date which needs to be compared with this date
   * @return 0 is both dates are same, -1 if this date is before the passed date, 1 otherwise
   */
  public int compareTo(Date date) {
    int result = 0;
    if((this.year == date.getYear()) && (this.month == date.getMonth()) 
        && (this.day == date.getDay())) {
      result = 0;
    }
    else if(this.year > date.getYear()) {
      result = 1;
    }
    else if(this.year < date.getYear()) {
      result = -1;
    }
    else {
      if(this.month > date.getMonth()) {
        result = 1;
      }
      else if(this.month < date.getMonth()) {
        result = -1;
      }
      else {
        if(this.day > date.getDay()) {
          result = 1;
        }
        else if(this.day < date.getDay()) {
          result = -1;
        }
      }
    }
    
    return result;
  }
  
  /**
   * This is a helper method that helps to decide if the passed year is a leap year
   * @param year - the year that needs to be checked
   * @return true if it is a leap year, false otherwise
   */
  private boolean isLeapYear(int year) {
    boolean leap = false;
    
    if(year % 400 == 0)
      return true;
    if (year % 100 == 0)
      return false;
    if(year % 4 == 0)
      return true;
    
    return leap;
  }
  
  @Override
  /**
   * Constructs the string representation of this date object
   * @return the string format of this date
   */
  public String toString() {
    String date = "";
    String dayString = this.day + "";
    String monthString = this.month + "";
    String yearString = this.year + "";
    
    date = monthString + "/" + dayString + "/" + yearString;
    return date;
  }
  
  /**
   * Checks if this is a valid date
   * @return true if is valid, false otherwise
   */
  public boolean isValid() {
    boolean valid = true;
    
    if(this.day == 0 || this.month == 0 || this.year == 0) 
      valid = false;
    else if(this.month == 2 && this.day > 28 && !(isLeapYear(year))) {
      valid = false;
    }
    else if(this.month == 2 && this.day <= 29 && isLeapYear(year)) {
      valid = true;
    }
    else if(this.month == 2 && this.day > 28)
      valid = false;
    else if(thirtyDays(this.month)) {
      if(this.day > 30)
        valid = false;
    }
    else if(thirtyOneDays(this.month)) {
      if(this.day > 31)
        valid = false;
    }
    else if(this.month > 12)
      valid = false;
    
    return valid;
  }
  
  /**
   * checks if the given month has 30 days or not
   * @param month - the month 
   * @return true if it has 30 days, false otherwise
   */
  private boolean thirtyDays(int month) {
    boolean thirty = false;
    switch(month) {
      case 4:
        thirty = true;
        break;
      case 6:
        thirty = true;
        break;
      case 9:
        thirty = true;
        break;
      case 11:
        thirty = true;
        break;
      default:
        thirty = false;
    }
    return thirty;
  }
  
  /**
   * checks if the given month has 31 days or not
   * @param month - the month 
   * @return true if it has 31 days, false otherwise
   */
  private boolean thirtyOneDays(int month) {
    boolean thirty = false;
    switch(month) {
      case 1:
        thirty = true;
        break;
      case 3:
        thirty = true;
        break;
      case 5:
        thirty = true;
        break;
      case 7:
        thirty = true;
        break;
      case 8:
        thirty = true;
        break;
      case 10:
        thirty = true;
        break;
      case 12:
        thirty = true;
        break;
      default:
        thirty = false;
    }
    return thirty;
  }

}
