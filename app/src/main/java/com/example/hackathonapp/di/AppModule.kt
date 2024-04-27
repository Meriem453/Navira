package com.example.hackathonapp.di

import android.app.Application
import com.example.hackathonapp.Domaine.NotificationsService
import com.example.hackathonapp.Domaine.SharedPrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
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
    @Provides
    @Singleton
    fun provideFCMInstance(c: Application): FirebaseMessaging {
        return FirebaseMessaging.getInstance()
    }
    @Provides
    @Singleton
    fun provideNotifService(c: Application): NotificationsService {
        return NotificationsService(c)
    }
    }
