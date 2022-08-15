package love.simbot.example;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class Log_settler {
    public static void settle(){
        File file =new File("./cache/logs");
        file.mkdirs();
    }
    public static void writelog(String content) {
        Log_settler lt= new Log_settler();
        Logger log = lt.getMylog();
        log.info(content);
    }

    public Logger getMylog(){
        ZonedDateTime time =ZonedDateTime.now();
        String date1  = time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Logger logger = Logger.getLogger(date1);
        logger.setLevel(Level.ALL);
        for(Handler h: logger.getHandlers()){
            h.close();
        }
        try{
            FileHandler fileHandler = new FileHandler("./cache/logs/"+date1+".log",true);
            fileHandler.setFormatter(new myFormat());
            logger.addHandler(fileHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
        return logger;
    }
}

class myFormat extends Formatter{
    @Override
    public String format(LogRecord record){
        ZonedDateTime zdf =ZonedDateTime.now();
        String sDate  = zdf.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        return "["+ sDate + "]: "+record.getMessage()+"\n";
    }
}