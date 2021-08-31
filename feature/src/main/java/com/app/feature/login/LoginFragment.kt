package com.app.feature.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.app.core.base.BaseFragment
import com.app.core.di.provideCoreComponent
import com.app.core.extensions.observe
import com.app.core.networking.NetworkState
import com.app.core.base.ProgressDialogManager
import com.app.feature.R
import com.app.feature.databinding.FragmentLoginBinding
import com.app.feature.login.di.DaggerLoginComponent
import com.app.feature.login.di.LoginModule

/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(
        layoutId = R.layout.fragment_login
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.networkState, ::onStateChange)
        observe(viewModel.errorField, ::onErrorStateChange)

        viewBinding.tvSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signUpFragment);
        }

    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {

        DaggerLoginComponent
            .builder()
            .coreComponent(provideCoreComponent(requireCompatActivity().application)).loginModule(
            LoginModule(this))
            .build()
            .inject(this)

    }

    /**
     * Initialize view data binding variables.
     */
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
