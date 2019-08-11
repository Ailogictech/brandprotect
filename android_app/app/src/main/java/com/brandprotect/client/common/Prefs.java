package com.brandprotect.client.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

public class Prefs {

    private static final String TAG = "Prefs";

    static Prefs prefs = null;

    static SharedPreferences preferences;

    static SharedPreferences.Editor editor;

    Prefs(Context context) {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static Prefs with(Context context) {
        if (prefs == null) {
            prefs = new Builder(context).build();
        }
        return prefs;
    }

    /**
     * Should be annotated by GSON
     *
     * @param key
     * @param object
     */
    public void save(String key, Object object) {

        if (object == null) {
            editor.putString(key, null).apply();
            return;
        }

        Gson gson = new Gson();
        String jsonObject = gson.toJson(object);
        save(key, jsonObject);
    }

    public void save(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public void save(String key, String value) {
        editor.putString(key, value).apply();
    }

    public void save(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public void save(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public void save(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public void save(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    public <T> T getObject(String key, Class<T> clazz) {

        String json = getString(key, null);
        if (TextUtils.isEmpty(json))
            return null;

        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValue) {
        return preferences.getStringSet(key, defValue);
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void remove(String key) {
        editor.remove(key).apply();
    }

    public void removeAll() {
        editor.clear().apply();
    }

    private static class Builder {

        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }

            this.context = context.getApplicationContext();
        }

        public Prefs build() {
            return new Prefs(context);
        }
    }
}
