package com.example.hackathonapp.di

import android.app.Application
import com.example.hackathonapp.Domaine.SharedPrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
        @Provides
        @Singleton
        fun provideFireStoreInstance(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }

        @Provides
        @Singleton
        fun provideFirebaseAuthInstance(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

    @Provides
    @Singleton
    fun getToken(c: Application): String?{
        return SharedPrefManager(c).used
    }
    }
