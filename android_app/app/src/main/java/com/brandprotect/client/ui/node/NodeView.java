package com.brandprotect.client.ui.node;

import com.brandprotect.client.ui.mvp.IView;

public interface NodeView extends IView {

    void displayNodeList(int count);
    void errorNodeList();

}
