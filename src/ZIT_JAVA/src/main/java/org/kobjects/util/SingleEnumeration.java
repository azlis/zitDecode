package org.kobjects.util;

import java.util.Enumeration;

/* loaded from: classes.dex */
public class SingleEnumeration implements Enumeration {
    Object object;

    public SingleEnumeration(Object object) {
        this.object = object;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.object != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Object result = this.object;
        this.object = null;
        return result;
    }
}
