package es.wokis.oompaloompas.ui.filter.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.wokis.oompaloompas.data.constants.AppConstants.FEMALE
import es.wokis.oompaloompas.data.constants.AppConstants.MALE
import es.wokis.oompaloompas.data.constants.AppConstants.NOTHING
import es.wokis.oompaloompas.domain.GetProfessionsUseCase
import es.wokis.oompaloompas.domain.GetSavedFiltersUseCase
import es.wokis.oompaloompas.domain.SaveFiltersUseCase
import es.wokis.oompaloompas.ui.filter.vo.FilterType
import es.wokis.oompaloompas.ui.filter.vo.FilterVO
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getProfessionsUseCase: GetProfessionsUseCase,
    private val saveFiltersUseCase: SaveFiltersUseCase,
    getSavedFiltersUseCase: GetSavedFiltersUseCase
) : ViewModel() {

    private var filtersLiveData: MutableLiveData<List<FilterVO>> =
        MutableLiveData()

    private var filtersSavedLiveData: MutableLiveData<Boolean> = MutableLiveData()

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

    fun getFiltersSavedLiveData() = filtersSavedLiveData as LiveData<Boolean>

    fun getProfessions() {
        var professions = getProfessionsUseCase().toVO(FilterType.PROFESSION)
            .addDefaultValue(FilterType.PROFESSION)

        selectedProfession?.let { selectedProfessionNotNull ->
            professions = professions.map {
                if (it.name == selectedProfessionNotNull.name) selectedProfessionNotNull else it
            }
        }
        filters = professions
        filtersLiveData.postValue(professions)
    }

    fun getGenders() {
        var genders =
            listOf(MALE, FEMALE).toVO(FilterType.GENDER).addDefaultValue(FilterType.GENDER)

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
                selectedProfession = if (filter.name == NOTHING) null else filter
            }

            FilterType.GENDER -> {
                selectedGender = if (filter.name == NOTHING) null else filter
            }
        }

    }

    fun saveFilters() {
        val saved = saveFiltersUseCase(Pair(selectedProfession?.name, selectedGender?.name))
        filtersSavedLiveData.postValue(saved)
    }

    private fun List<String>.toVO(type: FilterType) = this.map {
        it.toVO(type, false)
    }

    private fun String.toVO(type: FilterType, selected: Boolean = false) = FilterVO(
        name = this,
        type = type,
        selected = selected
    )

    private fun List<FilterVO>.addDefaultValue(type: FilterType): List<FilterVO> {
        val selected = if (type == FilterType.PROFESSION) {
            selectedProfession == null

        } else {
            selectedGender == null
        }
        val newList: MutableList<FilterVO> = mutableListOf(NOTHING.toVO(type, selected))
        newList.addAll(this)
        return newList
    }
}
