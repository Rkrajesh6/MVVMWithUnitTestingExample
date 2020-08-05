
package com.rajesh.mvvmunittestingassignment

import android.provider.ContactsContract
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import com.rajesh.mvvmunittestingassignment.network.UserService
import com.rajesh.mvvmunittestingassignment.viewmodel.MainViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


/*
*Created by rajeshkumar07 on 04-08-2020
*/

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var userService: UserService

    @InjectMocks
    var viewModel = MainViewModel()

    private var testSingle: Single<ModelResponse>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUserSuccess() {
        val model = ModelResponse(
            info = ModelResponse.Info(
                page = 1,
                results = 1,
                seed = "9b9674c625a3a9d6",
                version = "1.3"
            ), results = arrayListOf(ModelResponse.Result(
                cell = "0173-8167797",
                dob = ModelResponse.Result.Dob(age = 23, date = "1956-11-29T04:23:55.886Z"),
                email = "alexa.feldmeier@example.com",
                gender = "female",
                id = ModelResponse.Result.Id(name = "SSN", value = "878-50-6588"),
                location = ModelResponse.Result.Location(
                    city = "Neckarsteinach",
                    coordinates = ModelResponse.Result.Location.Coordinates(
                        latitude = "-44.0723", longitude = "37.5957"
                    ),
                    country = "Germany",
                    postcode = "17571",
                    state = "Bremen",
                    street = ModelResponse.Result.Location.Street(
                        name = "Neue Straße",
                        number = 6646
                    ),
                    timezone = ModelResponse.Result.Location.Timezone(
                        description = "Bangkok, Hanoi, Jakarta",
                        offset = "+7:00"
                    )
                ),
                login = ModelResponse.Result.Login(
                    md5 = "4b06cf2afa1207e54a7c7eea55a07b84",
                    password = "12312312",
                    salt = "2aoAshox",
                    sha1 = "fbd41e66995ceeb041b385cfde8b6a9719b79990",
                    sha256 = "9abadd906e148cfa0900c92d1df78bd11a46b2d9b52e38748067f26d067c5056",
                    username = "goldenelephant529",
                    uuid = "030dab94-1d03-4641-b505-373e8b851a3a"
                ),
                name = ModelResponse.Result.Name(
                    title = "Miss",
                    first = "Alexa",
                    last = "Feldmeier"
                ),
                nat = "DE",
                phone = "0753-1626193",
                picture = ModelResponse.Result.Picture(
                    large = "https://randomuser.me/api/portraits/women/49.jpg",
                    medium = "https://randomuser.me/api/portraits/med/women/49.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/women/49.jpg"
                ),
                registered = ModelResponse.Result.Registered(
                    age = 2,
                    date = "2018-02-01T03:51:52.103Z"
                )
            ))
        )

        testSingle = Single.just(model)
        Mockito.`when`(userService.getUser()).thenReturn(testSingle)

        viewModel.refresh()

        Assert.assertEquals(20, viewModel.users.value!!.results.size)
        Assert.assertEquals(false, viewModel.userLoadError.value)
        Assert.assertEquals(false, viewModel.loading.value)
    }

    @Test
    fun getUserFail(){
        testSingle = Single.error(Throwable())

        Mockito.`when`(userService.getUser()).thenReturn(testSingle)

        viewModel.refresh()

        Assert.assertEquals(false, viewModel.userLoadError.value)
        Assert.assertEquals(false, viewModel.loading.value )
    }

    @Before
    fun setUpRxSchedulers(){
        val imidiate = object :Scheduler(){
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, delay, unit)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler : Callable<Scheduler>? -> imidiate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler : Callable<Scheduler>? -> imidiate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler : Callable<Scheduler>? -> imidiate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler : Callable<Scheduler>? -> imidiate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler : Callable<Scheduler>? -> imidiate }

    }
}

