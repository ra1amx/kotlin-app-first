package example.com.excerciseproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_item_exercise.view.*

/**
 * Created by Mukhamed Issa on 5/19/19.
 */
class ExercisesAdapter(private var exercises: List<Work>)
    : RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    var checkedExercises = mutableListOf<Work>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ExerciseViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_exercise, parent, false))

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bindExercise(exercises[position])
    }

    override fun getItemCount() = exercises.size

    fun setItems(works: List<Work>) {
        this.exercises = works.toMutableList()
        notifyDataSetChanged()
    }

    inner class ExerciseViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bindExercise(exercise: Work) {
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