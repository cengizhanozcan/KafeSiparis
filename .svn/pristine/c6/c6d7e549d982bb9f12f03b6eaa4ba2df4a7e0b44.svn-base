/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cengizhan
 */
public class Time {

    public Time() {
    }

    public static Timestamp getTimeOfToday() {

        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        System.out.println(ourJavaTimestampObject);

        return ourJavaTimestampObject;
    }

    public static Timestamp getTimeOfSaved(String date) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {try {
            //this generic but you csan control another types of exception
            throw e;
            } catch (Exception ex) {
                Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return timestamp;
    }
    
    public static Timestamp getTimeOfSaved(Long date){
        return new Timestamp(date); 
    }
}
