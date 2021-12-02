package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class Timer {

    class Producer(
        val delay: Long
    ) {

        fun timer(): Observable<Long> {
            return Observable.timer(delay, TimeUnit.SECONDS)
        }

    }

    class Consumer {

        private val producer = Producer(3)

        fun subscribe() {
            producer.timer()
                .doOnSubscribe {
                    Log.d("RxJava timer", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava timer", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava timer", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava timer", "Flow is completed.")
                }
                .subscribe()
        }

    }

}