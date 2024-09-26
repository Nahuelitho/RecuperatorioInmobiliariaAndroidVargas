package com.softnahu.inmueblerecuperatoriovargas.ui.cargar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softnahu.inmueblerecuperatoriovargas.R;
import com.softnahu.inmueblerecuperatoriovargas.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private CargarViewModel cargarViewModel;
    private FragmentCargarBinding binding;

    public static CargarFragment newInstance() {
        return new CargarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);
        //Instanciar el view model
        cargarViewModel = new ViewModelProvider(requireActivity()).get(CargarViewModel.class);

        binding.btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String cantAmbString = binding.etCantAmb.getText().toString();
                String direccion = binding.etDireccion.getText().toString();
                String precioString = binding.etPrecio.getText().toString();
                double precio = Double.parseDouble(precioString);
                int cantAmb = Integer.parseInt(cantAmbString);
                cargarViewModel.cargarInmueble(codigo, descripcion, cantAmb, direccion, precio);
            }
        });
        cargarViewModel.getmErrorMsj().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msj) {
                binding.tvMensaje.setText(msj);

                // Limpiar los campos solo si el inmueble fue agregado con éxito
                if (msj.equals("Inmueble guardado con éxito")) {
                    limpiar();
                }
            }
        });
    }
    private void limpiar() {
        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etCantAmb.setText("");
        binding.etDireccion.setText("");
        binding.etPrecio.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}