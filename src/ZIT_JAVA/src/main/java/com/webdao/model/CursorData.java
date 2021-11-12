package com.webdao.model;

import java.util.Locale;

/* loaded from: classes.dex */
public class CursorData {
    public float C;
    public float F;
    public float L;
    public float R;
    public float T;
    public float V;
    public int pointPosi = -1;
    public int pointOffsetPosi = -1;

    public String[] toString(boolean isMeterStatus) {
        this.C = this.C == Float.POSITIVE_INFINITY ? 0.0f : this.C;
        return isMeterStatus ? new String[]{String.format(Locale.getDefault(), "T:%1$-5.1f\u03bcs V:%2$-3.2fmV R:%3$-3.2fm/s^2", Float.valueOf(1000.0f * this.T), Float.valueOf(this.V), Float.valueOf(this.R))} : new String[]{String.format(Locale.getDefault(), "T:%1$-3.2fms L:%2$-4.2fm C:%3$-3.2fm/s", Float.valueOf(this.T), Float.valueOf(this.L), Float.valueOf(this.C))};
    }
}
