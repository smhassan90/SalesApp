package com.greenstar.controller.sms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.Toolkit;

/**
 * @author Syed Muhammad Hassan
 * $Date
 */
@Controller
public class SMSParse {

    FFM_Cache cache;
    FFM_ParseSMS parseSMS;

    Toolkit toolkit;
    Timer timer;

    // Loggin activity execution
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    @RequestMapping(value = "/smsparse", method = RequestMethod.GET)
    @ResponseBody
    public String index(){


        try {

            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String today = format.format(new Date()).toUpperCase();

            fh = new FileHandler("E:/ffm_sms/FFMParsing"+ today +".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            toolkit = Toolkit.getDefaultToolkit();
            timer = new Timer();

            cache = new FFM_Cache();
            parseSMS = new FFM_ParseSMS();

            cache.loadCache();
            //parseSMS.loadRawSMS();
            //System.out.println("Loaded Raw SMS now starting parsing");
            //parseSMS.printRawSMS();
            //parseSMS.parseRawSMS(cache);

            //cache.printStaff();

            //parseSMS.loadRawSMS();
            //System.out.println("Loaded Raw SMS now starting parsing");

            //parseSMS.parseRawSMS(cache);

            timer.schedule(new FFM_TaskScheduler(), 0, // initial delay
                    120 * 1000); // subsequent rate 1 minute

        }
        catch(Exception ex){
            System.out.println("FFM_TaskScheduler: " + "");
            ex.printStackTrace();
        }
        return "Done";

    }


    class FFM_TaskScheduler extends TimerTask {
        int loop = 1720;		// 24 Hours
        int cacheReloadLood = 180;

        public void run() {

            try{

                // Check if counter has reached zero
                if (cacheReloadLood > 0){
                    cacheReloadLood--;
                }
                else{
                    // If reached zero then
                    cache.loadCache();

                    //cache.printStaff();
                    //cache.printProvider();
                    //cache.printActivity();

                    cacheReloadLood = 180;
                }

                if (loop > 0) {
                    //toolkit.beep();
                    //System.out.format("Knock Knock..!\n");

                    //SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm:ss", Locale.ENGLISH);
                    logger.info("Activty count: " + loop);

                    //TO-DO : Uncomment this
                    parseSMS.loadRawSMS();
                    System.out.println("Loaded Raw SMS now starting parsing");
                    parseSMS.printRawSMS();


                    //TO-DO : Uncomment this
                    parseSMS.parseRawSMS(cache);
                    //System.out.println(parseSMS.toString());
                    System.out.println("Parse cycle: " + loop + " complete");



                    loop--;

                } else {
                    //toolkit.beep();
                    //System.out.format("\nThat's it.. Done..!");
                    timer.cancel();
                }



            }
            catch(Exception ex){
                System.out.println("FFM_TaskScheduler: " + "");
                ex.printStackTrace();
            }
        }
    }


}
