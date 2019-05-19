package example.com.excerciseproject.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import de.cketti.mailto.EmailIntentBuilder
import example.com.excerciseproject.ExercisesAdapter
import example.com.excerciseproject.Work
import example.com.excerciseproject.model.Coordinate
import example.com.excerciseproject.model.MainInteractor
import example.com.excerciseproject.presenter.MainPresenter
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private var exercisesAdapter: ExercisesAdapter? = null
    private val presenter = MainPresenter(MainInteractor())
    private var simpleLocation: SimpleLocation? = null
    private var location: Coordinate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(example.com.excerciseproject.R.layout.activity_main)
        simpleLocation = SimpleLocation(this)
        simpleLocation!!.setListener {
            simpleLocation?.position?.let { position ->
                location = Coordinate(position.latitude, position.longitude)
            }
        }
        exercisesAdapter = ExercisesAdapter(emptyList())
        presenter.attachView(this)

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        simpleLocation!!.beginUpdates()
    }

    override fun onPause() {
        super.onPause()
        simpleLocation?.endUpdates()
    }

    private fun setupViews() {
        work_group.setOnCheckedChangeListener { _, i ->
            val workType = when (i) {
                example.com.excerciseproject.R.id.button_repair -> WorkType.REPAIR
                example.com.excerciseproject.R.id.button_service -> WorkType.SERVICE
                else -> throw Exception("Not implemented")
            }
            presenter.onCheckedType(workType)
        }

        send_button.setOnClickListener {
            val name = input_name.text.toString()
            val phone = input_phone.text.toString()

            presenter.onClickSend(
                name,
                phone,
                exercisesAdapter?.checkedExercises ?: mutableListOf(),
                if (checkbox_send_coordinates.isChecked) location else null
            )
        }
    }

    override fun openEmailApp(title: String, body: String, email: String) {
        EmailIntentBuilder.from(this)
            .to(email)
            .subject(title)
            .body(body)
            .start()
    }

    override fun showWork(works: List<Work>) {
        exercisesAdapter!!.setItems(works)
        with(exercises_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = exercisesAdapter
        }
    }
}
