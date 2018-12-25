package com.brandprotect.client.ui.intro;

import com.brandprotect.client.ui.mvp.IView;

interface IntroView extends IView {

    void startCreateAccountActivity();

    void startLoginActivity();

    void startBackupAccountActivity();

    void showErrorMsg();

    void doesNotSupportAlgorithm();

    void connectionError();
}
