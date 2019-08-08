package com.brandprotect.client.ui.accountdetail.transaction;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.tronlib.dto.Transaction;
import com.brandprotect.tronlib.dto.Transactions;
import com.brandprotect.client.common.AdapterDataModel;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.ui.mvp.BasePresenter;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class TransactionPresenter extends BasePresenter<TransactionView> {

    private AdapterDataModel<Transaction> mAdapterDataModel;
    private TronNetwork mTronNetwork;
    private RxJavaSchedulers mRxJavaSchedulers;

    public TransactionPresenter(TransactionView view, TronNetwork tronNetwork, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mTronNetwork = tronNetwork;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    public void setAdapterDataModel(AdapterDataModel<Transaction> adapterDataModel) {
        this.mAdapterDataModel = adapterDataModel;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void getTransactions(long block, long startIndex, int pageSize) {
        mView.showLoadingDialog();
        mTronNetwork.getTransactions(block, startIndex, pageSize, "-timestamp", true)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Transactions>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Transactions transactions) {
                mAdapterDataModel.addAll(transactions.getData());
                mView.finishLoading(transactions.getTotal());
            }

            @Override
            public void onError(Throwable e) {
                mView.showServerError();
            }
        });
    }

    public void getTransactions(String address, long startIndex, int pageSize) {
        mView.showLoadingDialog();

        mTronNetwork.getTransactions(address, startIndex, pageSize, "-timestamp", true)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Transactions>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Transactions transactions) {
                mAdapterDataModel.addAll(transactions.getData());
                mView.finishLoading(transactions.getTotal());
            }

            @Override
            public void onError(Throwable e) {
                mView.showServerError();
            }
        });
    }
}
