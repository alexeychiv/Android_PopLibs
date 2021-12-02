package gb.android.android_poplibs.rxjavademo.merge

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.FromIterable
import io.reactivex.rxjava3.disposables.Disposable

class MergeWith {
    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 2, 3, 4, 5))
        private val producer2 = FromIterable.Producer(listOf(6, 7, 8, 9, 0))

        fun subscribe(): Disposable {
            return producer.fromIterable()
                .mergeWith(producer2.fromIterable())
                .doOnSubscribe {
                    Log.d("RxJava MergeWith fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava MergeWith fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava MergeWith fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava MergeWith fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }
}