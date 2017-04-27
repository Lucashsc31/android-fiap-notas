package br.com.fiap.notas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.fiap.notas.entity.Doc;
import br.com.fiap.notas.util.CloudantRequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastraNotaActivity extends AppCompatActivity {

    EditText etTitulo, etConteudo;
    Spinner spAssunto;
    int docId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_nota);
        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etConteudo = (EditText) findViewById(R.id.etConteudo);
        spAssunto = (Spinner) findViewById(R.id.spAssunto);

        docId = getIntent().getExtras().getInt("docId");
    }

    //Metodo que infla menu (xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Metodo chamado quando o usuario seleciona um item do menu que inflamos
    //Item recebido como parametro sera o menu selecionado pelo usuario
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_cadastra_ok:
                Doc doc = new Doc();
                doc.set_id(String.valueOf(docId + 1));
                doc.setTitulo(etTitulo.getText().toString());
                doc.setConteudo(etConteudo.getText().toString());
                doc.setAssunto(spAssunto.getSelectedItem().toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://49031bbe-ee22-42b3-87ec-0deae41d9401-bluemix.cloudant.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CloudantRequestInterface api = retrofit.create(CloudantRequestInterface.class);

                api.cadastraNota(doc).enqueue(new Callback<Doc>() {
                    @Override
                    public void onResponse(Call<Doc> call, Response<Doc> response) {
                        Toast.makeText(CadastraNotaActivity.this, "Nota cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Doc> call, Throwable t) {
                        Toast.makeText(CadastraNotaActivity.this, "Erro ao cadastrar a Nota!", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
