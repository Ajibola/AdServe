package net.texsoftware.adservelibrary.listeners;

public interface NativeAdRequestListener {
    public void onNativeRequestSuccess();

    public void onNativeRequestFailed();

    public void onNativeImpressionLogged();

    public void onNativeClick();
}
