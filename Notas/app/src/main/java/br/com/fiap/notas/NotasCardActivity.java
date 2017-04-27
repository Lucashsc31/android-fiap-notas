package br.com.fiap.notas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.fiap.notas.entity.Doc;
import br.com.fiap.notas.util.ClickRecycleViewInterface;
import br.com.fiap.notas.util.CloudantRequestInterface;
import br.com.fiap.notas.util.CloudantResponseNota;
import br.com.fiap.notas.util.DataAdapter;
import br.com.fiap.notas.util.Row;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotasCardActivity extends AppCompatActivity implements ClickRecycleViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<Row> rows;

    //Adapter criado para trabalhar com o card e a RecyclerView
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_card);
    }

    @Override
    protected void onPostResume() {
        iniciarViews();
        super.onPostResume();
    }


    private void iniciarViews() {
        //Cria uma referencia para a nossa RecyclerView no layout da Activity NotasCardyAcativity
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_notas);
        //Usamos para melhorar a performance deixando o tamanho fixo
        recyclerView.setHasFixedSize(true);
        //Usamos um liner layout para exibir itens em uma lista de rolagem vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://49031bbe-ee22-42b3-87ec-0deae41d9401-bluemix.cloudant.com/fiap-notas/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CloudantRequestInterface api = retrofit.create(CloudantRequestInterface.class);

        api.getAllJSON().enqueue(new Callback<CloudantResponseNota>() {
            @Override
            public void onResponse(Call<CloudantResponseNota> call, Response<CloudantResponseNota> response) {

                CloudantResponseNota json = response.body();

                rows = new ArrayList<>(Arrays.asList(json.getRows()));

                for(Row item : rows){
                    Log.i("Nota:", item.getDoc().toString());
                }

                //Instanciamos o nosso adapter e passamos os dados vindos do Cloudant
                adapter = new DataAdapter(rows);
                //Setamos o RecyclerView com o Adapter Populado com os dados do Cloudant
                //Aqui a tela é carregada
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<CloudantResponseNota> call, Throwable t) {

                Log.d("Error", t.getMessage());

            }
        });
    }

    public void voltar(View v){
        finish();
    }

    public void novaNota(View v){
        Intent intentChamaCadastraNota = new Intent(this, CadastraNotaActivity.class);
        intentChamaCadastraNota.putExtra("docId", rows.size());
        startActivity(intentChamaCadastraNota);
    }

    @Override
    public void onRecyclerClick(Object object) {
        Doc doc = (Doc)object;
        Toast.makeText(this, doc.toString(), Toast.LENGTH_SHORT).show();
    }
}
