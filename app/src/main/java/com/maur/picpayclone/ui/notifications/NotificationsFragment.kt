package com.maur.picpayclone.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maur.picpayclone.R
import com.maur.picpayclone.databinding.FragmentNotificationsBinding
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.component.Componentes
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

//    private lateinit var notificationsViewModel: NotificationsViewModel
//    private var _binding: FragmentNotificationsBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
    private val notificationsViewModel: NotificationsViewModel by viewModel()
    private val componenteViewModel: ComponentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        notificationsViewModel =
//            ViewModelProvider(this).get(NotificationsViewModel::class.java)
//
//        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_notifications, container, false) //binding.root

//        val textView: TextView = root.findViewById(R.id.text_notifications) //binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componenteViewModel.temComponente = Componentes(bottonNavigation = true)
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}