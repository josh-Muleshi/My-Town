package cd.wayupdotdev.mytown.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDataBase::class.java,
//            "devs_quotes"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideQuotesDao(appDataBase: AppDataBase): ChurchDao {
//        return appDataBase.churchDao()
//    }
}
