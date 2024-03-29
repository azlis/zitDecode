package com.webdao.frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface CustomSerializable
{
    int InitFromStream(InputStream inputStream);

    int WriteToStream(OutputStream outputStream) throws IOException;
}
