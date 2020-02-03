package ru.asshands.softwire.instagirl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onError
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = GirlsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        girls_recycler.adapter = adapter
        girls_recycler.layoutManager = LinearLayoutManager(this)
        girls_recycler.setHasFixedSize(true)

        loadGirls()

    }

    private fun loadGirls() {
        showProgress()

        val api = (application as App).api

        api.girls()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onGirlsLoaded(it) },
                { onError(it)}
            )
    }

    private fun onError(it: Throwable?) {
        hideProgress()
        Toast.makeText(this,it?.message, Toast.LENGTH_LONG).show()
    }

    private fun onGirlsLoaded(it: List<Girl>){
        hideProgress()
        adapter.setGirls(it)

    }

    private fun showProgress(){
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress(){
        progress.visibility = View.GONE
    }
}
