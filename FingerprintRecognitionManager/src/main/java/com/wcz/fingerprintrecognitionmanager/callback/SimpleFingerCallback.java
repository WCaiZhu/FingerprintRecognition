package com.wcz.fingerprintrecognitionmanager.callback;


import com.wcz.fingerprintrecognitionmanager.interfaces.IFingerCallback;

public abstract class SimpleFingerCallback implements IFingerCallback {
    @Override
    public void onError(String error) {

    }

    @Override
    public void onHelp(String help) {

    }

    @Override
    public void onCancel() {

    }
}
