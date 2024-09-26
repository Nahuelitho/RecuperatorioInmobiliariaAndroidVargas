package com.softnahu.inmueblerecuperatoriovargas.ui.borrar;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softnahu.inmueblerecuperatoriovargas.MainActivity;
import com.softnahu.inmueblerecuperatoriovargas.model.Inmueble;

public class BorrarViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleEncontrado;
    private MutableLiveData<String> mErrorMsj;
    private MutableLiveData<Boolean> inmuebleEliminado;
    private MutableLiveData<Integer> botonBorrarVisibilidad;

    public BorrarViewModel(@NonNull Application application) {
        super(application);
        inmuebleEncontrado = new MutableLiveData<>();
        mErrorMsj = new MutableLiveData<>();
        inmuebleEliminado = new MutableLiveData<>(false);
        botonBorrarVisibilidad = new MutableLiveData<>(View.GONE);
    }

    public LiveData<Inmueble> getInmuebleEncontrado() {
        return inmuebleEncontrado;
    }

    public LiveData<String> getmErrorMsj() {
        return mErrorMsj;
    }

    public LiveData<Boolean> getInmuebleEliminado() {
        return inmuebleEliminado;
    }


    public LiveData<Integer> getBotonBorrarVisibilidad() {
        return botonBorrarVisibilidad;
    }

    public void buscarInmueble(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            mErrorMsj.setValue("El código no puede estar vacío.");
            return;
        }
        if (MainActivity.inmuebles == null || MainActivity.inmuebles.isEmpty()) {
            mErrorMsj.setValue("No hay inmuebles disponibles.");
            return;
        }
        boolean inmuebleEncontradoFlag = false;
        for (Inmueble inmueble : MainActivity.inmuebles) {

            if (inmueble != null && inmueble.getCodigo() != null && inmueble.getCodigo().equals(codigo)) {
                inmuebleEncontrado.setValue(inmueble);
                inmuebleEncontradoFlag = true;
                break;
            }
        }
        if (inmuebleEncontradoFlag) {
            botonBorrarVisibilidad.setValue(View.VISIBLE);
        } else {
            inmuebleEncontrado.setValue(null);
            mErrorMsj.setValue("El inmueble no existe");
        }
    }

    public void borrarInmueble() {
        Inmueble inmueble = inmuebleEncontrado.getValue(); // Obtén el inmueble actual
        if (inmueble != null) {
            boolean eliminado = MainActivity.inmuebles.remove(inmueble);
            if (eliminado) {
                inmuebleEliminado.setValue(true);
                mErrorMsj.setValue("Inmueble eliminado");
                botonBorrarVisibilidad.setValue(View.GONE);
            } else {
                mErrorMsj.setValue("Error al eliminar el inmueble");
            }
        } else {
            mErrorMsj.setValue("Inmueble no encontrado");
        }
    }
}
