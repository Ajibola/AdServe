package net.texsoftware.adservelibrary.listeners;

public interface VideoAdRequestListener {
    public void onVideoRequestSuccess();

    public void onVideoRequestFailed();

    public void onVideoImpressionLogged();

    public void onVideoStarted();

    public void onVideoFinished();

    public void onVideoClick();
}
