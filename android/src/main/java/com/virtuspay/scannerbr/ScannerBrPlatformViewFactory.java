package com.virtuspay.scannerbr;

import android.content.Context;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class ScannerBrPlatformViewFactory extends PlatformViewFactory {

    private final PluginRegistry.Registrar registrar;

    public ScannerBrPlatformViewFactory(PluginRegistry.Registrar registrar) {
        super(StandardMessageCodec.INSTANCE);
        this.registrar = registrar;
    }

    @Override
    public PlatformView create(Context context, int id, Object o) {
        return new ScannerBrPlatformView(registrar, id);
    }
}
