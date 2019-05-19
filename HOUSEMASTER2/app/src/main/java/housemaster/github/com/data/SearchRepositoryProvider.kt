package housemaster.github.com.data

object SearchRepositoryProvider {

    fun provideSearchRepository():SearchRepository {
        return SearchRepository(BashImApiService.create())
    }
}