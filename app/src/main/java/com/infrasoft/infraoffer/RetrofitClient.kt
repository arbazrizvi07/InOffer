package com.infrasoft.infraoffer

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor



class RetrofitClient private constructor() {
    private var retrofit: Retrofit? = null


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * An interceptor is used to modify each request before it is performed and alters the request header.
     * The advantage is, that you don’t have to add @Header("Authorization") to each API method definition.
     */
    private inner class ApplicationInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            //logRequest(original);
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            val response = chain.proceed(request)

            val rawJson = response.body()?.string()?.replace("{\"d\":null}", "")
            //logResponse(response, rawJson);
            return response.newBuilder()
                .body(ResponseBody.create(response.body()?.contentType(), rawJson)).build()
        }
    }

    companion object {
        val BASE_URL = "http://155.248.230.176:8089/customer/"
        private var sInstance: RetrofitClient? = null

        fun create() {
            if (sInstance == null) {
                synchronized(RetrofitClient::class.java) {
                    if (sInstance == null) {
                        sInstance = RetrofitClient()
                    }
                }
            } else
                throw IllegalStateException("RetrofitClient instance is already been created.")
        }

        /**
         * Returns the instance of [Retrofit].
         * This method must be called after [.create].
         */
        fun retrofit(): Retrofit? {
            synchronized(RetrofitClient::class.java) {
                if (sInstance == null)
                    throw IllegalStateException("RetrofitClient instance is not created yet. Call RetrofitClient.create() before calling getInstance()")
            }
            return sInstance!!.retrofit
        }

        /**
         * Returns the instance of [APIInterface].
         * This method must be called after [.create].
         */
        val apiInterface: APIInterface
            get() = retrofit()!!.create(APIInterface::class.java)
    }

}
