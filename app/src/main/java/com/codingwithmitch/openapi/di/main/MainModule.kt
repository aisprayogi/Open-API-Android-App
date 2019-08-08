package com.codingwithmitch.openapi.di.main

import com.codingwithmitch.openapi.api.main.OpenApiMainService
import com.codingwithmitch.openapi.persistence.AccountPropertiesDao
import com.codingwithmitch.openapi.persistence.AppDatabase
import com.codingwithmitch.openapi.persistence.AuthTokenDao
import com.codingwithmitch.openapi.persistence.BlogPostDao
import com.codingwithmitch.openapi.repository.main.AccountRepository
import com.codingwithmitch.openapi.repository.main.BlogRepository
import com.codingwithmitch.openapi.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {


    @MainScope
    @Provides
    fun provideOpenApiMainService(retrofitBuilder: Retrofit.Builder): OpenApiMainService {
        return retrofitBuilder
            .build()
            .create(OpenApiMainService::class.java)
    }

    @MainScope
    @Provides
    fun provideMainRepository(openApiMainService: OpenApiMainService, accountPropertiesDao: AccountPropertiesDao): AccountRepository {
        return AccountRepository(openApiMainService, accountPropertiesDao)
    }

    @MainScope
    @Provides
    fun provideBlogPostDao(db: AppDatabase): BlogPostDao {
        return db.getBlogPostDao()
    }

    @MainScope
    @Provides
    fun provideBlogRepository(openApiMainService: OpenApiMainService, blogPostDao: BlogPostDao): BlogRepository{
        return BlogRepository(openApiMainService, blogPostDao)
    }

}