package com.example.prueba01recuperacion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentFirstBinding;

import java.lang.reflect.Array;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    //Declaracion de variables
    private ScrollView scr_1;
    private Spinner sp_1;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        //asociacion variable - componente
        scr_1 = (ScrollView) binding.scr1;
        sp_1 = (Spinner) binding.sp1;

        //Array
        String[] names =
                {
                        "Ricardo",
                        "Ingrid",
                        "Menthor"
                };

        //Adapter para mostrar la lista names en el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
        //Se asocia el adapter al spinner
        sp_1.setAdapter(adapter);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Navegacion entre fragments (Primero a Segundo)
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        //*Accion de boton seleccionado
        binding.btnCerezas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*Retorna una notificacion con el nombre de la fruta seleccionada
                Toast.makeText(getContext(), "Cerezas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });
        //*
        binding.btnMora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*
                Toast.makeText(getContext(), "Moras seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });
        //*
        binding.btnFrutilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*
                Toast.makeText(getContext(), "Frutillas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });
        //*
        binding.btnBananas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*
                Toast.makeText(getContext(), "Bananas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });

        //Accion al presionar el boton de reproducir
        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crea una clase mediaplayer asociada al recurso mp3 sound long
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.sound_long);
                //Inicia la reproduccion del sonido asociado al mp
                mp.start();
            }
        });

        //Accion del boton mostrar informacion
        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cadena que toma el valor del item seleccionado
                String selected = sp_1.getSelectedItem().toString();
                //Comparacion para verificar el item que se selecciono
                switch (selected) {
                    //Dependiendo del string despliega una notificacion con la informacion asociada
                    case "Ricardo":
                        Toast.makeText(getContext(), selected + ": Edad 26", Toast.LENGTH_SHORT).show();
                        break;
                    case "Ingrid":
                        Toast.makeText(getContext(), selected + ": Edad 27", Toast.LENGTH_SHORT).show();
                        break;
                    case "Menthor":
                        Toast.makeText(getContext(), selected + ": Edad 60", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}