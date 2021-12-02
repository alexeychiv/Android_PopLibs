package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class DelayedJust {

    class Producer(
        val item: Long
    ) {

        fun just(): Observable<Long> {
            return Observable.just(item)
        }

    }

    class Consumer {

        private val producer = Producer(10)

        fun subscribe() {
            producer.just()
                .delay(2L, TimeUnit.SECONDS) //Delays subscription for specified period
                .doOnSubscribe {
                    Log.d("RxJava just", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava just", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava just", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava just", "Flow is completed.")
                }
                .subscribe()
        }

    }

}