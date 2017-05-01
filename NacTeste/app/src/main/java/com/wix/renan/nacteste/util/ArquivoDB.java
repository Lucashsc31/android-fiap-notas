package com.wix.renan.nacteste.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lcal on 01/05/2017.
 */

public class ArquivoDB {

    private SharedPreferences preferences;

    public void gravar(Context context, String prefName, HashMap<String, String> map){
        preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();
        for (Map.Entry<String, String> entry : map.entrySet()){
            e.putString(entry.getKey(),entry.getValue());
        }
        e.commit();
    }

    public void excluir(Context context, String prefName, List<String> keys){
        preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();
        for (String key : keys){
            e.remove(key);
        }
        e.commit();
    }

    public String retornar(Context context, String prefName, String key){
        String valor = "";
        preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        valor = preferences.getString(key, "");
        return valor;
    }
}
