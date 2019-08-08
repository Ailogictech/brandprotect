package com.brandprotect.client.common.security;

import android.support.annotation.NonNull;

public interface PasswordEncoder {

    String encode(@NonNull String rawPassword);

    boolean matches(@NonNull String rawPassword, @NonNull String encodedPassword);
}
