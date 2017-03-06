package br.com.thiengo.cinemaapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by viniciusthiengo on 05/03/17.
 */

public class SPAdSuporte {
    private static final String PREF = "pref";
    private static final String COUNTER_KEY = "counter";

    public static void incrementarCounterAbertura(Context context){
        SharedPreferences sp = context.getSharedPreferences( PREF, Context.MODE_PRIVATE );
        int counter = sp.getInt(COUNTER_KEY, 0);
        counter++;
        Log.i("log", "Counter: " + counter);
        sp.edit().putInt(COUNTER_KEY, counter).apply();
    }

    public static boolean ehTerceiraAbertura(Context context){
        SharedPreferences sp = context.getSharedPreferences( PREF, Context.MODE_PRIVATE );
        int counter = sp.getInt(COUNTER_KEY, 0);
        return counter % 3 == 0;
    }
}
