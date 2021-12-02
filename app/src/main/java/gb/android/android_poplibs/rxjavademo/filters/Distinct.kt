package gb.android.android_poplibs.rxjavademo.filters

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.FromIterable

class Distinct {
    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 1, 2, 2, 3, 3, 4, 4))

        fun subscribe() {
            producer.fromIterable()
                .distinct() //Takes only distinct items
                .doOnSubscribe {
                    Log.d("RxJava Distinct fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava Distinct fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava Distinct fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava Distinct fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }
}