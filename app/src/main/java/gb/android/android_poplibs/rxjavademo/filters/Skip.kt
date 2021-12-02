package gb.android.android_poplibs.rxjavademo.filters

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.Interval
import io.reactivex.rxjava3.disposables.Disposable

class Skip {
    class Consumer {
        private val producer = Interval.Producer(1)

        fun subscribe(): Disposable {
            return producer.interval()
                .skip(5) //Skips first 5 items
                .doOnSubscribe {
                    Log.d("RxJava skip interval", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava skip interval", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava skip interval", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava skip interval", "Flow is completed.")
                }
                .subscribe()
        }
    }
}