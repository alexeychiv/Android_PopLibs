package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable

class FromIterable {

    class Producer(
        val list: List<Long>
    ) {

        fun fromIterable(): Observable<Long> {
            return Observable.fromIterable(list)
        }

    }

    class Consumer {

        private val producer = Producer(listOf(1, 2, 3, 4, 5))

        fun subscribe() {
            producer.fromIterable()
                .doOnSubscribe {
                    Log.d("RxJava fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava fromIterable", "Flow is completed.")
                }
                .subscribe()
        }

    }

}