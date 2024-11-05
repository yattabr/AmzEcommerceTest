package com.yattatech.amzecommerce.koin

import com.yattatech.amzecommerce.ui.productList.ProductListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ProductListViewModel)
}