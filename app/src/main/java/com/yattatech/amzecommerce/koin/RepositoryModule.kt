package com.yattatech.amzecommerce.koin

import com.yattatech.amzecommerce.data.AmazonDataRepository
import com.yattatech.amzecommerce.data.AmazonDataRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AmazonDataRepository> { AmazonDataRepositoryImpl(get()) }
}