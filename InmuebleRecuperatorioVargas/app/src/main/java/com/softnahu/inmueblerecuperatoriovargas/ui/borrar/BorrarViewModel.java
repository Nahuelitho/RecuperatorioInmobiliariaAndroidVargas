package com.softnahu.inmueblerecuperatoriovargas.ui.borrar;

import android.app.Application;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softnahu.inmueblerecuperatoriovargas.MainActivity;
import com.softnahu.inmueblerecuperatoriovargas.model.Inmueble;

public class BorrarViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleEncontrado;
    private MutableLiveData<String> mErrorMsj;
    private MutableLiveData<Boolean> inmuebleEliminado;


    public BorrarViewModel(@NonNull Application application) {
        super(application);
        inmuebleEncontrado = new MutableLiveData<>();
        mErrorMsj = new MutableLiveData<>(); // Inicializa el mensaje de error
        inmuebleEliminado = new MutableLiveData<>(false); // Inicializa a false si no se ha eliminado nada
    }


    public LiveData<Inmueble> getInmuebleEncontrado() {
        if (inmuebleEncontrado == null) {
            inmuebleEncontrado = new MutableLiveData<>();
        }
        return inmuebleEncontrado;
    }
    public MutableLiveData<String> getmErrorMsj() {
        if (mErrorMsj == null) {
            mErrorMsj = new MutableLiveData<>();
        }
        return mErrorMsj;
    }

    public LiveData<Boolean> getInmuebleEliminado() {
        if (inmuebleEliminado == null) {
            inmuebleEliminado = new MutableLiveData<>();
        }
        return inmuebleEliminado;
    }
    /*********************************/
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
        if (!inmuebleEncontradoFlag) {
            inmuebleEncontrado.setValue(null);
            mErrorMsj.setValue("El inmueble no existe");
        }
    }

    /****************************************/
    public void borrarInmueble() {
        Inmueble inmueble = inmuebleEncontrado.getValue(); // Obtén el inmueble actual
        if (inmueble != null) {
            boolean eliminado = MainActivity.inmuebles.remove(inmueble);
            if (eliminado) {
                inmuebleEliminado.setValue(true);
                mErrorMsj.setValue("Inmueble eliminado");
            } else {
                mErrorMsj.setValue("Error al eliminar el inmueble");
            }
        } else {
            mErrorMsj.setValue("Inmueble no encontrado");
        }
    }


}