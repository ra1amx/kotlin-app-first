package housemaster.github.com.data

import java.lang.invoke.CallSite

class SearchRepository(val apiService: BashImApiService){

    fun searchQuotes(site: String, name: String):io.reacivex.Observable<List<Quote>>
        return apiService.searchQuotes(site, name, num: 50)

    fun searchSourcesQuotes():io.reacivex.Observable<List<SourceOfQuotes>>
    return apiService.searchSources()
}