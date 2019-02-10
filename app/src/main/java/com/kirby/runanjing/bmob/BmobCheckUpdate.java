package com.kirby.runanjing.bmob;
import cn.bmob.v3.*;

public class BmobCheckUpdate extends BmobObject
{
	String versionName;
	Integer versionCode;
	String changeLog;
	
	public String getVersionName(){
		return versionName;
	}
	
	public Integer getVersionCode(){
		return versionCode;
	}
	
	public String getChangeLog(){
		return changeLog;
	}
	
	public void setVersionName(String versionName){
		this.versionName=versionName;
	}
	
	public void setVersionCode(Integer versionCode){
		this.versionCode=versionCode;
	}
	
	public void setVersion(String changeLog){
		this.changeLog=changeLog;
	}
}