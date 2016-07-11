package com.example.zyh.bigduang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hope.myapplication.Activity_Home;
import com.example.models.Cinema;
import com.example.models.DataController;
import com.example.models.Movie;

public class Activity_Deal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        TextView movieView = (TextView)findViewById(R.id.select_movie);
        TextView cinemaView = (TextView)findViewById(R.id.select_cinema);
        TextView showtimeView = (TextView)findViewById(R.id.select_showtime);
        TextView seatView = (TextView)findViewById(R.id.select_seat);

        Movie movie = DataController.GetInstance().getSelectedMovie();
        Cinema cinema = DataController.GetInstance().getSelectedCinema();

        int seat = DataController.GetInstance().getSelectedSeat();
        movieView.setText(movie.getName());
        cinemaView.setText(cinema.getName());
        seatView.setText(String.valueOf(seat));

        Button bhome = (Button) findViewById(R.id.gohome);
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Deal.this, Activity_Home.class);
                startActivity(intent);
            }
        });


    }

}
