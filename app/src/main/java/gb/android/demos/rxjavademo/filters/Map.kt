package gb.android.demos.rxjavademo.filters

import android.util.Log
import gb.android.demos.rxjavademo.operators.Interval
import io.reactivex.rxjava3.disposables.Disposable

class Map {
    class Consumer {
        private val producer = Interval.Producer(1)

        fun subscribe(): Disposable {
            return producer.interval()
                .map { it % 5 } //Takes only first 5 items
                .doOnSubscribe {
                    Log.d("RxJava map interval", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava map interval", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava map interval", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava map interval", "Flow is completed.")
                }
                .subscribe()
        }
    }
}