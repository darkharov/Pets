package com.example.pets.presentation.ui.screens.dashboard;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.pets.R;
import com.example.pets.core.entities.Pet;
import com.example.pets.presentation.ui.bases.lists.BindingAdapter;
import com.example.pets.presentation.ui.bases.lists.ListViewModel;
import com.example.pets.presentation.ui.screens.Screens;

import java.util.List;

import javax.inject.Inject;

public class PetsListViewModel extends ListViewModel<Pet> {

    @Inject
    PetsListViewModel() {
    }

    @Override
    protected BindingAdapter.BindingViewHolder<Pet> createViewHolder(View view) {
        return new PetViewHolder(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_pet;
    }

    void init(Pet.Criteria criteria) {
        Log.e("PetsListViewModel", "init()");

        execute(
                networkFacade.getPets(criteria),
                new GetPetsObserver()
        );
    }


    private class GetPetsObserver extends BaseSingleObserver<List<Pet>> {

        @Override
        public void onSuccess(List<Pet> pets) {
            notifyListUpdated(pets);
        }

        @Override
        public void onError(Throwable e) {
            notifyListUpdateFailed();
        }
    }


    public class PetViewHolder extends BindingAdapter.BindingViewHolder<Pet> {

        PetViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void goToDetails() {
            router.navigateTo(Screens.PET_DETAILS, item.get());
        }
    }
}
