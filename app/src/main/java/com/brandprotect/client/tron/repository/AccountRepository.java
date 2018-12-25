package com.brandprotect.client.tron.repository;

import android.support.annotation.NonNull;

import com.brandprotect.client.database.model.AccountModel;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface AccountRepository {

    Single<Long> insertAccount(@NonNull AccountModel accountModel);

    Single<Boolean> updateAccount(@NonNull AccountModel accountModel);

    void delete(@NonNull AccountModel accountModel);

    Maybe<AccountModel> loadAccount(long index);

    Single<List<AccountModel>> loadAllAccounts();

    Integer countAccount();

    AccountModel loadByAccountKey(String accountKey);
}
