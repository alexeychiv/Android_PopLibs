package gb.android.demos.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable

class Range {

    class Producer(
        val start: Int,
        val count: Int
    ) {

        fun range(): Observable<Int> {
            return Observable.range(start, count)
        }

    }

    class Consumer {

        private val producer = Producer(10, 20)

        fun subscribe() {
            producer.range()
                .doOnSubscribe {
                    Log.d("RxJava range", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava range", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava range", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava range", "Flow is completed.")
                }
                .subscribe()
        }

    }

}