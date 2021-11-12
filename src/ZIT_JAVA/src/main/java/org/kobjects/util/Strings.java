package org.kobjects.util;

/* loaded from: classes.dex */
public class Strings {
    public static String replace(String src, String what, String by) {
        int i0 = src.indexOf(what);
        if (i0 == -1) {
            return src;
        }
        StringBuffer buf = new StringBuffer(src.substring(0, i0));
        while (true) {
            buf.append(by);
            int i02 = i0 + what.length();
            int i1 = src.indexOf(what, i02);
            if (i1 == -1) {
                buf.append(src.substring(i02));
                return buf.toString();
            }
            buf.append(src.substring(i02, i1));
            i0 = i1;
        }
    }

    public static String toAscii(String src) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c <= ' ') {
                buf.append(' ');
            } else if (c < '\u007f') {
                buf.append(c);
            } else {
                switch (c) {
                    case 196:
                        buf.append("Ae");
                        continue;
                    case '\u00d6':
                        buf.append("Oe");
                        continue;
                    case '\u00dc':
                        buf.append("Ue");
                        continue;
                    case '\u00df':
                        buf.append("ss");
                        continue;
                    case '\u00e4':
                        buf.append("ae");
                        continue;
                    case '\u00f6':
                        buf.append("oe");
                        continue;
                    case '\u00fc':
                        buf.append("ue");
                        continue;
                    default:
                        buf.append('?');
                        continue;
                }
            }
        }
        return buf.toString();
    }

    public static String fill(String s, int len, char c) {
        boolean left = len < 0;
        int len2 = Math.abs(len);
        if (s.length() >= len2) {
            return s;
        }
        StringBuffer buf = new StringBuffer();
        for (int len3 = len2 - s.length(); len3 > 0; len3--) {
            buf.append(c);
        }
        if (!left) {
            return s + buf.toString();
        }
        buf.append(s);
        return buf.toString();
    }

    public static String beautify(String s) {
        StringBuffer buf = new StringBuffer();
        if (s.length() > 0) {
            buf.append(Character.toUpperCase(s.charAt(0)));
            for (int i = 1; i < s.length() - 1; i++) {
                char c = s.charAt(i);
                if (Character.isUpperCase(c) && Character.isLowerCase(s.charAt(i - 1)) && Character.isLowerCase(s.charAt(i + 1))) {
                    buf.append(" ");
                }
                buf.append(c);
            }
            if (s.length() > 1) {
                buf.append(s.charAt(s.length() - 1));
            }
        }
        return buf.toString();
    }

    public static String lTrim(String s, String chars) {
        int i = 0;
        int len = s.length();
        while (i < len) {
            if (chars == null) {
                if (s.charAt(i) > ' ') {
                    break;
                }
                i++;
            } else if (chars.indexOf(s.charAt(i)) == -1) {
                break;
            } else {
                i++;
            }
        }
        return i == 0 ? s : s.substring(i);
    }

    public static String rTrim(String s, String chars) {
        int i = s.length() - 1;
        while (i >= 0) {
            if (chars == null) {
                if (s.charAt(i) > ' ') {
                    break;
                }
                i--;
            } else if (chars.indexOf(s.charAt(i)) == -1) {
                break;
            } else {
                i--;
            }
        }
        return i == s.length() + -1 ? s : s.substring(0, i + 1);
    }
}
