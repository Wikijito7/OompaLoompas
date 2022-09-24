package es.wokis.oompaloompas.ui.detail.viemwodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.domain.GetOompaLoompaByIdUseCase
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OompaDetailViewModel @Inject constructor(
    private val getOompaLoompaByIdUseCase: GetOompaLoompaByIdUseCase
) : ViewModel() {
    // region private live data
    private var oompaLoompasLiveData: MutableLiveData<AsyncResult<OompaLoompaBO>> =
        MutableLiveData()

    fun getOompaLoompasLiveData() =
        oompaLoompasLiveData as LiveData<AsyncResult<OompaLoompaBO>>

    fun getOompaLoompaInfo(oompaId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getOompaLoompaByIdUseCase(oompaId).collect {
                oompaLoompasLiveData.postValue(it)
            }
        }
    }
}