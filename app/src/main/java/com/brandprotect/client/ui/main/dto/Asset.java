package com.brandprotect.client.ui.main.dto;

import com.brandprotect.client.common.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset {

    private String name;

    private double balance;

    public String getSampleName() {
        if (name != null && !name.isEmpty()) {
            String[] split = name.split(";");
            if (split.length > 0) {
                return split[0];
            }
        }
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + Constants.tronBalanceFormat.format(balance) + ")";
    }
}
