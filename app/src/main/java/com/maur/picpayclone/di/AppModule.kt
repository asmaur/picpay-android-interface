package com.maur.picpayclone.di

import com.maur.picpayclone.service.ApiService
import com.maur.picpayclone.service.RetroFitService
import com.maur.picpayclone.ui.component.ComponentViewModel
import com.maur.picpayclone.ui.dashboard.DashboardViewModel
import com.maur.picpayclone.ui.home.HomeViewModel
import com.maur.picpayclone.ui.notifications.NotificationsViewModel
import com.maur.picpayclone.ui.transacao.TransacaoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ HomeViewModel(get()) }
    viewModel{ DashboardViewModel(get()) }
    viewModel{ NotificationsViewModel() }
    viewModel{ TransacaoViewModel(get()) }
    viewModel { ComponentViewModel() }
}

val serviceModule = module {
    single {
        RetroFitService.criarService<ApiService>()
    }
}