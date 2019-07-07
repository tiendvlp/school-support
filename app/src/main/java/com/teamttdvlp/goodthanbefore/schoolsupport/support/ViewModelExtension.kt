package com.teamttdvlp.goodthanbefore.schoolsupport.support

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified F : ViewModel> Fragment.getViewModel(noinline creator: (() -> F)? = null) : F {
    if (creator == null) {
        return ViewModelProviders.of(this).get(F::class.java)
    } else {
        return ViewModelProviders.of(this, BaseViewModelFactory(creator!!)).get(F::class.java)
    }
}

inline fun <reified A : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> A)? = null) : A {
    if (creator == null) {
        return ViewModelProviders.of(this).get(A::class.java)
    } else {
        return ViewModelProviders.of(this, BaseViewModelFactory(creator!!)).get(A::class.java)
    }
}
class BaseViewModelFactory<T> (val creator:() -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}
