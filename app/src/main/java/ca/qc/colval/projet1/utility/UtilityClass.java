package ca.qc.colval.projet1.utility;

import android.content.Context;
import android.widget.Toast;

public class UtilityClass {
    public static void Toast(Context context, String msg){
        Toast toast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
