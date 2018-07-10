package zup.com.br.zplay.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zup.com.br.zplay.R;
import zup.com.br.zplay.activities.DetailsMovieActivity;
import zup.com.br.zplay.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Activity activity;
    private List<Movie> movieList;

    public MovieAdapter(Activity activity, List<Movie> movieList) {
        this.activity = activity;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_movie, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Pair<View, String> imgZPlayLogoPair = Pair.create((View) imgZPlayLogo, imgZPlayLogo.getTransitionName());
//                Pair<View, String> imgZPlayNamePair = Pair.create((View) imgZPlayName, imgZPlayName.getTransitionName());
//                 ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgZPlayLogoPair, imgZPlayNamePair);

                Intent intent = new Intent(activity, DetailsMovieActivity.class);

                Movie movie = movieList.get(i);
                intent.putExtra("MOVIE_ID", movie);
                activity.startActivity(intent);
            }
        });
        ButterKnife.bind(this, itemView);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        Movie movie = movieList.get(i);

//        holder.nome.setText(movie.getNome());
//        holder.autor.setText(movie.getAutor());
//        holder.ano.setText(movie.getAno());
//        holder.sinopse.setText(movie.getSinopse());
//        holder.tempo.setText(movie.getTempo());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imagem)
        public ImageView imagem;

        @BindView(R.id.nome)
        public TextView nome;

        @BindView(R.id.autor)
        public TextView autor;

        @BindView(R.id.ano)
        public TextView ano;

        @BindView(R.id.sinopse)
        public TextView sinopse;

        @BindView(R.id.tempo)
        public TextView tempo;

        @BindView(R.id.rank)
        public LinearLayout rank;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
