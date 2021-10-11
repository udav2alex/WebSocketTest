package ru.gressor.websockettest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.gressor.websockettest.api.retrofit.RetrofitServiceProvider
import ru.gressor.websockettest.api.websocket.WebServicesProvider
import ru.gressor.websockettest.databinding.FragmentStartBinding
import ru.gressor.websockettest.domain.MainInteractor
import ru.gressor.websockettest.repository.MainRepository
import ru.gressor.websockettest.ui.vm.MainViewModel
import ru.gressor.websockettest.ui.vm.MainViewModelFactory

class StartFragment : Fragment() {
    private var viewBinding: FragmentStartBinding? = null
    private fun <T> views(block: FragmentStartBinding.() -> T): T? = viewBinding?.block()

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            MainInteractor(
                MainRepository(
                    RetrofitServiceProvider.service,
                    WebServicesProvider()
                )))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ) = FragmentStartBinding
        .inflate(inflater, container, false)
        .also {
            viewBinding = it
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        views {
            connectButton.setOnClickListener {
                Toast.makeText(requireActivity(), "Click!", Toast.LENGTH_LONG).show()
                viewModel.connect()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}