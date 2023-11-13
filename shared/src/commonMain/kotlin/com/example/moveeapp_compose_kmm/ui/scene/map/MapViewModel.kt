package com.example.moveeapp_compose_kmm.ui.scene.map

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.map.CinemaSearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel(
    private val cinemaSearchUseCase: CinemaSearchUseCase,
    private val locationService: LocationRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    private var job : Job? = null

    fun loadForecastWithLocation() {
        coroutineScope.launch {
            val location = locationService.getCurrentLocation()
            _uiState.value = _uiState.value.copy(lastLocation = location)
            getCinemasOnLocation(location)
        }
    }

    fun getUpdates(location: DeviceLocation) {
        job?.cancel()
        job = coroutineScope.launch {
            delay(1000)
            getCinemasOnLocation(location)
            job = null
        }
    }

    private suspend fun getCinemasOnLocation(coordinates: DeviceLocation?) {
        if (coordinates == null) return
            val result = cinemaSearchUseCase.execute(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude
            )
            result.onSuccess { list ->
                _uiState.update {
                    it.copy(cinemaList = list, lastLocation = coordinates)
                }
            }
    }

    fun setSelectedCinema(cinema: Cinema?) {
        val deviceLocation = cinema?.let {
            DeviceLocation(it.location.latitude, it.location.longitude, 12f)
        }
        _uiState.update {
            it.copy(selectedCinema = cinema, lastLocation = deviceLocation)
        }
    }
}