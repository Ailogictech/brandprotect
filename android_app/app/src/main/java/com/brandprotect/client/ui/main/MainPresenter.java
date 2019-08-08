package com.brandprotect.client.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.tronlib.dto.Account;
import com.brandprotect.tronlib.dto.CoinMarketCap;
import com.brandprotect.client.common.AdapterDataModel;
import com.brandprotect.client.common.Constants;
import com.brandprotect.client.common.CustomPreference;
import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.database.dao.FavoriteTokenDao;
import com.brandprotect.client.database.model.AccountModel;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;
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

public class MainPresenter extends BasePresenter<MainView> {

    private AdapterDataModel<Asset> mAdapterDataModel;
    private Tron mTron;
    private TronNetwork mTronNetwork;
    private RxJavaSchedulers mRxJavaSchedulers;
    private CustomPreference mCustomPreference;
    private FavoriteTokenDao mFavoriteTokenDao;
    private WalletAppManager mWalletAppManager;

    public MainPresenter(MainView view, Tron tron, WalletAppManager walletAppManager, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers, CustomPreference customPreference, AppDatabase appDatabase) {
        super(view);
        this.mTron = tron;
        this.mWalletAppManager = walletAppManager;
        this.mTronNetwork = tronNetwork;
        this.mRxJavaSchedulers = rxJavaSchedulers;
        this.mCustomPreference = customPreference;
        this.mFavoriteTokenDao = appDatabase.favoriteTokenDao();
    }

    public void setAdapterDataModel(AdapterDataModel<Asset> adapterDataModel) {
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

    public boolean isLogin() {
        return mTron.isLogin();
    }

    public void getMyAccountInfo() {
        mTron.getAccount(mTron.getLoginAddress())
                .map((account -> {
                    List<Frozen> frozenList = new ArrayList<>();

                    for (Account.FrozenTrx frozen : account.getFrozen().getBalances()) {
                        frozenList.add(Frozen.builder()
                                .frozenBalance(frozen.getAmount())
                                .expireTime(frozen.getExpires())
                                .build());
                    }

                    long accountId = mTron.getLoginAccount().getId();
                    List<Asset> assetList = new ArrayList<>();

                    for (Account.Balance balance : account.getTokenBalances()) {
                        if (Constants.TRON_SYMBOL.equalsIgnoreCase(balance.getName())) {
                            continue;
                        }

                        if (mCustomPreference.isFavoriteToken(accountId)) {
                            if (mFavoriteTokenDao.findByAccountIdAndTokenName(accountId, balance.getName()) != null) {
                                assetList.add(Asset.builder()
                                        .name(balance.getName())
                                        .balance(balance.getBalance())
                                        .build());
                            }
                        } else {
                            assetList.add(Asset.builder()
                                    .name(balance.getName())
                                    .balance(balance.getBalance())
                                    .build());
                        }
                    }

                    return TronAccount.builder()
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
                        mView.displayAccountInfo(account);
                        mAdapterDataModel.clear();
                        mAdapterDataModel.addAll(account.getAssetList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        // todo - error msg
                        if (e instanceof ConnectException) {
                            // internet error
                        }

                        mView.connectionError();
                    }
                });
    }

    public void getTronMarketInfo() {
        mTronNetwork.getCoinInfo(Constants.TRON_COINMARKET_NAME)
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(new SingleObserver<List<CoinMarketCap>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CoinMarketCap> coinMarketCaps) {
                        if (coinMarketCaps.size() > 0) {
                            mView.setTronMarketInfo(coinMarketCaps.get(0));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public boolean logout() {
        mTron.logout();

        return true;
    }

    @Nullable
    public AccountModel getLoginAccount() {
        return mTron.getLoginAccount();
    }

    public Single<Boolean> changeLoginAccountName(@NonNull String accountName) {
        return mTron.changeLoginAccountName(accountName);
    }

    public void createAccount(@NonNull String nickname, @NonNull String password) {
        mTron.createAccount(nickname, password)
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer result) {
                        if (result == Tron.SUCCESS) {
                            mView.successCreateAccount();
                        } else {
                            mView.showInvalidPasswordMsg();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public Single<List<AccountModel>> getAccountList() {
        return mTron.getAccountList();
    }

    public boolean changeLoginAccount(@NonNull AccountModel accountModel) {
        return mTron.changeLoginAccount(accountModel);
    }

    public void importAccount(@NonNull String nickname, @NonNull String privateKey, @NonNull String password) {
        mTron.importAccount(nickname, privateKey, password)
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer result) {
                        if (result == Tron.SUCCESS) {
                            mView.successImportAccount();
                        } else if (result == Tron.ERROR_EXIST_ACCOUNT) {
                            mView.duplicatedAccount();
                        } else if (result == Tron.ERROR_PRIVATE_KEY) {
                            mView.failCreateAccount();
                        } else if (result == Tron.ERROR_INVALID_PASSWORD) {
                            mView.showInvalidPasswordMsg();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.failCreateAccount();
                    }
                });
    }

    public long getLoginAccountIndex() {
        return mTron.getLoginAccount().getId();
    }

    public void setOnlyFavorites(boolean isFavorites) {
        if (mTron.getLoginAccount() != null) {
            mCustomPreference.setFavoriteToken(mTron.getLoginAccount().getId(), isFavorites);
        }
    }

    public boolean getIsFavoritesTokens() {
        if (mTron.getLoginAccount() != null) {
            return mCustomPreference.isFavoriteToken(mTron.getLoginAccount().getId());
        }

        return false;
    }

    public boolean matchPassword(@NonNull String password) {
        return mWalletAppManager.login(password) == WalletAppManager.SUCCESS;
    }

    public void changePassword(@NonNull String oldPassword, @NonNull String newPassword) {
        mView.showChangePasswordDialog();

        Single.fromCallable(() -> mTron.changePassword(oldPassword, newPassword))
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        mView.changePasswordResult(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.changePasswordResult(false);
                    }
                });
    }
}
