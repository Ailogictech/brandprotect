package com.brandprotect.client.common;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

import com.brandprotect.client.R;
import com.brandprotect.client.ui.accountdetail.AccountDetailActivity;
import com.brandprotect.client.ui.blockdetail.BlockDetailActivity;
import com.google.gson.Gson;

import org.tron.protos.Protocol;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Utils {

    public static String getContractTypeString(Context context, int contractType) {

        String[] contractTypes = context.getResources().getStringArray(R.array.contract_types);

        try {
            if (contractType == Protocol.Transaction.Contract.ContractType.UNRECOGNIZED.getNumber()) {
                return context.getString(R.string.unrecognized_text);
            }
            return contractTypes[contractType];
        } catch (Exception e) {
            return context.getString(R.string.unrecognized_text);
        }
    }

    public static String getDateTimeWithTimezone(long timestamp) {
        return Constants.sdf.format(new Date(timestamp));
    }

    public static String getUsdFormat(float number) {
        return Constants.usdFormat.format(number);
    }

    public static String getUsdFormat(double number) {
        return Constants.usdFormat.format(number);
    }

    public static String getCommaNumber(int number) {
        return Constants.numberFormat.format(number);
    }

    public static String getCommaNumber(long number) {
        return Constants.numberFormat.format(number);
    }

    public static String getTrxFormat(double number) {
        return Constants.tronBalanceFormat.format(number);
    }

    public static String getRealTrxFormat(long number) {
        return Constants.tronBalanceFormat.format((double) number / Constants.ONE_TRX);
    }

    public static String getPercentFormat(float number) {
        return Constants.percentFormat.format(number);
    }

    public static String getPercentFormat(double number) {
        return Constants.percentFormat.format(number);
    }

    public static void setAccountDetailAction(Context context, TextView textView, String address) {
        SpannableString content = new SpannableString(address);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        textView.setText(content);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AccountDetailActivity.class);
            intent.putExtra(AccountDetailActivity.EXTRA_ADDRESS, address);
            context.startActivity(intent);
        });
    }

    public static void setBlockDetailAction(Context context, TextView textView, long blockNum) {
        SpannableString content = new SpannableString(Constants.numberFormat.format(blockNum));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        textView.setText(content);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), BlockDetailActivity.class);
            intent.putExtra(BlockDetailActivity.EXTRA_BLOCK_NUMBER, blockNum);
            context.startActivity(intent);
        });
    }

    @Getter
    @Setter
    public static class ParsedToken {
        String brand;
        String name;
        String count;
        String date;
    }

    // todo implement me. Parse token name from "abcZX123-xxx" to {name: abc, id: 123, description: xxx}
    public static ParsedToken parseTokenName(String name) {
        ParsedToken parsedToken = new ParsedToken();
        String[] parsed = name.split("XZ");
        if (parsed.length == 4) {
            parsedToken.setBrand(parsed[0]);
            parsedToken.setName(parsed[1]);
            parsedToken.setCount(parsed[2]);
            parsedToken.setDate(String.valueOf(
                    String.valueOf(parsed[3].charAt(0)) + String.valueOf(parsed[3].charAt(1))
                            + "-" +
                            String.valueOf(parsed[3].charAt(2)) + String.valueOf(parsed[3].charAt(3))
                            + "-" +
                            String.valueOf(parsed[3].charAt(4)) + String.valueOf(parsed[3].charAt(5)) + String.valueOf(parsed[3].charAt(6)) + String.valueOf(parsed[3].charAt(7))
            ));
        } else {
            parsedToken.setName(name);
        }
        return parsedToken;
    }
}