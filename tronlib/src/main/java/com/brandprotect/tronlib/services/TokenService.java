package com.brandprotect.tronlib.services;

import com.brandprotect.tronlib.dto.Token;
import com.brandprotect.tronlib.dto.TokenHolders;
import com.brandprotect.tronlib.dto.Tokens;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TokenService {

    @GET("api/token")
    Single<Tokens> getTokens(@Query("start") long start, @Query("limit") int limit,
            @Query("sort") String sort, @Query("status") String status);

    @GET("api/token/{name}")
    Single<Token> getTokenDetail(@Path("name") String tokenName);

    @GET("api/token/{name}/address")
    Single<TokenHolders> getTokenHolders(@Path("name") String tokenName, @Query("start") long start,
            @Query("limit") int limit, @Query("sort") String sort);

    @GET("api/token")
    Single<Tokens> findTokens(@Query("name") String query, @Query("start") long start,
            @Query("limit") int limit, @Query("sort") String sort);
}
