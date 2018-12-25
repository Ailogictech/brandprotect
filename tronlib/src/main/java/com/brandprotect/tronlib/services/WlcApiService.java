package com.brandprotect.tronlib.services;

import com.brandprotect.tronlib.dto.Witnesses;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WlcApiService {

    @GET("api/vote/witness")
    Single<Witnesses> getVoteWitnesses();
}
