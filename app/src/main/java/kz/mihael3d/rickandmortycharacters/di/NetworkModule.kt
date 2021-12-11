package kz.mihael3d.rickandmortycharacters.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.mihael3d.rickandmortycharacters.data.api.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.api.EpisodeApi
import kz.mihael3d.rickandmortycharacters.data.api.LocationsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLogginInterseptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            interceptors().add(httpLoggingInterceptor)
        }.build()

    @Provides
    @Singleton
    fun provideGson():Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .serializeNulls()
            .setLenient()
            .create()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder().apply {
            baseUrl("https://rickandmortyapi.com/api/")
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
        }.build()

    @Provides
    @Singleton
    fun providesCharacterApi(retrofit: Retrofit) : CharacterApi =
        retrofit.create(CharacterApi::class.java)

    @Provides
    @Singleton
    fun providesEpisodeApi(retrofit: Retrofit) : EpisodeApi =
        retrofit.create(EpisodeApi::class.java)
    @Provides
    @Singleton
    fun providesLocationApi(retrofit: Retrofit) : LocationsApi =
        retrofit.create(LocationsApi::class.java)
}