package net.texsoftware.adservelibrary.listeners;

public interface InterstitialAdRequestListener {
    public void onInterstitialRequestSuccess();

    public void onInterstitialRequestFailed();

    public void onInterstitialImpressionLogged();

    public void onInterstitialClick();
}
