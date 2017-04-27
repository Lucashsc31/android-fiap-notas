package br.com.fiap.notas.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ArquivoDB {

    private SharedPreferences pref;

    public void gravarChaves(Context context, String prefName, HashMap<String, String> map) {
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            e.putString(entry.getKey(), entry.getValue());
        }
        e.commit();
    }

    public void excluirChaves(Context context, String prefName, HashMap<String, String> map) {
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            e.remove(entry.getKey());
        }
        e.commit();
    }

    public void excluirChaves(Context context, String prefName, List<String> keys) {
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        for (String sKey : keys) {
            e.remove(sKey);
        }
        e.commit();
    }

    public String retornarValor(Context context, String prefName, String key){
        String valor = null;
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        valor = pref.getString(key, "");
        return valor;
    }

    public boolean verificarChave(Context context, String prefName, String key) {
        pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        return pref.contains(key);
    }

    public HashMap<String, Boolean> verificarTodasChaves(Context context, String prefName, List<String> keys) {
        HashMap<String, Boolean> resp = null;
        SharedPreferences sharedPrefs = context.getSharedPreferences(prefName, MODE_PRIVATE);
        for (String sKey : keys) {
            resp.put(sKey, sharedPrefs.contains(sKey));
        }
        return resp;
    }

    public void gravarArquivo(Context context, String name, String value) throws FileNotFoundException {
        FileOutputStream fos = context.openFileOutput(name, MODE_PRIVATE);
        try {
            fos.write(value.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lerArquivo(Context context, String name) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String txt = null;
        try {
            txt = br.readLine();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public boolean excluirArquivo(Context context, String name) throws FileNotFoundException {
        try {
            return context.deleteFile(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}

