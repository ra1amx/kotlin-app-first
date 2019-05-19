package example.com.excerciseproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_item_exercise.view.*

/**
 * Created by Mukhamed Issa on 5/19/19.
 */
class ExercisesAdapter(private val exercises: ArrayList<Exercise>)
    : RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    val checkedExercises = ArrayList<Exercise>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ExerciseViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_exercise, parent, false))

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bindExercise(exercises[position])
    }

    override fun getItemCount() = exercises.size

    inner class ExerciseViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bindExercise(exercise: Exercise) {
            with(view.exercise_checkbox) {
                text = exercise.name
                setOnCheckedChangeListener { _, checked ->
                    if (checked) {
                        checkedExercises.add(exercise)
                    } else {
                        checkedExercises.remove(exercise)
                    }
                }
            }
        }
    }
}