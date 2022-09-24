package es.wokis.oompaloompas.ui.filter.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.wokis.oompaloompas.domain.GetProfessionsUseCase
import es.wokis.oompaloompas.domain.GetSavedFiltersUseCase
import es.wokis.oompaloompas.domain.SaveFiltersUseCase
import es.wokis.oompaloompas.ui.filter.vo.FilterType
import es.wokis.oompaloompas.ui.filter.vo.FilterVO
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getProfessionsUseCase: GetProfessionsUseCase,
    private val getSavedFiltersUseCase: GetSavedFiltersUseCase,
    private val saveFiltersUseCase: SaveFiltersUseCase
) : ViewModel() {

    private var filtersLiveData: MutableLiveData<List<FilterVO>> =
        MutableLiveData()

    private var selectedProfession: FilterVO? = null
    private var selectedGender: FilterVO? = null

    private var filters: List<FilterVO> = emptyList()

    init {
        val savedFilters = getSavedFiltersUseCase()
        selectedProfession = savedFilters.first?.toVO(FilterType.PROFESSION, true)
        selectedGender = savedFilters.second?.toVO(FilterType.GENDER, true)
    }

    fun getFiltersLiveData() =
        filtersLiveData as LiveData<List<FilterVO>>

    fun getProfessions() {
        var professions = getProfessionsUseCase().toVO(FilterType.PROFESSION)
        selectedProfession?.let { selectedProfessionNotNull ->
            professions = professions.map {
                if (it.name == selectedProfessionNotNull.name) selectedProfessionNotNull else it
            }
        }
        filters = professions
        filtersLiveData.postValue(professions)
    }

    fun getGenders() {
        var genders = listOf("M", "F").toVO(FilterType.GENDER)
        selectedGender?.let { selectedGenderNotNull ->
            genders = genders.map {
                if (it.name == selectedGenderNotNull.name) selectedGenderNotNull else it
            }
        }
        filters = genders
        filtersLiveData.postValue(genders)
    }

    fun saveSelection(filter: FilterVO) {
        when (filter.type) {
            FilterType.PROFESSION -> {
                selectedProfession = filter
            }

            FilterType.GENDER -> {
                selectedGender = filter
            }
        }
    }

    fun saveFilters() {
        saveFiltersUseCase(Pair(selectedProfession?.name, selectedGender?.name))
    }

    private fun List<String>.toVO(type: FilterType) = this.map {
        it.toVO(type, false)
    }

    private fun String.toVO(type: FilterType, selected: Boolean = false) = FilterVO(
        name = this,
        type = type,
        selected = selected
    )
}