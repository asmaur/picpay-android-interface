package com.maur.picpayclone.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.maur.picpayclone.R
import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.component.Componentes
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

     private val dashboardViewModel: DashboardViewModel by viewModel()
    private val controlador  by lazy { findNavController() }
    private val componenteViewModel: ComponentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_dashboard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componenteViewModel.temComponente = Componentes(bottonNavigation = true)
        observerContatos()
    }

    private fun observerContatos() {
        dashboardViewModel.contatos.observe(viewLifecycleOwner, Observer {
            it?.let { usuarios -> configurarRecyclerView(usuarios) }
        })
    }

    private fun configurarRecyclerView(usuarios: List<Usuario>) {

        recyclerContatos.adapter = PagarAdapter(usuarios){ usuario ->
            vaiParaTransacao(usuario)
        }
    }

    private fun vaiParaTransacao(usuario: Usuario) {
        val directions = DashboardFragmentDirections.actionNavigationDashboardToTransacaoFragment(usuario)
        controlador.navigate(directions)
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}