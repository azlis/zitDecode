package org.kobjects.xmlrpc;

import java.io.FileReader;
import org.kobjects.xml.XmlReader;

/* loaded from: classes.dex */
public class Driver {
    public static void main(String[] args) throws Exception {
        new XmlRpcParser(new XmlReader(new FileReader(args[0]))).parseResponse();
    }
}
