package com.brandprotect.client.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Build;

import com.brandprotect.tronlib.Hosts;
import com.brandprotect.tronlib.ServiceBuilder;
import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.tronlib.services.AccountService;
import com.brandprotect.tronlib.services.CoinMarketCapService;
import com.brandprotect.tronlib.services.TokenService;
import com.brandprotect.tronlib.services.TronScanService;
import com.brandprotect.tronlib.services.VoteService;
import com.brandprotect.tronlib.services.WlcApiService;
import com.brandprotect.client.common.Constants;
import com.brandprotect.client.common.CustomPreference;
import com.brandprotect.client.common.security.PasswordEncoder;
import com.brandprotect.client.common.security.PasswordEncoderImpl;
import com.brandprotect.client.common.security.UpdatableBCrypt;
import com.brandprotect.client.common.security.keystore.KeyStore;
import com.brandprotect.client.common.security.keystore.KeyStoreApi15Impl;
import com.brandprotect.client.common.security.keystore.KeyStoreApi18Impl;
import com.brandprotect.client.common.security.keystore.KeyStoreApi23Impl;
import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.di.ApplicationContext;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.rxjava.RxJavaSchedulersImpl;
import com.brandprotect.client.tron.AccountManager;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;
import com.brandprotect.client.tron.repository.LocalDbRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static CustomPreference provideCustomPreference(@ApplicationContext Context context) {
        return  new CustomPreference(context);
    }

    @Provides
    @Singleton
    static VoteService provideVoteService() {
        return ServiceBuilder.createService(VoteService.class, Hosts.TRONSCAN_API);
    }

    @Provides
    @Singleton
    static CoinMarketCapService provideCoinMarketCapService() {
        return ServiceBuilder.createService(CoinMarketCapService.class, Hosts.COINMARKETCAP_API);
    }

    @Provides
    @Singleton
    static TronScanService provideTronScanService() {
        return ServiceBuilder.createService(TronScanService.class, Hosts.TRONSCAN_API);
    }

    @Provides
    @Singleton
    static TokenService provideTokenService() {
        return ServiceBuilder.createService(TokenService.class, Hosts.TRONSCAN_API);
    }

    @Provides
    @Singleton
    static AccountService provideAccountService() {
        return ServiceBuilder.createService(AccountService.class, Hosts.TRONSCAN_API);
    }

    @Provides
    @Singleton
    static WlcApiService provideWlcApiService() {
        return ServiceBuilder.createService(WlcApiService.class, Hosts.TRONSCAN_WLC_API);
    }

    @Provides
    @Singleton
    static TronNetwork provideTronNetwork(VoteService voteService, CoinMarketCapService coinMarketCapService,
            TronScanService tronScanService, TokenService tokenService, AccountService accountService,
            WlcApiService wlcApiService) {
        return new TronNetwork(voteService, coinMarketCapService, tronScanService,
                tokenService, accountService, wlcApiService);
    }

    @Provides
    @Singleton
    static AccountManager provideAccountManager(AppDatabase appDatabase, KeyStore keyStore) {
        return new AccountManager(new LocalDbRepository(appDatabase), keyStore);
    }

    @Provides
    @Singleton
    static KeyStore provideKeyStore(@ApplicationContext Context context, CustomPreference customPreference) {
        KeyStore keyStore = null;

        int keyStoreVersion = customPreference.getInitWallet() ? customPreference.getKeyStoreVersion() : Build.VERSION.SDK_INT;

        if (keyStoreVersion >= Build.VERSION_CODES.M) {
            keyStore = new KeyStoreApi23Impl(customPreference);
        } else if (keyStoreVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            keyStore = new KeyStoreApi18Impl(context);
        } else {
            keyStore = new KeyStoreApi15Impl(customPreference);
        }

        keyStore.init();
        keyStore.createKeys(Constants.ALIAS_SALT);
        keyStore.createKeys(Constants.ALIAS_ACCOUNT_KEY);
        keyStore.createKeys(Constants.ALIAS_PASSWORD_KEY);
        keyStore.createKeys(Constants.ALIAS_ADDRESS_KEY);

        return keyStore;
    }

    @Provides
    @Singleton
    static UpdatableBCrypt provideUpdatableBCrypt() {
        return new UpdatableBCrypt(Constants.SALT_LOG_ROUND);
    }

    @Provides
    @Singleton
    static PasswordEncoder providePasswordEncoder(CustomPreference customPreference, KeyStore keyStore,
            UpdatableBCrypt updatableBCrypt) {
        PasswordEncoderImpl passwordEncoder = new PasswordEncoderImpl(customPreference, keyStore, updatableBCrypt);
        passwordEncoder.init();

        return passwordEncoder;
    }

    @Provides
    @Singleton
    static WalletAppManager provideWalletAppManager(PasswordEncoder passwordEncoder, AppDatabase appDatabase) {
        return new WalletAppManager(passwordEncoder, appDatabase);
    }

    @Provides
    @Singleton
    static Tron provideTron(@ApplicationContext Context context, TronNetwork tronNetwork,
            CustomPreference customPreference, AccountManager accountManager, WalletAppManager walletAppManager) {
        return new Tron(context, tronNetwork, customPreference, accountManager, walletAppManager);
    }

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
    }

    @Provides
    @Singleton
    static RxJavaSchedulers provideRxJavaSchedulers() {
        return new RxJavaSchedulersImpl();
    }
}
