package com.brandprotect.client.ui.accountdetail.overview;

import android.support.annotation.NonNull;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.tronlib.dto.Account;
import com.brandprotect.client.common.Constants;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.ui.main.dto.Asset;
import com.brandprotect.client.ui.main.dto.Frozen;
import com.brandprotect.client.ui.main.dto.TronAccount;
import com.brandprotect.client.ui.mvp.BasePresenter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class OverviewPresenter extends BasePresenter<OverviewView> {

    private TronNetwork mTronNetwork;
    private RxJavaSchedulers mRxJavaSchedulers;

    public OverviewPresenter(OverviewView view, TronNetwork tronNetwork, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mTronNetwork = tronNetwork;
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

    }

    @Override
    public void onDestroy() {

    }

    public void getAccount(@NonNull String address) {
        mView.showLoadingDialog();

        Single.zip(mTronNetwork.getAccount(address), mTronNetwork.getTransactionStats(address), ((account, transactionStats) -> {
            List<Frozen> frozenList = new ArrayList<>();

            for (Account.FrozenTrx frozen : account.getFrozen().getBalances()) {
                frozenList.add(Frozen.builder()
                        .frozenBalance(frozen.getAmount())
                        .expireTime(frozen.getExpires())
                        .build());
            }

            List<Asset> assetList = new ArrayList<>();

            for (Account.Balance balance : account.getTokenBalances()) {
                if (Constants.TRON_SYMBOL.equalsIgnoreCase(balance.getName())) {
                    continue;
                }

                assetList.add(Asset.builder()
                        .name(balance.getName())
                        .balance(balance.getBalance())
                        .build());
            }

            return TronAccount.builder()
                    .balance(account.getBalance())
                    .bandwidth(account.getBandwidth().getNetRemaining())
                    .assetList(assetList)
                    .frozenList(frozenList)
                    .transactions(transactionStats.getTransactions())
                    .transactionIn(transactionStats.getTransactionsIn())
                    .transactionOut(transactionStats.getTransactionsOut())
                    .account(account)
                    .build();
        }))
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<TronAccount>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(TronAccount account) {
                mView.finishLoading(account);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                // todo - error msg
                if (e instanceof ConnectException) {
                    // internet error
                }

                mView.showServerError();
            }
        });
    }
}
