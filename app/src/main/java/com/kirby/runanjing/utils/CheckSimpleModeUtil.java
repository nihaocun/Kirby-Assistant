package com.kirby.runanjing.utils;
import android.content.*;

public class CheckSimpleModeUtil
{
	public static boolean isSimpleMode(){
		SharedPreferences simple =ActManager.currentActivity(). getSharedPreferences("setting", 0);
        boolean is_simple = simple.getBoolean("simple_mode", false);
		return is_simple;
	}
}