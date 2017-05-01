package com.wix.renan.nacteste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wix.renan.nacteste.util.ArquivoDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNome, etSenha, etEmail;
    private final String sp = "dados";
    private ArquivoDB arquivoDB;
    private HashMap<String, String> mapDados;
    private List<String> keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        arquivoDB = new ArquivoDB();
        mapDados = new HashMap<>();
        keys = new ArrayList<>();
    }


    public boolean capturarDados(){
        String nome, email, senha;
        nome = etNome.getText().toString();
        email = etEmail.getText().toString();
        senha = etSenha.getText().toString();

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(senha) && !TextUtils.isEmpty(nome)){
            mapDados.put("nome",nome);
            mapDados.put("email",email);
            mapDados.put("senha",senha);
            return true;
        }else{
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    public void gravar(View v){
        if (capturarDados()){
            arquivoDB.gravar(this, sp, mapDados);
            Toast.makeText(this, "Dados cadastrados com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public void ler(View v){
        String nome, email, senha;
        nome = arquivoDB.retornar(this, sp, "nome");
        email= arquivoDB.retornar(this, sp, "email");
        senha = arquivoDB.retornar(this, sp, "senha");

        etNome.setText(nome);
        etEmail.setText(email);
        etSenha.setText(senha);
    }

    public void deletar(View v){
        keys.add("nome");
        keys.add("email");
        keys.add("senha");
        arquivoDB.excluir(this, sp, keys);
        Toast.makeText(this, "Deletado com sucesso!", Toast.LENGTH_SHORT).show();
        etNome.setText("");
        etEmail.setText("");
        etSenha.setText("");
    }

    public void limpar(View v){
        etNome.setText("");
        etEmail.setText("");
        etSenha.setText("");
    }
}
