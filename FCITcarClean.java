// Esraa Mohammed amin allouh _1616810 _ GA_ P4

package fcitcarclean;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.Date;

public class FCITcarClean {

    public static void main(String[] args) throws Exception {
        File file = new File("FCITcarClean.in"); //file read from it 
        File out = new File("FCITcarClean.out");//creat file to write in it
        //-----------------------------------------------------------------------------------------
        try (PrintWriter output = new PrintWriter(out) //creat object to write in output file
        ) {
            //-----------------------------------------------------------------------------------------
            if (ISEXIST(file)) {
                output.println("THE FILE IS NOT EXISIT ");
                System.out.println("THE FILE IS NOT EXISIT");
                output.close();
                System.exit(0);
            } //method check if file exisit or not
            
            output.println("Welcome to the FCIT Car Clean Simulator");
            output.println("");
            
            try (Scanner input = new Scanner(file)) {
                int woketime =360; // the work time is 6 hour equal 360 mint //if the work time cheage , just change this virable 
                int numberofwating = input.nextInt();
                int numDay = input.nextInt();
                
                FCITvouchers vou = new FCITvouchers();
                
                for (int day = 1; day <= numDay; day++) {
                    
                    output.println("**********\n"
                            + "Day " + day + ":\n"
                            + "**********" + "\n");
                    
                    // two object from FCITcarCleanQ
                    FCITcarCleanQ outsiadline = new FCITcarCleanQ();
                    FCITcarCleanQ watingQ = new FCITcarCleanQ();
                    
                    int finsh = 0, arraive = 0, id;
                    String first, second, code;
                    int wash = input.nextInt(), wax = input.nextInt(), vacum = input.nextInt();
                    int numeberoutline = input.nextInt();
                    //fill outsiadline
                    for (int i = 0; i < numeberoutline; i++) {
                        arraive = input.nextInt();
                        id = input.nextInt();
                        first = input.next();
                        second = input.next();
                        code = input.next();
                        
                        FCITmember linecustom = new FCITmember(arraive, id, first, second, code);
                        if (i == 0) {
                            linecustom.setTimeStarted(linecustom.getArrivalTime());
                            linecustom.setMinutesRemaining(0);
                            outsiadline.enqueue(arraive, id, first, second, code);
                            continue;
                        }
                        outsiadline.enqueue(arraive, id, first, second, code);
                        
                    }
                    
                    //-----------------------------------------------------------------------------------
                    int remingtime = 0, startsurv, watiTime;
                    FCITmember customerINservice = null;
                    
                    //--------------------------------------------------------
                    for (int i = 0; i < woketime || !watingQ.isEmpty() || customerINservice != null;) {
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                        if (outsiadline.isEmpty() || outsiadline.peek().getArrivalTime() != i) { //check the outsiadline or arrive time not equal current time
                            i++;
                            if (remingtime > 0 && customerINservice != null) {
                                remingtime--; // every mint increase .. decrease reming time
                            }
                        }
                        
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                        if (customerINservice != null && remingtime == 0) { // if some one in service and fish but still not out this metheod till him out and enter new one in service
                            watiTime = Math.abs(customerINservice.getTimeStarted() - customerINservice.getArrivalTime()); //clculat waite time
                            int serv = customerINservice.getMinutesRemaining(); //clculate service time
                            int total = watiTime + serv; // clculate all time
                            print_info( output ,  customerINservice,  serv,  total ,  watiTime ,  finsh);
                            customerINservice = null; // after he is finsh .. make this helpper varabile empty to reacive new one
                            remingtime = 0;
                            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                            if (!watingQ.isEmpty()) { //insted if to cheak if wating not empty
                                startsurv = finsh; //save fish time for previos one to start time to second one ...
                                watingQ.peek().setTimeStarted(startsurv);
                                //update finsh time for new customer and remin time
                                finsh = watingQ.peek().getTimeStarted() + service(watingQ, watingQ.peek().getArrivalTime(), watingQ.peek().getCode(), wash, wax, vacum);
                                remingtime = service(outsiadline, arraive, watingQ.peek().getCode(), wash, wax, vacum);
                                watingQ.peek().setMinutesRemaining(remingtime);
                                // full this verable mean there is one in servise
                                customerINservice = new FCITmember(watingQ.peek().getArrivalTime(), watingQ.peek().getID(), watingQ.peek().getFirstName(), watingQ.peek().getLastName(), watingQ.peek().getCode(), watingQ.peek().getTimeStarted(), watingQ.peek().getMinutesRemaining());
                                output.println(min_to_hourt(customerINservice.getTimeStarted()) + customerINservice.getFirstName() + " " + customerINservice.getLastName() + " has now started Class " + customerINservice.getCode() + " service.");
                                
                                vou.push(watingQ.peek().getArrivalTime(), watingQ.peek().getID(), watingQ.peek().getFirstName(), watingQ.peek().getLastName(), watingQ.peek().getCode(), finsh, startsurv);
                                watingQ.dequeue(); //out from wating
                            }
                        }
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                        if (!outsiadline.isEmpty() && outsiadline.peek().getArrivalTime() == i) { //if tere is customer in out said and arrival time equal current time
                            if (outsiadline.peek().getCode().equalsIgnoreCase("z")) {  // first check is nabeel or not
                                output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + "Mr Nabeel came and collected the following vouchers:");
                                while (!vou.isEmpty()) {
                                    output.println("           " + vou.peek().getFirstName() + " " + vou.peek().getLastName() + "(" + vou.peek().getID() + ")");
                                    vou.pop();
                                }
                                outsiadline.dequeue();
                                //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                            } else if (outsiadline.peek().getArrivalTime() >= woketime) { // if customer come 4 pm or after that the clean car closed
                                output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + outsiadline.peek().getFirstName() + " " + outsiadline.peek().getLastName() + " arrived at the FCIT Car Clean. However, the car wash is CLOSED.");
                                outsiadline.dequeue();
                                //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                            } else if (watingQ.isEmpty() && customerINservice == null && outsiadline.peek() != null) { //if no one in service and no one in wating
                                //go servic dirctly
                                startsurv = outsiadline.peek().getArrivalTime();
                                outsiadline.peek().setTimeStarted(startsurv);
                                
                                finsh = outsiadline.peek().getTimeStarted() + service(outsiadline, outsiadline.peek().getArrivalTime(), outsiadline.peek().getCode(), wash, wax, vacum);
                                remingtime = service(outsiadline, arraive, outsiadline.peek().getCode(), wash, wax, vacum);
                                
                                outsiadline.peek().setMinutesRemaining(remingtime);
                                
                                customerINservice = new FCITmember(outsiadline.peek().getArrivalTime(), outsiadline.peek().getID(), outsiadline.peek().getFirstName(), outsiadline.peek().getLastName(), outsiadline.peek().getCode(), outsiadline.peek().getTimeStarted(), outsiadline.peek().getMinutesRemaining());
                                
                                output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + outsiadline.peek().getFirstName() + " " + outsiadline.peek().getLastName() + " arrived at the FCIT Car Clean and immediately started Class " + outsiadline.peek().getCode() + " service.");
                                vou.push(outsiadline.peek().getArrivalTime(), outsiadline.peek().getID(), outsiadline.peek().getFirstName(), outsiadline.peek().getLastName(), outsiadline.peek().getCode(), finsh, startsurv);
                                outsiadline.dequeue();
                                //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^66
                            } else if (remingtime != 0 && watingQ.isEmpty() && outsiadline.peek() != null && customerINservice != null) { // if no one in wating but there is one in service
                                // go wating
                                watingQ.enqueue(outsiadline.peek().getArrivalTime(), outsiadline.peek().getID(), outsiadline.peek().getFirstName(), outsiadline.peek().getLastName(), outsiadline.peek().getCode());
                                output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + outsiadline.peek().getFirstName() + " " + outsiadline.peek().getLastName() + " arrived at the FCIT Car Clean and entered Wash Queue.");
                                outsiadline.dequeue();
                                //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                            } else if (!watingQ.isEmpty() && outsiadline.peek() != null && customerINservice != null) { //wating not empty and there is one in srvice
                                // check the number of custome in wating less of 5
                                if (watingQ.getNumCustomers() < numberofwating) {
                                    watingQ.enqueue(outsiadline.peek().getArrivalTime(), outsiadline.peek().getID(), outsiadline.peek().getFirstName(), outsiadline.peek().getLastName(), outsiadline.peek().getCode());
                                    output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + outsiadline.peek().getFirstName() + " " + outsiadline.peek().getLastName() + " arrived at the FCIT Car Clean and entered Wash Queue.");
                                    
                                    outsiadline.dequeue();
                                    
                                } else {
                                    output.println(min_to_hourt(outsiadline.peek().getArrivalTime()) + outsiadline.peek().getFirstName() + " " + outsiadline.peek().getLastName() + " arrived at the FCIT Car Clean. Unfortunately, the Wash Queue is full, and the customer left disappointed. ");
                                    outsiadline.dequeue();
                                }
                            }
                            
                        }
                        
                    }
                    if (finsh < woketime) { //if finsh less 4 pm nabeel coming
                        output.println(min_to_hourt(woketime) + "Mr Nabeel came and collected the following vouchers:");
                        NabilleIScoming(outsiadline, vou, finsh, output); //method to print stack
                        
                    } else { // if finsh not 4 pm nabeel coming after last one finsh
                        output.println(min_to_hourt(finsh + 1) + "Mr Nabeel came and collected the following vouchers:"); //nabeel came after one min after the final customer finsh this is in output file exambil
                        NabilleIScoming(outsiadline, vou, finsh, output);
                        
                    }
                }
            }
        }

    }

    //-------------------------------------------------------------------------------------
    public static boolean ISEXIST(File file) { //cheack if file exists or not
        return !file.exists();
    }

    //--------------------------------------------------------------------------------------
    public static int service(FCITcarCleanQ outsiadline, int arraive, String code, int wash, int wax, int vacum) { //clculate servise 
        int finsh = 0;
        switch (code) {
            case "W":
                finsh += wash;
                break;
            case "WW":
                finsh += wash + wax;
                break;
            case "WWV":
                finsh += wash + wax + vacum;
                break;
            case "WV":
                finsh += wash + vacum;
                break;
        }

        return finsh;
    }

    //----------------------------------------------------------------------------------------
    public static String min_to_hourt(int Minutes) { // to change the time 
        Date x = new Date(1439, 4, 3, 10, 0, 0);  
        String c="";
        for (int i = 0; i < Minutes; i++) {
            x.setSeconds(x.getSeconds() + 60);
        }
        c += x.getHours() > 12 ? (x.getHours() - 12) + ":" : x.getHours() + ":";
        c += String.valueOf(x.getMinutes()).length() == 1 ? "0" + x.getMinutes() : x.getMinutes();
        if (x.getHours() < 12) {
            c += " AM:  ";
        } else {
            c += " PM:  ";
        }
        return c;
    }

    //-----------------------------------------------
    public static void NabilleIScoming(FCITcarCleanQ outsiadline, FCITvouchers vou, int finsh, PrintWriter output) { //for print stack
        while (!vou.isEmpty()) {
            output.println("           " + vou.peek().getFirstName() + " " + vou.peek().getLastName() + "(" + vou.peek().getID() + ")");
            vou.pop();
            while (!outsiadline.isEmpty()) {
                outsiadline.dequeue();
            }
        }

    }
    //--------------------------------------------------------------------------
    public static void print_info(PrintWriter output ,  FCITmember customerINservice, int serv, int total , int watiTime , int finsh){ //method just for print information
          output.println(min_to_hourt(finsh) + "The car for " + customerINservice.getFirstName() + " " + customerINservice.getLastName() + " is now finished.");
                    output.println("           Waiting time in line: " + watiTime + " minutes");
                    output.println("           Service time: " + serv + " minutes");
                    output.println("           Total time: " + total + " minutes");
    }
}
