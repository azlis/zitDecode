package org.kxml2.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Hashtable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class KXmlParser implements XmlPullParser
{
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final int LEGACY = 999;
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    private static final int XML_DECL = 998;
    private int attributeCount;
    private int column;
    private boolean degenerated;
    private int depth;
    private String encoding;
    private Hashtable entityMap;
    private String error;
    private boolean isWhitespace;
    private int line;
    private Object location;
    private String name;
    private String namespace;
    private int peekCount;
    private String prefix;
    private boolean processNsp;
    private Reader reader;
    private boolean relaxed;
    private char[] srcBuf;
    private int srcCount;
    private int srcPos;
    private Boolean standalone;
    private boolean token;
    private int txtPos;
    private int type;
    private boolean unresolved;
    private String version;
    private boolean wasCR;
    private String[] elementStack = new String[16];
    private String[] nspStack = new String[8];
    private int[] nspCounts = new int[4];
    private char[] txtBuf = new char[128];
    private String[] attributes = new String[16];
    private int[] peek = new int[2];

    public KXmlParser()
    {
        int i = 128;
        this.srcBuf = new char[Runtime.getRuntime().freeMemory() >= 1048576 ? 8192 : i];
    }

    private final boolean isProp(String n1, boolean prop, String n2)
    {
        if (!n1.startsWith("http://xmlpull.org/v1/doc/"))
        {
            return false;
        }
        if (prop)
        {
            return n1.substring(42).equals(n2);
        }
        return n1.substring(40).equals(n2);
    }

    private final boolean adjustNsp() throws XmlPullParserException
    {
        String prefix = null;
        String attrName = null;
        boolean any = false;
        int i = 0;
        while (i < (this.attributeCount << 2))
        {
            String attrName2 = this.attributes[i + 2];
            int cut = attrName2.indexOf(58);
            if (cut != -1)
            {
                prefix = attrName2.substring(0, cut);
                attrName = attrName2.substring(cut + 1);
            } else if (attrName2.equals("xmlns"))
            {
                prefix = attrName2;
                attrName = null;
            } else
            {
                i += 4;
            }
            if (!prefix.equals("xmlns"))
            {
                any = true;
            } else
            {
                int[] iArr = this.nspCounts;
                int i2 = this.depth;
                int i3 = iArr[i2];
                iArr[i2] = i3 + 1;
                int j = i3 << 1;
                this.nspStack = ensureCapacity(this.nspStack, j + 2);
                this.nspStack[j] = attrName;
                this.nspStack[j + 1] = this.attributes[i + 3];
                if (attrName != null && this.attributes[i + 3].equals(XmlPullParser.NO_NAMESPACE))
                {
                    error("illegal empty namespace");
                }
                String[] strArr = this.attributes;
                int i4 = this.attributeCount - 1;
                this.attributeCount = i4;
                System.arraycopy(this.attributes, i + 4, strArr, i, (i4 << 2) - i);
                i -= 4;
            }
            i += 4;
        }
        if (any)
        {
            for (int i5 = (this.attributeCount << 2) - 4; i5 >= 0; i5 -= 4)
            {
                String attrName3 = this.attributes[i5 + 2];
                int cut2 = attrName3.indexOf(58);
                if (cut2 != 0 || this.relaxed)
                {
                    if (cut2 != -1)
                    {
                        String attrPrefix = attrName3.substring(0, cut2);
                        String attrName4 = attrName3.substring(cut2 + 1);
                        String attrNs = getNamespace(attrPrefix);
                        if (attrNs != null || this.relaxed)
                        {
                            this.attributes[i5] = attrNs;
                            this.attributes[i5 + 1] = attrPrefix;
                            this.attributes[i5 + 2] = attrName4;
                        } else
                        {
                            throw new RuntimeException("Undefined Prefix: " + attrPrefix + " in " + this);
                        }
                    }
                } else
                {
                    throw new RuntimeException("illegal attribute name: " + attrName3 + " at " + this);
                }
            }
        }
        int cut3 = this.name.indexOf(58);
        if (cut3 == 0)
        {
            error("illegal tag name: " + this.name);
        }
        if (cut3 != -1)
        {
            this.prefix = this.name.substring(0, cut3);
            this.name = this.name.substring(cut3 + 1);
        }
        this.namespace = getNamespace(this.prefix);
        if (this.namespace == null)
        {
            if (this.prefix != null)
            {
                error("undefined prefix: " + this.prefix);
            }
            this.namespace = XmlPullParser.NO_NAMESPACE;
        }
        return any;
    }

    private final String[] ensureCapacity(String[] arr, int required)
    {
        if (arr.length >= required)
        {
            return arr;
        }
        String[] bigger = new String[required + 16];
        System.arraycopy(arr, 0, bigger, 0, arr.length);
        return bigger;
    }

    private final void error(String desc) throws XmlPullParserException
    {
        if (!this.relaxed)
        {
            exception(desc);
        } else if (this.error == null)
        {
            this.error = "ERR: " + desc;
        }
    }

    private final void exception(String desc) throws XmlPullParserException
    {
        if (desc.length() >= 100)
        {
            desc = desc.substring(0, 100) + "\n";
        }
        throw new XmlPullParserException(desc, this, null);
    }

    private final void nextImpl() throws IOException, XmlPullParserException
    {
        boolean z = false;
        if (this.reader == null)
        {
            exception("No Input specified");
        }
        if (this.type == 3)
        {
            this.depth--;
        }
        do
        {
            this.attributeCount = -1;
            if (this.degenerated)
            {
                this.degenerated = false;
                this.type = 3;
                return;
            } else if (this.error != null)
            {
                for (int i = 0; i < this.error.length(); i++)
                {
                    push(this.error.charAt(i));
                }
                this.error = null;
                this.type = 9;
                return;
            } else
            {
                this.prefix = null;
                this.name = null;
                this.namespace = null;
                this.type = peekType();
                switch (this.type)
                {
                    case 1:
                        return;
                    case 2:
                        parseStartTag(false);
                        return;
                    case 3:
                        parseEndTag();
                        return;
                    case 4:
                        if (!this.token)
                        {
                            z = true;
                        }
                        pushText(60, z);
                        if (this.depth == 0 && this.isWhitespace)
                        {
                            this.type = 7;
                            return;
                        }
                        return;
                    case 5:
                    default:
                        this.type = parseLegacy(this.token);
                        break;
                    case 6:
                        pushEntity();
                        return;
                }
            }
        } while (this.type == XML_DECL);
    }

    private final int parseLegacy(boolean push) throws IOException, XmlPullParserException
    {
        int result;
        int term;
        String req = XmlPullParser.NO_NAMESPACE;
        int prev = 0;
        read();
        int c = read();
        if (c == 63)
        {
            if ((peek(0) == 120 || peek(0) == 88) && (peek(1) == 109 || peek(1) == 77))
            {
                if (push)
                {
                    push(peek(0));
                    push(peek(1));
                }
                read();
                read();
                if ((peek(0) == 108 || peek(0) == 76) && peek(1) <= 32)
                {
                    if (this.line != 1 || this.column > 4)
                    {
                        error("PI must not start with xml");
                    }
                    parseStartTag(true);
                    if (this.attributeCount < 1 )
                    {
                        error("version expected");
                    }
                    this.version = this.attributes[3];
                    int pos = 1;
                    if (1 < this.attributeCount && "encoding".equals(this.attributes[6]))
                    {
                        this.encoding = this.attributes[7];
                        pos = 1 + 1;
                    }
                    if (pos < this.attributeCount && "standalone".equals(this.attributes[(pos * 4) + 2]))
                    {
                        String st = this.attributes[(pos * 4) + 3];
                        if ("yes".equals(st))
                        {
                            this.standalone = Boolean.TRUE;
                        } else if ("no".equals(st))
                        {
                            this.standalone = Boolean.FALSE;
                        } else
                        {
                            error("illegal standalone value: " + st);
                        }
                        pos++;
                    }
                    if (pos != this.attributeCount)
                    {
                        error("illegal xmldecl");
                    }
                    this.isWhitespace = true;
                    this.txtPos = 0;
                    return XML_DECL;
                }
            }
            term = 63;
            result = 8;
        } else if (c != 33)
        {
            error("illegal: <" + c);
            return 9;
        } else if (peek(0) == 45)
        {
            result = 9;
            req = "--";
            term = 45;
        } else if (peek(0) == 91)
        {
            result = 5;
            req = "[CDATA[";
            term = 93;
            push = true;
        } else
        {
            result = 10;
            req = "DOCTYPE";
            term = -1;
        }
        for (int i = 0; i < req.length(); i++)
        {
            read(req.charAt(i));
        }
        if (result == 10)
        {
            parseDoctype(push);
            return result;
        }
        while (true)
        {
            int c2 = read();
            if (c2 == -1)
            {
                error(UNEXPECTED_EOF);
                return 9;
            }
            if (push)
            {
                push(c2);
            }
            if ((term == 63 || c2 == term) && peek(0) == term && peek(1) == 62)
            {
                if (term == 45 && prev == 45 && !this.relaxed)
                {
                    error("illegal comment delimiter: --->");
                }
                read();
                read();
                if (!push || term == 63)
                {
                    return result;
                }
                this.txtPos--;
                return result;
            }
            prev = c2;
        }
    }

    private final void parseDoctype(boolean push) throws IOException, XmlPullParserException
    {
        int nesting = 1;
        boolean quoted = false;
        while (true)
        {
            int i = read();
            switch (i)
            {
                case -1:
                    error(UNEXPECTED_EOF);
                    return;
                case 39:
                    if (quoted)
                    {
                        quoted = false;
                        break;
                    } else
                    {
                        quoted = true;
                        break;
                    }
                case 60:
                    if (!quoted)
                    {
                        nesting++;
                        break;
                    }
                    break;
                case 62:
                    if (!quoted && nesting - 1 == 0)
                    {
                        return;
                    }
                    break;
            }
            if (push)
            {
                push(i);
            }
        }
    }

    private final void parseEndTag() throws IOException, XmlPullParserException
    {
        read();
        read();
        this.name = readName();
        skip();
        read('>');
        int sp = (this.depth - 1) << 2;
        if (this.depth == 0)
        {
            error("element stack empty");
            this.type = 9;
        } else if (!this.relaxed)
        {
            if (!this.name.equals(this.elementStack[sp + 3]))
            {
                error("expected: /" + this.elementStack[sp + 3] + " read: " + this.name);
            }
            this.namespace = this.elementStack[sp];
            this.prefix = this.elementStack[sp + 1];
            this.name = this.elementStack[sp + 2];
        }
    }

    private final int peekType() throws IOException
    {
        switch (peek(0))
        {
            case -1:
                return 1;
            case 38:
                return 6;
            case 60:
                switch (peek(1))
                {
                    case 33:
                    case 63:
                        return LEGACY;
                    case 47:
                        return 3;
                    default:
                        return 2;
                }
            default:
                return 4;
        }
    }

    private final String get(int pos)
    {
        return new String(this.txtBuf, pos, this.txtPos - pos);
    }

    private final void push(int c)
    {
        this.isWhitespace = (c <= 32) & this.isWhitespace;
        if (this.txtPos == this.txtBuf.length)
        {
            char[] bigger = new char[((this.txtPos * 4) / 3) + 4];
            System.arraycopy(this.txtBuf, 0, bigger, 0, this.txtPos);
            this.txtBuf = bigger;
        }
        char[] cArr = this.txtBuf;
        int i = this.txtPos;
        this.txtPos = i + 1;
        cArr[i] = (char) c;
    }

    private final void parseStartTag(boolean xmldecl) throws IOException, XmlPullParserException
    {
        if (!xmldecl)
        {
            read();
        }
        this.name = readName();
        this.attributeCount = 0;
        while (true)
        {
            skip();
            int c = peek(0);
            if (!xmldecl)
            {
                if (c != 47)
                {
                    if (c == 62 && !xmldecl)
                    {
                        read();
                        break;
                    }
                } else
                {
                    this.degenerated = true;
                    read();
                    skip();
                    read('>');
                    break;
                }
            } else if (c == 63)
            {
                read();
                read('>');
                return;
            }
            if (c == -1)
            {
                error(UNEXPECTED_EOF);
                return;
            }
            String attrName = readName();
            if (attrName.length() == 0)
            {
                error("attr name expected");
                break;
            }
            int i = this.attributeCount;
            this.attributeCount = i + 1;
            int i2 = i << 2;
            this.attributes = ensureCapacity(this.attributes, i2 + 4);
            int i3 = i2 + 1;
            this.attributes[i2] = XmlPullParser.NO_NAMESPACE;
            int i4 = i3 + 1;
            this.attributes[i3] = null;
            int i5 = i4 + 1;
            this.attributes[i4] = attrName;
            skip();
            if (peek(0) != 61)
            {
                if (!this.relaxed)
                {
                    error("Attr.value missing f. " + attrName);
                }
                this.attributes[i5] = attrName;
            } else
            {
                read('=');
                skip();
                int delimiter = peek(0);
                if (delimiter == 39 || delimiter == 34)
                {
                    read();
                } else
                {
                    if (!this.relaxed)
                    {
                        error("attr value delimiter missing!");
                    }
                    delimiter = 32;
                }
                int p = this.txtPos;
                pushText(delimiter, true);
                this.attributes[i5] = get(p);
                this.txtPos = p;
                if (delimiter != 32)
                {
                    read();
                }
            }
        }
        int i6 = this.depth;
        this.depth = i6 + 1;
        int sp = i6 << 2;
        this.elementStack = ensureCapacity(this.elementStack, sp + 4);
        this.elementStack[sp + 3] = this.name;
        if (this.depth >= this.nspCounts.length)
        {
            int[] bigger = new int[this.depth + 4];
            System.arraycopy(this.nspCounts, 0, bigger, 0, this.nspCounts.length);
            this.nspCounts = bigger;
        }
        this.nspCounts[this.depth] = this.nspCounts[this.depth - 1];
        if (this.processNsp)
        {
            adjustNsp();
        } else
        {
            this.namespace = XmlPullParser.NO_NAMESPACE;
        }
        this.elementStack[sp] = this.namespace;
        this.elementStack[sp + 1] = this.prefix;
        this.elementStack[sp + 2] = this.name;
    }

    private final void pushEntity() throws IOException, XmlPullParserException
    {
        boolean z = true;
        push(read());
        int pos = this.txtPos;
        while (true)
        {
            int c = peek(0);
            if (c == 59)
            {
                read();
                String code = get(pos);
                this.txtPos = pos - 1;
                if (this.token && this.type == 6)
                {
                    this.name = code;
                }
                if (code.charAt(0) == '#')
                {
                    push(code.charAt(1) == 'x' ? Integer.parseInt(code.substring(2), 16) : Integer.parseInt(code.substring(1)));
                    return;
                }
                String result = (String) this.entityMap.get(code);
                if (result != null)
                {
                    z = false;
                }
                this.unresolved = z;
                if (!this.unresolved)
                {
                    for (int i = 0; i < result.length(); i++)
                    {
                        push(result.charAt(i));
                    }
                    return;
                } else if (!this.token)
                {
                    error("unresolved: &" + code + ";");
                    return;
                } else
                {
                    return;
                }
            } else if (c >= 128 || ((c >= 48 && c <= 57) || ((c >= 97 && c <= 122) || ((c >= 65 && c <= 90) || c == 95 || c == 45 || c == 35))))
            {
                push(read());
            } else
            {
                if (!this.relaxed)
                {
                    error("unterminated entity ref");
                }
                System.out.println("broken entitiy: " + get(pos - 1));
                return;
            }
        }
    }

    private final void pushText(int delimiter, boolean resolveEntities) throws IOException, XmlPullParserException
    {
        int next = peek(0);
        int cbrCount = 0;
        while (next != -1 && next != delimiter)
        {
            if (delimiter != 32 || (next > 32 && next != 62))
            {
                if (next == 38)
                {
                    if (resolveEntities)
                    {
                        pushEntity();
                    } else
                    {
                        return;
                    }
                } else if (next == 10 && this.type == 2)
                {
                    read();
                    push(32);
                } else
                {
                    push(read());
                }
                if (next == 62 && cbrCount >= 2 && delimiter != 93)
                {
                    error("Illegal: ]]>");
                }
                if (next == 93)
                {
                    cbrCount++;
                } else
                {
                    cbrCount = 0;
                }
                next = peek(0);
            } else
            {
                return;
            }
        }
    }

    private final void read(char c) throws IOException, XmlPullParserException
    {
        int a = read();
        if (a != c)
        {
            error("expected: '" + c + "' actual: '" + ((char) a) + "'");
        }
    }

    private final int read() throws IOException
    {
        int result;
        if (this.peekCount == 0)
        {
            result = peek(0);
        } else
        {
            result = this.peek[0];
            this.peek[0] = this.peek[1];
        }
        this.peekCount--;
        this.column++;
        if (result == 10)
        {
            this.line++;
            this.column = 1;
        }
        return result;
    }

    private final int peek(int pos) throws IOException
    {
        int nw;
        int nw2;
        while (pos >= this.peekCount)
        {
            if (this.srcBuf.length <= 1)
            {
                nw2 = this.reader.read();
            } else if (this.srcPos < this.srcCount)
            {
                char[] cArr = this.srcBuf;
                int i = this.srcPos;
                this.srcPos = i + 1;
                nw2 = cArr[i];
            } else
            {
                this.srcCount = this.reader.read(this.srcBuf, 0, this.srcBuf.length);
                if (this.srcCount <= 0)
                {
                    nw = -1;
                } else
                {
                    nw = this.srcBuf[0];
                }
                this.srcPos = 1;
                nw2 = nw;
            }
            if (nw2 == 13)
            {
                this.wasCR = true;
                int[] iArr = this.peek;
                int i2 = this.peekCount;
                this.peekCount = i2 + 1;
                iArr[i2] = 10;
            } else
            {
                if (nw2 != 10)
                {
                    int[] iArr2 = this.peek;
                    int i3 = this.peekCount;
                    this.peekCount = i3 + 1;
                    iArr2[i3] = nw2 == 1 ? 1 : 0;
                } else if (!this.wasCR)
                {
                    int[] iArr3 = this.peek;
                    int i4 = this.peekCount;
                    this.peekCount = i4 + 1;
                    iArr3[i4] = 10;
                }
                this.wasCR = false;
            }
        }
        return this.peek[pos];
    }

    private final String readName() throws IOException, XmlPullParserException
    {
        int pos = this.txtPos;
        int c = peek(0);
        if ((c < 97 || c > 122) && ((c < 65 || c > 90) && c != 95 && c != 58 && c < 192 && !this.relaxed))
        {
            error("name expected");
        }
        while (true)
        {
            push(read());
            int c2 = peek(0);
            if (c2 < 97 || c2 > 122)
            {
                if (c2 < 65 || c2 > 90)
                {
                    if (c2 < 48 || c2 > 57)
                    {
                        if (!(c2 == 95 || c2 == 45 || c2 == 58 || c2 == 46 || c2 >= 183))
                        {
                            String result = get(pos);
                            this.txtPos = pos;
                            return result;
                        }
                    }
                }
            }
        }
    }

    private final void skip() throws IOException
    {
        while (true)
        {
            int c = peek(0);
            if (c <= 32 && c != -1)
            {
                read();
            } else
            {
                return;
            }
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader) throws XmlPullParserException
    {
        this.reader = reader;
        this.line = 1;
        this.column = 0;
        this.type = 0;
        this.name = null;
        this.namespace = null;
        this.degenerated = false;
        this.attributeCount = -1;
        this.encoding = null;
        this.version = null;
        this.standalone = null;
        if (reader != null)
        {
            this.srcPos = 0;
            this.srcCount = 0;
            this.peekCount = 0;
            this.depth = 0;
            this.entityMap = new Hashtable();
            this.entityMap.put("amp", "&");
            this.entityMap.put("apos", "'");
            this.entityMap.put("gt", ">");
            this.entityMap.put("lt", "<");
            this.entityMap.put("quot", "\"");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[Catch: Exception -> 0x008c, TryCatch #0 {Exception -> 0x008c, blocks: (B:8:0x0017, B:10:0x001e, B:12:0x0025, B:14:0x002c, B:15:0x002f, B:17:0x0036, B:20:0x005b, B:22:0x0076, B:26:0x00ac, B:27:0x00b4, B:28:0x00bc, B:29:0x00cd, B:30:0x00df, B:31:0x00fa, B:32:0x0115, B:34:0x011c, B:36:0x0131, B:39:0x0149, B:41:0x0151, B:43:0x0159, B:44:0x015d, B:45:0x016d, B:47:0x0174, B:48:0x0197, B:50:0x019e), top: B:51:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016d A[Catch: Exception -> 0x008c, TryCatch #0 {Exception -> 0x008c, blocks: (B:8:0x0017, B:10:0x001e, B:12:0x0025, B:14:0x002c, B:15:0x002f, B:17:0x0036, B:20:0x005b, B:22:0x0076, B:26:0x00ac, B:27:0x00b4, B:28:0x00bc, B:29:0x00cd, B:30:0x00df, B:31:0x00fa, B:32:0x0115, B:34:0x011c, B:36:0x0131, B:39:0x0149, B:41:0x0151, B:43:0x0159, B:44:0x015d, B:45:0x016d, B:47:0x0174, B:48:0x0197, B:50:0x019e), top: B:51:0x0017 }] */
    @Override // org.xmlpull.v1.XmlPullParser
    /* Code decompiled incorrectly, please refer to instructions dump */
    public void setInput(InputStream is, String _enc) throws XmlPullParserException, IOException
    {
        int i;
        this.srcPos = 0;
        this.srcCount = 0;
        String enc = _enc;
        if (is == null)
        {
            throw new IllegalArgumentException();
        }
        if (enc == null)
        {
            int chk = 0;
            while (this.srcCount < 4 && (i = is.read()) != -1)
            {
                try
                {
                    chk = (chk << 8) | i;
                    char[] cArr = this.srcBuf;
                    int i2 = this.srcCount;
                    this.srcCount = i2 + 1;
                    cArr[i2] = (char) i;
                } catch (Exception e)
                {
                    throw new XmlPullParserException("Invalid stream or encoding: " + e.toString(), this, e);
                }
            }
            if (this.srcCount == 4)
            {
                switch (chk)
                {
                    case -131072:
                        enc = "UTF-32LE";
                        this.srcCount = 0;
                        break;
                    case 60:
                        enc = "UTF-32BE";
                        this.srcBuf[0] = '<';
                        this.srcCount = 1;
                        break;
                    case 65279:
                        enc = "UTF-32BE";
                        this.srcCount = 0;
                        break;
                    case 3932223:
                        enc = "UTF-16BE";
                        this.srcBuf[0] = '<';
                        this.srcBuf[1] = '?';
                        this.srcCount = 2;
                        break;
                    case 1006632960:
                        enc = "UTF-32LE";
                        this.srcBuf[0] = '<';
                        this.srcCount = 1;
                        break;
                    case 1006649088:
                        enc = "UTF-16LE";
                        this.srcBuf[0] = '<';
                        this.srcBuf[1] = '?';
                        this.srcCount = 2;
                        break;
                    case 1010792557:
                        while (true)
                        {
                            int i3 = is.read();
                            if (i3 != -1)
                            {
                                char[] cArr2 = this.srcBuf;
                                int i4 = this.srcCount;
                                this.srcCount = i4 + 1;
                                cArr2[i4] = (char) i3;
                                if (i3 == 62)
                                {
                                    String s = new String(this.srcBuf, 0, this.srcCount);
                                    int i0 = s.indexOf("encoding");
                                    if (i0 != -1)
                                    {
                                        int i02 = i0;
                                        while (s.charAt(i02) != '\"' && s.charAt(i02) != '\'')
                                        {
                                            i02++;
                                        }
                                        int i03 = i02 + 1;
                                        enc = s.substring(i03, s.indexOf(s.charAt(i02), i03));
                                    }
                                }
                            }
                        }
                    default:
                        if ((-65536 & chk) == -16842752)
                        {
                        }
                        break;
                }
            }
        }
        if (enc == null)
        {
            enc = "UTF-8";
        }
        int sc = this.srcCount;
        setInput(new InputStreamReader(is, enc));
        this.encoding = _enc;
        this.srcCount = sc;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean getFeature(String feature)
    {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature))
        {
            return this.processNsp;
        }
        if (isProp(feature, false, "relaxed"))
        {
            return this.relaxed;
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getInputEncoding()
    {
        return this.encoding;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void defineEntityReplacementText(String entity, String value) throws XmlPullParserException
    {
        if (this.entityMap == null)
        {
            throw new RuntimeException("entity replacement text must be defined after setInput!");
        }
        this.entityMap.put(entity, value);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public Object getProperty(String property)
    {
        if (isProp(property, true, "xmldecl-version"))
        {
            return this.version;
        }
        if (isProp(property, true, "xmldecl-standalone"))
        {
            return this.standalone;
        }
        if (isProp(property, true, "location"))
        {
            return this.location != null ? this.location : this.reader.toString();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getNamespaceCount(int depth)
    {
        if (depth <= this.depth)
        {
            return this.nspCounts[depth];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespacePrefix(int pos)
    {
        return this.nspStack[pos << 1];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespaceUri(int pos)
    {
        return this.nspStack[(pos << 1) + 1];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace(String prefix)
    {
        if ("xml".equals(prefix))
        {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(prefix))
        {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int i = (getNamespaceCount(this.depth) << 1) - 2; i >= 0; i -= 2)
        {
            if (prefix == null)
            {
                if (this.nspStack[i] == null)
                {
                    return this.nspStack[i + 1];
                }
            } else if (prefix.equals(this.nspStack[i]))
            {
                return this.nspStack[i + 1];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getDepth()
    {
        return this.depth;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPositionDescription()
    {
        StringBuffer buf = new StringBuffer(this.type < TYPES.length ? TYPES[this.type] : "unknown");
        buf.append(' ');
        if (this.type == 2 || this.type == 3)
        {
            if (this.degenerated)
            {
                buf.append("(empty) ");
            }
            buf.append('<');
            if (this.type == 3)
            {
                buf.append('/');
            }
            if (this.prefix != null)
            {
                buf.append("{" + this.namespace + "}" + this.prefix + ":");
            }
            buf.append(this.name);
            int cnt = this.attributeCount << 2;
            for (int i = 0; i < cnt; i += 4)
            {
                buf.append(' ');
                if (this.attributes[i + 1] != null)
                {
                    buf.append("{" + this.attributes[i] + "}" + this.attributes[i + 1] + ":");
                }
                buf.append(this.attributes[i + 2] + "='" + this.attributes[i + 3] + "'");
            }
            buf.append('>');
        } else if (this.type != 7)
        {
            if (this.type != 4)
            {
                buf.append(getText());
            } else if (this.isWhitespace)
            {
                buf.append("(whitespace)");
            } else
            {
                String text = getText();
                if (text.length() > 16)
                {
                    text = text.substring(0, 16) + "...";
                }
                buf.append(text);
            }
        }
        buf.append("@" + this.line + ":" + this.column);
        if (this.location != null)
        {
            buf.append(" in ");
            buf.append(this.location);
        } else if (this.reader != null)
        {
            buf.append(" in ");
            buf.append(this.reader.toString());
        }
        return buf.toString();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getLineNumber()
    {
        return this.line;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getColumnNumber()
    {
        return this.column;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException
    {
        if (!(this.type == 4 || this.type == 7 || this.type == 5))
        {
            exception(ILLEGAL_TYPE);
        }
        return this.isWhitespace;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getText()
    {
        if (this.type < 4 || (this.type == 6 && this.unresolved))
        {
            return null;
        }
        return get(0);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public char[] getTextCharacters(int[] poslen)
    {
        if (this.type < 4)
        {
            poslen[0] = -1;
            poslen[1] = -1;
            return null;
        } else if (this.type == 6)
        {
            poslen[0] = 0;
            poslen[1] = this.name.length();
            return this.name.toCharArray();
        } else
        {
            poslen[0] = 0;
            poslen[1] = this.txtPos;
            return this.txtBuf;
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace()
    {
        return this.namespace;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getName()
    {
        return this.name;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPrefix()
    {
        return this.prefix;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isEmptyElementTag() throws XmlPullParserException
    {
        if (this.type != 2)
        {
            exception(ILLEGAL_TYPE);
        }
        return this.degenerated;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getAttributeCount()
    {
        return this.attributeCount;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeType(int index)
    {
        return "CDATA";
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isAttributeDefault(int index)
    {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeNamespace(int index)
    {
        if (index < this.attributeCount)
        {
            return this.attributes[index << 2];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeName(int index)
    {
        if (index < this.attributeCount)
        {
            return this.attributes[(index << 2) + 2];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributePrefix(int index)
    {
        if (index < this.attributeCount)
        {
            return this.attributes[(index << 2) + 1];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(int index)
    {
        if (index < this.attributeCount)
        {
            return this.attributes[(index << 2) + 3];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(String namespace, String name)
    {
        for (int i = (this.attributeCount << 2) - 4; i >= 0; i -= 4)
        {
            if (this.attributes[i + 2].equals(name) && (namespace == null || this.attributes[i].equals(namespace)))
            {
                return this.attributes[i + 3];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getEventType() throws XmlPullParserException
    {
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int next() throws XmlPullParserException, IOException
    {
        this.txtPos = 0;
        this.isWhitespace = true;
        int minType = 9999;
        this.token = false;
        while (true)
        {
            nextImpl();
            if (this.type < minType)
            {
                minType = this.type;
            }
            if (minType > 6 || (minType >= 4 && peekType() >= 4))
            {
                break;
            }
        }
        this.type = minType;
        if (this.type > 4)
        {
            this.type = 4;
        }
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextToken() throws XmlPullParserException, IOException
    {
        this.isWhitespace = true;
        this.txtPos = 0;
        this.token = true;
        nextImpl();
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextTag() throws XmlPullParserException, IOException
    {
        next();
        if (this.type == 4 && this.isWhitespace)
        {
            next();
        }
        if (!(this.type == 3 || this.type == 2))
        {
            exception("unexpected type");
        }
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void require(int type, String namespace, String name) throws XmlPullParserException, IOException
    {
        if (type != this.type || ((namespace != null && !namespace.equals(getNamespace())) || (name != null && !name.equals(getName()))))
        {
            exception("expected: " + TYPES[type] + " {" + namespace + "}" + name);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String nextText() throws XmlPullParserException, IOException
    {
        String result;
        if (this.type != 2)
        {
            exception("precondition: START_TAG");
        }
        next();
        if (this.type == 4)
        {
            result = getText();
            next();
        } else
        {
            result = XmlPullParser.NO_NAMESPACE;
        }
        if (this.type != 3)
        {
            exception("END_TAG expected");
        }
        return result;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setFeature(String feature, boolean value) throws XmlPullParserException
    {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature))
        {
            this.processNsp = value;
        } else if (isProp(feature, false, "relaxed"))
        {
            this.relaxed = value;
        } else
        {
            exception("unsupported feature: " + feature);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setProperty(String property, Object value) throws XmlPullParserException
    {
        if (isProp(property, true, "location"))
        {
            this.location = value;
            return;
        }
        throw new XmlPullParserException("unsupported property: " + property);
    }

    public void skipSubTree() throws XmlPullParserException, IOException
    {
        require(2, null, null);
        int level = 1;
        while (level > 0)
        {
            int eventType = next();
            if (eventType == 3)
            {
                level--;
            } else if (eventType == 2)
            {
                level++;
            }
        }
    }
}
