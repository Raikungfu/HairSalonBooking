package com.prmproject.hairsalonbooking.ui.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.repository.HairServiceRepository;
import com.prmproject.hairsalonbooking.data.repository.StylistRepository;

public class HairServiceViewModel extends ViewModel {
    private HairServiceRepository hairServiceRepository;

    public HairServiceViewModel() {
        hairServiceRepository = new HairServiceRepository();
    }

    public LiveData<ListResponse> getListHairService() {
        return hairServiceRepository.getListServices();
    }

    public LiveData<ObjectResponse> createHairService(RequestDTO.CreateServiceDTO request) {
        return hairServiceRepository.createHairService(request);
    }

    public LiveData<ObjectResponse> updateHairService(int id, RequestDTO.UpdateServiceDTO request) {
        return hairServiceRepository.updateHairService(id, request);
    }

    public LiveData<ObjectResponse> deleteHairService(int id, RequestDTO.RemoveServiceDTO request) {
        return hairServiceRepository.deleteHairService(id, request);
    }
}