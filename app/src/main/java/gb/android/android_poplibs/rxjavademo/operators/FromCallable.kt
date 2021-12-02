package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import gb.android.android_poplibs.rxjavademo.getCurrentTime
import io.reactivex.rxjava3.core.Observable

class FromCallable {

    class Producer {

        fun fromCallable(): Observable<Long> {
            return Observable.fromCallable { getCurrentTime() }
        }

    }

    class Consumer {

        private val producer = Producer()

        fun subscribe() {
            producer.fromCallable()
                .doOnSubscribe {
                    Log.d("RxJava fromCallable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava fromCallable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava fromCallable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava fromCallable", "Flow is completed.")
                }
                .subscribe()
        }

    }

}