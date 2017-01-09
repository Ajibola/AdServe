package net.texsoftware.adservelibrary.data;

/**
 * Created by Jibola on 10/9/2015.
 */
public class NativeAdObject {

    String id;
    String type;
    String title;
    String description;
    String image_url;
    String icon_url;
    String cta_text;
    String social_text;
    String notify_text;
    String rating;

    public NativeAdObject() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getCta_text() {
        return cta_text;
    }

    public void setCta_text(String cta_text) {
        this.cta_text = cta_text;
    }

    public String getSocial_text() {
        return social_text;
    }

    public void setSocial_text(String social_text) {
        this.social_text = social_text;
    }

    public String getNotify_text() {
        return notify_text;
    }

    public void setNotify_text(String notify_text) {
        this.notify_text = notify_text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

