package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainActivityFragment extends Fragment {

    private MenuItem progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        setHasOptionsMenu(true);

        Button btn=root.findViewById(R.id.jokeBtn);
        btn.setOnClickListener(this::tellJoke);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        progressBar = menu.findItem(R.id.progress);
    }

    public void tellJoke(View view) {
        JokeAsyncTask task=new JokeAsyncTask();
        task.setFinishListener(()->progressBar.setVisible(false));
        task.execute(getContext());
        progressBar.setVisible(true);
    }
}
