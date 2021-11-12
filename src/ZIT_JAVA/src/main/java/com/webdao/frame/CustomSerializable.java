package com.webdao.frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public interface CustomSerializable
{
    int initFromStream(InputStream inputStream);

    int writeToStream(OutputStream outputStream) throws IOException;
}
