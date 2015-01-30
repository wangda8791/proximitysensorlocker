/*
 * Copyright 2015-2016 Wang Da
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Cordova (Android) plugin for proximity screen lock
 * @author Wang Da <wangda8791@163.com>
 */
package org.scn.cordova.proximity;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

/**
 * Plugin class which does the actual handling
 */
public class ProximitySensorLocker extends CordovaPlugin {

	private PowerManager.WakeLock wakeLock = null;
	private PowerManager powerManager = null;

	/**
	 * Fetch a reference to the power-service when the plugin is initialized
	 */
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		
		this.powerManager = (PowerManager) cordova.getActivity().getSystemService(Context.POWER_SERVICE);
	}
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {

		PluginResult result = null;
		Log.d("ProximitySensorLockerPlugin", "Plugin execute called - " + this.toString() );
		Log.d("ProximitySensorLockerPlugin", "Action is " + action );

		if( action.equals("acquire") ) {
			result = this.acquire();
		}
		else if( action.equals("release") ) {
			result = this.release();
		}
		
		callbackContext.sendPluginResult(result);
		return true;
	}
	
	private PluginResult acquire() {
		  int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
		  this.wakeLock = this.powerManager.newWakeLock(PROXIMITY_SCREEN_OFF_WAKE_LOCK, "PROXIMITY_SCREEN_OFF_WAKE_LOCK");
		  this.wakeLock.acquire();
		  return new PluginResult(PluginResult.Status.OK);
	}
	
	private PluginResult release() {
		if ( null != this.wakeLock ) {
			if (this.wakeLock.isHeld()) this.wakeLock.release();
			this.wakeLock = null;
	  }
	  return new PluginResult(PluginResult.Status.OK);
	}
	
	/**
	 * Make sure any wakelock is released if the app goes into pause
	 */
	@Override
	public void onPause(boolean multitasking) {
		//if( this.wakeLock != null ) this.wakeLock.release();

		super.onPause(multitasking);
	}
	
	/**
	 * Make sure any wakelock is acquired again once we resume
	 */
	@Override
	public void onResume(boolean multitasking) {
		if( this.wakeLock != null ) this.wakeLock.acquire();

		super.onResume(multitasking);
	}
}
