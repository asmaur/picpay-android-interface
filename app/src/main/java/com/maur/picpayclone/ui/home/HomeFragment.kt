package com.maur.picpayclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maur.picpayclone.R
import com.maur.picpayclone.data.UsuarioLogado
import com.maur.picpayclone.extensions.formatarMoeda
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.component.Componentes
import com.maur.picpayclone.ui.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
//import com.maur.picpayclone.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel
//    private var _binding: FragmentHomeBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()
    private val controlador  by lazy { findNavController() }
    private val componenteViewModel: ComponentViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //val root: View = binding.root

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componenteViewModel.temComponente = Componentes(bottonNavigation = true)

        if(UsuarioLogado.isUsuarioNaoLogado()){
            vaiParaLogin()
        }
        observarSaldo()
        observerErro()
        observarTransacoes()

        observarCarregar()

        //HomeFragmentDirections.actionGlobalLoginFragment()
    }

    private fun observarCarregar() {
        homeViewModel.onLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBar.visibility = VISIBLE
                recycler_view.visibility = GONE
            } else {
                progressBar.visibility = GONE
                recycler_view.visibility = VISIBLE
            }
        })
    }

    private fun observarTransacoes() {
        homeViewModel.transacoes.observe(viewLifecycleOwner, Observer {
            it?.let { transacoes ->
                recycler_view.adapter = HomeAdapter(transacoes)
            }
        })
    }

    private fun observerErro() {
        homeViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let { mensagem ->
                Toast.makeText(this@HomeFragment.context, mensagem, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            it?.let { saldo ->
                textViewSaldo.text = saldo.formatarMoeda()
            }
        })
    }

    private fun vaiParaLogin() {
        val directions =
            HomeFragmentDirections.actionGlobalLoginFragment()

        //val controlador = findNavController()
        controlador.navigate(directions)
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}