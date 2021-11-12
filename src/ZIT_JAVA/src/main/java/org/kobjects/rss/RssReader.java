package org.kobjects.rss;

import java.io.IOException;
import java.io.Reader;
import org.kobjects.xml.XmlReader;

/* loaded from: classes.dex */
public class RssReader {
    public static final int AUTHOR = 4;
    public static final int DATE = 3;
    public static final int DESCRIPTION = 2;
    public static final int LINK = 1;
    public static final int TITLE = 0;
    XmlReader xr;

    public RssReader(Reader reader) throws IOException {
        this.xr = new XmlReader(reader);
    }

    void readText(StringBuffer buf) throws IOException {
        while (this.xr.next() != 3) {
            switch (this.xr.getType()) {
                case 2:
                    readText(buf);
                    break;
                case 4:
                    buf.append(this.xr.getText());
                    break;
            }
        }
    }

    public String[] next() throws IOException {
        String[] item = new String[5];
        while (this.xr.next() != 1) {
            if (this.xr.getType() == 2) {
                String n = this.xr.getName().toLowerCase();
                if (n.equals("item") || n.endsWith(":item")) {
                    while (this.xr.next() != 3) {
                        if (this.xr.getType() == 2) {
                            String name = this.xr.getName().toLowerCase();
                            int cut = name.indexOf(":");
                            if (cut != -1) {
                                name = name.substring(cut + 1);
                            }
                            StringBuffer buf = new StringBuffer();
                            readText(buf);
                            String text = buf.toString();
                            if (name.equals("title")) {
                                item[0] = text;
                            } else if (name.equals("link")) {
                                item[1] = text;
                            } else if (name.equals("description")) {
                                item[2] = text;
                            } else if (name.equals("date")) {
                                item[3] = text;
                            } else if (name.equals("author")) {
                                item[4] = text;
                            }
                        }
                    }
                    return item;
                }
            }
        }
        return null;
    }
}
