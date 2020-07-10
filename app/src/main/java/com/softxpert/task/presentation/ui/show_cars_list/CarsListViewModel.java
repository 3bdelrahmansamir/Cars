package com.softxpert.task.presentation.ui.show_cars_list;


import androidx.lifecycle.MutableLiveData;

import com.softxpert.task.business.UserRepository;
import com.softxpert.task.models.Car;
import com.softxpert.task.models.Response;
import com.softxpert.task.presentation.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Action1;

public class CarsListViewModel extends BaseViewModel<CarsListNavigator> {

    public MutableLiveData<Boolean> progressDialogLoading = new MutableLiveData<>();
    public MutableLiveData<Void> noInternet = new MutableLiveData<>();
    public MutableLiveData<List<Car>> carsListLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> lastPage = new MutableLiveData<>();
    private UserRepository userRepository = new UserRepository();

    public Action1<Void> onUserSwiped = aVoid -> {
        lastPage.setValue(false);
        getAllCarsByPage(1);
    };

    public void getAllCarsByPage(int page) {
        progressDialogLoading.setValue(true);
        Disposable disposable = userRepository.getAllCarInfoByPage(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getConsumerLoginOnNext(), getConsumerLoginOnError());
        getCompositeDisposable().add(disposable);
    }


    public void loadMoreData(int page) {
        if (!progressDialogLoading.getValue()) {
            getAllCarsByPage(page);
        }
    }


    private Consumer<Throwable> getConsumerLoginOnError() {
        return throwable -> {
            progressDialogLoading.setValue(false);
            noInternet.setValue(null);
        };
    }


    private Consumer<retrofit2.Response<Response<Car>>> getConsumerLoginOnNext() {
        return response -> {
            progressDialogLoading.setValue(false);
            if (response.code() == 200) {
                if (response.body().status == 1) {
                    carsListLiveData.setValue(response.body().data);
                } else {
                    lastPage.setValue(true);
                }
            }
        };
    }


}
