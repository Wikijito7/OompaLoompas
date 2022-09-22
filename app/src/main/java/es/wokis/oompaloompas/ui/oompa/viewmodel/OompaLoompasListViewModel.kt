package es.wokis.oompaloompas.ui.oompa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.map
import es.wokis.oompaloompas.domain.GetMaxPageUseCase
import es.wokis.oompaloompas.domain.GetOompaLoompasUseCase
import es.wokis.oompaloompas.ui.oompa.mapper.toVO
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OompaLoompasListViewModel @Inject constructor(
    private val getOompaLoompasUseCase: GetOompaLoompasUseCase,
    private val getMaxPageUseCase: GetMaxPageUseCase
) : ViewModel() {
    // region private live data
    private var oompaLoompasLiveData: MutableLiveData<AsyncResult<List<OompaLoompaVO>>> =
        MutableLiveData()
    private var maxPageLiveData: MutableLiveData<Int> = MutableLiveData()
    // endregion
    private var page: Int = 1
    private var maxPage: Int = 1

    // region public live data
    fun getOompaLoompasLiveData() =
        oompaLoompasLiveData as LiveData<AsyncResult<List<OompaLoompaVO>>>

    fun getMaxPageLiveData() = maxPageLiveData as LiveData<Int>
    // endregion

    fun getOompaLoompas() {
        viewModelScope.launch(Dispatchers.IO) {
            val oompaLoompas = getOompaLoompasUseCase(page).map { it.toVO() }
            oompaLoompasLiveData.postValue(oompaLoompas)
        }
    }

    fun nextPage() {
        if (page < maxPage) {
            page++
        }
    }

    fun previousPage() {
        if (page > 1) {
            page--
        }
    }

    fun getCurrentPage() = page

    fun getMaxPage() {
        val maxPage = getMaxPageUseCase()
        this.maxPage = maxPage
        maxPageLiveData.postValue(maxPage)
    }
}