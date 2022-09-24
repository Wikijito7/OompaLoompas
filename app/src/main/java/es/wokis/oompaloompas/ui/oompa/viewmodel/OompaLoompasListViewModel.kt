package es.wokis.oompaloompas.ui.oompa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.map
import es.wokis.oompaloompas.domain.GetMaxPageUseCase
import es.wokis.oompaloompas.domain.GetOompaLoompasUseCase
import es.wokis.oompaloompas.domain.GetSavedFiltersUseCase
import es.wokis.oompaloompas.domain.SaveFiltersUseCase
import es.wokis.oompaloompas.ui.oompa.mapper.toVO
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OompaLoompasListViewModel @Inject constructor(
    private val getOompaLoompasUseCase: GetOompaLoompasUseCase,
    private val getSavedFiltersUseCase: GetSavedFiltersUseCase,
    private val saveFiltersUseCase: SaveFiltersUseCase,
    private val getMaxPageUseCase: GetMaxPageUseCase
) : ViewModel() {
    // region private live data
    private var oompaLoompasLiveData: MutableLiveData<AsyncResult<List<OompaLoompaVO>>> =
        MutableLiveData()
    private var maxPageLiveData: MutableLiveData<Int> = MutableLiveData()

    // endregion
    private var page: Int = 1
    private var maxPage: Int = 1
    private var filters: Pair<String?, String?> = Pair(null, null)

    // region public live data
    fun getOompaLoompasLiveData() =
        oompaLoompasLiveData as LiveData<AsyncResult<List<OompaLoompaVO>>>

    fun getMaxPageLiveData() = maxPageLiveData as LiveData<Int>
    // endregion

    fun getOompaLoompas() {
        viewModelScope.launch(Dispatchers.IO) {
            getOompaLoompasUseCase(page).collect { asyncResult ->
                oompaLoompasLiveData.postValue(asyncResult.map {
                    it.toVO().filtered()
                })
            }
        }
    }

    fun nextPage() {
        if (page < maxPage) {
            page++
            getOompaLoompas()
        }
    }

    fun previousPage() {
        if (page > 1) {
            page--
            getOompaLoompas()
        }
    }

    fun getCurrentPage() = page

    fun getMaxPage() {
        val maxPage = getMaxPageUseCase()
        this.maxPage = maxPage
        maxPageLiveData.postValue(maxPage)
    }

    fun applyFilters() {
        val filters = getSavedFiltersUseCase()
        oompaLoompasLiveData.value?.data.orEmpty()
        this.filters = filters
        getOompaLoompas()
    }

    fun removeFilters() {
        saveFiltersUseCase(Pair(null, null))
        applyFilters()
    }

    fun areThereFiltersApplied() = filters.first != null ||
            filters.second != null

    private fun List<OompaLoompaVO>.filtered(): List<OompaLoompaVO> {
        var listFiltered = this
        filters.first?.let { professionFilter ->
            listFiltered = listFiltered.filter {
                it.profession == professionFilter
            }
        }
        filters.second?.let { genderFilter ->
            listFiltered = listFiltered.filter {
                it.gender == genderFilter
            }
        }
        return listFiltered
    }
}
