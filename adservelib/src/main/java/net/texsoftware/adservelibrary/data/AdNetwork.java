package net.texsoftware.adservelibrary.data;

/**
 * Created by Jibola on 10/9/2015.
 */
public abstract class AdNetwork {

    String ad_network;
    String class_name;
    String library_class_name;
    String api_key;
    String ad_unit_id;
    String ad_size;
    boolean enabled;
    int priority;
    String date_updated;

    public AdNetwork() {
    }

    public String getAd_network() {
        return ad_network;
    }

    public void setAd_network(String ad_network) {
        this.ad_network = ad_network;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getLibrary_class_name() {
        return library_class_name;
    }

    public void setLibrary_class_name(String library_class_name) {
        this.library_class_name = library_class_name;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getAd_unit_id() {
        return ad_unit_id;
    }

    public void setAd_unit_id(String ad_unit_id) {
        this.ad_unit_id = ad_unit_id;
    }

    public String getAd_size() {
        return ad_size;
    }

    public void setAd_size(String ad_size) {
        this.ad_size = ad_size;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }
}

