package net.texsoftware.adservelibrary.utils;

/**
 * Created by Jibola on 12/1/2016.
 */

public class Analytics {

    protected static Analytics INSTANCE;

    protected Analytics() {

    }

    public static Analytics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Analytics();
        }

        return INSTANCE;
    }


    public void sendScreenEvent(String screenName) {

    }

    public void sendActionEvent(String type, String action) {


    }

    public void sendActionLabelEvent(String type, String action, String label, long time) {

    }
}
