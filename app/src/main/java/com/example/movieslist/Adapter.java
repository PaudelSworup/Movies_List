package com.example.movieslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Movies> movie;
    Context context;


    public Adapter(Context context, List<Movies> movie){
        this.inflater = LayoutInflater.from(context);
        this.movie = movie;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies mov = movie.get(position);
        holder.movieTitle.setText(movie.get(position).getTitle());
        holder.movieType.setText(movie.get(position).getType());
        Picasso.get().load(movie.get(position).getCardImg()).into(holder.movieImage);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> movieList = new ArrayList<>();
                movieList.add(mov.getTitle());
                movieList.add((mov.getDesc()));
                movieList.add(mov.getCardImg());
                Intent in  = new Intent(context, Detail.class);
                in.putStringArrayListExtra("tmdbMovies",movieList);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView movieTitle, movieType;
        ImageView movieImage;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.txtTitle);
            movieType = itemView.findViewById(R.id.txtType);
            movieImage = itemView.findViewById(R.id.image);
            relativeLayout = itemView.findViewById(R.id.main_layout);

        }


    }
}
