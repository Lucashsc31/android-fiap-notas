package br.com.fiap.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.HashMap;

import br.com.fiap.notas.util.ArquivoDB;

public class CadastraLoginActivity extends AppCompatActivity {

    private EditText etNome, etSobrenome, etNascimento, etEmail, etSenha;
    private RadioGroup rgSexo;
    private ArquivoDB arquivoDB;

    private final String ARQ = "dados.txt";
    private final String SP = "dados";
    private HashMap<String, String> mapDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_login);

        etNome = (EditText) findViewById(R.id.edtNome);
        etSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        etNascimento = (EditText) findViewById(R.id.edtDataNascimento);
        etEmail =  (EditText) findViewById(R.id.edtEmail);
        etSenha = (EditText) findViewById(R.id.edtSenha);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        arquivoDB = new ArquivoDB();
        mapDados = new HashMap<>();
    }

    //valida a entrada de dados e popula o HashMap
    private boolean capturaDadosDaTela(){
        String nome, sobrenome, nascimento, email, senha, sexo;
        boolean dadosOK = false;

        nome = etNome.getText().toString();
        sobrenome = etSobrenome.getText().toString();
        nascimento = etNascimento.getText().toString();
        email = etEmail.getText().toString();
        senha = etSenha.getText().toString();

        //getCheckedRadioButtonId retorna o id do RadioButton que está selecionado
        //no RadioGroup
        int sexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);

        // (sexoId != -1) quando nenhum RadioButton for selecionado
        // o método getCheckedRadioButtonId retornará -1
        if( Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !TextUtils.isEmpty(senha) &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(sobrenome) &&
                !TextUtils.isEmpty(nascimento) &&
                (sexoId != -1))
        {

            dadosOK = true;
            sexo = rbSexo.getText().toString();
            mapDados.put("usuario", email);
            mapDados.put("senha", senha);
            mapDados.put("nome", nome);
            mapDados.put("sobrenome", sobrenome);
            mapDados.put("nascimento", nascimento);
            mapDados.put("sexo", sexo);
        }else{
            Toast.makeText(this, R.string.dados_conta_nok, Toast.LENGTH_SHORT).show();
        }
        return dadosOK;
    }

    //Grava o SP com o HashMap mapDados como parâmetro
    public void gravarChaves(View v){
        if(capturaDadosDaTela()){
            arquivoDB.gravarChaves(this, SP, mapDados);
            Toast.makeText(this, R.string.cadastro_ok, Toast.LENGTH_SHORT).show();
        }

    }

    //Exclui chaves do SP com o HashMap mapDados como parâmetro
    public void excluirChaves(View v){
        if(capturaDadosDaTela()){
            arquivoDB.excluirChaves(this, SP, mapDados);
            Toast.makeText(this, R.string.exclusao_ok, Toast.LENGTH_SHORT).show();
        }
    }

    //Verifica se no SharedPreferences SP existem as chaves usuario e senha
    public boolean verificarChaves(View v){
        if(arquivoDB.verificarChave(this, SP, "usuario") &&
           arquivoDB.verificarChave(this, SP, "senha") ){
            Toast.makeText(this, R.string.dados_login_ok, Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(this, R.string.dados_login_nok, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void carregarChaves(View v){
        if(verificarChaves(v)){
            etNome.setText(arquivoDB.retornarValor(this, SP, "nome"));
            etSobrenome.setText(arquivoDB.retornarValor(this, SP, "sobrenome"));
            etNascimento.setText(arquivoDB.retornarValor(this, SP, "nascimento"));
            etEmail.setText(arquivoDB.retornarValor(this, SP, "usuario"));
            etSenha.setText(arquivoDB.retornarValor(this, SP, "senha"));

            String sexo = arquivoDB.retornarValor(this, SP, "sexo");
            if(sexo.equals(getString(R.string.feminino))){
                rgSexo.check(R.id.rbFeminino);
            }else{
                rgSexo.check(R.id.rbMasculino);
            }

        }

    }

    //Método que grava um arquivo txt
    public void gravarArquivo(View v){
         if(capturaDadosDaTela()){
            try {
                arquivoDB.gravarArquivo(this, ARQ, mapDados.toString());
                Toast.makeText(this, R.string.gravar_arquivo_ok, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.gravar_arquivo_nok, Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Método que lê um arquivo
    public void lerArquivo(View v){
        try {
            String txt = arquivoDB.lerArquivo(this, ARQ);
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.ler_arquivo_nok, Toast.LENGTH_LONG).show();
        }
    }

    //Método que exclui um arquivo
    public void excluirArquivo(View v){
        try {
            arquivoDB.excluirArquivo(this, ARQ);
            Toast.makeText(this, R.string.excluir_arquivo_ok, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.excluir_arq_nok, Toast.LENGTH_SHORT).show();
        }

    }

    public void voltar(View v){finish();}

}
