package com.brandprotect.client.ui.sendtoken;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.exception.InvalidAddressException;
import com.brandprotect.client.tron.exception.InvalidPasswordException;
import com.brandprotect.client.ui.mvp.BasePresenter;

import org.tron.protos.Protocol;

import java.net.ConnectException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SendTokenPresenter extends BasePresenter<SendTokenView> {

    private Tron mTron;
    private RxJavaSchedulers mRxJavaSchedulers;

    public SendTokenPresenter(SendTokenView view, Tron tron, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mTron = tron;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        mTron.queryAccount(mTron.getLoginAddress())
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Protocol.Account>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Protocol.Account account) {
                mView.displayAccountInfo(account);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                // todo - error msg
                if (e instanceof ConnectException) {
                    // internet error
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    public void sendTron(String password, String toAddress, long amount) {
        if (!mTron.isLogin()) {
            mView.invalidPassword();
            return;
        }

        mTron.sendCoin(password, toAddress, amount)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Boolean>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Boolean result) {
                mView.sendTokenResult(result);
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof InvalidAddressException) {
                    mView.invalidAddress();
                } else if (e instanceof InvalidPasswordException) {
                    mView.invalidPassword();
                } else if (e instanceof RuntimeException) {
                    mView.connectionError();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void transferAsset(String password, String toAddress, String assetName, long amount) {
        if (!mTron.isLogin()) {
            mView.invalidPassword();
            return;
        }

        mTron.transferAsset(password, toAddress, assetName, amount)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Boolean>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Boolean result) {
                mView.sendTokenResult(result);
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof InvalidAddressException) {
                    mView.invalidAddress();
                } else if (e instanceof InvalidPasswordException) {
                    mView.invalidPassword();
                } else if (e instanceof RuntimeException) {
                    mView.connectionError();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
