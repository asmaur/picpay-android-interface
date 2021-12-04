package com.maur.picpayclone.ui.transacao

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maur.picpayclone.R
import com.maur.picpayclone.data.CartaoCredito
import com.maur.picpayclone.data.UsuarioLogado
import com.maur.picpayclone.data.transacao.Transacao
import com.maur.picpayclone.extensions.formatar
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.component.Componentes
import kotlinx.android.synthetic.main.transacao_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TransacaoFragment : Fragment() {

    companion object {
        fun newInstance() = TransacaoFragment()
    }

    private val transacaoViewModel: TransacaoViewModel by viewModel()
    private val argumentos by navArgs<TransacaoFragmentArgs>()
    private val usuario by lazy { argumentos.usuario }
    private val controlador by lazy { findNavController() }
    private val componenteViewModel: ComponentViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transacao_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componenteViewModel.temComponente = Componentes(bottonNavigation = false)

        configurarUsuario()

        configurarBotaoTransferir()

        configurarRadioGroup()

        observarTransacao()
    }

    private fun observarTransacao() {
        transacaoViewModel.transacao.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val direction = TransacaoFragmentDirections.actionTransacaoFragmentToNavigationDashboard()
            controlador.navigate(direction)
        })
    }

    private fun configurarRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonCredito -> {
                    constraintLayoutCartao.visibility = VISIBLE
                }
                R.id.radioButtonSaldo -> {
                    constraintLayoutCartao.visibility = INVISIBLE
                }
            }
        }
    }

    private fun configurarBotaoTransferir() {
        buttonTransferir.setOnClickListener {
            val isCartaoCredito = radioButtonCredito.isChecked
            val valor = getValor()

            val transacao = if (isCartaoCredito) {
                criarTransferenciaCartao(isCartaoCredito, valor)
            } else {
                criarTransferenciaSaldo(valor)
            }

            transacaoViewModel.transferir(transacao)

        }
    }

    private fun configurarUsuario() {
        textViewLogin.text = usuario.login
        textViewNomeCompleto.text = usuario.nomeCompleto
    }

    private fun criarTransferenciaSaldo(valor: Double): Transacao {
        return Transacao(
            Transacao.gerarHash(),
            UsuarioLogado.usuario,
            usuario,
            Calendar.getInstance().formatar(),
            false,
            valor,
        )
    }

    private fun getValor(): Double {
        val valor = textViewValor.text.toString() //.toDouble()
        return if (valor.isEmpty()){ 0.0} else{ valor.toDouble() }
    }

    private fun criarTransferenciaCartao(cartaoCredito: Boolean, valor: Double):Transacao {
        //TODO("Not yet implemented")
       return Transacao(
            Transacao.gerarHash(),
            UsuarioLogado.usuario,
            usuario,
            Calendar.getInstance().formatar(),
            cartaoCredito,
            valor,
            criarCartaoCredito()
        )
    }

    private fun criarCartaoCredito(): CartaoCredito {
        val numero = numeroCartao.text.toString()
        val nomeTitular = titular.text.toString()
        val vencimento = dataVencimento.text.toString()
        val cvv = cvv_.text.toString()
        return CartaoCredito(
            numeroCartao = numero,
            nomeTitular = nomeTitular,
            dataExpiracao = vencimento,
            codigoSeguranca = cvv,
            usuario = UsuarioLogado.usuario
        )
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(TransacaoViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}