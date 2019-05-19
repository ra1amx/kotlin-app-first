package example.com.excerciseproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var exercisesAdapter: ExercisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {
        gender_group.setOnCheckedChangeListener { _, i ->
            val exercises = when (i) {
                R.id.button_male -> Utils.getItemsByGender(Utils.GENDER_MALE)
                R.id.button_female -> Utils.getItemsByGender(Utils.GENDER_FEMALE)

                else -> ArrayList()
            }

            setupExercises(exercises)
        }

        send_button.setOnClickListener {
            if (::exercisesAdapter.isInitialized) {
                val selectedExercises = exercisesAdapter.checkedExercises

                val name = input_name.text.toString()
                val phone = input_phone.text.toString()

                Toast.makeText(it.context,
                        "$name \n $phone \n $selectedExercises", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupExercises(exercises: ArrayList<Exercise>) {
        exercisesAdapter = ExercisesAdapter(exercises)

        with(exercises_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = exercisesAdapter
        }
    }
}
