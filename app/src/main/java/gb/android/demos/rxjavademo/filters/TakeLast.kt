package gb.android.demos.rxjavademo.filters

import android.util.Log
import gb.android.demos.rxjavademo.operators.FromIterable

class TakeLast {
    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 2, 3, 4, 5, 6, 7))

        fun subscribe() {
            producer.fromIterable()
                .takeLast(3) //Takes last 3 items
                .doOnSubscribe {
                    Log.d("RxJava takeLast fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava takeLast fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava takeLast fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava takeLast fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }
}