
package com.example.pharmeasy.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.pharmeasy.ScheduleApplication;

/**
 * AppSharedPrefrences 
 * defines the data that can persisted using hte shared preferences
 * 
 */
public class AppSharedPrefrences {

	/* the shared preference store name where the persistence data get saved*/
	private static final String stSharePrefereceStore = "prefrences";


	/* singleton reference to access the share persistence data  */
	private static AppSharedPrefrences mAppSharedPreferences = null;
	private static SharedPreferences mSharedPreferences;

	private AppSharedPrefrences() { }

	/**
	 * Initialize the AppSharedPrefrences through single ton pattern
	 * @param
	 *
	 * @return
	 * the application shared preference
	 */
	public static AppSharedPrefrences getAppSharedPrefrencesInstace() {
		if(mAppSharedPreferences == null)
		{
			mAppSharedPreferences = new AppSharedPrefrences();

		}
		return mAppSharedPreferences;

	}

	/**
	 * Initialize the SharedPrefrences the allows to save the persistence data
	 * @param
	 *
	 * @return
	 * the application SharedPreferencese
	 */
	public SharedPreferences getSharePreferencesInstance(){

		if(mSharedPreferences == null) {
			mSharedPreferences = ScheduleApplication.Companion.getApplicationContex().getSharedPreferences(stSharePrefereceStore,
					Context.MODE_PRIVATE);
		}
		return mSharedPreferences;
	}

	public  void setFcmToken(String fcmToken){
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("fcmToken", fcmToken);
		edit.apply();
	}

	/**
	 * getting fcmToken
	 * @return
	 */
	public String getFcmToken(){
		String fcmToken = getSharePreferencesInstance().getString("fcmToken", "");
		return fcmToken;
	}
	
}
