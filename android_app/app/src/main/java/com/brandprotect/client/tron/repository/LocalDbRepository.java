package com.brandprotect.client.tron.repository;

import android.support.annotation.NonNull;

import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.database.dao.AccountDao;
import com.brandprotect.client.database.model.AccountModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class LocalDbRepository implements AccountRepository {

    private AccountDao mAccountDao;

    public LocalDbRepository(@NonNull AppDatabase appDatabase) {
        mAccountDao = appDatabase.accountDao();
    }

    @Override
    public Single<Long> insertAccount(@NonNull AccountModel accountModel) {
        return Single.fromCallable(() -> {
            Date now = Calendar.getInstance().getTime();
            accountModel.setCreated(now);

            return mAccountDao.insert(accountModel);
        });
    }

    @Override
    public Single<Boolean> updateAccount(@NonNull AccountModel accountModel) {
        return Single.fromCallable(() -> {
            Date now = Calendar.getInstance().getTime();
            accountModel.setUpdated(now);

            mAccountDao.update(accountModel);

            return true;
        });
    }

    @Override
    public void delete(@NonNull AccountModel accountModel) {
        mAccountDao.delete(accountModel);
    }

    @Override
    public Maybe<AccountModel> loadAccount(long index) {
        return Maybe.fromCallable(() -> mAccountDao.loadAccountById(index));
    }

    @Override
    public Single<List<AccountModel>> loadAllAccounts() {
        return Single.fromCallable(() -> mAccountDao.loadAllAccounts());
    }

    @Override
    public Integer countAccount() {
        return mAccountDao.countAccounts();
    }

    @Override
    public AccountModel loadByAccountKey(String accountKey) {
        return mAccountDao.loadByAccountKey(accountKey);
    }
}
