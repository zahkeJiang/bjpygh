package com.wxgzpt.bjpygh.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyConfig implements WXPayConfig {

    private byte[] certData;


    public String getAppID() {
        return "wx74d8d40a83387a3e";
    }

    public String getMchID() {
        return "1483244352";
    }

    public String getKey() {
        return "6t78rt8GFH780gfYE9yf90EY90eyf09E";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}