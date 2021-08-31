package com.app.feature.signup.di

import com.app.core.di.scopes.FeatureScope
import com.app.core.extensions.viewModel
import com.app.core.networking.repositiories.AuthRepository
import com.app.feature.signup.SignUpFragment
import com.app.feature.signup.SignUpViewModel
import dagger.Module
import dagger.Provides


@Module
class SignUpModule(val fragment: SignUpFragment) {


    @FeatureScope
    @Provides
    fun providesLoginViewModel(
        repository: AuthRepository
    ) = fragment.viewModel {
        SignUpViewModel(repository = repository)
    }


}