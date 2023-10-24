package com.example.moveeapp_compose_kmm.ui.scene.map

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.LocationService
import com.example.moveeapp_compose_kmm.domain.location.OSMObject
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.map.CinemaSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MapViewModel(
    private val cinemaSearchUseCase: CinemaSearchUseCase
) : ViewModel, KoinComponent {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    private val locationService: LocationService = get()

    fun loadForecastWithLocation() {
        coroutineScope.launch {
            val location = locationService.getCurrentLocation()
            _uiState.value = _uiState.value.copy(lastLocation = location)
            getCinemasOnLocation(location)
        }
    }

    fun getCinemasOnLocation(coordinates: DeviceLocation?) {
        if (coordinates == null) return

        coroutineScope.launch {
            val result = cinemaSearchUseCase.execute(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude
            )
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(cinemaList = result.getOrNull())
            }
        }
    }

    fun setSelectedCinema(cinema: OSMObject?) {
        _uiState.update {
            it.copy(selectedCinema = cinema)
        }
    }
}