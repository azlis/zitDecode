package com.webdao.frame;

import com.webdao.model.CFileInfo;
import com.webdao.model.CPileInfo;
import com.webdao.model.ShowParams;

/* loaded from: classes.dex */
public interface IStatusChangedListener {
    void AfterCreatedPile(CPileInfo cPileInfo);

    void FileInfoChanged(CFileInfo cFileInfo, boolean z);

}
