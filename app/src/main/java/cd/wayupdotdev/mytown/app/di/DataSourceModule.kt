package cd.wayupdotdev.mytown.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

//    @Provides
//    fun provideLocaleDataSource(ChurchDao: ChurchDao): LocalDataSource {
//        return RoomChurchDataSource(ChurchDao)
//    }
}
