package zup.com.br.zplay.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zup.com.br.zplay.models.MovieSearch;

public interface OmdbapiService {

    @GET()
    Call<List<MovieSearch>> obterLista(@Query("s") String search);

    @GET()
    Call<MovieSearch> obterFilme(@Query("t") String imdbID);

}
