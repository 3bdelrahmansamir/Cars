package com.softxpert.task.presentation.ui.show_cars_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.softxpert.task.R;
import com.softxpert.task.databinding.AdapterCarsBinding;
import com.softxpert.task.models.Car;
import com.softxpert.task.presentation.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> implements BaseAdapter<Car> {

    private List<Car> cars;

    public CarsAdapter() {
        cars = new ArrayList<>();
    }


    @NonNull
    @Override
    public CarsAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                        int viewType) {
        AdapterCarsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_cars, parent, false);

        return new CarViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.CarViewHolder holder, int position) {
        final Car car = cars.get(position);
        holder.bind(car);

    }


    @Override
    public int getItemCount() {
        return cars.size();
    }

    @Override
    public void setType(int type) {

    }

    @Override
    public void setData(List<Car> cars) {
        this.cars.clear();
        this.cars.addAll(cars);
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<Car> cars) {
        this.cars.addAll(cars);
        notifyDataSetChanged();
    }


    public class CarViewHolder extends RecyclerView.ViewHolder {

        private AdapterCarsBinding adapterCarsBinding;

        public CarViewHolder(AdapterCarsBinding binding) {
            super(binding.getRoot());
            this.adapterCarsBinding = binding;
        }

        public void bind(Car car) {
            adapterCarsBinding.setCar(car);
            adapterCarsBinding.executePendingBindings();
        }
    }
}
