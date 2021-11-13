package com.webdao.frame;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class CommonUtility
{
    public static String getCurrentDate()
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static CharSequence getOtherCurrentDate()
    {
        return new SimpleDateFormat("yyyy'-'MM'-'dd").format(new Date());
    }
}
