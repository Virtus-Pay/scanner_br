package com.virtuspay.scannerbr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.virtuspay.scannerbr.handler.ScannerHandler;
import com.virtuspay.scannerbr.handler.ScannerStartHandler;
import com.virtuspay.scannerbr.handler.ScannerStopHandler;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.platform.PlatformView;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;



class ResizableViewFinder extends ViewFinderView {
    public ResizableViewFinder(Context context) {
        super(context);
    }

    public ResizableViewFinder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //drawViewFinderBorder(canvas);
       // drawViewFinderMask(canvas);
        //drawLaser(canvas);
       // drawViewFinderBorder(canvas);

    }

}

public class ScannerBrPlatformView implements PlatformView,ZXingScannerView.ResultHandler,PluginRegistry.RequestPermissionsResultListener {
    final PluginRegistry.Registrar registrar;
    final ZXingScannerView zXingScannerView;
    MethodChannel methodChannel;


    public ScannerBrPlatformView(PluginRegistry.Registrar registrar, int id) {

        this.registrar = registrar;

      //  zXingScannerView = registrar.activity().findViewById(R.id.zxing_barcode_scanner);

        zXingScannerView = new ZXingScannerView(registrar.activity()) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                ViewFinderView finderView = new ResizableViewFinder(context);

                return finderView ;
            }
        };

       //final List<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE);
        // zXingScannerView.setFormats(formats);
        zXingScannerView.setResultHandler(this);
        methodChannel = new MethodChannel(registrar.messenger(),"plugins.flutterplatform/scannerbr_" + id);

        ScannerHandler scannerStartHandler = new ScannerStartHandler(zXingScannerView, registrar.activity());
        ScannerHandler scannerStopHandler = new ScannerStopHandler(zXingScannerView);
        scannerStartHandler.setNext(scannerStopHandler);
        methodChannel.setMethodCallHandler(scannerStartHandler);
        registrar.addRequestPermissionsResultListener(this);



        /*
              //final List<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE);
           // zXingScannerView.setFormats(formats);
            // zXingScannerView.setFlash(true);
            zXingScannerView.setAutoFocus(true);
            zXingScannerView.setResultHandler(this);
            zXingScannerView.startCamera();
         */

    }


    @Override
    public View getView() {
        return zXingScannerView;
    }

    @Override
    public void dispose() {
        zXingScannerView.setResultHandler(null);
        zXingScannerView.removeAllViews();
        zXingScannerView.stopCamera();
     methodChannel.setMethodCallHandler(null);
     methodChannel = null;
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.d("RESULT",rawResult.getText());
        methodChannel.invokeMethod("onResult",rawResult.getText());
       zXingScannerView.resumeCameraPreview(this);

    }

    @Override
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e("PERMISSÃO DNV","PERMISSÃO AAAAAAAA");

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                zXingScannerView.startCamera();
            }
        }
        return true;
    }
}
