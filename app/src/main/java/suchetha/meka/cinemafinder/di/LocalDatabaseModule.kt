package suchetha.meka.cinemafinder.di

import suchetha.meka.cinemafinder.hilt.model.local.AppDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "MovieDB"
    ).allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase) = database.movieDao()

}