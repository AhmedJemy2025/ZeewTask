package com.app.feature.login.di

import com.app.core.di.CoreComponent
import com.app.core.di.scopes.FeatureScope
import com.app.feature.login.LoginFragment
import com.app.feature.signup.di.SignUpModule
import dagger.Component


@FeatureScope
@Component(
    modules = [LoginModule::class],
    dependencies = [CoreComponent::class]
)

interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}
