package com.brandprotect.client.ui.blockexplorer.overview;

import com.brandprotect.tronlib.dto.CoinMarketCap;
import com.brandprotect.tronlib.dto.RichInfo;
import com.brandprotect.tronlib.dto.RichTotal;

import lombok.Getter;

/**
 * Created by user on 2018. 5. 30..
 */

@Getter
public class RichItemViewModel {
    private String balanceRange;
    private int addressCount;
    private float addressPercentage;
    private double coinPercentage;
    private double coins;
    private double usd;

    public RichItemViewModel(RichTotal total, RichInfo richInfo, CoinMarketCap coinMarketCap) {
        balanceRange = richInfo.getFrom() + "\n-\n" + richInfo.getTo();
        addressCount = richInfo.getAccounts();
        addressPercentage = richInfo.getAccounts() / (float) total.getAccounts() * 100f;
        coins = richInfo.getBalance();
        coinPercentage = richInfo.getBalance() / total.getCoins() * 100f;
        usd = coins * Double.parseDouble(coinMarketCap.getPriceUsd());
    }
}
