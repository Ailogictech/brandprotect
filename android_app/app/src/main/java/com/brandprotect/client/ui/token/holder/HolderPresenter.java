package com.brandprotect.client.ui.token.holder;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.tronlib.dto.TokenHolder;
import com.brandprotect.tronlib.dto.TokenHolders;
import com.brandprotect.client.common.AdapterDataModel;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.ui.mvp.BasePresenter;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HolderPresenter extends BasePresenter<HolderView> {

    private AdapterDataModel<TokenHolder>  mAdapterDataModel;
    private TronNetwork mTronNetwork;
    private RxJavaSchedulers mRxJavaSchedulers;

    public HolderPresenter(HolderView view, TronNetwork tronNetwork, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mTronNetwork = tronNetwork;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    public void setAdapterDataModel(AdapterDataModel<TokenHolder> adapterDataModel) {
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

    public void getTokenHolders(String tokenName, long startIndex, int pageSize) {
        mView.showLoadingDialog();

        Single.zip(mTronNetwork.getTokenHolders(tokenName, startIndex, pageSize, "-balance"), mTronNetwork.getTokenDetail(tokenName),
                ((tokenHolders, token) -> {
                    for (TokenHolder tokenHolder : tokenHolders.getData()) {
                        tokenHolder.setTotalSupply(token.getTotalSupply());
                        tokenHolder.setBalancePercent(((double) tokenHolder.getBalance() / (double) token.getTotalSupply()) * 100f);
                    }

                    return tokenHolders;
                }))
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(new SingleObserver<TokenHolders>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TokenHolders tokenHolders) {
                        mAdapterDataModel.addAll(tokenHolders.getData());
                        mView.finishLoading(tokenHolders.getTotal());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showServerError();
                    }
                });
    }
}
