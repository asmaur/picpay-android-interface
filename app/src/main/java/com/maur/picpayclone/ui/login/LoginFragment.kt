package com.maur.picpayclone.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maur.picpayclone.R
import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.data.UsuarioLogado
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.component.Componentes
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private val componenteViewModel: ComponentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        componenteViewModel.temComponente = Componentes(bottonNavigation = false)

        configBottonLogin()
        //HomeFragmentDirections.actionGlobalLoginFragment()
    }

    private fun configBottonLogin() {
        button.setOnClickListener {
            UsuarioLogado.usuario = Usuario("maur")
            vaiParaHome()
        }
    }

    private fun vaiParaHome() {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToNavigationHome()

        val controlador = findNavController()
        controlador.navigate(directions)
    }

}