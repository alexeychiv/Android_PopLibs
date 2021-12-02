package gb.android.android_poplibs.rxjavademo.filters

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.Interval
import io.reactivex.rxjava3.disposables.Disposable

class Take {
    class Consumer {
        private val producer = Interval.Producer(1)

        fun subscribe(): Disposable {
            return producer.interval()
                .take(5) //Takes only first 5 items
                .doOnSubscribe {
                    Log.d("RxJava take interval", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava take interval", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava take interval", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava take interval", "Flow is completed.")
                }
                .subscribe()
        }
    }
}