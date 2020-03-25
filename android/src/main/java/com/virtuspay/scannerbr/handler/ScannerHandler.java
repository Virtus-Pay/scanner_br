package com.virtuspay.scannerbr.handler;

import io.flutter.plugin.common.MethodChannel;

public interface ScannerHandler extends MethodChannel.MethodCallHandler {

    void setNext(ScannerHandler next);
}
