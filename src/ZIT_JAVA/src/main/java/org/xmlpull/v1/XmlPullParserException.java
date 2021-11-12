package org.xmlpull.v1;

/* loaded from: classes.dex */
public class XmlPullParserException extends Exception {
    protected int column;
    protected Throwable detail;
    protected int row;

    public XmlPullParserException(String s) {
        super(s);
        this.row = -1;
        this.column = -1;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public XmlPullParserException(String msg, XmlPullParser parser, Throwable chain) {

        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        if (msg == null) {
            str = XmlPullParser.NO_NAMESPACE;
        } else {
            str = msg + " ";
        }
        StringBuilder append = sb.append(str);
        if (parser == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        } else {
            str2 = "(position:" + parser.getPositionDescription() + ") ";
        }
        StringBuilder append2 = append.append(str2);
        if (chain == null) {
            str3 = XmlPullParser.NO_NAMESPACE;
        } else {
            str3 = "caused by: " + chain;
        }
        this.row = -1;
        this.column = -1;
        if (parser != null) {
            this.row = parser.getLineNumber();
            this.column = parser.getColumnNumber();
        }
        this.detail = chain;
    }

    public Throwable getDetail() {
        return this.detail;
    }

    public int getLineNumber() {
        return this.row;
    }

    public int getColumnNumber() {
        return this.column;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.detail == null) {
            super.printStackTrace();
            return;
        }
        synchronized (System.err) {
            System.err.println(super.getMessage() + "; nested exception is:");
            this.detail.printStackTrace();
        }
    }
}
