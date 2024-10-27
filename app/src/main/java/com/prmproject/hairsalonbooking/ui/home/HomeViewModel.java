package com.prmproject.hairsalonbooking.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.data.repository.HairServiceRepository;
import com.prmproject.hairsalonbooking.data.repository.StylistRepository;

public class HomeViewModel extends ViewModel {

    private HairServiceRepository hairServiceRepository;
    private StylistRepository stylistRepository;

    public HomeViewModel() {
        hairServiceRepository = new HairServiceRepository();
        stylistRepository = new StylistRepository();
    }

    public LiveData<ListResponse> getListHairService() {
        return hairServiceRepository.getListServices();
    }

    public LiveData<ListResponse> getListStylistService(int id) {
        return stylistRepository.getListServices(id);
    }
}