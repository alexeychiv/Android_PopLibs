package gb.android.android_poplibs.rxjavademo.filters

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.Interval
import io.reactivex.rxjava3.disposables.Disposable

class Filter {
    class Consumer {
        private val producer = Interval.Producer(1)

        fun subscribe(): Disposable {
            return producer.interval()
                .filter { it % 2 == 0L } //Applies condition on an item (this case only even numbers would go through)
                .doOnSubscribe {
                    Log.d("RxJava filter interval", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava filter interval", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava filter interval", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava filter interval", "Flow is completed.")
                }
                .subscribe()
        }
    }
}