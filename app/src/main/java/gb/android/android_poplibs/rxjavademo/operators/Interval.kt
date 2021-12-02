package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class Interval {

    class Producer(
        val period: Long
    ) {

        fun interval(): Observable<Long> {
            return Observable.interval(period, TimeUnit.SECONDS)
        }

    }

    class Consumer {

        private val producer = Producer(1)

        fun subscribe(): Disposable {
            return producer.interval()
                .doOnSubscribe {
                    Log.d("RxJava interval", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava interval", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava interval", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava interval", "Flow is completed.")
                }
                .subscribe()
        }

    }

}