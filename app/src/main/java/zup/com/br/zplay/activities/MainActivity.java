package zup.com.br.zplay.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zup.com.br.zplay.R;
import zup.com.br.zplay.adapters.MovieAdapter;
import zup.com.br.zplay.models.Movie;
import zup.com.br.zplay.services.OmdbapiService;
import zup.com.br.zplay.utils.AppClient;

public class MainActivity extends SupportActivity {

    @BindView(R.id.textSearch)
    public EditText textSearch;

    @BindView(R.id.listMovie)
    public RecyclerView listMovie;

    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    int layoutID() {
        return R.layout.activity_main;
    }

    @Override
    void inicializar(Bundle savedInstanceState) {
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        listMovie.setLayoutManager(mLayoutManager);
        listMovie.setItemAnimator(new DefaultItemAnimator());
        listMovie.setAdapter(movieAdapter);

        prepareList();
    }

    private void prepareList() {
        //OmdbapiService omdbapiService = AppClient.getClient().create(OmdbapiService.class);

        /*
        *
        * Call<JsonObject> consultaVeiculo = veiculoService.consultarVeiculoPublico(editPlacaLetra.getText().toString() +
                    editPlacaNumero.getText().toString(), editRenavam.getText().toString());*/

//        movieList.add(new Movie("Nome", "autor", "ano", "sinopse", "120 min.", "image", 10));
//        movieList.add(new Movie("Nome", "autor", "ano", "sinopse", "120 min.", "image", 10));
//        movieList.add(new Movie("Nome", "autor", "ano", "sinopse", "120 min.", "image", 10));
//        movieList.add(new Movie("Nome", "autor", "ano", "sinopse", "120 min.", "image", 10));
//        movieList.add(new Movie("Nome", "autor", "ano", "sinopse", "120 min.", "image", 10));

        movieAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btnImgSearch, R.id.btnImgAdd, R.id.btnImgUser})
    public void actions() {

    }
}
