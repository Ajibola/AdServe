
AdServe
======

A simple mobile advertising waterfall library for Android Apps with analytics listeners built in. The AdServe library combines SDKs from over 15 different mobile advertising networks into one easy to setup library with less than 200KB in size. You only need to add the parameters for the libraries that you are interested in for your application, and do not need to include any unwanted SDKs for other libraries.


What ad network sdks are implemented
-------------------

AdColony			-	com.adcolony:sdk:3.0.5  
Ampiri				-	com.ampiri.sdk:ampiri-sdk:3.4.0  
Audience Network	- 	com.facebook.android:audience-network-sdk:4.18.0  
Aerserve 			- 	aerserv-sdk.jar  
Applovin			-	applovin-sdk-6.4.0.jar  
Heyzap				-	heyzap-unified-platform-10.2.0.jar  
Ogury				-	presage-lib-1.8.1.jar  
RevMob				-	revmob.jar  
StartApp			-	StartAppInApp-3.5.3.jar  
Vungle				-	vungle-publisher-adaptive-id-4.0.3.jar  
Avocarrot			-	com.avocarrot:android-sdk:3.7.+  
Twitter MoPub		- 	com.mopub:mopub-sdk:4.11.0@aar  
LoopMe				- 	com.loopme:loopme-sdk:5.0@aar  
SuperSonic			- 	com.supersonic.sdk:mediationsdk:6.4.19@jar  
InMobi				-	com.inmobi.monetization:inmobi-ads:6.0.3  
Yahoo Flurry				-	com.flurry.android:ads:6.7.0  


Download
--------

Gradle:
```groovy
compile 'net.texsoftware.adservelibrary:adserve-lib:0.0.1'
```


How do I use AdServe?
-------------------
A sample app demo is included in the project with implementation for all ad types (Native, Banner, Interstitial, Video)

```java
		// Create the AdManager object that loads adverts and swaps ad networks, with an optional analytics implementation and a required file containing adnetwork info
        AdManager adManager = new AdManager(thisObj, AppAnalytics.getInstance(), "ad_networks.json");	
		
		//create a listener for events on the placement
        InterstitialAdRequestListener adRequestListener = new InterstitialAdRequestListener() {
            @Override
            public void onInterstitialRequestSuccess() {
				...
            }

            @Override
            public void onInterstitialRequestFailed() {
				...
            }

            @Override
            public void onInterstitialImpressionLogged() {
				...
            }

            @Override
            public void onInterstitialClick() {
				...
            }
        };
		
		//load ad placement, listener fires based on response received
		InterstitialAd interstitialAd  = adManager.loadInterstitialAd(thisObj, adRequestListener);
		
		// you can swap ad networks like this, automatically goes to the next network in your waterfall
        adManager.swapInterstitialAd();
		interstitialAd = adManager.loadInterstitialAd(thisObj, adRequestListener);

```

Status
------------
This is the initial version of the library, while it has been thoroughly tested, there still bugs in the implementation and more placements and ad networks that could be added. Please reach out to me @Ajibz on Twitter if you are having trouble implementing it in your app.


Author
------
Ajibola Aiyedogbon - @Ajibola on GitHub, @Ajibz on Twitter


License
-------
GPLV3 See the [LICENSE][2] file for details.

       
	Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://www.gnu.org/licenses/gpl-3.0.en.html
[2]: https://www.gnu.org/licenses/gpl-3.0.en.html