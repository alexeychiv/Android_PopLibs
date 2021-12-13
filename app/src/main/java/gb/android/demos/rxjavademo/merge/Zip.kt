package gb.android.demos.rxjavademo.merge

import android.util.Log
import gb.android.demos.rxjavademo.operators.FromIterable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class Zip {
    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 2, 3, 4, 5))
        private val producer2 = FromIterable.Producer(listOf(6, 7, 8, 9, 0))

        fun subscribe(): Disposable {

            return Observable.zip(
                producer.fromIterable(),
                producer2.fromIterable(),
                { item1, item2 ->
                    Pair(item1, item2)
                })
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe {
                    Log.d("RxJava Zip fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava Zip fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava Zip fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava Zip fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }
}