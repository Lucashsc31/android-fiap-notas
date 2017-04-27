package br.com.fiap.notas.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.fiap.notas.R;

/**
 * Created by logonrm on 19/04/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Row> rows;

    //Construtor que recebe um list de rows
    public DataAdapter(ArrayList<Row> _rows){
        this.rows = _rows;
    }

    //Cria uma nova view com base no layout do card row
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    //Troca o conteudo da view chamada pelo layout manager
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.tvId.setText(rows.get(position).getDoc().get_id());
        holder.tvTitulo.setText(rows.get(position).getDoc().getTitulo());
        holder.tvAssunto.setText(rows.get(position).getDoc().getAssunto());
        holder.tvConteudo.setText(rows.get(position).getDoc().getConteudo());
    }

    //Retornar o tamanho do dataset
    @Override
    public int getItemCount() {
        return rows.size();
    }

    //Provicencia referência
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitulo, tvId, tvAssunto, tvConteudo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvAssunto = (TextView) itemView.findViewById(R.id.tvAssunto);
            tvConteudo = (TextView) itemView.findViewById(R.id.tvConteudo);
        }
    }
}
