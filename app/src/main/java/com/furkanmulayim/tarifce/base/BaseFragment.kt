package com.furkanmulayim.tarifce.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.util.viewMessageError
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    protected lateinit var viewModel: VM
    lateinit var mcontext: Context
    private var connection: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        val vm =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1].cast<Class<VM>>()
        viewModel = ViewModelProvider(this)[vm]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkInternetConnection()
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context
    }


    private inline fun <reified T : Any> Any.cast(): T {
        return this as T
    }

    fun navigateTo(
        actionId: Int,
        bundle: Bundle? = null,
        popUpToId: Int? = null,
        inclusive: Boolean = false,
        animControl: Boolean? = true
    ) {
        val navController = findNavController()
        if (animControl == true) {
            val options = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_in_left)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .apply {
                    popUpToId?.let {
                        setPopUpTo(it, inclusive)
                    }
                }
                .build()
            navController.navigate(actionId, bundle, options)
        }
    }

    fun onBackPressed() {
        findNavController().popBackStack()
    }
}