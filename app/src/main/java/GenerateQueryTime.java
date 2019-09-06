import android.util.Log;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class GenerateQueryTime {

    public GenerateQueryTime(){

    }

    public static String getTimeInterval(){
        // generate current time
        LocalDateTime ldt = new LocalDateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-0M-dd HH:mm:ss");
        String str = fmt.print(ldt);
        Log.d("joda time", str);
        str.replace(' ', 'T');
        String []map = new String[]{""};
        return null;

    }


}
