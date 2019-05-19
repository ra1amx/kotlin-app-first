package example.com.excerciseproject.model

import com.squareup.moshi.Moshi
import example.com.excerciseproject.Constanst.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class MainInteractor {

    val moshi: Moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(LoggingInterceptor.Builder()
//                .loggable(BuildConfig.DEBUG)
//                .setLevel(Level.BODY)
//                .log(okhttp3.internal.platform.Platform.INFO)
//                .executor(Executors.newSingleThreadExecutor())
//                .build()
//            )
        .retryOnConnectionFailure(true)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    val service = retrofit.create(BitrixService::class.java)

    suspend fun sendWebHook(name: String, phone: String, coordinates: Coordinate) {
        val map = mapOf(
            "FIRST_NAME" to name,
            "PHONE" to phone,
            "COMMENTS" to coordinates.toString()
        )
        val responseDeferred = service.sendWebHook(map)

//        return responseDeferred.await()
    }
}