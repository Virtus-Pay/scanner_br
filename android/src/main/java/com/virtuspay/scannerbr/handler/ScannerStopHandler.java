package com.virtuspay.scannerbr.handler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerStopHandler implements  ScannerHandler {
    private ScannerHandler next;
    private final ZXingScannerView zXingScannerView;

    public ScannerStopHandler(ZXingScannerView zXingScannerView) {
        this.zXingScannerView = zXingScannerView;
    }

    @Override
    public void setNext(ScannerHandler next) {
        this.next = next;
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

        if(methodCall.method.equalsIgnoreCase("stop")){
            zXingScannerView.stopCamera();
            result.success(null);
        }else if(next != null){
            next.onMethodCall(methodCall,result);
        }else{
            result.notImplemented();
        }
    }
}

