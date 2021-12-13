package gb.android.demos.rxjavademo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gb.android.android_poplibs.databinding.ActivityRxjavaDemoBinding
import gb.android.demos.rxjavademo.filters.*
import gb.android.demos.rxjavademo.filters.Map
import gb.android.demos.rxjavademo.merge.FlatMap
import gb.android.demos.rxjavademo.merge.MergeWith
import gb.android.demos.rxjavademo.merge.SwitchMap
import gb.android.demos.rxjavademo.merge.Zip
import gb.android.demos.rxjavademo.operators.*
import io.reactivex.rxjava3.disposables.Disposable

class RxJavaDemoActivity : AppCompatActivity() {

    private var _binding: ActivityRxjavaDemoBinding? = null
    private val binding: ActivityRxjavaDemoBinding
        get() = _binding!!


    private var disposableInterval: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRxjavaDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //OPERATORS
        binding.btnJust.setOnClickListener {
            Just.Consumer().subscribe()
        }
        binding.btnDelayedJust.setOnClickListener {
            DelayedJust.Consumer().subscribe()
        }
        binding.btnFromIterable.setOnClickListener {
            FromIterable.Consumer().subscribe()
        }
        binding.btnRange.setOnClickListener {
            Range.Consumer().subscribe()
        }
        binding.btnInterval.setOnClickListener {
            if (disposableInterval == null)
                disposableInterval = Interval.Consumer().subscribe()
        }
        binding.btnDisposeInterval.setOnClickListener {
            disposableInterval?.dispose()
            disposableInterval = null
        }
        binding.btnTimer.setOnClickListener {
            Timer.Consumer().subscribe()
        }
        binding.btnFromCallable.setOnClickListener {
            FromCallable.Consumer().subscribe()
        }
        binding.btnCreate.setOnClickListener {
            Create.Consumer().subscribe()
        }

        //FILTERS
        binding.btnSkip.setOnClickListener {
            if (disposableInterval == null)
                disposableInterval = Skip.Consumer().subscribe()
        }
        binding.btnTake.setOnClickListener {
            Take.Consumer().subscribe()
        }
        binding.btnTakeLast.setOnClickListener {
            TakeLast.Consumer().subscribe()
        }
        binding.btnMap.setOnClickListener {
            if (disposableInterval == null)
                disposableInterval = Map.Consumer().subscribe()
        }
        binding.btnDistinct.setOnClickListener {
            Distinct.Consumer().subscribe()
        }
        binding.btnFilter.setOnClickListener {
            if (disposableInterval == null)
                disposableInterval = Filter.Consumer().subscribe()
        }

        //MERGING OPERATORS
        binding.btnMergeWith.setOnClickListener {
            MergeWith.Consumer().subscribe()
        }
        binding.btnFlatMap.setOnClickListener {
            FlatMap.Consumer().subscribe()
        }
        binding.btnSwitchMap.setOnClickListener {
            SwitchMap.Consumer().subscribe()
        }
        binding.btnZip.setOnClickListener {
            Zip.Consumer().subscribe()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}