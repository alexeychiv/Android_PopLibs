package gb.android.android_poplibs.rxjavademo.operators

import android.util.Log
import io.reactivex.rxjava3.core.Observable

class Create {

    class Producer {

        fun create(): Observable<Long> {
            return Observable.create { emitter ->
                (1L..10L).forEach {
                    emitter.onNext(it)
                }
                emitter.onError(IllegalStateException("Error"))
                emitter.onComplete()
            }
        }

    }

    class Consumer {

        private val producer = Producer()

        fun subscribe() {
            producer.create()
                .doOnSubscribe {
                    Log.d("RxJava create", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava create", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava create", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava create", "Flow is completed.")
                }
                .subscribe()
        }

    }

}