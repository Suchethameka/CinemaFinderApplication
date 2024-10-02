package suchetha.meka.cinemafinder.hilt.viewmodel

import suchetha.meka.cinemafinder.hilt.model.local.MovieDao
import suchetha.meka.cinemafinder.hilt.model.local.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesMovieViewModel @Inject constructor(private val movieDao: MovieDao) : ViewModel() {

    private val _favMovies = MutableLiveData<List<Result>>()
    val favMovies: LiveData<List<Result>> = _favMovies

    init {
        _favMovies.value = movieDao.fetchMovie()
    }

    fun add(result: Result) {
        movieDao.insertMovie(result)
    }
}