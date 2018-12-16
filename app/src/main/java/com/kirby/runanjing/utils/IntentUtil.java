package com.kirby.runanjing.utils;
import android.content.Intent;
import android.content.Context;
import android.app.ActivityOptions;
import android.app.Activity;
import com.kirby.runanjing.BaseActivity;

public class IntentUtil
{
	public static void startActivityWithAnim(Intent intent,Activity activity){
		activity.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
	}
}