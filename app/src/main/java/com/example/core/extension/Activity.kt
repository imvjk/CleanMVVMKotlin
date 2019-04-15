package com.example.core.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.data.RepoHelper
import com.example.productinfo.ViewModelFactory

fun <T : ViewModel> FragmentActivity.getViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application,
                RepoHelper.provideRepository())).get(viewModelClass)