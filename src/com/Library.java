package com;
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Library {
  
  //Fields
  public static int period;
  public static String email;
  public static String password;
  
  public static WebDriver driver = new HtmlUnitDriver();
  public static User user;
  
  public static void main(String[] args) {  
	 driver.get("http://www.google.com");
    Scanner input = new Scanner(System.in);
    System.out.print("\nEnter your email: ");
    email = input.nextLine().trim();
    System.out.print("\nEnter your password: ");
    password = input.nextLine().trim();
    System.out.print("\nEnter your desired period: ");
    period = Integer.parseInt(input.nextLine().trim());
    System.out.print("\nYour current email is "+email+"");
    System.out.print("\nYour current password is "+password+"");
    System.out.print("\nYour current period is R"+period+"");
    if (!(period >= 1 && period <= 7)) {
      System.out.print("\nYou have entered an invalid period.");
      return;
    }
    if (!email.contains("@") || !email.contains(".")) {
      System.out.print("\nYou have entered an invalid email.");
      return;
    }
	if (period == 5) {
	    System.out.print("\nYou can't sign up for the library R5.");
	    return;
	    }
    Timer timer = new Timer();
    timer.schedule( new TimerTask() {
      public void run() {
        if (getTime().contains("7:01")) {
            try {
                Desktop d=Desktop.getDesktop();
                d.browse(new URI("https://pickatime.com/client?ven=11607876&login=true&email="+email+"&password="+password+"")); 
                System.out.print("\nLogging into your account on pickatime.com");
                if (period == 1)
                  d.browse(new URI("https://pickatime.com/client?slot=34630570&bookIt=true")); 
                else if (period == 2)
                  d.browse(new URI("https://pickatime.com/client?slot=34630571&bookIt=true")); 
                else if (period == 3)
                  d.browse(new URI("https://pickatime.com/client?slot=34630572&bookIt=true")); 
                else if (period == 4)
                  d.browse(new URI("https://pickatime.com/client?slot=34630573&bookIt=true")); 
                else if (period == 6)
                  d.browse(new URI("https://pickatime.com/client?slot=34630574&bookIt=true")); 
                else if (period == 7)
                  d.browse(new URI("https://pickatime.com/client?slot=34630575&bookIt=true")); 
                System.out.print("\nSucessfully signed you up for the library R"+period+".");
                System.out.print("\nProgram coded by Miles Black HR: 101");
              } catch(Exception ex) {
                ex.printStackTrace();
              }
        } else if (getTime().contains("7:00")) {
        	System.out.print("\nIt is now 7PM, preparing to sign you up...");
        }
      }
    }, 0, 60*1000);
  }
  
	public static String getTime() { 
		SimpleDateFormat format = new SimpleDateFormat("h:mm"); 
		return format.format(new Date());
	}
	
}

