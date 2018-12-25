package com.brandprotect.tronlib.services;

import com.brandprotect.tronlib.dto.Account;
import com.brandprotect.tronlib.dto.AccountMedia;
import com.brandprotect.tronlib.dto.RichData;
import com.brandprotect.tronlib.dto.TopAddressAccounts;
import com.brandprotect.tronlib.dto.TronAccounts;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountService {

    @GET("api/account/{address}")
    Single<Account> getAccount(@Path("address") String address);

    @GET("api/account")
    Single<TronAccounts> getAccounts(@Query("start") long start, @Query("limit") int limit,
            @Query("sort") String sort);

    @GET("api/account")
    Single<TopAddressAccounts> getTopAddressAccounts(@Query("sort") String sort,
            @Query("limit") int limit);

    @GET("api/account/{address}/media")
    Single<AccountMedia> getAccountMedia(@Path("address") String address);

    @GET("api/account/richlist")
    Single<RichData> getRichData();

}
