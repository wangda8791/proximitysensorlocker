cordova.define("org.scn.cordova.proximity.ProximitySensorLocker", function(require, exports, module) { /*
 * Copyright 2015 Wang Da
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

var ProximitySensorLocker = function() {};

ProximitySensorLocker.prototype.acquire = function() {
    cordova.exec(null, null, 'ProximitySensorLocker', 'acquire', []);
}

ProximitySensorLocker.prototype.release = function() {
    cordova.exec(null, null, 'ProximitySensorLocker', 'release', []);
}

module.exports = new ProximitySensorLocker();

});
