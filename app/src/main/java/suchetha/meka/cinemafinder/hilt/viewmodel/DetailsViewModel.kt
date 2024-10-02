package suchetha.meka.cinemafinder.hilt.viewmodel

import suchetha.meka.cinemafinder.hilt.model.MovieRepository
import suchetha.meka.cinemafinder.hilt.model.details.MovieDetailsResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _detailsLiveData = MutableLiveData<MovieDetailsResponse>()
    val detailsLiveData: LiveData<MovieDetailsResponse> = _detailsLiveData
    private val _detailsErrorData = MutableLiveData<String>()
    val detailsErrorData: LiveData<String> = _detailsErrorData
    private lateinit var detailsDisposable: Disposable

    fun fetchMovieDetails(movieId: String) {
        detailsDisposable = movieRepository.getDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _detailsLiveData.value = it
                    Log.e(DETAIL_SCREEN_TAG, it.toString())
                }, {
                    _detailsErrorData.value = it.message
                    Log.e(DETAIL_SCREEN_TAG, it.message.toString())
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        if (this::detailsDisposable.isInitialized) {
            detailsDisposable.dispose()
        }
    }

    private companion object {
        const val DETAIL_SCREEN_TAG = "detail screen"
    }
}