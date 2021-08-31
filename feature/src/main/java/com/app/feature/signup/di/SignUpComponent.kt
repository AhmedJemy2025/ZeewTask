package com.app.feature.signup.di

import com.app.core.di.CoreComponent
import com.app.core.di.scopes.FeatureScope
import com.app.feature.signup.SignUpFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [SignUpModule::class],
    dependencies = [CoreComponent::class]
)

interface SignUpComponent {
    fun inject(signUpFragment: SignUpFragment)
}
