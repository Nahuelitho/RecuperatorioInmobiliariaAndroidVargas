package com.softnahu.inmueblerecuperatoriovargas.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softnahu.inmueblerecuperatoriovargas.MainActivity;
import com.softnahu.inmueblerecuperatoriovargas.model.Inmueble;

public class CargarViewModel extends AndroidViewModel {
    MutableLiveData<String> mErrorMsj;

    public MutableLiveData<String> getmErrorMsj() {
        if (mErrorMsj == null) {
            mErrorMsj = new MutableLiveData<>();
        }
        return mErrorMsj;
    }
    public CargarViewModel(@NonNull Application application) {
        super(application);}
    public void cargarInmueble(String codigo, String descripcion, int cantAmb, String direccion, double precio) {
        if (codigo.isEmpty() || descripcion.isEmpty() || cantAmb <= 0 || descripcion.isEmpty()|| precio <= 0) {
            getmErrorMsj().setValue("Todos los campos deben estar completos y el precio debe ser mayor a 0");
            return;
        }

        if (verificarCodigo(codigo)) {
            getmErrorMsj().setValue("El codigo ya existe en la lista.");
            return;
        }
        Inmueble inmueble = new Inmueble(codigo, descripcion, cantAmb, direccion, precio);
        MainActivity.inmuebles.add(inmueble);
        getmErrorMsj().setValue("Inmueble guardado con éxito");

    }

    public boolean verificarCodigo(String codigo) {
        for (Inmueble inmueble : MainActivity.inmuebles) {
            if (inmueble.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}