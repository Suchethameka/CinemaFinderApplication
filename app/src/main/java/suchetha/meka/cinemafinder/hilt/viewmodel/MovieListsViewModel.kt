package suchetha.meka.cinemafinder.hilt.viewmodel


import suchetha.meka.cinemafinder.hilt.model.MovieRepository
import suchetha.meka.cinemafinder.hilt.model.nowplaying.NowplayingMovieResponse
import suchetha.meka.cinemafinder.hilt.model.popular.PopularResponse
import suchetha.meka.cinemafinder.hilt.model.toprated.TopRatedResponse
import suchetha.meka.cinemafinder.hilt.model.upcoming.UpcomingResponse
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
class MovieListsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _popularLiveData = MutableLiveData<PopularResponse>()
    val popularLiveData: LiveData<PopularResponse> = _popularLiveData

    private val _popularErrorData = MutableLiveData<String>()
    val popularErrorData: LiveData<String> = _popularErrorData

    private lateinit var popularDisposable: Disposable

    fun fetchPopularMovieDetails() {
        popularDisposable = movieRepository.getPopularMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _popularLiveData.value = it
                    Log.e("url", it.toString())
                }, {
                    _popularErrorData.value = it.message
                    Log.e("urlerror", it.message.toString())
                }
            )
    }

    private val _nowplayingLiveData = MutableLiveData<NowplayingMovieResponse>()
    val nowplayingLiveData: LiveData<NowplayingMovieResponse> = _nowplayingLiveData

    private val _nowplayingErrorData = MutableLiveData<String>()
    val nowplayingErrorData: LiveData<String> = _nowplayingErrorData

    private lateinit var nowplayingDisposable: Disposable

    fun fetchNowPlayingMovieDetails() {
        nowplayingDisposable = movieRepository.getNowPlayingMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _nowplayingLiveData.value = it
                    Log.e("url", it.toString())
                }, {
                    _nowplayingErrorData.value = it.message
                    Log.e("urlerror", it.message.toString())
                }
            )
    }

    private val _upcomingLiveData = MutableLiveData<UpcomingResponse>()
    val upcomingLiveData: LiveData<UpcomingResponse> = _upcomingLiveData

    private val _upcomingErrorData = MutableLiveData<String>()
    val upcomingErrorData: LiveData<String> = _upcomingErrorData

    private lateinit var upcomingDisposable: Disposable
    fun fetchUpcomingMovieDetails() {
        upcomingDisposable = movieRepository.getUpcomingMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _upcomingLiveData.value = it
                    Log.e("url", it.toString())
                }, {
                    _upcomingErrorData.value = it.message
                    Log.e("urlerror", it.message.toString())
                }
            )
    }

    private val _topratedLiveData = MutableLiveData<TopRatedResponse>()
    val topratedLiveData: LiveData<TopRatedResponse> = _topratedLiveData

    private val _topratedErrorData = MutableLiveData<String>()
    val topratedErrorData: LiveData<String> = _topratedErrorData

    private lateinit var topratedDisposable: Disposable

    fun fetchTopRatedMovieDetails() {
        topratedDisposable = movieRepository.getTopRatedMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _topratedLiveData.value = it
                    Log.e("url", it.toString())
                }, {
                    _topratedErrorData.value = it.message
                    Log.e("urlerror", it.message.toString())
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        if (this::popularDisposable.isInitialized) {
            popularDisposable.dispose()
        }
        if (this::nowplayingDisposable.isInitialized) {
            nowplayingDisposable.dispose()
        }
        if (this::topratedDisposable.isInitialized) {
            topratedDisposable.dispose()
        }
        if (this::upcomingDisposable.isInitialized) {
            upcomingDisposable.dispose()
        }
    }
}