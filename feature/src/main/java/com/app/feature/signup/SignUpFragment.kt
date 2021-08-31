package com.app.feature.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.core.base.BaseFragment
import com.app.core.di.provideCoreComponent
import com.app.core.extensions.observe
import com.app.core.networking.NetworkState
import com.app.feature.R
import com.app.feature.databinding.FragmentSignUpBinding
import com.app.feature.login.di.DaggerLoginComponent
import com.app.feature.signup.di.DaggerSignUpComponent
import com.app.feature.signup.di.SignUpModule

/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
        layoutId = R.layout.fragment_sign_up
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.networkState, ::onStateChange)
        observe(viewModel.errorField, ::onErrorStateChange)

        viewBinding.ccp.setOnCountryChangeListener { country->viewModel.countryCode.set(country.phoneCode) }

    }
    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {

        DaggerSignUpComponent
            .builder()
            .coreComponent(provideCoreComponent(requireCompatActivity().application))
            .signUpModule(SignUpModule(this))
            .build()
            .inject(this)

    }


    override fun onInitDataBinding() {

        viewBinding.viewModel = viewModel

    }


    private fun onStateChange(state: NetworkState) {

        when (state) {
            is NetworkState.Loading -> {
                showProgressDialog()
            }
            is NetworkState.Success ->{
                hideProgressDialog()
                Toast.makeText(requireContext() , state.userToken , Toast.LENGTH_LONG).show()
            }
            is NetworkState.Error ->{
                hideProgressDialog()
                Toast.makeText(requireContext() , state.errorMessage , Toast.LENGTH_LONG).show()
            }

        }
    }


    private fun onErrorStateChange(state: String) {

        Toast.makeText(requireContext() , state , Toast.LENGTH_LONG).show()

    }



}
