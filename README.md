phonegap-plugin-proximitysensorlocker
==========================

A phonegap 3.x plugin for proximity sensor lock and unlock screen control for android.

Installing
======
You may use phonegap CLI as follows:

<pre>
cordova plugin add https://github.com/wangda8791/proximitysensorlocker.git
</pre>

Using
====
The code below can be placed into script tag.

```javascript
		function acquire() {
			proximitySensorLocker.acquire();
		}
		function release() {
			proximitySensorLocker.release();
		}
```
