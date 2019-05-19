package housemaster.github.com.data

interface BashImApiService {
    @GET("api/get")
    fun searchQuotes(
        @Query("site") site: String,
        @Query("name")name: String,
        @Query("num") num: Int): io.reactivex.Observable<List<Quote>>

    @GET("api/sources")
    fun searchSources(): io.reactivex.Observable<List<SourceOfQuotes>>

    companion object Factory{
        fun create():BashImApiService{
            val gson:Gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.Create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl: "http://umorili.herokuapp.com")
                .build();
            return retrofit.create(BashImApiService::class.java)
        }
    }
}