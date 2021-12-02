package gb.android.android_poplibs.rxjavademo.merge

import android.util.Log
import gb.android.android_poplibs.rxjavademo.operators.FromIterable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class SwitchMap {

    class Consumer {
        private val producer = FromIterable.Producer(listOf(1, 2, 3, 4, 5))

        fun subscribe(): Disposable {
            return producer.fromIterable()
                .switchMap {
                    return@switchMap FromIterable.Producer(listOf(11, 22, 33)).fromIterable()
                        .delay(1, TimeUnit.SECONDS)
                }
                .doOnSubscribe {
                    Log.d("RxJava SwitchMap fromIterable", "Subscribed to producer.")
                }
                .doOnNext {
                    Log.d("RxJava SwitchMap fromIterable", "Item received: $it")
                }
                .doOnError {
                    Log.e("RxJava SwitchMap fromIterable", "Error: $it", it)
                }
                .doOnComplete {
                    Log.d("RxJava SwitchMap fromIterable", "Flow is completed.")
                }
                .subscribe()
        }
    }

}