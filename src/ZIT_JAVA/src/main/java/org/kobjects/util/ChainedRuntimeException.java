package org.kobjects.util;

/* loaded from: classes.dex */
public class ChainedRuntimeException extends RuntimeException {
    Exception chain;

    public static ChainedRuntimeException create(Exception e, String s) {
        try {
            return ((ChainedRuntimeException) Class.forName("org.kobjects.util.ChainedRuntimeExceptionSE").newInstance())._create(e, s);
        } catch (Exception e2) {
            return new ChainedRuntimeException(e, s);
        }
    }

    ChainedRuntimeException() {
    }

    ChainedRuntimeException(Exception e, String s) {
        super((s == null ? "rethrown" : s) + ": " + e.toString());
        this.chain = e;
    }

    ChainedRuntimeException _create(Exception e, String s) {
        throw new RuntimeException("ERR!");
    }

    public Exception getChained() {
        return this.chain;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        super.printStackTrace();
        if (this.chain != null) {
            this.chain.printStackTrace();
        }
    }
}
