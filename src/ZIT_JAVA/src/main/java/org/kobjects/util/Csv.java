package org.kobjects.util;

import java.util.Vector;

/* loaded from: classes.dex */
public class Csv {
    public static String encode(String value, char quote) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == quote || c == '^') {
                buf.append(c);
                buf.append(c);
            } else if (c < ' ') {
                buf.append('^');
                buf.append((char) (c + '@'));
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String encode(Object[] values) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            if (i != 0) {
                buf.append(',');
            }
            Object v = values[i];
            if ((v instanceof Number) || (v instanceof Boolean)) {
                buf.append(v.toString());
            } else {
                buf.append('\"');
                buf.append(encode(v.toString(), '\"'));
                buf.append('\"');
            }
        }
        return buf.toString();
    }

    public static String[] decode(String line) {
        int p0;
        Vector tmp = new Vector();
        int p02 = 0;
        int len = line.length();
        while (true) {
            if (p02 < len && line.charAt(p02) <= ' ') {
                p02++;
            } else if (p02 >= len) {
                break;
            } else if (line.charAt(p02) == '\"') {
                int p03 = p02 + 1;
                StringBuffer buf = new StringBuffer();
                while (true) {
                    p0 = p03 + 1;
                    char c = line.charAt(p03);
                    if (c != '^' || p0 >= len) {
                        if (c != '\"') {
                            p03 = p0;
                        } else if (p0 == len || line.charAt(p0) != '\"') {
                            break;
                        } else {
                            p03 = p0 + 1;
                        }
                        buf.append(c);
                    } else {
                        p03 = p0 + 1;
                        char c2 = line.charAt(p0);
                        if (c2 != '^') {
                            c2 = (char) (c2 - '@');
                        }
                        buf.append(c2);
                    }
                }
                tmp.addElement(buf.toString());
                int p04 = p0;
                while (p04 < len && line.charAt(p04) <= ' ') {
                    p04++;
                }
                if (p04 >= len) {
                    break;
                } else if (line.charAt(p04) != ',') {
                    throw new RuntimeException("Comma expected at " + p04 + " line: " + line);
                } else {
                    p02 = p04 + 1;
                }
            } else {
                int p1 = line.indexOf(44, p02);
                if (p1 == -1) {
                    tmp.addElement(line.substring(p02).trim());
                    break;
                }
                tmp.addElement(line.substring(p02, p1).trim());
                p02 = p1 + 1;
            }
        }
        String[] result = new String[tmp.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (String) tmp.elementAt(i);
        }
        return result;
    }
}
