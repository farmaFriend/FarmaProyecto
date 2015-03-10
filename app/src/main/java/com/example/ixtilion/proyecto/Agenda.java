package com.example.ixtilion.proyecto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ixtilion.proyecto.R;

/**
 * Created by Ixtilion on 3/9/2015.
 */
public class Agenda extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.lay_agenda, container, false);
        return rootView;
    }
}
