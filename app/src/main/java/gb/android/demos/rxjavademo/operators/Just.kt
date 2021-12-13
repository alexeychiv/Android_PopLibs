package gb.android.demos.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable

class Just {

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