package com.virtuspay.scannerbr.handler;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerStartHandler implements  ScannerHandler , PluginRegistry.RequestPermissionsResultListener {
    private final ZXingScannerView zXingScannerView;
    private ScannerHandler next;
    private final Activity activity;

    public ScannerStartHandler(ZXingScannerView zXingScannerView, Activity activity) {
        this.zXingScannerView = zXingScannerView;
        this.activity = activity;
    }

    @Override
    public void setNext(ScannerHandler next) {
        this.next = next;

    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equalsIgnoreCase("start")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                int MY_CAMERA_REQUEST_CODE = 100;
                boolean granted = activity.checkSelfPermission(Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED;
                if (!granted) {
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);

                } else {
                    zXingScannerView.startCamera();
                    zXingScannerView.setAutoFocus(true);
                }

            } else {
                zXingScannerView.startCamera();
            }


            result.success(null);
        } else if (next != null) {
            next.onMethodCall(methodCall, result);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e("PERMISSÃO DNV", "PERMISSÃO AAAAAAAA");

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                zXingScannerView.startCamera();
            }
            return true;
        }
        return false;
    }
}
