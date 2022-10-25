package com.example.prueba01recuperacion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    //Declaracion de variables inputs
    private EditText txt_code, txt_product;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        //Asociacion de variables y componentes
        txt_code = (EditText) binding.txtCode;
        txt_product = (EditText) binding.txtProduct;
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Boton accion: Anterior, Navegar entre fragments (tercero a segundo)
        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_SecondFragment);
            }
        });

        //Boton accion: Guardar, ejecuta la funcion Save al ser seleccionado
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        //Boton accion: Eliminar, ejecuta la funcion Delete al ser seleccionado
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Funcion Save
    public void save() {
        //Define un admin de la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        //Base de datos, permite que el admin definido anteriormente pueda editar
        SQLiteDatabase database = admin.getWritableDatabase();

        //Recoge los inputs Codigo y Nombre y los guarda en variables
        String code = txt_code.getText().toString();//*
        String name = txt_product.getText().toString();//*

        //Se ejecuta siempre que ambos campos sean llenados
        if (!code.isEmpty() && !name.isEmpty()) {
            //Registra los campos en la base de datos
            ContentValues register = new ContentValues();
            //toma (Llave, Valor (Input ingresado por el usuario)*)
            register.put("code", code);
            register.put("name", name);
            //Inserta los datos y los guarda en una tabla, toma la tabla, null y los valores anteriormente guardados
            database.insert("products", null, register);
            //Termina
            database.close();
            //Ejecuta la funcion para limpiar el formmulario
            cleanForm();
            //Notificacion, registro logrado
            Toast.makeText(this.getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        }
        //Se ejecuta en caso de que uno de los campos no se haya llenado
        else {
            //Notificacion, faltan datos
            Toast.makeText(this.getActivity(), "Completar todos los datos", Toast.LENGTH_SHORT).show();
        }
    }

    //Funcion Delete
    public void delete() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        admin.getWritableDatabase();

        //Toma el campo Codigo y guarda el valor
        String code = txt_code.getText().toString();

        //Se ejecuta siempre que haya un valor en el campo codigo
        if (!code.isEmpty()) {
            /*Eliminar el valor en la base de datos que es igual al dato que se envia
            * el estado pasa de 0 a 1 en caso de que se haya procesado */
            int count = database.delete("products", "code=" + code, null);
            //Si el valor es 1 el proceso termina y depliega una notificacion
            if (count == 1) {
                database.close();
                cleanForm();
                Toast.makeText(this.getActivity(), "Eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }
            //En caso contrario despliega una notificacion de valor inexistente
            else {
                Toast.makeText(this.getActivity(), "El producto no existe", Toast.LENGTH_SHORT).show();
            }
        }
        //Envia una notificacion de llenar el campo en caso contrario
        else {
            Toast.makeText(this.getActivity(), "Ingresar código", Toast.LENGTH_SHORT).show();
        }
    }

    /*Funcion para limpiar el formulario:
    * Cambia los datos de los campos a vacio, se ejecuta para que a la hora
    * de querer ingresar otro valor los datos anteriores no sigan ahi*/
    public void cleanForm() {
        txt_code.setText("");
        txt_product.setText("");
    }
}