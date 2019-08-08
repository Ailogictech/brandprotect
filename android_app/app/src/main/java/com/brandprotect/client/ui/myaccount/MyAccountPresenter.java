package com.brandprotect.client.ui.myaccount;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.brandprotect.tronlib.dto.Account;
import com.brandprotect.client.common.Constants;
import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.database.dao.FavoriteTokenDao;
import com.brandprotect.client.database.model.AccountModel;
import com.brandprotect.client.database.model.FavoriteTokenModel;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;
import com.brandprotect.client.tron.exception.InvalidPasswordException;
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

public class MyAccountPresenter extends BasePresenter<MyAccountView> {

    private Tron mTron;
    private WalletAppManager mWalletAppManager;
    private RxJavaSchedulers mRxJavaSchedulers;
    private FavoriteTokenDao mFavoriteTokenDao;

    public MyAccountPresenter(MyAccountView view, Tron tron, WalletAppManager walletAppManager,
            RxJavaSchedulers rxJavaSchedulers, AppDatabase appDatabase) {
        super(view);
        this.mTron = tron;
        this.mWalletAppManager = walletAppManager;
        this.mRxJavaSchedulers = rxJavaSchedulers;
        this.mFavoriteTokenDao = appDatabase.favoriteTokenDao();
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

    public Single<List<AccountModel>> getAccountList() {
        return mTron.getAccountList();
    }

    public void getAccountAccountInfo() {
        final String address = mTron.getLoginAddress();

        mView.showLoadingDialog();

        mTron.getAccount(address)
        .map((account -> {
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
                    .name(account.getName())
                    .balance(account.getBalance())
                    .bandwidth(account.getBandwidth().getNetRemaining())
                    .assetList(assetList)
                    .frozenList(frozenList)
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
                mView.displayAccountInfo(address, account);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                // todo - error msg
                if (e instanceof ConnectException) {
                    // internet error
                }

                mView.showServerError();
                mView.displayAccountInfo(address, null);
            }
        });
    }



    public String getLoginPrivateKey(@NonNull String password) {
        return mTron.getLoginPrivateKey(password);
    }

    public void changeLoginAccount(@NonNull AccountModel accountModel) {
        mTron.changeLoginAccount(accountModel);
    }

    public void freezeBalance(@NonNull String password, long freezeBalance) {
        mView.showLoadingDialog();

        mTron.freezeBalance(password, freezeBalance, Constants.FREEZE_DURATION)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    mView.successFreezeBalance();
                } else {
                    mView.showServerError();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof InvalidPasswordException) {
                    mView.showInvalidPasswordMsg();
                } else {
                    e.printStackTrace();
                    mView.showServerError();
                }
            }
        });
    }

    public void unfreezeBalance(@NonNull String password) {
        mView.showLoadingDialog();

        mTron.unfreezeBalance(password)
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    mView.successFreezeBalance();
                } else {
                    mView.showServerError();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof RuntimeException) {
                    mView.unableToUnfreeze();
                } else {
                    mView.showServerError();
                }
            }
        });
    }

    public long getLoginAccountIndex() {
        return mTron.getLoginAccount().getId();
    }

    public AccountModel getLoginAccount() {
        return mTron.getLoginAccount();
    }

    public int getAccountCount() {
        return mTron.getAccountCount();
    }

    @Nullable
    public boolean isFavoriteToken(@NonNull String tokenName) {
        if (mTron.getLoginAccount() != null) {
            long accountId = mTron.getLoginAccount().getId();

            return mFavoriteTokenDao.findByAccountIdAndTokenName(accountId, tokenName) != null;
        }

        return false;
    }

    public void doFavorite(@NonNull String tokenName) {
        if (mTron.getLoginAccount() != null) {
            long accountId = mTron.getLoginAccount().getId();
            FavoriteTokenModel model = FavoriteTokenModel.builder()
                    .accountId(accountId)
                    .tokenName(tokenName)
                    .build();

            mFavoriteTokenDao.insert(model);
        }
    }

    public void removeFavorite(@NonNull String tokenName) {
        if (mTron.getLoginAccount() != null) {
            long accountId = mTron.getLoginAccount().getId();
            FavoriteTokenModel favoriteTokenModel = mFavoriteTokenDao.findByAccountIdAndTokenName(accountId, tokenName);
            mFavoriteTokenDao.delete(favoriteTokenModel);
        }
    }

    public boolean matchPassword(@NonNull String password) {
        return mWalletAppManager.login(password) == WalletAppManager.SUCCESS;
    }

    public void removeAccount(long accountId, String accountName) {
        mView.showLoadingDialog();
        Single.fromCallable(() -> {
            mTron.removeAccount(accountId, accountName);
            return true;
        })
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe((result) -> mView.successDelete());
    }
}
