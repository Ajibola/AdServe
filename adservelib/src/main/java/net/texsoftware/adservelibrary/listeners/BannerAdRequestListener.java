package net.texsoftware.adservelibrary.listeners;

public interface BannerAdRequestListener {
    public void onBannerRequestSuccess();

    public void onBannerRequestFailed();

    public void onBannerImpressionLogged();

    public void onBannerClick();
}
