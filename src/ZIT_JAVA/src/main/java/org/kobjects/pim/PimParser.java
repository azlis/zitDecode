package org.kobjects.pim;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.kobjects.io.LookAheadReader;

/* loaded from: classes.dex */
public class PimParser {
    LookAheadReader reader;
    Class type;

    public PimParser(Reader reader, Class type) {
        this.reader = new LookAheadReader(reader);
        this.type = type;
    }

    public PimItem readItem() throws IOException {
        Object readStringValue;
        String beg = readName();
        if (beg == null) {
            return null;
        }
        if (!beg.equals("begin")) {
            throw new RuntimeException("'begin:' expected");
        }
        try {
            PimItem item = (PimItem) this.type.newInstance();
            this.reader.read();
            if (!item.getType().equals(readStringValue().toLowerCase())) {
                throw new RuntimeException("item types do not match!");
            }
            while (true) {
                String name = readName();
                if (name.equals("end")) {
                    this.reader.read();
                    System.out.println("end:" + readStringValue());
                    return item;
                }
                PimField field = new PimField(name);
                readProperties(field);
                switch (item.getType(name)) {
                    case 1:
                        readStringValue = readArrayValue(item.getArraySize(name));
                        break;
                    default:
                        readStringValue = readStringValue();
                        break;
                }
                field.setValue(readStringValue);
                System.out.println("value:" + readStringValue);
                item.addField(field);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    String readName() throws IOException {
        String name = this.reader.readTo(":;").trim().toLowerCase();
        System.out.println("name:" + name);
        if (this.reader.peek(0) == -1) {
            return null;
        }
        return name;
    }

    String[] readArrayValue(int size) throws IOException {
        Vector values = new Vector();
        StringBuffer buf = new StringBuffer();
        boolean stay = true;
        do {
            buf.append(this.reader.readTo(";\n\r"));
            switch (this.reader.read()) {
                case 10:
                    break;
                case 13:
                    if (this.reader.peek(0) == 10) {
                        this.reader.read();
                        break;
                    }
                    break;
                case 59:
                    values.addElement(buf.toString());
                    buf.setLength(0);
                    continue;
            }
            if (this.reader.peek(0) != 32) {
                stay = false;
                continue;
            } else {
                this.reader.read();
                continue;
            }
        } while (stay);
        if (buf.length() != 0) {
            values.addElement(buf.toString());
        }
        String[] ret = new String[size];
        for (int i = 0; i < Math.min(ret.length, values.size()); i++) {
            ret[i] = (String) values.elementAt(i);
        }
        return ret;
    }

    String readStringValue() throws IOException {
        String value = this.reader.readLine();
        while (this.reader.peek(0) == 32) {
            this.reader.read();
            value = value + this.reader.readLine();
        }
        return value;
    }

    void readProperties(PimField field) throws IOException {
        int c = this.reader.read();
        while (c == 32) {
            c = this.reader.read();
        }
        while (c != 58) {
            String name = this.reader.readTo(":;=").trim().toLowerCase();
            c = this.reader.read();
            if (c == 61) {
                field.setProperty(name, this.reader.readTo(":;").trim().toLowerCase());
                c = this.reader.read();
            } else {
                field.setAttribute(name, true);
            }
        }
    }
}
