package org.kobjects.pim;

/* loaded from: classes.dex */
public class VCard extends PimItem {
    public VCard() {
    }

    public VCard(VCard orig) {
        super(orig);
    }

    @Override // org.kobjects.pim.PimItem
    public String getType() {
        return "vcard";
    }

    @Override // org.kobjects.pim.PimItem
    public int getArraySize(String name) {
        if (name.equals("n")) {
            return 5;
        }
        if (name.equals("adr")) {
            return 6;
        }
        return -1;
    }
}
