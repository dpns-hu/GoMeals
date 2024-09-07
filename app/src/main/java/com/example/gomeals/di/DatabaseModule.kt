package com.example.gomeals.di

import com.example.gomeals.data.repository.DatabaseRepositoryImpl
import com.example.gomeals.domain.repository.DatabaseRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
     @Provides
     @Singleton
     fun providesDataBaseRepository():DatabaseRepository {
         return DatabaseRepositoryImpl(FirebaseDatabase.getInstance())
     }
}