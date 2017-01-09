package net.texsoftware.adserve;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.flurry.android.FlurryAgent;

import net.texsoftware.adservelibrary.utils.Analytics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jibola on 12/1/2016.
 */

public class AppAnalytics extends Analytics {

    private AppAnalytics() {

    }

    public static Analytics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppAnalytics();
        }

        return INSTANCE;
    }

    @Override
    public void sendScreenEvent(String screenName) {
        super.sendScreenEvent(screenName);
        FlurryAgent.logEvent(screenName);
        Answers.getInstance().logContentView(new ContentViewEvent().putContentName(screenName)
                .putContentType("Screen View"));
    }

    @Override
    public void sendActionEvent(String category, String action) {
        super.sendActionEvent(category, action);

        Map<String, String> articleParams = new HashMap<String, String>();
        articleParams.put(action, "");
        FlurryAgent.logEvent(category, articleParams, true);

        Answers.getInstance().logCustom(new CustomEvent(category)
                .putCustomAttribute(action, ""));
    }

    @Override
    public void sendActionLabelEvent(String category, String action, String label, long time) {
        super.sendActionLabelEvent(category, action, label, time);

        Map<String, String> articleParams = new HashMap<String, String>();
        articleParams.put(action, label);
        FlurryAgent.logEvent(category, articleParams, true);

        Answers.getInstance().logCustom(new CustomEvent(category)
                .putCustomAttribute(action, label));
    }
}
