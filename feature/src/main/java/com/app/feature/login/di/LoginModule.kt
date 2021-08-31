package com.app.feature.login.di

import com.app.core.di.scopes.FeatureScope
import com.app.core.extensions.viewModel
import com.app.core.networking.repositiories.AuthRepository
import com.app.feature.login.LoginFragment
import com.app.feature.login.LoginViewModel
import dagger.Module
import dagger.Provides


@Module
class LoginModule(val fragment: LoginFragment) {


    @FeatureScope
    @Provides
    fun providesLoginViewModel(
        repository: AuthRepository
    ) = fragment.viewModel {
        LoginViewModel(repository =repository)
    }


}