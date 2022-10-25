package com.example.prueba01recuperacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    //Declaracion de las variables input
    private EditText txt_email, txt_name, txt_search;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

       //Asociacion de las variables con los componentes de la vista
        txt_email = (EditText) binding.txtEmail;
        txt_name = (EditText) binding.txtName;
        txt_search = (EditText) binding.txtSearch;
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //**
        //Navegacion entre fragments (Del segundo al primero)
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        //***
        //Accion de los botones al ser presiondos
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Llama a la funcion:
                save();
            }
        });

        //***
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        //***
        binding.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate();
            }
        });

        //** (Del segundo al tercero)
        binding.btnToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Funcion para navegar
    public void navigate() {
        //Recoge el input ingresado
        String url = txt_search.getText().toString();
        //Navega entre activitys, envia al activity web
        Intent next = new Intent(this.getActivity(), ActivityWeb.class);
        //Envia la informacion del url
        next.putExtra("url", url);
        //Inicia el activity
        startActivity(next);
    }

    //Funcion para guardar la informacion (Nombre y Correo)
    public void save() {
        //Regoge los inputs ingresados de nombre y correo
        String name = txt_name.getText().toString();
        String email = txt_email.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString(name, email);
        obj_editor.commit();
        txt_email.setText("");
        txt_name.setText("");
        //Despliega una notificacion
        Toast.makeText(this.getActivity(), "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();
    }

    //Funcion para buscar por nombre
    public void search() {
        //Recoge el input de nombre
        String name = txt_name.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String info = preferences.getString(name, "");
        //Busca en info, por el nombre la informacion asociada
        if (info.length() == 0) {
            //despliega una notificacion cuando no lo encuentra
            Toast.makeText(getActivity(), "No se encontró ningun registro", Toast.LENGTH_LONG).show();
        } else {
            //despliega la informacion al encontrarlo
            txt_email.setText(info);
        }
    }
}