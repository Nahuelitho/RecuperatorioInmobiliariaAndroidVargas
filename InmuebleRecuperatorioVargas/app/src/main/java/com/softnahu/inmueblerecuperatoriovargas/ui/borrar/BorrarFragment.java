package com.softnahu.inmueblerecuperatoriovargas.ui.borrar;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softnahu.inmueblerecuperatoriovargas.R;
import com.softnahu.inmueblerecuperatoriovargas.databinding.FragmentBorrarBinding;

public class BorrarFragment extends Fragment {

    private BorrarViewModel borrarViewModel;
    private FragmentBorrarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        borrarViewModel = new ViewModelProvider(this).get(BorrarViewModel.class);

        borrarViewModel.getInmuebleEncontrado().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvCodigo.setText("Codigo: " + inmueble.getCodigo());
            binding.tvDescripcion.setText("Descripcion: " + inmueble.getDescripcion());
            binding.tvCantAmb.setText("Ambientes: " + inmueble.getCantAmbiente());
            binding.tvDireccion.setText("Direccion: " + inmueble.getDireccion());
            binding.tvPrecio.setText("Precio: " + inmueble.getPrecio());
        });

        binding.btBuscar.setOnClickListener(v -> {
            String codigo = binding.etBuscar.getText().toString();
            borrarViewModel.buscarInmueble(codigo);
        });

        binding.btBorrar.setOnClickListener(v -> {
            limpiar();
            borrarViewModel.borrarInmueble();

        });

        borrarViewModel.getmErrorMsj().observe(getViewLifecycleOwner(), msj -> {
            binding.tvMsj.setText(msj);
        });

        borrarViewModel.getBotonBorrarVisibilidad().observe(getViewLifecycleOwner(), visibilidad -> {
            binding.btBorrar.setVisibility(visibilidad);
        });
    }
    private void limpiar() {
        binding.etBuscar.setText("");
        binding.tvCodigo.setText("");
        binding.tvDescripcion.setText("");
        binding.tvCantAmb.setText("");
        binding.tvDireccion.setText("");
        binding.tvPrecio.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



