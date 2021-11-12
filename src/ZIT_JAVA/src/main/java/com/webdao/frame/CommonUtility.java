package com.webdao.frame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class CommonUtility {
    public static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null) {
            return null;
        }
        int i = strSource.indexOf(strFrom, 0);
        if (i < 0) {
            return strSource;
        }
        char[] cSrc = strSource.toCharArray();
        char[] cTo = strTo.toCharArray();
        int len = strFrom.length();
        StringBuffer buf = new StringBuffer(cSrc.length);
        buf.append(cSrc, 0, i).append(cTo);
        while (true) {
            int i2 = i + len;
            i = strSource.indexOf(strFrom, i2);
            if (i > 0) {
                buf.append(cSrc, i2, i - i2).append(cTo);
            } else {
                buf.append(cSrc, i2, cSrc.length - i2);
                return buf.toString();
            }
        }
    }

    public static CharSequence getUTCTime() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.add(14, -(cal.get(15) + cal.get(16)));
        SimpleDateFormat s = new SimpleDateFormat("yyyy'-'MM'-'dd' 'kk':'mm':'ss'");
        return s.format(cal);
    }

    public static CharSequence getUTCTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(14, -(cal.get(15) + cal.get(16)));
        SimpleDateFormat s = new SimpleDateFormat("yyyy'-'MM'-'dd' 'kk':'mm':'ss'");
        return s.format(cal);
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static CharSequence getOtherCurrentDate() {
        return new SimpleDateFormat("yyyy'-'MM'-'dd").format(new Date());
    }

    public static String getFormatedDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static CharSequence getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getGPStime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }
}
