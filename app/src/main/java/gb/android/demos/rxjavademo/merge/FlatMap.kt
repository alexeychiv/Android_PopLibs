package gb.android.demos.rxjavademo.merge

import android.util.Log
import gb.android.demos.rxjavademo.operators.FromIterable
import io.reactivex.rxjava3.disposables.Disposable

class FlatMap {

    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 2, 3, 4, 5))

        fun subscribe(): Disposable {
            return producer.fromIterable()
                .flatMap { return@flatMap FromIterable.Producer(listOf(11, 22, 33)).fromIterable() }
                .doOnSubscribe {
                    Log.d("RxJava FlatMap fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava FlatMap fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava FlatMap fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava FlatMap fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }

}