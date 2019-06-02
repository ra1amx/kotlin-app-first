package example.com.excerciseproject.view.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.runtimepermission.RuntimePermission.askPermission
import de.cketti.mailto.EmailIntentBuilder
import example.com.excerciseproject.R
import example.com.excerciseproject.Work
import example.com.excerciseproject.model.Coordinate
import example.com.excerciseproject.model.MainInteractor
import example.com.excerciseproject.presenter.MainPresenter
import example.com.excerciseproject.view.order.ProcessOrderActivity
import example.com.excerciseproject.view.select.WorkTypePagerAdapter
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(MainInteractor())
    private var simpleLocation: SimpleLocation? = null
    private var location: Coordinate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpleLocation = SimpleLocation(this)

        val permission = askPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        permission.onAccepted {
            simpleLocation!!.setListener {
                simpleLocation?.position?.let { position ->
                    location = Coordinate(position.latitude, position.longitude)
                }
            }
            simpleLocation!!.beginUpdates()
        }
        permission.onDenied {
            Toast.makeText(this@MainActivity, "Геолокация нужна для корректной работы приложения", Toast.LENGTH_LONG).show()
            SimpleLocation.openSettings(this)
        }
        permission.ask()

        presenter.attachView(this)

        setupViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            simpleLocation?.endUpdates()
        }
    }

    private fun setupViews() {
        view_pager.adapter = WorkTypePagerAdapter(this, supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        send_button.setOnClickListener {
            startActivity(Intent(this, ProcessOrderActivity::class.java))
        }

//        work_group.setOnCheckedChangeListener { _, i ->
//            val workType = when (i) {
//                example.com.excerciseproject.R.id.button_repair -> WorkType.REPAIR
//                example.com.excerciseproject.R.id.button_service -> WorkType.SERVICE
//                else -> throw Exception("Not implemented")
//            }
//            presenter.onCheckedType(workType)
//        }
//
//        send_button.setOnClickListener {
//            val name = input_name.text.toString()
//            val phone = input_phone.text.toString()
//
//            presenter.onClickSend(
//                name,
//                phone,
//                exercisesAdapter?.checkedExercises ?: mutableListOf(),
//                if (checkbox_send_coordinates.isChecked) location else null
//            )
//        }
    }

    override fun openEmailApp(title: String, body: String, email: String) {
        EmailIntentBuilder.from(this)
            .to(email)
            .subject(title)
            .body(body)
            .start()
    }

    override fun showWork(works: List<Work>) {
//        exercisesAdapter!!.setItems(works)
//        with(exercises_list) {
//            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//            adapter = exercisesAdapter
//        }
    }
}
